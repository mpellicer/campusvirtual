package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public class Assignatura {

	//
	// Fields
	//		
	private int codnum;
	private String assignatura;		
	private List<GrupEnqAssPrf> lgrup;
	private GrupAssignatura gassignatura;
		

	//
	// Constructors
	//	
	public Assignatura(int codnum, String assig,EinaEnqVirtualService s,String dni,String anyaca,int model){	
		this.setCodNum(codnum);						
		if(assig!=null)	this.setAssignatura(assig);
		setGrupEnqAssPrf(dni,anyaca,model,s);
		setGrupAssignatura(dni,anyaca,model,s);
		
	}
	
	public Assignatura() {};

	//
	// Model methods
	//
	
		
	public void setCodNum ( int newVar ) {
		this.codnum = newVar;
	}
	public int getCodNum ( ) {  
		return this.codnum;
	}
	
		
	public void setAssignatura ( String newVar ) {
		this.assignatura = newVar;
	}
	public String getAssignatura ( ) {
		return this.assignatura;
	}
	
	public void setGrupEnqAssPrf (String dni,String anyaca,int model,EinaEnqVirtualService s) {
		this.lgrup = s.getGrupEnqAssPrf(dni,anyaca,codnum,model,s);
	}
	public List<GrupEnqAssPrf> getGrupEnqAssPrf (String dni,String anyaca,int model,EinaEnqVirtualService s) {
			
		List<GrupEnqAssPrf> lgrups = s.getGrupEnqAssPrf(dni,anyaca,codnum,model,s);				
		return lgrups;
	}
	
	public List<GrupEnqAssPrf> getGrupEnqAssPrf () {							
		return this.lgrup;
	}
	
	
	public void setGrupAssignatura (String dni,String anyaca,int model,EinaEnqVirtualService s) 
	{
		this.gassignatura = new GrupAssignatura(codnum,s,dni,anyaca,model);
	}
	
	public GrupAssignatura getGrupAssignatura()
	{
		return this.gassignatura;
	}
	
	

}
