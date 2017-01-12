package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlForm; //import org.sakaiproject.jsf.component.ViewComponent;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTab;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import java.util.*;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.application.Application;

public class VAssignatura extends HtmlPanelTab {
	private String vassignatura;
	private int vcodnum;
	private List<VGrupEnqAssPrf> vgrups = new ArrayList<VGrupEnqAssPrf>();
	private VGrupAssignatura vgrupassignatura;
	FacesContext facesContext;
	ValueBinding valueBinding;
	MethodBinding methodBinding;
	Application app;
	Application appI;	
	HtmlCommandButton cmpitem;
	HtmlCommandButton cmpitemI;	

	public void setId(String id) {
		super.setId(id);
	}

	public VAssignatura() {
	};

	public VAssignatura(Assignatura assig, String id, HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni)
	{
		
		// Per a buscar els missatges de sortida
		ResourceBundle messageBundle = ResourceBundle.getBundle("cat.udl.asic.app.einaenqvirtual.Messages");
		
		this.setId(id);
		List<GrupEnqAssPrf> grups = assig.getGrupEnqAssPrf();
		int ngrups = grups.size();
		//this.setColumns(1);
		//this.setBorder(3);
		//this.setWidth("1000");
		
		/* Afegim assignatura */
		this.vassignatura = assig.getAssignatura();
		this.vcodnum = assig.getCodNum();
		
		// Etiqueta amb el nom de l'assignatura
		//this.setLabel(vassignatura);
		this.setLabel(String.valueOf(vcodnum));
		

		
		// Posem el nom de l'assignatura
		HtmlOutputText htmlText = new HtmlOutputText(); 
		htmlText.setValue(messageBundle.getString ("assig")+" "+vassignatura.toUpperCase());
		htmlText.setStyle("font-weight: bold;font-size: 17px;color: #707070;font-style: italic;");
		this.getChildren().add(htmlText);
		
		
		
		facesContext = FacesContext.getCurrentInstance();
		
		
		// Comprovem si ja està contestada l'assignatura
		GrupAssignatura gassig = assig.getGrupAssignatura();
		List<Integer> lenq_codnum = gassig.getEnq_codnum();
		String contestat = s.getContestat(lenq_codnum,dni);
		
		if (contestat.equals("N"))
		{
			/* Afegim un HtmlPanelTabbedPane que englobara els grups 
			 * que seran HtmlPanelTabs
			 */
			HtmlPanelTabbedPane panelGrups = new HtmlPanelTabbedPane();
			this.getChildren().add(panelGrups);
									
			
			// Grups - Professors
			for (int i=0; i<grups.size(); i++)
			{
				
				VGrupEnqAssPrf v = new VGrupEnqAssPrf(grups.get(i),id+'g'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg);
				vgrups.add(v);									
				panelGrups.getChildren().add((HtmlPanelTab)v);
				
			}		
							
			// GrupAssignatura
			vgrupassignatura = new VGrupAssignatura(assig.getGrupAssignatura(),id+new String("ga0"),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg);
			panelGrups.getChildren().add((HtmlPanelTab)vgrupassignatura);
			
			
			// Posem el boto de guardar
			cmpitem = new HtmlCommandButton();		
			cmpitem.setValue("Enviar enquesta");		
			app = facesContext.getApplication();					
			cmpitem.setId(this.getId()+"c");		
			GestorBotons gb = new GestorBotons();		
			gb.omplirParametre(respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,s,dni);		
			cmpitem.addActionListener(gb);
			this.getChildren().add(cmpitem);
			
			
			
			// Posem el boto de incidencies
/*			cmpitemI = new HtmlCommandButton();		
			cmpitemI.setValue("Incidències");		
			appI = facesContext.getApplication();					
			cmpitemI.setId(this.getId()+"i");		
			GestorIncidencies gi = new GestorIncidencies();		
			gi.omplirParametre(assig,s,dni);		
			cmpitemI.addActionListener(gi);
			this.getChildren().add(cmpitemI);			
*/
			
			// Missatges d'error			
			HtmlPanelGrid panError = new HtmlPanelGrid();
			panError.setColumns(1);
			
			HtmlOutputText htmlTextContestatError = new HtmlOutputText(); 
			htmlTextContestatError.setValue("");			
			panError.getChildren().add(htmlTextContestatError);
			
			HtmlMessage missatge1 = new HtmlMessage();
			missatge1.setFor(cmpitem.getClientId(facesContext));
			missatge1.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
			panError.getChildren().add(missatge1);
			
			this.getChildren().add(panError);
				
		}
		else if (contestat.equals("S"))
		{
			// Posem un missatge de que ja està contestada l'assignatura									
			
			HtmlPanelGrid pan = new HtmlPanelGrid();
			pan.setColumns(1);
			
			HtmlOutputText htmlTextContestat = new HtmlOutputText(); 			
			htmlTextContestat.setValue(".");			
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: #FFFFFF;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);			
			
			htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue(messageBundle.getString ("enqEnviada"));
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);
						
			
			for (int i = 0; i<25;i++)	// Per posar salts de linia
			{
				htmlTextContestat = new HtmlOutputText(); 			
				htmlTextContestat.setValue(".");			
				htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: #FFFFFF;font-style: italic;");
				pan.getChildren().add(htmlTextContestat);
				
			}
						
			
			this.getChildren().add(pan);
			
		}
		

		
		
		
	}
}