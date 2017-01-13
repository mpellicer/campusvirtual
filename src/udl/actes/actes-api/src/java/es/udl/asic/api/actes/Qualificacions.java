package es.udl.asic.api.app.actes;

import java.util.prefs.InvalidPreferencesFormatException;
import java.util.Comparator;
import java.util.*;
import es.udl.asic.api.app.actes.Qualificacio;
/**
 * 
 * @author ASIC - alex
 * Classe que ens servira per gestionar les qualificacions
 *
 */


public class Qualificacions{
	
	private List qualificacions = null; //Llista amb totes les qualifiacions 
	
	/* Creem la llista i inserim la qualificacio buida */
	
	public void inicialitzaLlista(){
		//L'emplenem de moment a mà
		qualificacions = new ArrayList();
		qualificacions.add(new Qualificacio("","","","","N"));
	}
	
	/* 
	 * Ens retorna l'iterador de les qualificacions per poder
	 * imprimir llistats de qualificacions en capes superiors 
	 */
	
	
	public Iterator getIterator(){
		return qualificacions.iterator();
	}
	
	/* Obte la qualificacio a partir de la nota */
	
	public Qualificacio getQualificacio(String nota){
		Iterator it = qualificacions.iterator();
		Qualificacio quali = null;		
		String acuadrar ="";
		
		while (it.hasNext()){
			quali = (Qualificacio) it.next();
			if (quali.terang())
				if (quali.dinsRang(nota))
					return quali;
		}
		
		return null;
	}
	
	/* Insereix una qualificacio dins de la llista de qualificacions
	 * 	st1 = identificador
	 * 	st2 = nom qualificacio
	 * 	st3 = Llinda superior
	 * 	st5 = Llinda indferior
	 * */
	
	public void afergirQuali (String st1,String st2,String st3,String st4,String req){
		if (st3==null)
			st3 = "";
		if (st4==null)
			st4="";
		
		qualificacions.add(new Qualificacio(st1,st2,st3,st4,req));
	}
	
	/* Donat l'id retornem la qualificacio associada*/
	
	public Qualificacio getQualificacioPerId(String ident){
		Iterator it = qualificacions.iterator();
		Qualificacio quali = null;		
		String acuadrar ="";
		
		while (it.hasNext() && !acuadrar.equals(ident)){
			quali = (Qualificacio) it.next();
			acuadrar = quali.getId();

		}
	
		if (acuadrar.equals(ident)){
			//System.out.println("Retorno la seguent qualificacio:" + quali.getNom());
			return quali;
		}
		else{
			//System.out.println("Retorno la seguent qualificacio: null");
			return null;
		}
	}
}