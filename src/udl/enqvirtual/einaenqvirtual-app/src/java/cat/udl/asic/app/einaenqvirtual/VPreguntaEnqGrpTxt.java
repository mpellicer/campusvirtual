package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.component.html.HtmlInputTextarea;
import java.util.*;
import java.lang.*;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class VPreguntaEnqGrpTxt extends VPreguntaEnqGrp
{	
	private String resposta;	
	
	public void setId(String id)
	{
		super.setId(id);
	}
		
	
	public VPreguntaEnqGrpTxt() {};
	
	public VPreguntaEnqGrpTxt(PreguntaEnqGrpTxt pregunta, String id, HashMap respostes, HashMap hmEnq_codnum, HashMap hmPrg_codnum, HashMap hmTipus_prg,List<Integer> enq_codnum, String dni)
	{
		super(pregunta,id,respostes,enq_codnum,dni);			
				
		FacesContext facesContext;
		ValueBinding valueBinding;
		Application app;
		
		/* Afegim textarea */
		HtmlInputTextarea cmp = new HtmlInputTextarea();		
		//cmp.setRequired(true);
		cmp.setId(id+"s");		
		cmp.setStyleClass("taula_row");		
		cmp.setCols(95);
		cmp.setRows(8);
		
		
		/***********************************************
		 * Per guardar les respostes
		 ***********************************************/
		
		facesContext = FacesContext.getCurrentInstance();
       	app = facesContext.getApplication();
       	valueBinding = app.createValueBinding("#{GetModelCtl.respostesBean.respostes['"+this.getId()+"']}");
    	cmp.setValueBinding("value",valueBinding);
		
		
    	respostes.put(this.getId(),"");
    	hmEnq_codnum.put(this.getId(),enq_codnum);
    	hmPrg_codnum.put(this.getId(),pregunta.getCodNum());
    	hmTipus_prg.put(this.getId(),pregunta.getTipprg_codnum());
    	
    	
    	
    	
    	/***********************************************
    	 * 
    	 */
		
		
		
		this.getChildren().add(cmp);
	}
}