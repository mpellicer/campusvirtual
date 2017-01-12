package cat.udl.asic.einaenqvirtual.api;

import java.util.*;

public class GrupEnqAssPrf extends Grup
{
	
	private int que_codnum;
	private List<Integer> enq_codnum;
	private String anyaca;
	private int cen_codnum;
	private int dur_codalf;
	private int ass_codnum;
	private int gas_codnum;
	private String prf_codalf;
	private String grp_desid1;
	private String dni;
	private String data;
	private String hora;
	private String aula;
	private String flg_recollit;
	private String flg_passat;
	private String flg_activat;
	private int nmat;
	private int ncontestats;
	private String flg_responsable;
	private String enviar_a;
	private String incidencies;
	private String becari;
	private int num_fulls;
	private String full_inici;
	private String full_fi;
	private String pla;
	private int credits_prf;
	private String alu_dnialu;	
	private int numord;
	private String pla_codalf;
	private int mod_codnum;
	private String centre;
	private String durada;
	private String professor;
	private String departament;
	private String assignatura;	
	private Questionari questionari;
	private List<Bloc> lbloc;
	
	public GrupEnqAssPrf(int que_codnum,List<Integer> enq_codnum,String anyaca,int cen_codnum,int dur_codalf,int ass_codnum,int gas_codnum,String prf_codalf,String grp_desid1,String dni,String data,String hora,String aula,String flg_recollit,String flg_passat,String flg_activat,int nmat,int ncontestats,String flg_responsable,String enviar_a,String incidencies,String becari,int num_fulls,String full_inici,String full_fi,String pla,int credits_prf,String alu_dnialu,int numord,String pla_codalf,int mod_codnum,String centre,String durada,String professor,String departament,String assignatura,EinaEnqVirtualService s){

		this.setQue_codnum(que_codnum);
		this.setEnq_codnum(enq_codnum);		
		if(anyaca!=null)	this.setAnyaca(anyaca);				
		if(cen_codnum!=0)	this.setCen_codnum(cen_codnum);		
		if(dur_codalf!=0)	this.setDur_codalf(dur_codalf);
		if(ass_codnum!=0)	this.setAss_codnum(ass_codnum);
		if(gas_codnum!=0)	this.setGas_codnum(gas_codnum);
		if(prf_codalf!=null)	this.setPrf_codalf(prf_codalf);
		if(grp_desid1!=null)	this.setGrp_desid1(grp_desid1);
		if(dni!=null)	this.setDni(dni);
		if(data!=null)	this.setData(data);
		if(hora!=null)	this.setHora(hora);
		if(aula!=null)	this.setAula(aula);
		if(flg_recollit!=null)	this.setFlg_recollit(flg_recollit);
		if(flg_passat!=null)	this.setFlg_passat(flg_passat);
		if(flg_activat!=null)	this.setFlg_activat(flg_activat);
		if(nmat!=0)	this.setNmat(nmat);
		if(ncontestats!=0)	this.setNcontestats(ncontestats);
		if(flg_responsable!=null)	this.setFlg_responsable(flg_responsable);
		if(enviar_a!=null)	this.setEnviar_a(enviar_a);
		if(incidencies!=null)	this.setIncidencies(incidencies);
		if(becari!=null)	this.setBecari(becari);		
		if(num_fulls!=0)	this.setNum_fulls(num_fulls);
		if(full_inici!=null)	this.setFull_inici(full_inici);
		if(full_fi!=null)	this.setFull_fi(full_fi);
		if(pla!=null)	this.setPla(pla);
		if(credits_prf!=0)	this.setCredits_prf(credits_prf);		
		if(alu_dnialu!=null)	this.setAlu_dnialu(alu_dnialu);		
		if(numord!=0)	this.setNumord(numord);		
		if(pla_codalf!=null)	this.setPla_codalf(pla_codalf);		
		if(mod_codnum!=0)	this.setMod_codnum(mod_codnum);
		if(centre!=null)	this.setCentre(centre);
		if(durada!=null)	this.setDurada(durada);
		if(professor!=null)	this.setProfessor(professor);
		if(departament!=null)	this.setDepartament(departament);
		if(assignatura!=null)	this.setAssignatura(assignatura);		
		setQuestionari(this.que_codnum,s);
		setBloc(this.que_codnum,s);
		
	}
	
	public GrupEnqAssPrf(){};
	
	//
	// Model methods
	//
	
	public void setQue_codnum ( int newVar ) {
		que_codnum = newVar;
	}
	public int getQue_codnum ( ) {  
		return que_codnum;
	}
	
	public void setEnq_codnum ( List<Integer> newVar ) {
		this.enq_codnum = newVar;
	}
	public List<Integer> getEnq_codnum ( ) {  
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
	
	public void setDur_codalf ( int newVar ) {
		dur_codalf = newVar;
	}
	public int getDur_codalf ( ) {  
		return dur_codalf;
	}
	
	public void setAss_codnum ( int newVar ) {
		ass_codnum = newVar;
	}
	public int getAss_codnum ( ) {  
		return ass_codnum;
	}	
	
	public void setGas_codnum ( int newVar ) {
		gas_codnum = newVar;
	}
	public int getGas_codnum ( ) {  
		return gas_codnum;
	}
	
	public void setPrf_codalf ( String newVar ) {
		prf_codalf = newVar;
	}
	public String getPrf_codalf ( ) {
		return prf_codalf;
	}
	
	public void setGrp_desid1 ( String newVar ) {
		grp_desid1 = newVar;
	}
	public String getGrp_desid1 ( ) {
		return grp_desid1;
	}	
	
	public void setDni ( String newVar ) {
		dni = newVar;
	}
	public String getDni ( ) {
		return dni;
	}
	
	public void setData ( String newVar ) {
		data = newVar;
	}
	public String getData ( ) {
		return data;
	}
	
	public void setHora ( String newVar ) {
		hora = newVar;
	}
	public String getHora ( ) {
		return hora;
	}
	
	public void setAula ( String newVar ) {
		aula = newVar;
	}
	public String getAula ( ) {
		return aula;
	}
	
	public void setFlg_recollit ( String newVar ) {
		flg_recollit = newVar;
	}
	public String getFlg_recollit ( ) {
		return flg_recollit;
	}
	
	public void setFlg_passat ( String newVar ) {
		flg_passat = newVar;
	}
	public String getFlg_passat ( ) {
		return flg_passat;
	}	
	
	public void setFlg_activat ( String newVar ) {
		flg_activat = newVar;
	}
	public String getFlg_activat ( ) {
		return flg_activat;
	}
	
	public void setNmat ( int newVar ) {
		nmat = newVar;
	}
	public int getNmat ( ) {  
		return nmat;
	}
	
	public void setNcontestats ( int newVar ) {
		ncontestats = newVar;
	}
	public int getNcontestats ( ) {  
		return ncontestats;
	}
	
	public void setFlg_responsable ( String newVar ) {
		flg_responsable = newVar;
	}
	public String getFlg_responsable ( ) {
		return flg_responsable;
	}
	
	public void setEnviar_a ( String newVar ) {
		enviar_a = newVar;
	}
	public String getEnviar_a ( ) {
		return enviar_a;
	}
	
	public void setIncidencies ( String newVar ) {
		incidencies = newVar;
	}
	public String getIncidencies ( ) {
		return incidencies;
	}
	
	public void setBecari ( String newVar ) {
		becari = newVar;
	}
	public String getBecari ( ) {
		return becari;
	}
	
	public void setNum_fulls ( int newVar ) {
		num_fulls = newVar;
	}
	public int getNum_fulls ( ) {  
		return num_fulls;
	}
	
	public void setFull_inici ( String newVar ) {
		full_inici = newVar;
	}
	public String getFull_inici ( ) {
		return full_inici;
	}
	
	public void setFull_fi ( String newVar ) {
		full_fi = newVar;
	}
	public String getFull_fi ( ) {
		return full_fi;
	}
	
	public void setPla ( String newVar ) {
		pla = newVar;
	}
	public String getPla ( ) {
		return pla;
	}
	
	public void setCredits_prf ( int newVar ) {
		credits_prf = newVar;
	}
	public int getCredits_prf ( ) {  
		return credits_prf;
	}
	
	public void setAlu_dnialu ( String newVar ) {
		alu_dnialu = newVar;
	}
	public String getAlu_dnialu ( ) {
		return alu_dnialu;
	}
	
	public void setNumord ( int newVar ) {
		numord = newVar;
	}
	public int getNumord ( ) {  
		return numord;
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
	
	public void setDurada ( String newVar ) {
		durada = newVar;
	}
	public String getDurada ( ) {
		return durada;
	}	
	
	public void setProfessor ( String newVar ) {
		professor = newVar;
	}
	public String getProfessor ( ) {
		return professor;
	}
	
	public void setDepartament ( String newVar ) {
		departament = newVar;
	}
	public String getDepartament ( ) {
		return departament;
	}
	
	public void setAssignatura ( String newVar ) {
		assignatura = newVar;
	}
	public String getAssignatura ( ) {
		return assignatura;
	}
	
	
	public void setQuestionari (int cod,EinaEnqVirtualService s) {
		this.questionari = s.getQuestionari(cod);
	}
	public Questionari getQuestionari (int cod,EinaEnqVirtualService s) {
			
		Questionari q = s.getQuestionari(cod);				
		return q;
	}
	public Questionari getQuestionari () {							
		return this.questionari;
	}
	
	public void setBloc (int que_codnum,EinaEnqVirtualService s) {		
		this.lbloc = s.getBlocDigitTipus(que_codnum,new String("1"),new String("05"),s);		
	}
	public List<Bloc> getBloc (int que_codnum,EinaEnqVirtualService s) {
			
		List<Bloc> lb = s.getBlocDigitTipus(que_codnum,new String("1"),new String("05"),s);						
		return lb;
	}
	public List<Bloc> getBloc () {
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