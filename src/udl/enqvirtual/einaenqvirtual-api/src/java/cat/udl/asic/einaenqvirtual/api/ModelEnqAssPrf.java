package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Model
 */
public class ModelEnqAssPrf extends Model{
	private List<Assignatura> assignatures;
	
	public ModelEnqAssPrf(){};
	
	public ModelEnqAssPrf(int codnum, String desid1, String tparam, int ordre, String visible,String anyaca, String user_id, EinaEnqVirtualService s)
	{
		super(codnum,desid1,tparam,ordre,visible);
		setAssignatura(anyaca,user_id,codnum,s);	
	}
	
	
	
	public void setAssignatura (String anyaca, String user_id, int model_codnum, EinaEnqVirtualService s) {
		this.assignatures = s.getAssignatura(user_id,anyaca,model_codnum,s);		
	}
	
	public List<Assignatura> getAssignatura (String anyaca, String user_id, int model_codnum, EinaEnqVirtualService s) {
		if (assignatures==null)		
			assignatures = s.getAssignatura(user_id,anyaca,model_codnum,s);
	
		return assignatures;
				 
	}
	
	public List<Assignatura> getAssignatura () {							
		return this.assignatures;
	}
	
	
}
