package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Bloc
 */
public class BlocEnqGrp {

	//
	// Fields
	//
	
	private int codnum;
	private int que_codnum;
	private String desid1;
	private String desid2;
	private String desid3;	
	private String tipbloc;	
	private List<PreguntaEnqGrp> lpregunta;

	//
	// Constructors
	//	
	public BlocEnqGrp(int codnum, int que_codnum, String desid1, String desid2, String desid3, String tipbloc, EinaEnqVirtualService s){

		this.setCodNum(codnum);
		this.setQue_CodNum(que_codnum);
		if(desid1!=null)	this.setDesid1(desid1);		
		if(desid2!=null)	this.setDesid2(desid2);
		if(desid3!=null)	this.setDesid3(desid3);			
		if(tipbloc!=null)	this.setTipbloc(tipbloc);		
		setPreguntaEnqGrp(this.que_codnum,this.codnum, s);
	}
	
	public BlocEnqGrp() {};

	//
	// Model methods
	//
	
	public void setCodNum ( int newVar ) {
		codnum = newVar;
	}
	public int getCodNum ( ) {  
		return codnum;
	}
	
	public void setQue_CodNum ( int newVar ) {
		que_codnum = newVar;
	}
	public int getQue_CodNum ( ) {  
		return que_codnum;
	}
	
	public void setDesid1 ( String newVar ) {
		desid1 = newVar;
	}
	public String getDesid1 ( ) {
		return desid1;
	}
	
	public void setDesid2 ( String newVar ) {
		desid2 = newVar;
	}
	public String getDesid2 ( ) {
		return desid2;
	}	
	
	public void setDesid3 ( String newVar ) {
		desid3 = newVar;
	}
	public String getDesid3 ( ) {
		return desid3;
	}		
	
	public void setTipbloc ( String newVar ) {
		tipbloc = newVar;
	}
	public String getTipbloc ( ) {  
		return tipbloc;
	}	

	public void setPreguntaEnqGrp (int que_codnum,int codnum, EinaEnqVirtualService s) {					
		this.lpregunta = s.getPreguntaEnqGrp(que_codnum,codnum,s);
	}
	public List<PreguntaEnqGrp> getPreguntaEnqGrp (int que_codnum,int codnum,EinaEnqVirtualService s) {				
		List<PreguntaEnqGrp> lp = s.getPreguntaEnqGrp(que_codnum,codnum,s);				
		return lp;
	}
	public List<PreguntaEnqGrp> getPreguntaEnqGrp () {							
		return this.lpregunta;
	}

}
