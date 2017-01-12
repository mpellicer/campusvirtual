package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import java.util.List;
import javax.faces.component.html.HtmlPanelGrid;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import java.util.HashMap;

public abstract class VFactory {
		
	
	public int mod_codnum;
	public String tip_param;
	
	public int getMod_codnum()
	{
		return this.mod_codnum;
	}
	public void setMod_codnum(int mod)
	{
		this.mod_codnum=mod;
	}
	
	public String getTip_param()
	{
		return this.tip_param;
	}
	public void setTip_param(String tip)
	{
		this.tip_param=tip;
	}
	
		private static VFactory INSTANCE = null;
		
		public VFactory()	{	}
		
		private synchronized static void crearInstancia(String tipParam)
		{
								
			if (INSTANCE == null || !tipParam.equals(INSTANCE.getTip_param())) {				
					if (tipParam.equals("TQUE_TIPPARAM1"))
						INSTANCE = new V1Factory();
					/*else if (mod_codnum==2)
						INSTANCE =  new V2Factory();					
					else if (mod_codnum==3)
						INSTANCE = new V3Factory();*/					
					else 
						INSTANCE = new V2Factory();
			}
		}
		
		public static VFactory crearVFactory(String tipParam) {
			//if (INSTANCE == null) {
				crearInstancia(tipParam);			
			//}
			return INSTANCE;
		}
	
		
		public HtmlPanelTabbedPane pintaModel(Model m,HashMap respostes,HashMap hmEnq_codnum,HashMap hmPrg_codnum, HashMap hmTipus_prg,EinaEnqVirtualService s,String dni,int mod_codnum)
		{	
			return null;
		}
		
		public List<VAssignatura> pintaAssignatures(List<Assignatura> assignatures) 
		{
			return null;
		}
		
		
}