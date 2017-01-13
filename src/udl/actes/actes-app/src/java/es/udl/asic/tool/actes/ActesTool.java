package es.udl.asic.tool.actes;


import org.sakaiproject.tool.api.Session; 
import org.sakaiproject.tool.cover.SessionManager;



//import org.sakaiproject.api.kernel.session.Session;
//import org.sakaiproject.api.kernel.session.cover.SessionManager;

//import org.sakaiproject.service.framework.log.Logger;
//import org.sakaiproject.service.framework.portal.cover.PortalService;
//import org.sakaiproject.service.legacy.id.cover.IdService;
//import org.sakaiproject.service.legacy.realm.Realm;
//import org.sakaiproject.service.legacy.realm.cover.RealmService;
//import org.sakaiproject.service.legacy.user.cover.UserDirectoryService;
import org.sakaiproject.user.cover.UserDirectoryService;
import org.sakaiproject.user.api.User;
//import org.sakaiproject.service.legacy.user.User;
//import org.sakaiproject.api.kernel.tool.Tool;

import org.sakaiproject.tool.api.Placement;
//import org.sakaiproject.api.kernel.tool.Placement;
//import org.sakaiproject.api.kernel.tool.cover.ToolManager;

import org.sakaiproject.tool.cover.ToolManager;
//import org.sakaiproject.service.framework.session.cover.UsageSessionService;
import org.sakaiproject.event.cover.UsageSessionService;
import org.sakaiproject.event.api.UsageSession;


import java.util.prefs.InvalidPreferencesFormatException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import es.udl.asic.api.app.actes.Actes;
import es.udl.asic.api.app.actes.Acta;
import es.udl.asic.api.app.actes.InfoAssig;
import es.udl.asic.api.app.actes.LiniaActaServ;
import es.udl.asic.api.app.actes.Qualificacions;
import es.udl.asic.api.app.actes.Qualificacio;

import java.util.*;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;

import javax.naming.*;
import javax.naming.directory.*;
import javax.sql.DataSource;


import org.w3c.dom.*;
import org.apache.xerces.dom.DocumentImpl;

//CANVIS CODI 17 Novembre
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;	
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;
import javax.xml.xpath.*;
import javax.xml.parsers.*;

import org.apache.fop.apps.Driver;
import org.apache.fop.messaging.MessageHandler;
import javax.xml.xpath.*;
import javax.xml.namespace.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

//FI CANVIS CODI 17 Novembre

/**
 * 
 * @author Alex - ASIC
 *
 * Aquesta classe s'encarrega de capturar els events, i definir el 
 * comportament de la vista. 
 */

public class ActesTool {
	
	private Liniaacta liniaacta=null; 	//Linia d'acta actual
	private List llistalinies=null;		//Llista amb les linies d'acta
	private List llistaactes = null;	//Llista amb les actes
	private Acta acta_actual;			// Acta actual

	private Actes actes;				//Servei d'actes
	
	/* Propietats sobre el professor */
	
	private String dni="";				//dni del profesor
	private String pricog="";			//Primer cognom (no s'utilitza)
	private String segcog="";			//Segon cognom (no s'utilitza)
	private String nom="";				//Nom
	
	/*Propietats de l'acta*/ 
	 
	private String anyaca="";			//Any academic
	private String codiassig="";		//Codi assignatura
	private String nomassig="";			//Nom del grup de l'assignatura
	private String grupassig="";		//Codi del grup de l'assignatura
	private String convo="";			//Convocatoria

	/*Parametres d'ordenacio de la llista*/
	private int ordre = 0;				// Ascendent o descendent
	private int camp = 4;				// Criteri d'ordenacio
	
	private int nombreLinies = 0;		//Numero de linies d'acta
	private String expresio="";			// Expressio
	private boolean errors=false;		// Boolea que diu si hi ha errors al especificar la 
										// qualificacio
	
	private Pattern r;
	 
	private List opcionsQual=null;		//Llista de qualificacions
	public Qualificacions qual= null; 	//Objecte de qualificacions 
	
	
	/* Atributs per poder paginar la llista */
	
	private int numPag = 1; 		// Numero de la pagina acutal 

	private int numPerPag = 30; 	// Numero de registres per pagina

	private int numPagTotal = 0; 	// Numero de pagines total

	private int numReg = 0; 		// Numero de primer registre de la pagina 
									// actual 
	
	/* Cadenes que mostren informacio per pantalla */

	private String selite = "30"; 	// Selecció actual del nombre de usuaris per
									// pagina

	private String anterior = "";

	private String seguent = "";
	
	private boolean withStudents=false;
	private boolean existActes=false;
	
    protected void finalize() throws Throwable{
        //System.out.println("finalize l'acata");
        actes.shutdown();
        super.finalize();
}

	
	/* Getters i setters de les propietats*/
	
	public String getAnyaca() {
		return anyaca;
	}
	public void setAnyaca(String anyaca) {
		this.anyaca = anyaca;
	}
	public String getCodiassig() {
		return codiassig;
	}
	public void setCodiassig(String codiassig) {
		this.codiassig = codiassig;
	}
	public String getConvo() {
		return convo;
	}
	public void setConvo(String convo) {
		this.convo = convo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getGrupassig() {
		return grupassig;
	}
	public void setGrupassig(String grupassig) {
		this.grupassig = grupassig;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomassig() {
		return nomassig;
	}
	public void setNomassig(String nomassig) {
		this.nomassig = nomassig;
	}
	public String getPricog() {
		return pricog;
	}
	public void setPricog(String pricog) {
		this.pricog = pricog;
	}
	public String getSegcog() {
		return segcog;
	}
	public void setSegcog(String segcog) {
		this.segcog = segcog;
	}
	public List getOpcionsQual() {
		return opcionsQual;
	}
	
	public void setLlistalinies(List llis){
		llistalinies = llis;
	}
	
	public String getSelite() {
		return selite;
	}

	public void setSelite(String si) {
		selite = si;
	}
	public void setNombreLinies (int i){
		nombreLinies = i;
	}
	
	public int getNombreLinies (){
		
		List llista = getLlistaactes();
		nombreLinies = llista.size();
		
		return nombreLinies;
	}
	
	public void setExistActes(){		
		existActes = (nombreLinies > 0);		
		
	}
	
	public boolean getExistActes(){		
		existActes = (nombreLinies > 0);
		return existActes;
		
	}
	
	public void setWithStudents(boolean b){
		withStudents = b;
	}
	
	public boolean getWithStudents(){
		return withStudents;
	}
	
	
	
	public void setLiniaacta(Liniaacta act){
		liniaacta = act;
	}
	public Liniaacta getLiniaacta(){
		return liniaacta;
	}
	
	public void setLlistaactes(List llistaactesaux){
		llistaactes= llistaactesaux;
	}
	
	public String getMissatge(){
		if (errors)
			return "Has de solucionar els possibles errors de les línies d'acta per poder desar els canvis";
		else 
			return "";
	}
	
	public void setActes(Actes act){
		actes = act;
	}
	
	public Actes getActes(){
		return actes;
	}
	public void setActa_actual(Acta aux){
		acta_actual = aux;
	}
	
	public Acta getActa_actual(){
		return acta_actual;
	}
	
	
	/* Getters de anterior (->) 
	 * Calcula si ha de mostrar-se o no
	 * */

	public String getAnterior() {
		if (numPag == 1) {
			anterior = "nogif.gif";
		} else {
			anterior = "anterior.gif";
		}

		if (numPag == numPagTotal) {
			seguent = "nogif.gif";
		} else {
			seguent = "seguent.gif";
		}

		return anterior;
	}

	/* Getters de seguent (->) 
	 * No calcula res ja que ja es calcula en anterior 
	 * */

	public String getSeguent() {
		return seguent;
	}

	/* Getter i Seter de numPerPag */ 
	
	public void setNumPerPag(int numperpag) {
		numPerPag = numperpag;
	}

	public int getNumPerPag() {
		return numPerPag;
	}

	/* Getter i Setter de numPag (numero de pagina)*/
	
	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numpag) {
		numPag = numpag;
	}
	
	
	/* Getter de numReg Obtenir el numero del primer registre de la pagina */
	
	public int getNumReg() {
		return (numPerPag * numPag) - numPerPag;
	}
	
	/* Getter de numPagTotal - numero total de pagines */
	
	public int getNumPagTotal() {

		numPagTotal = llistalinies.size() / numPerPag;

		//Afegim una ultima pagina si conve
		if ((llistalinies.size() % numPerPag) > 0) {
			numPagTotal++; // Li afegim la pagina de restos
		}
		return numPagTotal;
	}
	
	//Ens porta a la pagina principal
	
	public String principal(){
		llistalinies=null;
		errors=false;
		return "main";
	}
	
	//Retorna la llista d'actes del profesor
	
	public List getLlistaactes(){
		
		
		if (llistaactes == null){ // No tenim dades
			
			//Obtenim les dades de configuaració
			Placement pla = ToolManager.getCurrentPlacement();
			
			actes.setDriver(pla.getConfig().getProperty("driver"));
			actes.setDb(pla.getConfig().getProperty("db"));
		
			//Nou recollim l'any acadèmic
			String anyaca = pla.getConfig().getProperty("anyaca");
			
			
			//actes.setupDataSource();
			
			//Obtenim l'id del curs
			
//			String idcurs = PortalService.getCurrentSiteId(); Per versio 2.0

//			Versio 2.2
			String idcurs=pla.getContext();

			InfoAssig ia= new InfoAssig(idcurs);
			
			//Obtenim el dni de l'usuari i el nom
			
			if (dni.equals("")){ // Si encara no hem buscat la persona
				UsageSession uses = UsageSessionService.getSession();
				String ident = uses.getUserEid();
				String ident2 = uses.getUserId();
				
				dni = actes.getDni(ident);
				try {
					User usr = UserDirectoryService.getUser(ident2);
					nom = usr.getDisplayName();
				}catch (Exception ex){
					System.out.println("No s'ha pogut obtenir l'usuari");
				}
			}
			
			// Obtenim la llista d'actes
		
			List llista =null;
			
			
			if (!anyaca.equals("")){
				llista = actes.getLlistaActes("toteslesactes",dni,anyaca);
			}else{
				// CANVIS CODI 18 Gener
				//llista = actes.getLlistaActes(ia.getCodi(),dni,ia.getAnyaca());
				// conservem la funció però li estem passant anyaca="" 
				// tot queda preparat si es vol recuperar el filtre per any acadèmic
				llista = actes.getLlistaActes("toteslesactes",dni,anyaca);
				// FI CANVIS CODI 18 Gener
			}
			
			llistaactes = new ArrayList();
			
			
			Iterator it = llista.iterator();
			
			while (it.hasNext()){

				//Transformem la llista obtinguda a una de l'objecte vista
				Acta act = (Acta) it.next();
				ActaVista av = new ActaVista (act);
				llistaactes.add(av);
			}
			

			//Emplenem la llista que aniran al combo de qualificacions			 
			 
			opcionsQual = new ArrayList();
			
			qual = actes.getQualificacions();
			Iterator it2 = qual.getIterator();
			
			while (it2.hasNext()){
				Qualificacio aux = (Qualificacio) it2.next();
				opcionsQual.add(new SelectItem(aux.getId(), aux.getNom()));
			}
		}
		
		return llistaactes;
	}
	


	public ActesTool(){
		System.out.println("Creadora");
		expresio = "([0-9])|(10)|([0-9][\\.,][0-9])";
		r= Pattern.compile(expresio); // Patró (Expresió regular)
	}
	
	
	private void mouOrdre(int cmp) {
		if (camp != cmp) {
			ordre = 0;
			camp = cmp;
		} else {
			if (ordre == 0) {
				ordre = 1;
			} else {
				ordre = 0;
			}
		}
	}

	/*
	 *  Retorna el nom del gif a pintar per senyalar l'ordre 
	 */
	
	private String getStrOrd(int num) {
		if (camp == num) {
			if (ordre == 0) {
				return "amunt.gif";
			} else {
				return "avall.gif";
			}
		} else {
			return "nogif.gif";
		}
	}

	/* Metodes per a realitzar l'ordenacio de la columna*/
	public String getStrOrd1() {
		return getStrOrd(1);
	}

	public String getStrOrd2() {
		return getStrOrd(2);
	}

	public String getStrOrd3() {
		return getStrOrd(3);
	}
	public String getStrOrd4() {
		return getStrOrd(4);
	}
	public String getStrOrd5() {
		return getStrOrd(5);
	}
	public String getStrOrd6() {
		return getStrOrd(6);
	}
	public String getStrOrd7() {
		return getStrOrd(7);
	}
	public String getStrOrd8() {
		return getStrOrd(8);
	}
	public String getStrOrd9() {
		return getStrOrd(9);
	}
	public String ordenaCol1() {
		mouOrdre(1);

		return "mostraActa";
	}

	public String ordenaCol2() {
		mouOrdre(2);

		return "mostraActa";
	}

	public String ordenaCol3() {
		mouOrdre(3);
		return "mostraActa";
	}

	public String ordenaCol4() {
		mouOrdre(4);

		return "mostraActa";
	}

	public String ordenaCol5() {
		mouOrdre(5);

		return "mostraActa";
	}
	public String ordenaCol6() {
		mouOrdre(6);

		return "mostraActa";
	}
	
	public String ordenaCol7() {
		mouOrdre(7);

		return "mostraActa";
	}
	public String ordenaCol8() {
		mouOrdre(8);

		return "mostraActa";
	}
	
	public String ordenaCol9() {
		mouOrdre(9);

		return "mostraActa";
	}
	
	
	/* Obte les linies d'acta*/
	public List getLlistalinies(){
		
		//Carreguem la llista de qualificacions temporalment
		 
		if (llistalinies == null){
			numPag = 1;
			
			List llista = actes.getLlistaLinies(acta_actual);
			
			llistalinies = new ArrayList();
			
			Iterator it = llista.iterator();
			
			while (it.hasNext()){
				LiniaActaServ las = (LiniaActaServ) it.next();
				Liniaacta lac = new Liniaacta(las);
				llistalinies.add(lac);	
			}
		}

		/* Creem un objecte comparador per ordenar les linies d'acta*/
		ComparaNom cp = new ComparaNom();

		//Passem els parametres d'ordenacio al comparador
		cp.setOrdre(ordre);
		cp.setCamp(camp);
		
		Collections.sort(llistalinies, cp);
		
		
		//calculem el nombre de pagines 
		numPagTotal = llistalinies.size() / numPerPag;

		//Afegin una ultima pagina si conve
		if ((llistalinies.size() % numPerPag) > 0) {
			numPagTotal++; // Li afegim la pagina de restos
		}
		
		
		return llistalinies;
	}
	
	
	
	
	/*-------------------------------------------------------------------------
	 * Conjunt de metodes que intervenen en la navegacio per pagines de la 
	 * llista
	 * 
	 * ------------------------------------------------------------------------
	 */
	
	
	/*
	 * Metode per recalcular el nombre de numero de linies per pagina lligat 
	 * al combo box dels main.jsp
	 */
	
	public void canvia(ValueChangeEvent event) {
		try {
			numPerPag = Integer.parseInt((String) event.getNewValue());
			numPag = 1;
		} catch (Exception ex) {
			numPerPag = 100;
		}
	}
	
	/*Anem a la seguent pagina*/
	
	public String procSeguent() {
		numPag++;

		return "mostraActa";
	}
	
	/* Anem a la pagina anterior*/
	
	public String procAnterior() {
		numPag--;

		return "mostraActa";
	}
	
	
	public void generaLlista(){
		
	}
	
	public String desaCanvis(){
		filtra();
		if(!errors){
			boolean desat = desaDades();
			
			if (!desat){
				System.out.println("Hi ha un error a l'acta " + acta_actual.getAss_codnum());
				errors = true;
			}
			else {
				llistalinies = null; //Farem que les carregui de la Bd per veure realment els canvis
				return "mostraActaDesada";
			}
		}
		return "mostraActa";
	}
	

	public String actaCompleta(){
			llistalinies =null;
		return "mostraActa";
	}
	
	
	/* Ordena desar les linies modificades */
	
	public boolean desaDades(){
		//modifiquem la variable de presentació del l'informe
		withStudents = false;
		
		Iterator it = llistalinies.iterator();
		
		List amodificar = new ArrayList();
		
		while (it.hasNext()){
			Liniaacta aux = (Liniaacta) it.next();
			if (aux.isModificat()){
				//Si per un casual la bd no pogues inserir el registre ¿?Coses de la vida¿?
				aux.modificaLinia();
				amodificar.add(aux.getLiniaActa());
			}
		}
		return actes.desaDades(amodificar); 
	}
	
	//accio per imprimir informes
	
	public String printVersion(){
		// CANVIS CODI 17 Novembre
		// Session session = 	SessionManager.getCurrentSession();
		// FI CANVIS CODI 17 Novembre
		
		Document doc = new DocumentImpl();
		Element acta = doc.createElement("acta");
		
		
		//Fiquem informacio de la capçalera de l'acta
		
		Element elanyaca = doc.createElement("anyaca");
		Element elcodiass = doc.createElement("codiass");
		Element	elnomass =  doc.createElement("nomass");
		Element elnomgrup = doc.createElement("nomgrup");
		Element elconvo = doc.createElement("convo");
		//Element ellogo = doc.createElement("logo");
		
		
		elanyaca.appendChild(doc.createTextNode(acta_actual.getAnyaca()));
		elcodiass.appendChild( doc.createTextNode("" + acta_actual.getAss_codnum()));
		elnomass.appendChild(doc.createTextNode(acta_actual.getNom_ass()));
		elnomgrup.appendChild(doc.createTextNode(acta_actual.getNom_gas()));
		elconvo.appendChild(doc.createTextNode(acta_actual.getNomtco()));
		//ellogo.appendChild(doc.createTextNode(getFileName("udl.gif")));
		
		
		acta.appendChild(elanyaca);
		acta.appendChild(elcodiass);
		acta.appendChild(elnomass);
		acta.appendChild(elnomgrup);
		acta.appendChild(elconvo);
		//acta.appendChild(ellogo);		
		
		//Dades del professor
		Element elemnom = doc.createElement("nom");
		Element elemdni = doc.createElement("dni");
		
		elemnom.appendChild(doc.createTextNode(nom));
		elemdni.appendChild(doc.createTextNode(dni));
	
		
		
		acta.appendChild(elemnom);
		acta.appendChild(elemdni);
		
		
		//Afegin les línies d'acta
		
		Element liniesacta = doc.createElement("linies");
		
		Iterator it = llistalinies.iterator();
		
		while (it.hasNext()){
			Liniaacta linacta = (Liniaacta) it.next();
			
			Element linia = doc.createElement("linia");
			Element elnumexp = doc.createElement ("numexp");
			Element elcodpla = doc.createElement ("codpla");
			Element eldnialu = doc.createElement ("dnialu");
			Element elnomalu = doc.createElement ("nomalu");
			Element eltipusass = doc.createElement ("tipusass");
			Element elconv = doc.createElement ("conv");
			Element elquali = doc.createElement ("quali");
			Element elnota = doc.createElement ("nota");
			Element dibuix = doc.createElement ("dibuix");
			
			
			elnumexp.appendChild(doc.createTextNode(linacta.getExpedient()));
			elcodpla.appendChild(doc.createTextNode(linacta.getCodipla()));
			eldnialu.appendChild(doc.createTextNode(linacta.getDni()));
			elnomalu.appendChild(doc.createTextNode(linacta.getAlumne()));
			eltipusass.appendChild(doc.createTextNode(linacta.getTipus()));
			elconv.appendChild(doc.createTextNode(linacta.getConvocatoria()));
			elquali.appendChild(doc.createTextNode("" + linacta.getNomqualificacio()));
			elnota.appendChild(doc.createTextNode("" + linacta.getNota()));

			
			
			linia.appendChild(elnumexp);
			linia.appendChild(elcodpla);
			linia.appendChild(eldnialu);
			linia.appendChild(elnomalu);
			linia.appendChild(eltipusass);
			linia.appendChild(elconv);
			linia.appendChild(elquali);
			linia.appendChild(elnota);
			
			liniesacta.appendChild(linia);
		
		}
		acta.appendChild(liniesacta);

		//Afegin l'acta al document
		doc.appendChild(acta);
		
		
		
		
		//doc.setRootElement(elem);
		// CANVIS CODI 17 Novembre
		//session.setAttribute("document", doc);
		//if (withStudents)
		//	session.setAttribute("tipus","pdfstudents");
		//else
		//	session.setAttribute("tipus","pdf");
		// FI CANVIS CODI 17 Novembre
		
		
		
		try {
		FacesContext fc =FacesContext.getCurrentInstance();
		// CANVIS CODI 17 Novembre 
		// GENEREM PDF DES DEL BEAN
		// fc.getExternalContext().redirect( "actes/" + acta_actual.getAss_codnum() + "_" + acta_actual.getGas_codnum() + "_" + acta_actual.getAnyaca() + ".pdf");
		ServletContext context = (ServletContext)fc.getExternalContext().getContext();
		String rootpath = context.getRealPath("/");
		String filename = null;
		if (withStudents)
			filename = rootpath+"actes/pdfwithstudents.xsl";
		else
			filename = rootpath+"actes/pdfwithoutstudents.xsl";
		Element ellogo = doc.createElement("logo");
		ellogo.appendChild(doc.createTextNode(rootpath+"actes/udl.gif"));
		acta.appendChild(ellogo);
		HttpServletResponse response =
			(HttpServletResponse) fc.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		String nomFitxer = acta_actual.getAss_codnum() + "_" + acta_actual.getGas_codnum() + "_" + acta_actual.getAnyaca() + ".pdf";
		// canvis problema Nov09 IE8
		response.setHeader("Pragma","");
		response.setHeader("Content-Disposition","attachment; filename=\""+nomFitxer+"\"");
		// el posem com a attachment per a que s'obri una finestra amb opció Obrir/Guardar
		generatePDF(doc,filename,response.getOutputStream());
		fc.responseComplete();
		}//catch (Exception ex){System.out.println("Error a la redireccio");}
		catch (Exception ex){
			System.out.println("Eina Actes: Error al generar el fitxer pdf "+ acta_actual.getAss_codnum() + "_" + acta_actual.getGas_codnum() + "_" + acta_actual.getAnyaca() + ".pdf");
		}
		// FI CANVIS CODI 17 Novembre 
		return "mostraActaDesada";
	}

	// CANVIS CODI 17 Novembre
	private void generatePDF(Document doc, String xslFileName , OutputStream streamOut)
	{
		Driver driver = new Driver();

		//Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_ERROR);
		//MessageHandler.setScreenLogger(logger);
		//driver.setLogger(logger);

		driver.setOutputStream(streamOut);
		driver.setRenderer(Driver.RENDER_PDF);

		Transformer transformer = null;
		try
		{
			transformer =
				TransformerFactory.newInstance().newTransformer(
					new StreamSource(new File(xslFileName)));
		}

		catch (TransformerException e)
		{
			//Log.debug("sakai", "Actes.generatePDF(): " + e);
			e.printStackTrace();
			System.out.println("Eina Actes(generatePDF): Error al tranformar ");
			return;
		}

		Source x = new DOMSource(doc);

		try
		{
			transformer.transform(x, new SAXResult(driver.getContentHandler()));
		}

		catch (TransformerException e1)
		{
			System.out.println("Eina Actes(generatePDF): Error al tranformar2");
			return;
		}
	}
	// FI CANVIS CODI 17 Novembre
	
	//Filtra les posibles coses que ens fiquen com a qualificacio*/
	
	public void filtra(){
		Iterator it = llistalinies.iterator();
		errors = false;
		
		int i=1;
		
		while (it.hasNext()){//Per totes le linies
			Liniaacta aux = (Liniaacta) it.next();

			if (!comparaNota(aux.getNota())){//La nota ficada no es algo sense sentit
				aux.setInc(true);
				aux.setMsgerror(i +") El format de la nota no té sentit");
				errors=true;
				i++;
			}
			else{//Si la nota que hi ha no es cap bestiesa
				String nota = aux.getNota(); 	//Obtenim la nota
				nota = nota.replace(',','.'); 	//Modifiquem les posibles comes per punts  
				
				aux.setNota(nota);	//Tornem a ficar la nota
				
				if (!aux.getQualificacio().equals("")){ //Si tenim qualificacio
					if (!aux.getNota().equals("")){ //Si tenim nota

						//Obtenim la qualificacio que hem ficat
						
						Qualificacio qualificacio = qual.getQualificacioPerId(aux.getQualificacio());
						if (qualificacio.requereixNota()){// Mirem si requereix nota
							if (qualificacio.dinsRang(nota)){ // si requereix nota i es troba dins de rang 
								aux.setInc(false);			  // es una linia valida
							}
							else {							 //Si no esta dins del rang la marquem  
								aux.setInc(true);			 //i especifiquem el problema
								aux.setMsgerror(i +") La nota no es troba dins del rang");
								errors=true;				 //Afirmem que hem trobat errors
								i++;
							}
							
						}
						else { //La qualificacio no requereix nota i li hem ficat 
							aux.setInc(true); // Marquem l'error
							aux.setMsgerror(i +") Aquesta qualificació ha d'anar sense nota numèrica");
							errors=true;
							i++;
						}

					}
					else {//No te nota i si qualificacio
						
						//Obtenim la qualificacio
						Qualificacio qualificacio = qual.getQualificacioPerId(aux.getQualificacio());
						if (qualificacio.requereixNota()){//La qualificacio requereix nota
							aux.setInc(true); // Marquem l'error
							aux.setMsgerror(i +") Aquesta qualificació requereix nota numèrica");
							errors=true;
							i++;
						}
						else //La qualificacio no requereix nota
							aux.setInc(false); // Tot be
					}
				}
				else{//No tenim qualificacio
					//System.out.println("No tinc qualificacio");
					
					if (!aux.getNota().equals("")){//Tenim nota
						
						//Calculem la qualificacio per aquella nota
						Qualificacio qualificacio = qual.getQualificacio(aux.getNota());
						try{
							String novaQuali = qual.getQualificacio(aux.getNota()).getId();
							aux.setQualificacio(novaQuali);
							aux.setInc(false);
						}
						catch (Exception ex){
							aux.setInc(true);
							aux.setMsgerror(i +") Aquesta nota no s'adapta a cap de les qualificacions "); //No hauria de sortir mai però per si a cas
							errors=true;
							i++;
							//aux.setQualificacio("");
						}
					}
					else //Ni qualificacio ni nota
						aux.setInc(false);	 // No passa res
				}
			}
		}
	}
	
	/*Mira si el string amb la nota correspon al patro*/
	
	private boolean comparaNota(String camp){
		boolean cmp=false;
		
		if (r.matcher(camp).matches() || camp.equals("")){ //Analitza el contingut del camp
			cmp = true;
			//System.out.println("Coincideix el camp");
		}
			
		return cmp;
	}
	
	/*Classe per dur a terme la ordenacio de les columnes*/ 

	public class ComparaNom implements Comparator {
		private int ordre;

		private int camp;

		public void setOrdre(int ord) {
			ordre = ord;
		}

		public void setCamp(int cmp) {
			camp = cmp;
		}

		public int compare(Object obj1, Object obj2) {
			String str1 = "";
			String str2 = "";

			switch (camp) {
			case 1:
				//Per tal de no convertir els numeros d'expedient a numeros
				//empleno de 0 davant per fer-los de igual longitud
				
				
				str1 = ((Liniaacta) obj1).getExpedient();
				str2 = ((Liniaacta) obj2).getExpedient();
				
				str1  = "0000000".substring(0,7-str1.length()) + str1;
				str2  = "0000000".substring(0,7-str2.length()) + str2;
				
				break;

			case 2:
				str1 = ((Liniaacta) obj1).getCodipla();
				str2 = ((Liniaacta) obj2).getCodipla();

				break;

			case 3:
				str1 = ((Liniaacta) obj1).getDni();
				str2 = ((Liniaacta) obj2).getDni();

				break;

			case 4:
				str1 =  netejaString(((Liniaacta) obj1).getAlumne().toUpperCase());
				str2 =  netejaString(((Liniaacta) obj2).getAlumne().toUpperCase());

				break;
			
			case 5:
				str1 = ((Liniaacta) obj1).getTipus();
				str2 = ((Liniaacta) obj2).getTipus();

				break;
			
			case 6:
				str1 = ((Liniaacta) obj1).getConvocatoria();
				str2 = ((Liniaacta) obj2).getConvocatoria();

				break;

			case 7:
				str1 = ((Liniaacta) obj1).getQualificacio();
				str2 = ((Liniaacta) obj2).getQualificacio();

				break;

			case 8:
				str1 = ((Liniaacta) obj1).getNota();
				str2 = ((Liniaacta) obj2).getNota();

				break;
			
			}

			if (ordre == 0) {
				return str1.compareTo(str2);
			} else {
				return str2.compareTo(str1);
			}
		}
		
		public String netejaString(String str)
    	{
			String ret = new String (str);

    		//ret = killSpaces(str);
    		ret = ret.replaceAll("'",       "");
    		ret = ret.replaceAll("[ñ]",     "n");
    		ret = ret.replaceAll("[Ñ]",     "N");
    		ret = ret.replaceAll("[¥]",     "n");
    		ret = ret.replaceAll("[áàäâã]", "a");
    		ret = ret.replaceAll("[ÁÀÄÂÃ]", "A");
    		ret = ret.replaceAll("[éèëê]",  "e");
    		ret = ret.replaceAll("[ÉÈËÊ]",  "E");
    		ret = ret.replaceAll("[íìïî]",  "i");
    		ret = ret.replaceAll("[ÍÌÏÎ]",  "I");
    		ret = ret.replaceAll("[óòöôõ]", "o");
    		ret = ret.replaceAll("[ÓÒÖÔÕ]", "O");
    		ret = ret.replaceAll("[úùüû]",  "u");
    		ret = ret.replaceAll("[ÚÙÜÛ]",  "U");
    		ret = ret.replaceAll("[ç]",     "c");
    		ret = ret.replaceAll("[Ç]",     "C");
    		ret = ret.replaceAll("[ýÿ]",    "y");
    		ret = ret.replaceAll("[Ý]",     "Y");
    		ret = ret.replaceAll("[ºª]",    ".");
    		ret = ret.replaceAll("·",       "");

    		return ret;
    	}
		
	}

	
	/* Classe que conte les dades que pinta com a linies d'acta*/
	
	
	public class Liniaacta{
		
		String id;			//Id
		String expedient;	//Num expedient
		String codipla;		//Codi pla	
		String dni;			// Dni alumne
		String alumne;		// Nom alumne
		String tipus;		// Tipus d'assignatura 
		String convocatoria;// Convocatoria
		String qualificacio;// Qualificacio
		String nomqualificiacio=""; //Nom de la qualificacio
		String nota;		//Nota
		String inc;			// Incorrecte
		LiniaActaServ liniaActa;// Objecte amb les dades

		/*Constructor amb parametres */
		
		public Liniaacta(String expedient, String codipla, String dni, String alumne, String tipus, String convocatoria, String qualificacio, String nota, boolean inc) {
			super();
			// TODO Auto-generated constructor stub
			
			this.expedient = expedient;
			this.codipla = codipla;
			this.dni = dni;
			this.alumne = alumne;
			this.tipus = tipus;
			this.convocatoria = convocatoria;
			
			if (qualificacio != null){
				Qualificacio quali = qual.getQualificacioPerId(qualificacio);
				//this.qualificacio= quali.getNom();
				this.qualificacio = qualificacio;
				setNomqualificacio(quali.getNom());
			}
			else 
				this.qualificacio = "";
			if (nota != null)
				this.nota = nota;
			else 
				this.nota = "";
			setInc(inc);
		}
		
		/*Constructor que te per parametre una liniadactaserv*/
		
		public Liniaacta (LiniaActaServ las){
			this(las.getExp_numord(),las.getPla_codalf(),las.getDnialu(),las.getNomalu(),las.getNomtas(),"" + las.getNumcon(),las.getQua_codalf(), las.getQuanum(),false);
			liniaActa = las;
			
		}
		
		/* Getters i setters*/
		
		public LiniaActaServ getLiniaActa(){
			return liniaActa;
		}
		public String getAlumne() {
			return alumne;
		}
		public void setAlumne(String alumne) {
			this.alumne = alumne;
		}
		public String getCodipla() {
			return codipla;
		}
		public void setCodipla(String codipla) {
			this.codipla = codipla;
		}
		public String getConvocatoria() {
			return convocatoria;
		}
		public void setConvocatoria(String convocatoria) {
			this.convocatoria = convocatoria;
		}
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getExpedient() {
			return expedient;
		}
		public void setExpedient(String expedient) {
			this.expedient = expedient;
		}
		public String getInc() {
			if (liniaActa.getInc()){
				return "warn.gif";
			}
			else{
				return "no.gif";
			}
		}
		public void setInc(boolean inc) {
			if (liniaActa!=null){
				liniaActa.setInc(inc);
				if (!inc)
					liniaActa.setMsgerror("");//si no hi ha error no hi ha d'haver msg
			}
		}
		public String getNota() {
			return nota;
		}
		public void setNota(String nota) {
			this.nota = nota;
		}
		
		
		public String getQualificacio() {
			return qualificacio;
		}
		public void setQualificacio(String qualificacio) {
			this.qualificacio = qualificacio;
		}
		public String getTipus() {
			return tipus;
		}
		public void setTipus(String tipus) {
			this.tipus = tipus;
		}
		
		public String getNomqualificacio(){
			return nomqualificiacio;
		}
		
		public void setNomqualificacio(String nomq){
			nomqualificiacio = nomq;
			
		}
		
		public String getNumError(){
			String numero = liniaActa.getMsgerror();
			int index = numero.indexOf(")");
			if (index>0){
				numero = numero.substring (0,index);
				numero = " " + numero;
			}
			else 
				numero = "";
			return numero;
		}
		
		public String getMsgerror (){
			return liniaActa.getMsgerror();
		}
		
		public void setMsgerror(String msg){
			if (liniaActa!=null){
				liniaActa.setMsgerror(msg);
			}
		}
		
		// Neteja el contingut de les qualificacions
		public void neteja(){
			qualificacio = "";
			nota= "";
			filtra();
		}
		
		
		/* Modifica les dades de l'objecte linia d'acta per confirmar que ho hem modificat*/
		public void modificaLinia(){
			String quali =qualificacio;
			String not = nota;
			if (qualificacio.equals("")){
				quali = null;
			}
			if (nota.equals("")){
				not = null;
			}
			
			liniaActa.setQua_codalf(quali);
			liniaActa.setQuanum(not);
		}
		
		/* Retorna cert si hem realitzat canvis a la linia d'acta*/
		public boolean isModificat(){
			
			if (qualificacio.equals("") && liniaActa.getQua_codalf()==null){
				if (nota.equals("") && liniaActa.getQuanum() == null)
					return false; // Els dos son iguals
				else{
					if (nota.equals(liniaActa.getQuanum()))
						return false;
					else
						return true;
				}
			}
			else{
				if (qualificacio.equals(liniaActa.getQua_codalf())){

					if (nota.equals("") && liniaActa.getQuanum() == null)
						return false; // Els dos son iguals
					else{
						if (nota.equals(liniaActa.getQuanum()))
							return false;
						else
							return true;
					}
				}
				else
					return true;
			}
		}
	}
	
	
	/*Classe que representa l'acta en la vista*/
	
	
	public class ActaVista{	
		String nom;		//Nom de l'acta
		// CANVIS CODI 18 Gener
		String any;		// Any acadèmic
		// FI CANVIS CODI 18 Gener
		String numord;	//Numero d'ordre
		String convo;	//Convocatoria
		String grup;	//Numero de grup
		
		Acta acta;
		
		/*Constructor que te per paramtre un objecte acta*/
		
		public ActaVista (Acta acta){
			//New: Ara s'ha de veure el nom de l'assignatura tb
			this.nom = acta.getNom_ass() +"-" +acta.getNom_gas();
			// CANVIS CODI 18 Gener
			// s'ha de veure l'any acadèmic
			this.any = "" + acta.getAnyaca();
			// FI CANVIS CODI 18 Gener
			this.numord = "" + acta.getNumord();
			this.convo = "" + acta.getNomtco();
			this.grup = "" + acta.getGas_codnum();
			this.acta = acta;
		}
		
		/*Getter de les propietats*/
		
		public String getNom(){
			return nom;
		}
		public String getNumord(){
			return numord;
		}
		public String getGrup(){
			return grup;
		}
		
		public String getConvo(){
			return convo;
		}
		
		// CANVIS CODI 18 Gener
		public String getAny(){
			return any;
		}
		// FI CANVIS CODI 18 Gener
		
		public Acta getActa(){
			return acta;
		}
		
		
		/* Especifica que l'acta acutal es la cliacada
		 * i envia a la vista de l'acta 
		 */
		
		public String actaCompleta(){
			acta_actual=this.acta;
			llistalinies=null;
			return "mostraActa";
		}
		
		
	}
}

