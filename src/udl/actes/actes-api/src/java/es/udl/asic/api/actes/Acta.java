package es.udl.asic.api.app.actes;

import java.util.List;
import java.util.Properties;

/**
 * @author Alex - ASIC
 * Aquesta classe conté l'estructura d'informació d'una acta
 */

public class Acta{
	String 	anyaca; 	//Any academic
	int 	ass_codnum; //Codi assignatura
	String 	nom_ass;	//Nom assignatura
	int 	gas_codnum;	//Codi del grup
	String 	nom_gas;	//Nom del grup
	int 	numord;		//Numer ordre
	String 	dniprf;		//Dni professor
	String 	tco_codalf;	//Codi de convocatoria
	String 	nomtco;		//Nom convocatoria 
	
	public Acta(){
		
	}
	
	/*Constructor amb tots els parametres*/
	
	public Acta(String anyaca, int ass_codnum, String nom_ass, int gas_codnum, String nom_gas, int numord, String dniprf,String tco_codalf,String nomtco) {
		super();
		// TODO Auto-generated constructor stub
		this.anyaca = anyaca;
		this.ass_codnum = ass_codnum;
		this.nom_ass = nom_ass;
		this.gas_codnum = gas_codnum;
		this.nom_gas = nom_gas;
		this.numord = numord;
		this.dniprf = dniprf;
		this.tco_codalf = tco_codalf;
		this.nomtco  = nomtco;
	}


	/* Conjunt de getters i setters per a obtindre les propietats*/
		
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


	public String getDniprf() {
		return dniprf;
	}


	public void setDniprf(String dniprf) {
		this.dniprf = dniprf;
	}


	public int getGas_codnum() {
		return gas_codnum;
	}


	public void setGas_codnum(int gas_codnum) {
		this.gas_codnum = gas_codnum;
	}


	public String getNom_ass() {
		return nom_ass;
	}


	public void setNom_ass(String nom_ass) {
		this.nom_ass = nom_ass;
	}


	public String getNom_gas() {
		return nom_gas;
	}


	public void setNom_gas(String nom_gas) {
		this.nom_gas = nom_gas;
	}


	public int getNumord() {
		return numord;
	}


	public void setNumord(int numord) {
		this.numord = numord;
	}

	public String getTco_codalf() {
		return tco_codalf;
	}


	public void setTco_codalf(String tco_codalf) {
		this.tco_codalf = tco_codalf;
	}
	
	public String getNomtco() {
		return nomtco;
	}

	public void setNomtco(String nomtco) {
		this.nomtco = nomtco;
	}
	
	public String actaCompleta(){
		return "";
	}

	
}