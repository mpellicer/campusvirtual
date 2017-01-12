package cat.udl.asic.app.einaenqvirtual;

import java.util.*;
import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import javax.faces.context.FacesContext;
import javax.faces.application.Application;

public class V2Factory extends VFactory
{
	FacesContext facesContext;
	Application app;

	
	public HtmlPanelTabbedPane pintaModel(Model m,HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni,int mod_codnum)
	{
		VModelEnqGrp vModel = new VModelEnqGrp(m,respostes,hmEnq_codnum,hmPrg_codnum, hmTipus_prg,s,dni,mod_codnum);
		return vModel;
	}
	
	
}