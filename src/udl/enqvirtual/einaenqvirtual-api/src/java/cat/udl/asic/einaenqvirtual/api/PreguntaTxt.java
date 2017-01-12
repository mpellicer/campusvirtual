package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class PreguntaOP
 */
public class PreguntaTxt extends Pregunta {

	//
	// Fields
	//		
	private String resposta;
	

	//
	// Constructors
	//	

	public PreguntaTxt(int que_codnum, int codnum, String text, int ordre, int tipprg_codnum, String prg_vell, String text1, String resposta){
		
		super(que_codnum,codnum,text,ordre, tipprg_codnum,prg_vell,text1);
		if(resposta!=null)	this.setResposta(resposta);
	}
	
	public PreguntaTxt() {};

	//
	// Model methods
	//
	
	public void setResposta ( String newVar ) {
		this.resposta = newVar;
	}
	public String getResposta ( ) {
		return this.resposta;
	}
	


}
