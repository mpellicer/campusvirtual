package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public class Alumne {

	//
	// Fields
	//		
	private String dni;		
	private int convCodnum;
	private int assCodnum;
	private String plaCodalf;
	private String prfCodalf;	
		

	//
	// Constructors
	//	
	public Alumne(String dni, int convCodnum, int assCodnum, String plaCodalf, String prfCodalf){
		
		this.dni        = dni;
		this.convCodnum = convCodnum;
		this.assCodnum  = assCodnum;
		this.plaCodalf  = plaCodalf;
		this.prfCodalf  = prfCodalf;
	}
	
	public Alumne() {};

	//
	// Model methods
	//
	
		
	public void setConvCodnum ( int newVar ) {
		this.convCodnum = newVar;
	}
	public int getConvCodnum ( ) {  
		return this.convCodnum;
	}
	
		
	public void setDni ( String newVar ) {
		this.dni = newVar;
	}
	public String getDni ( ) {
		return this.dni;		
	}
	
	public void setAssCodnum ( int newVar ) {
		this.assCodnum = newVar;
	}
	public int getAssCodnum ( ) {
		return this.assCodnum;
	}
	
	public void setPlaCodalf ( String newVar ) {
		this.plaCodalf = newVar;
	}
	public String getPlaCodalf ( ) {
		return this.plaCodalf;
	}
	
	public void setPrfCodalf( String newVar ) {
		this.prfCodalf= newVar;
	}
	public String getPrfCodalf ( ) {
		return this.prfCodalf;
	}
	
	public String getCadena ( ) {
		return this.convCodnum + " - " + this.plaCodalf + " - " + this.assCodnum + " - " + this.prfCodalf + " - " + this.dni; 
	}
	
}
