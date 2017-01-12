package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public abstract class Pregunta {	 
	//
	// Fields
	//	
	private int que_codnum;
	private int codnum;	
	private String text;
	private int tipprg_codnum;
	private String prg_vell;
	private String text1;	
	private int ordre;
//	private List<Opcio> lopcio;
	

	//
	// Constructors
	//	

	public Pregunta(int que_codnum, int codnum, String text, int ordre,int tipprg_codnum, String prg_vell, String text1){

		this.setQue_CodNum(que_codnum);
		this.setCodNum(codnum);
		this.setOrdre(ordre);
		if(text!=null)	this.setText(text);
		if(tipprg_codnum!=0)	this.setTipprg_codnum(tipprg_codnum);		
		if(prg_vell!=null)	this.setPrg_vell(prg_vell);			
		if(text1!=null)	this.setText1(text1);
//		setOpcio(this.tipprg_codnum,s);
	}
	
	public Pregunta() {};

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
		
	public void setOrdre ( int newVar ) {
		ordre = newVar;
	}
	public int getOrdre ( ) {  
		return ordre;
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
	
/*	public void setOpcio (int tipprg_codnum,EinaEnqVirtualService s) {
		this.lopcio = s.getOpcio(tipprg_codnum);
	}
	public List<Opcio> getOpcio (int tipprg_codnum,EinaEnqVirtualService s) {			
		List<Opcio> lop = s.getOpcio(tipprg_codnum);				
		return lop;
	}
	public List<Opcio> getOpcio () {								
		return this.lopcio;
	}
*/

}
