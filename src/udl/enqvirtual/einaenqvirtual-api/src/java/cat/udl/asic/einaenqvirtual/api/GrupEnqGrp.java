package cat.udl.asic.einaenqvirtual.api;

import java.util.*;

public class GrupEnqGrp extends Grup
{
		
	private int que_codnum;
	private int enq_codnum;
	private String anyaca;
	private int cen_codnum;
	private String incidencies;
	private String alu_dnialu;
	private String pla_codalf;		
	private int mod_codnum;
	private String centre;
	private String nompla;
	private QuestionariEnqGrp questionari;
	private List<BlocEnqGrp> lbloc;
	
	public GrupEnqGrp(String alu_dnialu,String pla_codalf,int mod_codnum,String anyaca,int cen_codnum,int que_codnum,int enq_codnum,String incidencies,String centre,String nompla,EinaEnqVirtualService s){

		this.setQue_codnum(que_codnum);
		this.setEnq_codnum(enq_codnum);		
		if(anyaca!=null)	this.setAnyaca(anyaca);				
		if(cen_codnum!=0)	this.setCen_codnum(cen_codnum);					
		if(incidencies!=null)	this.setIncidencies(incidencies);						
		if(alu_dnialu!=null)	this.setAlu_dnialu(alu_dnialu);
		if(pla_codalf!=null)	this.setPla_codalf(pla_codalf);		
		if(mod_codnum!=0)	this.setMod_codnum(mod_codnum);
		if(centre!=null)	this.setCentre(centre);				
		if(nompla!=null)	this.setNom_pla(nompla);
		setQuestionariEnqGrp(this.que_codnum,s);
		setBlocEnqGrp(this.que_codnum,s);
		
	}
	
	public GrupEnqGrp(){};
	
	//
	// Model methods
	//
	
	public void setQue_codnum ( int newVar ) {
		que_codnum = newVar;
	}
	public int getQue_codnum ( ) {  
		return que_codnum;
	}
	
	public void setEnq_codnum ( int newVar ) {
		this.enq_codnum = newVar;
	}
	public int getEnq_codnum ( ) {  
		return this.enq_codnum;
	}
	
	public void setAnyaca ( String newVar ) {
		anyaca = newVar;
	}
	public String getAnyaca ( ) {
		return anyaca;
	}
	
	public void setCen_codnum ( int newVar ) {
		cen_codnum = newVar;
	}
	public int getCen_codnum ( ) {  
		return cen_codnum;
	}		
	
	public void setIncidencies ( String newVar ) {
		incidencies = newVar;
	}
	public String getIncidencies ( ) {
		return incidencies;
	}
		
	public void setAlu_dnialu ( String newVar ) {
		alu_dnialu = newVar;
	}
	public String getAlu_dnialu ( ) {
		return alu_dnialu;
	}
		
	public void setPla_codalf ( String newVar ) {
		pla_codalf = newVar;
	}
	public String getPla_codalf ( ) {
		return pla_codalf;
	}
			
	public void setMod_codnum ( int newVar ) {
		mod_codnum = newVar;
	}
	public int getMod_codnum ( ) {  
		return mod_codnum;
	}		
	
	public void setCentre ( String newVar ) {
		centre = newVar;
	}
	public String getCentre ( ) {
		return centre;
	}
	
	public void setNom_pla ( String newVar ) {
		nompla = newVar;
	}
	public String getNom_pla ( ) {
		return nompla;
	}
	
	
	public void setQuestionariEnqGrp (int cod,EinaEnqVirtualService s) {
		this.questionari = s.getQuestionariEnqGrp(cod);
	}
	public QuestionariEnqGrp getQuestionariEnqGrp (int cod,EinaEnqVirtualService s) {
			
		QuestionariEnqGrp q = s.getQuestionariEnqGrp(cod);				
		return q;
	}
	public QuestionariEnqGrp getQuestionariEnqGrp () {							
		return this.questionari;
	}
	
	
	
	public void setBlocEnqGrp (int que_codnum,EinaEnqVirtualService s) {		
		this.lbloc = s.getBlocEnqGrp(que_codnum,s);		
	}
	public List<BlocEnqGrp> getBlocEnqGrp (int que_codnum,EinaEnqVirtualService s) {
			
		List<BlocEnqGrp> lb = s.getBlocEnqGrp(que_codnum,s);						
		return lb;
	}
	public List<BlocEnqGrp> getBlocEnqGrp () {
		return this.lbloc;
	}
			

	
	
	
	
	
	
	public int getCodnum(){
		return -1;
	}
	public void setCodnum(int val){}
	public List getPreguntes(){
		return null;
	}
	public void setPreguntes(List val){
	}
	
	
	
		
	
	
	
	
}