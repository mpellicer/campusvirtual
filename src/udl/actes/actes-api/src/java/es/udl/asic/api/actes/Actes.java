
package es.udl.asic.api.app.actes;

import java.util.List;
import java.util.Properties;
import es.udl.asic.api.app.actes.Acta;
import es.udl.asic.api.app.actes.Qualificacions;
import javax.sql.DataSource;

/**
 * 
 * @author Alex - ASIC
 *
 * Interficie actes. Conté les definicions dels metodes del servei d'actes
 * 
 */

public interface Actes{
	
	/*Apaga el pool de connexions*/
	public void shutdown();
	
	/* Obte la llistat d'actes d'un profesor en un any i d'una assignatura*/
	public List getLlistaActes(String cod_ass,String dni,String any_acad);
	
	/*Ens retorna el dni a partir del login*/
	public String getDni(String login);
	
	/*Ens retorna la llista de linies d'acta d'una acta*/
	public List getLlistaLinies(Acta acta);
	
	/*Desa les linies modificades*/
	public boolean desaDades(List llistalinies);
	
	/*Especifica el driver de la connexions*/
	public void setDriver(String dd);
	
	/*Especifica la bd de les connexions*/
	public void setDb(String data);
	
	/*Obte la llista de qualificacion permeses*/
	public Qualificacions getQualificacions();
	
	/* Configura l'acces a la bd*/
	public DataSource setupDataSource();
}



