package es.udl.asic.api.app.directori;


public class RegistreActiva{
	
	private String dni;
	private String login;
	private String codiMatricula;
	
	public String getDni(){
		return dni;
	}
	
	public String getLogin(){
		return login;
	}
	public String getCodiMatricula (){
		return codiMatricula;
	}
	
	public void setDni(String dni){
		this.dni = dni;
	}
	
	public void setLogin (String login){
		this.login = login;
	}
	
	public void setCodiMatricula (String codiMatricula){
		this.codiMatricula = codiMatricula;
	}
	
}