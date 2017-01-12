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

public class VGrupEnqGrp extends HtmlPanelTab {

	
	private String vgrp_desid1;
	private String vgrp_codalf;
	private QuestionariEnqGrp vquestionari;
	private List<VBlocEnqGrp> vblocs = new ArrayList<VBlocEnqGrp>();
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

	public VGrupEnqGrp() {
	};

	public VGrupEnqGrp(GrupEnqGrp grup, String id, HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni)
	{
		this.setId(id);
		List<BlocEnqGrp> bloc = grup.getBlocEnqGrp();
		int nblocs = bloc.size();
								
		/* Afegim grup */
		this.vgrp_desid1 = grup.getNom_pla();		
		this.vgrp_codalf = grup.getPla_codalf();
		
		
//		HtmlOutputText htmlText = new HtmlOutputText(); 
//		htmlText.setValue(vgrp_desid1.toUpperCase());				
//		htmlText.setStyle("font-weight: bold;font-size: 17px;color: #707070;font-style: italic;");
		
		
		
		// Etiqueta amb el nom del grup / pla
		//this.setLabel(vassignatura);
		this.setLabel(String.valueOf(vgrp_codalf));
		

		
		// Posem el nom del grup / pla
		HtmlOutputText htmlText = new HtmlOutputText(); 
		htmlText.setValue(vgrp_desid1.toUpperCase());
		htmlText.setStyle("font-weight: bold;font-size: 17px;color: #707070;font-style: italic;");
		this.getChildren().add(htmlText);
		
		
		
		facesContext = FacesContext.getCurrentInstance();
		
		
		// Comprovem si ja està contestada el grup / pla		
		int e_cod = grup.getEnq_codnum();
		Integer enq = new Integer(e_cod);
		List<Integer> lenq_codnum = new ArrayList<Integer>();
		lenq_codnum.add(enq);
		String contestat = s.getContestat(lenq_codnum,dni);
		
		
		if (contestat.equals("N"))
		{
			/* Afegim un HtmlPanelTabbedPane que englobara els grups 
			 * que seran HtmlPanelTabs
			 */
//			HtmlPanelTabbedPane panelGrups = new HtmlPanelTabbedPane();
//			this.getChildren().add(panelGrups);
									
			for (int i=0; i<bloc.size(); i++)
			{
				VBlocEnqGrp v = new VBlocEnqGrp(bloc.get(i),id+'b'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,lenq_codnum,dni);
				vblocs.add(v);									
				this.getChildren().add((HtmlPanelGrid)v);
				
			}		
			
			this.vquestionari = grup.getQuestionariEnqGrp();							
			
			// Posem el boto de guardar
			cmpitem = new HtmlCommandButton();		
			cmpitem.setValue("Enviar enquesta");		
			app = facesContext.getApplication();					
			cmpitem.setId(this.getId()+"c");		
			GestorBotons gb = new GestorBotons();		
			gb.omplirParametre(respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,s,dni);		
			cmpitem.addActionListener(gb);
			this.getChildren().add(cmpitem);
			
			
			
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
			// Posem un missatge de que ja està contestat el pla						
			
			
			HtmlPanelGrid pan = new HtmlPanelGrid();
			pan.setColumns(1);
			
			HtmlOutputText htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue("");			
			pan.getChildren().add(htmlTextContestat);
			
			
			htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue("Aquesta enquesta ja està contestada");
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);
			
			
			this.getChildren().add(pan);
			
		}
		
		
	}
}