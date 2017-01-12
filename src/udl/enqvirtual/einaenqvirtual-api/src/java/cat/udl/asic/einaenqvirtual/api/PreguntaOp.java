package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class PreguntaOP
 */
public class PreguntaOp extends Pregunta {

	//
	// Fields
	//		
	private List<Opcio> lopcio;
	

	//
	// Constructors
	//	

	public PreguntaOp(int que_codnum, int codnum, String text, int ordre, int tipprg_codnum, String prg_vell, String text1, EinaEnqVirtualService s){
		
		super(que_codnum,codnum,text,ordre,tipprg_codnum,prg_vell,text1);
		setOpcio(tipprg_codnum,s);
	}
	
	public PreguntaOp() {};

	//
	// Model methods
	//
	
	
	public void setOpcio (int tipprg_codnum,EinaEnqVirtualService s) {
		this.lopcio = s.getOpcio(tipprg_codnum);
	}
	public List<Opcio> getOpcio (int tipprg_codnum,EinaEnqVirtualService s) {			
		List<Opcio> lop = s.getOpcio(tipprg_codnum);				
		return lop;
	}
	public List<Opcio> getOpcio () {								
		return this.lopcio;
	}


}
