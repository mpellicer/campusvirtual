package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import java.util.*;
import java.lang.*;
import javax.faces.component.UIComponent;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;



public class VPreguntaEnqGrpOp extends VPreguntaEnqGrp
{	
	private List<VOpcioEnqGrp> vopcions = new ArrayList<VOpcioEnqGrp>();	
	
	public void setId(String id)
	{
		super.setId(id);
	}
		
	
	public VPreguntaEnqGrpOp() {};
	
	public VPreguntaEnqGrpOp(PreguntaEnqGrpOp pregunta, String id, HashMap respostes, HashMap hmEnq_codnum, HashMap hmPrg_codnum, HashMap hmTipus_prg, List<Integer> enq_codnum, String dni)
	{
		super(pregunta,id,respostes,enq_codnum,dni);
		/* Busquem les columnes que ha de tenir*/
		List<OpcioEnqGrp> opcio = pregunta.getOpcioEnqGrp();		
		int nopcions = opcio.size();		
		
		FacesContext facesContext;
		ValueBinding valueBinding;
		Application app;
		
		
		/* Afegim opcions */
		HtmlSelectOneRadio cmp = new HtmlSelectOneRadio();
		GestorEvents c = new GestorEvents();
		
//		cmp.setRequired(true);
		cmp.setId(id+"s");		
		cmp.setStyleClass("taula_row");	
		
		//cmp.addValueChangeListener("#{GestorEvents.copiaResposta}");
	//	cmp.addValueChangeListener(c);
		
		//cmp.setOnclick("{color:#0000ff}showPopupWindow(this); return false;{color:#0000ff}");
		//cmp.setOnclick("javascript:hola(this.value,this.name);");
	  	
		/***********************************************
		 * Per guardar les respostes
		 ***********************************************/
		
		facesContext = FacesContext.getCurrentInstance();
       	app = facesContext.getApplication();
    	valueBinding = app.createValueBinding("#{GetModelCtl.respostesBean.respostes['"+this.getId()+"']}");
//    	valueBinding = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{GetModelCtl.respostesBean.respostes['"+this.getId()+"']}");
    	
    	cmp.setValueBinding("value",valueBinding);
		
    	
        
    	
		
    	respostes.put(this.getId(),"");
    	hmEnq_codnum.put(this.getId(),enq_codnum);
    	hmPrg_codnum.put(this.getId(),pregunta.getCodNum());
    	hmTipus_prg.put(this.getId(),pregunta.getTipprg_codnum());
    	
    	
    	
    	
		
    	
    	/***********************************************
    	 * 
    	 */
    	
		//cmp.setValue(this.vtext);
		cmp.setValue(super.getVtext());
		
		for (int i=1; i<opcio.size(); i++)
		{			
			VOpcioEnqGrp v = new VOpcioEnqGrp(opcio.get(i),id+'o'+String.valueOf(i));
			vopcions.add(v);			
			cmp.getChildren().add(v);
			
		}		

		this.getChildren().add(cmp);
	}
}