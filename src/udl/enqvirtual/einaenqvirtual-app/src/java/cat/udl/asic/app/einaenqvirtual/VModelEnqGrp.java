package cat.udl.asic.app.einaenqvirtual;

import javax.faces.component.html.HtmlPanelGrid;
import java.util.*;
import cat.udl.asic.einaenqvirtual.api.*;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTab;

public class VModelEnqGrp extends HtmlPanelTabbedPane
{
	
	private List<VGrupEnqGrp> vgrups = new ArrayList<VGrupEnqGrp>();
	
	public VModelEnqGrp() {};
	
	public VModelEnqGrp(Model m, HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni,int mod_codnum)
	{	
		
		
		
		List<GrupEnqGrp> grups = m.getGrupEnqGrp();
		int ngrups = grups.size();
		
		if (ngrups!=0)
		{
		
			for (int i=0; i<grups.size(); i++)
			{
				VGrupEnqGrp v = new VGrupEnqGrp(grups.get(i),'g'+String.valueOf(i),respostes,hmEnq_codnum,hmPrg_codnum,hmTipus_prg,s,dni);
				vgrups.add(v);						
				this.getChildren().add(v);
				
			}
					
		}
		else	// Si no te grups
		{
			VGrupEnqGrpBuit v = new VGrupEnqGrpBuit("gbuit");
			this.getChildren().add(v);			
		}

	}
}