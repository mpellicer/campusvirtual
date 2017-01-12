package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public class OpcioEnqGrp {

	//
	// Fields
	//		
	public int codnum;
	public int tipprg_codnum;
	public int val_codnum;
	public String val_codalf;
		

	//
	// Constructors
	//	
	public OpcioEnqGrp(int codnum, int tipprg_codnum, int val_codnum, String val_codalf){
	
		this.setCodNum(codnum);				
		if(tipprg_codnum!=0)	this.setTipprg_codnum(tipprg_codnum);		
		if(val_codnum!=0)	this.setVal_codnum(val_codnum);
		if(val_codalf!=null)	this.setVal_codalf(val_codalf);			
		
	}
	
	public OpcioEnqGrp() {};

	//
	// Model methods
	//
	
		
	public void setCodNum ( int newVar ) {
		codnum = newVar;
	}
	public int getCodNum ( ) {  
		return codnum;
	}
	
	public void setTipprg_codnum ( int newVar ) {
		tipprg_codnum = newVar;
	}
	public int getTipprg_codnum ( ) {
		return tipprg_codnum;
	}
	
	public void setVal_codnum ( int newVar ) {
		val_codnum = newVar;
	}
	public int getVal_codnum ( ) {  
		return val_codnum;
	}
	
	public void setVal_codalf ( String newVar ) {
		val_codalf = newVar;
	}
	public String getVal_codalf ( ) {
		return val_codalf;
	}
	

}
