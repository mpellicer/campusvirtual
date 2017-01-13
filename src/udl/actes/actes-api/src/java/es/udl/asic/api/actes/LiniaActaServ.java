package es.udl.asic.api.app.actes;

/**
 * 
 * @author ASIC - Alex
 *
 *	Aquesta classe conte les dades necessaries d'una linia d'acta
 */

public class LiniaActaServ{
	
	private
		String 	anyaca;			//Any academic		
		String 	tco_codalf;		//Codi convocatoria	
		int 	ass_codnum;		//Codi assignatura
		int 	gas_codnum;		//Codi grup
		int 	act_numord;		//Numero d'ordre
		String 	dnialu;			//Dni de l'alumne
		String 	nomalu;			//Nom de l'alumne
		String 	pla_codalf;		//Codi del pla
		String 	nompla;			//Nom del pla
		String 	qua_codalf;		//Codi de la qualificacio
		String 	quanum;			//Nota de la linia
		String 	exp_numord; 	//Millor canviar-ho a double
		String 	numcon;			//Numer de convocatoria
		String 	nomtas;			//	Altres dades necessaries per
		String	tqu_codalf;		//	desar els canvis de la linia
		String 	pac_codnum;		//
		String 	pla_codalfmat;	//
		
		boolean inc;			// Indica si les dades de la linia son
								// incorrectes
		String 	msgerror="";	//Missatge que indica el tipus d'error.
								//Es necessari tenir-ho aqui ja que també 
								//recullen errors capturats per la Bd
	
	
	/* Constructor amb els parametres */
	public LiniaActaServ(String anyaca, String tco_codalf, int ass_codnum, int gas_codnum, int act_numord, String dnialu, String nomalu, String pla_codalf, String nompla, String qua_codalf, String quanum, String exp_numord, String numcon, String nomtas,String tqu_codalf,String pac_codnum,String pla_codalfmat) {
		super();
 
		this.anyaca = anyaca;
		this.tco_codalf = tco_codalf;
		this.ass_codnum = ass_codnum;
		this.gas_codnum = gas_codnum;
		this.act_numord = act_numord;
		this.dnialu = dnialu;
		this.nomalu = nomalu;
		this.pla_codalf = pla_codalf;
		this.nompla = nompla;
		this.qua_codalf = qua_codalf;
		this.quanum = quanum;
		this.exp_numord = exp_numord;
		this.numcon = numcon;
		this.nomtas = nomtas;
		this.tqu_codalf = tqu_codalf;
		this.pac_codnum = pac_codnum;
		this.pla_codalfmat = pla_codalfmat;
		inc = false;
	}
	
	
	/* Conjunt de getters i setters*/
	
	public int getAct_numord() {
		return act_numord;
	}
	public void setAct_numord(int act_numord) {
		this.act_numord = act_numord;
	}
	public String getAnyaca() {
		return anyaca;
	}
	public void setAnyaca(String anyaca) {
		this.anyaca = anyaca;
	}
	public int getAss_codnum() {
		return ass_codnum;
	}
	public void setAss_codnum(int ass_codnum) {
		this.ass_codnum = ass_codnum;
	}
	public String getDnialu() {
		return dnialu;
	}
	public void setDnialu(String dnialu) {
		this.dnialu = dnialu;
	}
	public String getExp_numord() {
		return exp_numord;
	}
	public void setExp_numord(String exp_numord) {
		this.exp_numord = exp_numord;
	}
	public int getGas_codnum() {
		return gas_codnum;
	}
	public void setGas_codnum(int gas_codnum) {
		this.gas_codnum = gas_codnum;
	}
	public String getNomalu() {
		return nomalu;
	}
	public void setNomalu(String nomalu) {
		this.nomalu = nomalu;
	}
	public String getNompla() {
		return nompla;
	}
	public void setNompla(String nompla) {
		this.nompla = nompla;
	}
	public String getNomtas() {
		return nomtas;
	}
	public void setNomtas(String nomtas) {
		this.nomtas = nomtas;
	}
	public String getNumcon() {
		return numcon;
	}
	public void setNumcon(String numcon) {
		this.numcon = numcon;
	}
	public String getPla_codalf() {
		return pla_codalf;
	}
	public void setPla_codalf(String pla_codalf) {
		this.pla_codalf = pla_codalf;
	}
	public String getQua_codalf() {
		return qua_codalf;
	}
	public void setQua_codalf(String qua_codalf) {
		this.qua_codalf = qua_codalf;
	}
	public String getQuanum() {
		return quanum;
	}
	public void setQuanum(String quanum) {
		this.quanum = quanum;
	}
	public String getTco_codalf() {
		return tco_codalf;
	}
	public void setTco_codalf(String tco_codalf) {
		this.tco_codalf = tco_codalf;
	}
	
	public void setTqu_codalf(String tqu_codalf){
		this.tqu_codalf = tqu_codalf;
	}
	
	public String getTqu_codalf(){
		return tqu_codalf;
	}
	
	public void setPac_codnum(String pac_codnum){
		this.pac_codnum = pac_codnum;
	}
	
	public String getPac_codnum(){
		return pac_codnum;
	}
	
	public void setPla_codalfmat(String pla_codalfmat){
		this.pla_codalfmat = pla_codalfmat;
	}
	
	public String getPla_codalfmat(){
		return pla_codalfmat;
	}
	
	public boolean getInc(){
		return inc;
	}
	public void setInc(boolean inc){
		this.inc = inc;
	}
	public String getMsgerror(){
		return msgerror;
	}
	public void setMsgerror(String msgerror){
		this.msgerror = msgerror;
	}
	
}