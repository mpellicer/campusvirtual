package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTab;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;

import java.util.*;


public class VGrupAssignatura  extends HtmlPanelTab
{
	private String vgrp_desid1;	
	private List<VBloc> vblocs = new ArrayList<VBloc>();
	FacesContext facesContext;
	ValueBinding valueBinding;
	MethodBinding methodBinding;
	Application app;
	HtmlCommandButton cmpitem;
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public VGrupAssignatura(){};
	
	public VGrupAssignatura(GrupAssignatura grup,String id, HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg)
	{

		this.setId(id);
		List<Bloc> bloc = grup.getBloc();
		int nblocs = bloc.size();
//		this.setColumns(1);
//		this.setBorder(4);
	
		/* Afegim grup */
//		this.vgrp_desid1 = grup.getProfessor();				
		HtmlOutputText htmlText = new HtmlOutputText(); 
		htmlText.setValue("ASSIGNATURA");				
		htmlText.setStyle("font-weight: bold;font-size: 17px;color: #707070;font-style: italic;");
		// Fixem el titol del grup que sera el nom del professor
		//this.setLabel(vgrp_desid1.toUpperCase());		
		this.setLabel("ASSIGNATURA");
		this.getChildren().add(htmlText);
		
		// Parametres que necessitem a VPregunta per grabar els resultats
		List<Integer> enq_codnum = grup.getEnq_codnum();
		String dni = grup.getDni();
		

		
		
		for (int i=0; i<bloc.size(); i++)
		{
			VBloc v = new VBloc(bloc.get(i),id+new String("b")+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,enq_codnum,dni);
			vblocs.add(v);									
			this.getChildren().add((HtmlPanelGrid)v);
		}		
		
//		this.vquestionari = grup.getQuestionari();
		
		
		
		
		
		// Posem el boto de guardar
/*		cmpitem = new HtmlCommandButton();
		
		cmpitem.setImmediate(true);

		cmpitem.setValue("Guardar enquesta");

		facesContext = FacesContext.getCurrentInstance();
		app = facesContext.getApplication();	
		methodBinding = app.createMethodBinding("#{GetModelCtl.guardarGrup}", null);
		cmpitem.setAction(methodBinding);
		cmpitem.setId(this.getId()+"c");
		this.getChildren().add(cmpitem);
		*/
		
	}
}