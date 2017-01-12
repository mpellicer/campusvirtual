package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public class Convocatoria {	 
	//
	// Fields
	//		
	private int codnum;	
	private String desid1;
	private String periode2_ini;
	private String periode2_fi;	

	

	//
	// Constructors
	//	

	public Convocatoria(int codnum, String desid1, String periode2_ini, String periode2_fi){
	
		this.setCodNum(codnum);
		if(desid1!=null)	this.setDesid1(desid1);			
		if(periode2_ini!=null)	this.setPeriode2_ini(periode2_ini);			
		if(periode2_fi!=null)	this.setPeriode2_fi(periode2_fi);
	}
	
	public Convocatoria() {};

	//
	// Model methods
	//
	
	public void setCodNum ( int newVar ) {
		this.codnum = newVar;
	}
	public int getCodNum ( ) {  
		return this.codnum;
	}
	
	public void setDesid1 ( String newVar ) {
		this.desid1 = newVar;
	}
	public String getDesid1 ( ) {
		return this.desid1;
	}
	
	public void setPeriode2_ini ( String newVar ) {
		this.periode2_ini = newVar;
	}
	public String getPeriode2_ini ( ) {
		return this.periode2_ini;
	}	
	
	public void setPeriode2_fi ( String newVar ) {
		this.periode2_fi = newVar;
	}
	public String getPeriode2_fi ( ) {
		return this.periode2_fi;
	}	
		
	
	
	
	
	
}
