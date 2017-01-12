package cat.udl.asic.einaenqvirtual.api;

import java.util.*;
import java.lang.*;

public class GrupAssignatura extends Grup
{
	private int que_codnum;
	private int ass_codnum;
	private List<Integer> enq_codnum;
	private List<Bloc> lbloc;
	private String dni;
	
		
	
	public GrupAssignatura(int ass_codnum,EinaEnqVirtualService s,String dni,String anyaca,int model)
	{
		this.setEnq_codnum(s,dni,anyaca,ass_codnum,model);
		if(ass_codnum!=0)	this.setAss_codnum(ass_codnum);
		this.setQue_codnum(s,dni,anyaca,ass_codnum,model);
		setBloc(this.que_codnum,s);
		
				
	}
	
	public GrupAssignatura(){};
	
	//
	// Model methods
	//
	
	
	
	
	public int getQue_codnum ( ) {  
		return que_codnum;
	}
	
	public void setQue_codnum (EinaEnqVirtualService s,String dni,String anyaca,int ass_codnum,int model) {
		
		
		List<Integer> codnum = s.getGrupAssignatura1(dni,anyaca,ass_codnum,model,s);
		this.que_codnum = codnum.get(0);
	}
	
	public void setEnq_codnum (EinaEnqVirtualService s,String dni,String anyaca,int ass_codnum,int model) {
		
		this.enq_codnum = s.getGrupAssignatura(dni,anyaca,ass_codnum,model,s);
		
	}

	public List<Integer> getEnq_codnum ( ) {  
		return enq_codnum;
	}

	public void setAss_codnum ( int newVar ) {
		ass_codnum = newVar;
	}
	
	public int getAss_codnum ( ) {  
		return ass_codnum;
	}	
	
	public void setDni ( String newVar ) {
		dni = newVar;
	}
	
	public String getDni ( ) {  
		return dni;
	}
					
	public void setBloc (int que_codnum,EinaEnqVirtualService s) {
		this.lbloc = s.getBlocDigitTipus(que_codnum,new String("2"),new String("25"),s);
	}
	
	public List<Bloc> getBloc (int que_codnum,EinaEnqVirtualService s) {
			
		List<Bloc> lb = s.getBlocDigitTipus(que_codnum,new String("2"),new String("25"),s);				
		return lb;
	}
	public List<Bloc> getBloc () {
		return this.lbloc;
	}

	public int getCodnum(){
		return -1;
	}
	
	public void setCodnum(int val){}
	
	public List getPreguntes(){		return null;    }
	
	public void setPreguntes(List val){}
	
	
	
		
	
	
	
	
}