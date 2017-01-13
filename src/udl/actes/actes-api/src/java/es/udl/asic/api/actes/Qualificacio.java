package es.udl.asic.api.app.actes;


import java.util.prefs.InvalidPreferencesFormatException;
import java.util.Comparator;
import java.util.*;

/**
 * 
 * @author ASIC - alex
 *
 *	Classe que determina l'estructura d'una qualificacio
 */

public class Qualificacio  {
		
		private 
			String 	id=""; 		//Identificador de la qualificacio
			String 	nom="";		//Nom de la qualificacio
			String 	minim="";	//Llinda inferior
			String 	maxim="";	//Llinda superior
			boolean	req;		//Requereix nota numerica
			

		/*Constructor amb els parametres*/
			
		public Qualificacio(String id,String nom,String minim,String maxim,String req){
			this.id = id;
			this.nom =nom;
			this.minim = minim;
			this.maxim = maxim;
				
			if (req.equals("S"))
				this.req = true;
			else
				this.req = false;
			}	
			
		/* Getter id */	
		public String getId(){
			return id;
		}
		
		/* Getter nom*/
		public String getNom(){
			return nom;
		}
		
		/* Comprova que la qualificacio te un rang */
		
		public boolean terang(){
			if (minim.equals("") && maxim.equals("")){
				return false;
			}else 
				return true;
		}
		
		/* Comprova que la qualificacio requereix nota numerica*/
		
		public boolean requereixNota(){
			return req;
		}
		
		/* Comprova que la nota es trova dins del rang*/
		
		public boolean dinsRang(String nota){
			double min = 0.0;
			double max = 0.0;
			double not = 0.0;
			
			if (terang()){ // Si te rang ho comprovem
				try{//Intentem convertir els marges del rang i la nota
					min = Double.valueOf(minim);
					max = Double.valueOf(maxim);
					not = Double.valueOf(nota);
				}catch (Exception ex){
					System.out.println("No he pogut convertir els valors dels rangs");
				}

				if (min <= not && not <= max){
					return true;
				}
				else 
					return false;
				
			}
			else{ // Si no te rang aleshores la nota sempre esta dins del rang
				return true;
			}
		}
	}
