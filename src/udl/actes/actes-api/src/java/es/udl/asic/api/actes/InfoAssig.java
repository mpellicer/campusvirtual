package es.udl.asic.api.app.actes;

import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 
 * @author Alex - ASIC
 *
 * Aquesta classe ens serveix per transforma codis d'assignatura en 
 * el codi de l'assignatura i el any academic. 
 */

public class InfoAssig{
	
	private 
		String codi=""; //Codi de l'assignatura 
		String anyaca=""; //Any academic
		String expresio=".*-[0-9][0-9][0-9][0-9]"; //Patró per comprovar que el format es correcte
	

	/*Costructor */	
	public InfoAssig(String identificador){
		
		Pattern r= Pattern.compile(expresio); // Patró (Expresió regular)
		
		if (r.matcher(identificador).matches()){ //Analitza el contingut del camp
			int index = identificador.indexOf("-");
			codi = identificador.substring(0,index);
			//D'aqui a 100 any haurem de revisar l'aplicacio
			anyaca = "20"  + identificador.substring (index+1,index+3) + "-" 
							+ identificador.substring(index+3,index+5);
		
		}
	}
	
	
	/*Getters de les propietats*/
	
	public String getCodi(){
		return codi;
	}
	
	public String getAnyaca(){
		return anyaca;
	}
	
}