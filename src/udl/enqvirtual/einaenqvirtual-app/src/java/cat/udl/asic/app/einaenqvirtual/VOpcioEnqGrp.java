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

public class VOpcioEnqGrp extends UISelectItem
{
	public int vval_codnum;
	public String vval_codalf;
	
	public void setId(String id)
	{
		super.setId(id);
	}
	
	public VOpcioEnqGrp() {};
	
	public VOpcioEnqGrp(OpcioEnqGrp opcio,String id)
	{
		this.setId(id);
		this.setItemLabel(id);
		this.vval_codnum = opcio.getCodNum();
		this.vval_codalf = opcio.getVal_codalf();
			
		this.setValue(new SelectItem(""+this.vval_codnum,this.vval_codalf,id));
			

	}
	

}