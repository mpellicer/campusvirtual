package cat.udl.asic.einaenqvirtual.api;

import java.util.*;


public abstract class MFactory {
	
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
	
	private static MFactory INSTANCE = null;
	
	public MFactory()	{	}
	
	private synchronized static void crearInstancia(String tipParam)
	{		
		if (INSTANCE == null || tipParam != INSTANCE.getTip_param()) {
				if (tipParam.equals("TQUE_TIPPARAM1"))
					INSTANCE = new M1Factory();
				/*else if (mod_codnum==2)
					INSTANCE =  new M2Factory();
				else if (mod_codnum==3)
					INSTANCE =  new M3Factory();*/
				else 
					INSTANCE = new M2Factory();							
		}
						
	}
	
	public static MFactory crearMFactory(String tipParam) {
		//if (INSTANCE == null) {
			crearInstancia(tipParam);			
		//}
		return INSTANCE;
	}
	public Grup crearGrup(){
		return null;
	}
	
	public List buscarGrups(String anyaca, String user_id, EinaEnqVirtualService s){
		return null;
	}
	
	public List buscarAssignatures(String anyaca, String user_id, EinaEnqVirtualService s)
	{
		return null;
	}
	
	public Model buscarModel(String any, String dni, int model, EinaEnqVirtualService s)
	{
		return null;
	}
	
}