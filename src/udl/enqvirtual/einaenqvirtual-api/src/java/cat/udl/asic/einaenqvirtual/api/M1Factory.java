package cat.udl.asic.einaenqvirtual.api;

import java.util.List;
import java.util.*;


public class M1Factory extends MFactory
{
	
	
	//public GrupM1 grup;
	public int mod_codnum;
	
	

	public Model buscarModel(String any, String dni, int model, EinaEnqVirtualService s)
	{		
		return s.getModel(model,dni,any,s);
		
	}

	
		
	
}