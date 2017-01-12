package cat.udl.asic.app.einaenqvirtual;


import cat.udl.asic.einaenqvirtual.api.*;


import javax.faces.event.ValueChangeEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIPanel;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.model.SelectItem;

import java.util.*;
import java.util.Locale;
import java.util.HashMap;
import org.sakaiproject.tool.cover.ToolManager;
import org.sakaiproject.tool.api.Placement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Set;
import java.util.Iterator;
//import org.sakaiproject.util.Tool; 
import org.sakaiproject.tool.api.Placement;
import org.sakaiproject.site.api.ToolConfiguration;
import org.sakaiproject.site.cover.SiteService;
import org.sakaiproject.event.cover.UsageSessionService;
import org.sakaiproject.event.api.UsageSession;
import org.sakaiproject.authz.api.GroupNotDefinedException;
import org.sakaiproject.authz.api.Role;
import org.sakaiproject.entity.api.ResourceProperties;

import org.sakaiproject.user.cover.UserDirectoryService;
import org.sakaiproject.user.api.User;
import org.sakaiproject.component.cover.ComponentManager;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;

public class GetModelCtl {

	public Model m;
	public  EinaEnqVirtualService s;
	public String dniP;
	public MFactory mfactory;
	public VFactory vfactory;
	FacesContext facesContext;
	Application app;
	public String contacta = "N";
	public String email = "";
	public String comentaris = "";
		
	
	
	public String rol;
	public String emailRemitent = "";
	String any, dni;
	public List grups;
	UIComponent panel=null;
	
	/**************************************************
	 * Preparem el Bean on van les respostes
	 */
	public GuardarBean respostesBean = new GuardarBean();
	
	
  	public void setRespostesBean(GuardarBean p)
	{
  		respostesBean = p;
  	}
  	
  	public GuardarBean getRespostesBean()
  	{
  		return respostesBean;
  	}
	
	
	public UIComponent getPanel() {

		if (panel == null) panel = new UIPanel();
		panel = pintaGrups(m.getCodNum());
		return panel;
	}

   public void setPanel(UIComponent component)
   {
        this.panel = component;
    } 	

	
    public ArrayList<SelectItem> alumnes(){

/******** Accedim a les propietats de configuració de l'eina ***/
		
		Placement p = ToolManager.getCurrentPlacement();
		ToolConfiguration toolConfig = SiteService.findTool(p.getId());
		
		/******** Recuperem el curs acadèmic de les propietats **********/
		
		any = toolConfig.getPlacementConfig().getProperty("any");
		
		
		List<Alumne> a = s.getAlumnes(any, s);

		ArrayList<SelectItem> list = new ArrayList();
    	if (a != null)
		{			
			Iterator iter = a.iterator();
			while (iter.hasNext())
			{
				try
				{					
					Alumne val =  (Alumne) iter.next();
									
					list.add( new SelectItem (val.getDni(), val.getCadena()));					
				}
				catch (Exception e)			
				{
					e.printStackTrace();
				}
			}
		}	
    	
    	return list;
    }
   
	public String inici (String dniP){
		
		setDniP(dniP);
		
		/******** Accedim a les propietats de configuració de l'eina ***/
		
		Placement p = ToolManager.getCurrentPlacement();
		ToolConfiguration toolConfig = SiteService.findTool(p.getId());
		
		/******** Recuperem el codi del model d'enquesta ****************/

		String model = toolConfig.getPlacementConfig().getProperty("codimodel");
		int codiMod = Integer.parseInt(model);
		

		/******** Recuperem el curs acadèmic de les propietats **********/
		
		any = toolConfig.getPlacementConfig().getProperty("any");
		
		UsageSession uses = UsageSessionService.getSession();
	  	String id_login= uses.getUserEid();
		String id_rol= uses.getUserId();		

		/******** Recuperem el DNI de l'usuari que ha entrat ************/
		if (this.dniP!=null && !this.dniP.equals(""))	// dni possat per l'administrador
			dni=this.dniP;
		else	// agafariem el dni de la sessió
			
		{
			  try{
	              User sakuserr =    UserDirectoryService.getUser(uses.getUserId());
	              ResourceProperties props = sakuserr.getProperties();
	              String prop = props.getProperty ("dni");            
	              if (prop==null || "".equals(prop)){
	            	  dni = id_login;
	              }
	              else{
	            	  dni = prop;
	              }
	          }catch (Exception ex){
	              ex.printStackTrace();
	          } 
			
		}	
			
			
		// Busquem el correu del remitent per si s'ha d'enviar un formulari de contacte			
		try {
			User usr = UserDirectoryService.getUser(id_rol);
			emailRemitent = usr.getEmail();			
		}catch (Exception ex){
			System.out.println("No s'ha pogut obtenir l'email de l'usuari");
		}
		
	

		/******** A partir del codi del model recuperem el objecte model de la bbdd *******/
		
//		m = s.getModel(codiMod);	
	/*	int codi = m.getCodNum();
		String desid1 = m.getDesid1();
		String tparam = m.getTParam();				
*/		

		/*** Construir els grups a enquestar ***/
		
		return construirGrups(codiMod);
	}
	

	public void setM(Model mo)
	{
		this.m = mo;
	}
	
	public Model getM()
	{
		return this.m;
	}
	
	public void setS(EinaEnqVirtualService ser)
	{
		this.s = ser;
	}
	
	public EinaEnqVirtualService getS()
	{
		return this.s;
	}	
	public void getUserCtl()
	{
		
	}
	
	public void setDniP(String dni)
	{
		this.dniP = dni;
	}
	
	public String getDniP()
	{
		return this.dniP;
	}
	
	public void setContacta(String contacta)
	{
		this.contacta = contacta;
	}
	
	public String getContacta()
	{
		return this.contacta;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setComentaris(String comentaris)
	{
		this.comentaris = comentaris;
	}
	
	public String getComentaris()
	{
		return this.comentaris;
	}
	
	
	
	
	public String gestioContacta()
	{
		setContacta("S");	
		
		return "inici";
	}
	
	
	public String enviarIncidencies()
	{

		setContacta("N");
		
		s.EnviarMail(this.email,this.comentaris,emailRemitent);
		this.email="";
		this.comentaris="";		
		return "inici";
	}
	
	
	
	/**************************************************************************************
	 * 
	 * construirGrups : recupera tots els grups per a un curs, usuari i model determinat
	 *
	 **************************************************************************************/
	
	public String construirGrups(int mod_codnum)
	{



		// Busquem el Tipparam del Model
		String tipParam = s.GetTipParam(mod_codnum);
		
		

		// Construim els grups
		mfactory = MFactory.crearMFactory(tipParam);
//		ModelM1 m1 = new ModelM1();
		
		
		
		//m = mfactory.buscarModel(any,dni,mod_codnum,s);
		m = mfactory.buscarModel(any,dni,mod_codnum,s);

		

		int codi = m.getCodNum();
		String desid1 = m.getDesid1();
		String tparam = m.getTParam();		
				
		return desid1;

	}
	
	public HtmlPanelTabbedPane pintaGrups(int mod_codnum)
	{
		// Busquem el Tipparam del Model
		String tipParam = s.GetTipParam(mod_codnum);
		
		vfactory = VFactory.crearVFactory(tipParam);		
		
		return ((HtmlPanelTabbedPane)(vfactory.pintaModel(m,respostesBean.getRespostes(),respostesBean.getEnq_codnum(),respostesBean.getPrg_codnum(),respostesBean.getTipus_prg(),s,dni,mod_codnum)));
		
		
	}
	
	public String guardarGrup()
	{

	HashMap respostes = respostesBean.getRespostes();
	HashMap enq_codnum = respostesBean.getEnq_codnum();
	HashMap prg_codnum = respostesBean.getPrg_codnum();
	HashMap tipus_prg = respostesBean.getTipus_prg();
	HashMap tque_contestat = new HashMap();



	//************** Fem el bucle per recorrer els HashMaps i guardar-ho a la bbdd ************************

	if (respostes!=null && respostes.size()!=0 && enq_codnum!=null && enq_codnum.size()!=0 && prg_codnum!=null && prg_codnum.size()!=0 && tipus_prg!=null && tipus_prg.size()!=0)
	{
		
		Set keySet = respostes.keySet();
		for ( Iterator iter = keySet.iterator(); iter.hasNext(); )
  			{
	 		String key = (String) iter.next().toString();	 							
		
			Integer t_prg = (Integer)tipus_prg.get(key);
			String text = new String("");
			Integer opcio = new Integer(0);
			List<Integer> e_codnum = (List<Integer>)enq_codnum.get(key);
			Integer p_codnum = (Integer)prg_codnum.get(key);

			if (t_prg.intValue()==7)	// text lliure
			{
				text = (String)respostes.get(key);			
			}
			else	// opcio radio
			{			
				String op = (String)respostes.get(key);			
				if (op==null || op.equals(""))
					opcio = new Integer(0);
				else				
					opcio = Integer.valueOf(op);				
			}
			
			// Insertem a la bbdd
			for (int j=0; j<e_codnum.size();j++)	//Per cada enq_codnum fer una inserció
			{
				Integer e_cod = (Integer)e_codnum.get(j);
				s.setResposta(e_cod,p_codnum,opcio,text);
				
				// Guardem els diferents enq_codnum per afegir-ños a la taula TQUE_CONTESTAT				
				if (!tque_contestat.containsKey(e_cod)) 				
					tque_contestat.put(e_cod,"");
								
			}
		}
		
		// Ara afegim a la taula TQUE_CONTESTAT els enq_codnum per a que no s'introdueixi mes vegades
		Set keySet_contestat = tque_contestat.keySet();
		for ( Iterator iter_contestat = keySet_contestat.iterator(); iter_contestat.hasNext(); )
  			{
	 		Integer key_contestat = (Integer) iter_contestat.next();
	 		s.setTQue_contestat(key_contestat,dni);
	 	
  			}
		
	}







/*		facesContext = FacesContext.getCurrentInstance();

		UIViewRoot uIViewRoot = facesContext.getViewRoot();
		
		uIViewRoot.getChildren().clear();
*/
		return "getInici";
	}
	
}
	
	
	
