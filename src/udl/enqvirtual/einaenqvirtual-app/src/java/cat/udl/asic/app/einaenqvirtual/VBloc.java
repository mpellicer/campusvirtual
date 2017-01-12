package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import java.util.*;

public class VBloc extends HtmlPanelGrid
{
	private String vdesid1;	
	private List<VPregunta> vpreguntes = new ArrayList<VPregunta>();
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public VBloc() {};
	
	public VBloc(Bloc bloc, String id, HashMap respostes, HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg, List<Integer> enq_codnum, String dni)
	{		
		this.setId(id);	
		/* Busquem les columnes que ha de tenir*/
		List<Pregunta> pregunta = bloc.getPregunta();		
		int npreguntes = pregunta.size();
		this.setColumns(1);
		this.setStyle("background-color:#F7F7F6");		
		/* Afegim bloc */
		this.vdesid1 = bloc.getDesid1();
		
		
		
		//Posem el nom del bloc		
		if (pregunta.size()!=0)	//sempre i quant hi hagi preguntes
		{
			
			
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

			
			htmlText = new HtmlOutputText(); 
			htmlText.setValue(vdesid1.toUpperCase());
			htmlText.setStyle("font-weight: bold;font-size: 13px;");
			// Si es resposta opcional, no possem res
			if (!(vdesid1.trim().toUpperCase()).equals("RESPOSTA OPCIONAL"))		
				this.getChildren().add(htmlText);
		}
		
		/* Afegim preguntes */					
		for (int i=0; i<pregunta.size(); i++)
		{
			
			if (( ((Pregunta)pregunta.get(i)).getTipprg_codnum())!=7)	//Si no es comentari i es opcio
			{
				PreguntaOp pgOp = (PreguntaOp)pregunta.get(i);
				VPregunta v = new VPreguntaOp(pgOp,id+'p'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,enq_codnum,dni);
				this.getChildren().add((HtmlPanelGrid)v);			
				vpreguntes.add(v);
			}
			else 	//Si es un comentari
			{
				PreguntaTxt pgTxt = (PreguntaTxt)pregunta.get(i);
				VPregunta v = new VPreguntaTxt(pgTxt,id+'p'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,enq_codnum,dni);
				this.getChildren().add((HtmlPanelGrid)v);			
				vpreguntes.add(v);
			}
			
			
		}				

	}
}