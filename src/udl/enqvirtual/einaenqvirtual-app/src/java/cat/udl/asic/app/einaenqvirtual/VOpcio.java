package cat.udl.asic.app.einaenqvirtual;

import javax.faces.component.html.HtmlSelectOneRadio;
import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIPanel;
import javax.faces.component.UIViewRoot;
import javax.faces.component.UISelectItem;
import javax.faces.model.SelectItem;

public class VOpcio extends UISelectItem
{
	public int vval_codnum;
	public String vval_codalf;
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public VOpcio() {};
	
	public VOpcio(Opcio opcio,String id)
	{
		this.setId(id);
		this.setItemLabel(id);
		this.vval_codnum = opcio.getCodNum();
		this.vval_codalf = opcio.getVal_codalf();
		
		
		
		
    	// Lliguem el valor amb el Map parametre del ParamBean
/*    	FacesContext facesContext = FacesContext.getCurrentInstance();
       	Application app = facesContext.getApplication();
    	ValueBinding valueBinding = app.createValueBinding("#{EinaDPPA.pbean.parametre['form:"+cmp.getId()+"']}");
    	cmp.setValueBinding("value",valueBinding);
*/    	
		
//		if (vval_codnum!=99)
//		{
			/* Afegim opcions */
//			this.vval_codalf = opcio.getVal_codalf();				
//			HtmlOutputText htmlText = new HtmlOutputText(); 
//			htmlText.setValue(vval_codalf);
//			this.getChildren().add(htmlText);
			
			
			
			this.setValue(new SelectItem(""+this.vval_codnum,this.vval_codalf,id));
			
			
			
			
			
//		}
		
		
				
		
//		this.setTitle(vval_codalf);
//		this.setValue(""+ vval_codnum);
	}
	

}