package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import java.util.*;

public class VBlocEnqGrp extends HtmlPanelGrid
{
	private String vdesid1;	
	private List<VPreguntaEnqGrp> vpreguntes = new ArrayList<VPreguntaEnqGrp>();
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public VBlocEnqGrp() {};
	
	public VBlocEnqGrp(BlocEnqGrp bloc, String id, HashMap respostes, HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg, List<Integer> enq_codnum, String dni)
	{		
		this.setId(id);	
		/* Busquem les columnes que ha de tenir*/
		List<PreguntaEnqGrp> pregunta = bloc.getPreguntaEnqGrp();		
		int npreguntes = pregunta.size();
		this.setColumns(1);
		this.setStyle("background-color:#F7F7F6");
		/* Afegim bloc */
		this.vdesid1 = bloc.getDesid1();
		
		// Posem returns, sals de linia
		HtmlOutputText htmlText = new HtmlOutputText(); 
		htmlText.setValue("");
		this.getChildren().add(htmlText);
		htmlText = new HtmlOutputText(); 
		htmlText.setValue("");
		this.getChildren().add(htmlText);
		htmlText = new HtmlOutputText(); 
		htmlText.setValue("");		
		this.getChildren().add(htmlText);
		
		
		//Posem el nom del bloc
		htmlText = new HtmlOutputText(); 
		htmlText.setValue(vdesid1.toUpperCase());

		htmlText.setStyle("font-weight: bold;font-size: 13px;");
		// Si es resposta opcional, no possem res
		if (!(vdesid1.trim().toUpperCase()).equals("RESPOSTA OPCIONAL"))		
			this.getChildren().add(htmlText);
		
		/* Afegim preguntes */					
		for (int i=0; i<pregunta.size(); i++)
		{
			
			if (( ((PreguntaEnqGrp)pregunta.get(i)).getTipprg_codnum())!=7)	//Si no es comentari i es opcio
			{
				PreguntaEnqGrpOp pgOp = (PreguntaEnqGrpOp)pregunta.get(i);
				VPreguntaEnqGrp v = new VPreguntaEnqGrpOp(pgOp,id+'p'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,enq_codnum,dni);
				this.getChildren().add((HtmlPanelGrid)v);			
				vpreguntes.add(v);			
			}
			else 	//Si es un comentari
			{
				PreguntaEnqGrpTxt pgTxt = (PreguntaEnqGrpTxt)pregunta.get(i);
				VPreguntaEnqGrp v = new VPreguntaEnqGrpTxt(pgTxt,id+'p'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,enq_codnum,dni);
				this.getChildren().add((HtmlPanelGrid)v);			
				vpreguntes.add(v);		
			}
		}				

	}
}