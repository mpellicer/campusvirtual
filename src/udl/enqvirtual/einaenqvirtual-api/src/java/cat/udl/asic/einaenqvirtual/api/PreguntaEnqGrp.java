package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public abstract class PreguntaEnqGrp {	 
	//
	// Fields
	//	
	private int que_codnum;
	private int codnum;	
	private String text;
	private int tipprg_codnum;
	private String prg_vell;
	private String text1;	


	//
	// Constructors
	//	

	public PreguntaEnqGrp(int que_codnum, int codnum, String text, int tipprg_codnum, String prg_vell, String text1){

		this.setQue_CodNum(que_codnum);
		this.setCodNum(codnum);		
		if(text!=null)	this.setText(text);
		if(tipprg_codnum!=0)	this.setTipprg_codnum(tipprg_codnum);		
		if(prg_vell!=null)	this.setPrg_vell(prg_vell);			
		if(text1!=null)	this.setText1(text1);

	}
	
	public PreguntaEnqGrp() {};

	//
	// Model methods
	//
	
	public void setQue_CodNum ( int newVar ) {
		que_codnum = newVar;
	}
	public int getQue_CodNum ( ) {  
		return que_codnum;
	}
	
	public void setCodNum ( int newVar ) {
		codnum = newVar;
	}
	public int getCodNum ( ) {  
		return codnum;
	}
	
	public void setText ( String newVar ) {
		text = newVar;
	}
	public String getText ( ) {
		return text;
	}
	
	public void setTipprg_codnum ( int newVar ) {
		tipprg_codnum = newVar;
	}
	public int getTipprg_codnum ( ) {
		return tipprg_codnum;
	}	
	
	public void setPrg_vell ( String newVar ) {
		prg_vell = newVar;
	}
	public String getPrg_vell ( ) {
		return prg_vell;
	}		
	
	public void setText1 ( String newVar ) {
		text1 = newVar;
	}
	public String getText1 ( ) {  
		return text1;
	}	
	


}
