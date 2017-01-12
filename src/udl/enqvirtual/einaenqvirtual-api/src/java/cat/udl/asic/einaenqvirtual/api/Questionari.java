package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class Questionari
 */
public class Questionari {

	//
	// Fields
	//
	
	private int codnum;
	private String desid1;
	private String desid2;
	private String desid3;	
	private int ordre;
	private String visible;	

	//
	// Constructors
	//
	


	public Questionari(int codnum, String desid1, String desid2, String desid3, int ordre, String visible){

		this.setCodNum(codnum);
		if(desid1!=null)	this.setDesid1(desid1);		
		if(desid2!=null)	this.setDesid2(desid2);
		if(desid3!=null)	this.setDesid3(desid3);
		if(ordre!=0)	this.setOrdre(ordre);		
		if(visible!=null)	this.setVisible(visible);	  
	}
	
	public Questionari () {
		codnum = 0;		
		desid1 = "";
		desid2 = "";
		desid3 = "";
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


}
