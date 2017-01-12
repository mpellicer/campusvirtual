package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Model
 */
public abstract class Model {

	//
	// Fields
	//
	
	private int codnum;
	private String desid1;
	private String tparam;	
	private int ordre;
	private String visible;	

	//
	// Constructors
	//
	


	public Model(int codnum, String desid1, String tparam, int ordre, String visible){

		this.setCodNum(codnum);
		if(desid1!=null)	this.setDesid1(desid1);		
		if(tparam!=null)	this.setTParam(tparam);
		if(ordre!=0)	this.setOrdre(ordre);		
		if(visible!=null)	this.setVisible(visible);	  
	}
	
	public Model () {
		codnum = 0;
		tparam = "";
		desid1 = "";
		ordre = 0;
		visible = null;				
	};

	//
	// Model methods
	//
	
	public void setCodNum ( int newVar ) {
		codnum = newVar;
	}
	public int getCodNum ( ) {  
		return codnum;
	}
	
	public void setDesid1 ( String newVar ) {
		desid1 = newVar;
	}
	public String getDesid1 ( ) {
		return desid1;
	}
	
	public void setTParam ( String newVar ) {
		tparam = newVar;
	}
	public String getTParam ( ) {
		return tparam;
	}	
	
	public void setOrdre ( int newVar ) {
		ordre = newVar;
	}
	public int getOrdre ( ) {  
		return ordre;
	}	
	
	public void setVisible ( String newVar ) {
		visible = newVar;
	}
	public String getVisible ( ) {  
		return visible;
	}	

	public List<Assignatura> getAssignatura (String anyaca, String user_id, EinaEnqVirtualService s) {
		return null;
	}
	
	public List<Assignatura> getAssignatura () {
		return null;
	}	
	
	
	public List<GrupEnqGrp> getGrupEnqGrp (String dni,String anyaca,int model,EinaEnqVirtualService s) {
		
		return null;
	}
	
	public List<GrupEnqGrp> getGrupEnqGrp () {							
		return null;
	}

}
