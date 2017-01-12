package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Model
 */
public class ModelEnqGrp extends Model{
	private List<GrupEnqGrp> lgrup;
	
	public ModelEnqGrp(){};
	
	public ModelEnqGrp(int codnum, String desid1, String tparam, int ordre, String visible,String anyaca, String dni, EinaEnqVirtualService s,int model)
	{
		super(codnum,desid1,tparam,ordre,visible);		
		setGrupEnqGrp(dni,anyaca,model,s);
	}
	
	
	
	public void setGrupEnqGrp (String dni,String anyaca,int model,EinaEnqVirtualService s) {
		
		this.lgrup = s.getGrupEnqGrp(dni,anyaca,model,s);				
	}
	
	public List<GrupEnqGrp> getGrupEnqGrp (String dni,String anyaca,int model,EinaEnqVirtualService s) {
			
		List<GrupEnqGrp> lgrups = s.getGrupEnqGrp(dni,anyaca,model,s);				
		return lgrups;
	}
	
	public List<GrupEnqGrp> getGrupEnqGrp () {							
		return this.lgrup;
	}
	
	
}
