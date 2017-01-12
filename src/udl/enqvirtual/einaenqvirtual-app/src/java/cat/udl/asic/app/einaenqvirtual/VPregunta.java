package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.application.Application;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import java.util.*;
import java.lang.*;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import java.util.ArrayList;
import java.util.List;



public abstract class VPregunta extends HtmlPanelGrid
{
	private String vtext;	
	private int num;
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public String getVtext()
	{
		return this.vtext;
	}
	
	public VPregunta() {};
	
	public VPregunta(Pregunta pregunta, String id, HashMap respostes, List<Integer> enq_codnum, String dni)
	{
		FacesContext facesContext;
		ValueBinding valueBinding;
		Application app;
		this.setId(id);
		/* Busquem les columnes que ha de tenir*/
//		List<Opcio> opcio = pregunta.getOpcio();		
//		int nopcions = opcio.size();
//		this.setColumns(nopcions+1); // +1 no ja que treiem l'opcio d'error

//		this.setBorder(0);
		this.setWidth("800");
		
		/* Afegim pregunta */
		this.vtext = pregunta.getText();		
		HtmlOutputText htmlText = new HtmlOutputText();
		
		

		//Posem el numero davant de la pregunta
		int num = pregunta.getOrdre();
		if (num == 0)
			htmlText.setValue(vtext);
		else
			htmlText.setValue(num+".- "+vtext);
		
/*		int pos=id.indexOf('p');				
		String subStr=id.substring(pos+1);
		int numero=Integer.parseInt(subStr.trim());
		numero++;
		String num = String.valueOf(numero);		
*/
		
				
		
		
		
		//htmlText.setValue(vtext);
		htmlText.setStyle("font-weight: bold;");		
		this.getChildren().add(htmlText);
		
		
		
	}
}