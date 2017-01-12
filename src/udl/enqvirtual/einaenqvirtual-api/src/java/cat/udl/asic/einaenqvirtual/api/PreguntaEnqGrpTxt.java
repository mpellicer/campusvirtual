package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class PreguntaOP
 */
public class PreguntaEnqGrpTxt extends PreguntaEnqGrp {

	//
	// Fields
	//		
	private String resposta;
	

	//
	// Constructors
	//	

	public PreguntaEnqGrpTxt(int que_codnum, int codnum, String text, int tipprg_codnum, String prg_vell, String text1, String resposta){
		
		super(que_codnum,codnum,text,tipprg_codnum,prg_vell,text1);
		if(resposta!=null)	this.setRespostaEnqGrp(resposta);
	}
	
	public PreguntaEnqGrpTxt() {};

	//
	// Model methods
	//
	
	public void setRespostaEnqGrp ( String newVar ) {
		this.resposta = newVar;
	}
	public String getRespostaEnqGrp ( ) {
		return this.resposta;
	}
	


}
