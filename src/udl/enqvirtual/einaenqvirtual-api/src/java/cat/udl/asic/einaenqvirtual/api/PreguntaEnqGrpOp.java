package cat.udl.asic.einaenqvirtual.api;


import java.util.*;


/**
 * Class PreguntaOP
 */
public class PreguntaEnqGrpOp extends PreguntaEnqGrp {

	//
	// Fields
	//		
	private List<OpcioEnqGrp> lopcio;
	

	//
	// Constructors
	//	

	public PreguntaEnqGrpOp(int que_codnum, int codnum, String text, int tipprg_codnum, String prg_vell, String text1, EinaEnqVirtualService s){
		
		super(que_codnum,codnum,text,tipprg_codnum,prg_vell,text1);
		setOpcioEnqGrp(tipprg_codnum,s);
	}
	
	public PreguntaEnqGrpOp() {};

	//
	// Model methods
	//
	
	
	public void setOpcioEnqGrp (int tipprg_codnum,EinaEnqVirtualService s) {
		this.lopcio = s.getOpcioEnqGrp(tipprg_codnum);
	}
	public List<OpcioEnqGrp> getOpcioEnqGrp (int tipprg_codnum,EinaEnqVirtualService s) {			
		List<OpcioEnqGrp> lop = s.getOpcioEnqGrp(tipprg_codnum);				
		return lop;
	}
	public List<OpcioEnqGrp> getOpcioEnqGrp () {								
		return this.lopcio;
	}


}
