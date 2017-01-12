package cat.udl.asic.app.einaenqvirtual;

import javax.faces.component.html.HtmlPanelGrid;
import java.util.*;
import cat.udl.asic.einaenqvirtual.api.*;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTab;

public class VModelEnqAssPrf extends HtmlPanelTabbedPane
{
	
	private List<VAssignatura> vassignatures = new ArrayList<VAssignatura>();
	
	public VModelEnqAssPrf() {};
	
	public VModelEnqAssPrf(Model m, HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni,int mod_codnum)
	{	
		List<Assignatura> assignatures = m.getAssignatura();
		int nassignatures = assignatures.size();
		//this.setColumns(nassignatures+1);
	
		
		if (nassignatures!=0)
		{
		
			for (int i=0; i<assignatures.size(); i++)
			{
				VAssignatura v = new VAssignatura(assignatures.get(i),'a'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,s,dni);
				vassignatures.add(v);						
				this.getChildren().add(v);
				
			}		
		}
		else	// Si no te assignatures
		{
			VAssignaturaBuit v = new VAssignaturaBuit("abuit",s,dni,mod_codnum);
			this.getChildren().add(v);
		}

	}
}