package cat.udl.asic.einaenqvirtual.api;

import java.util.List;


public class M3Factory extends MFactory
{
	
	
	//public GrupM3 grup;
	
	

	public Model buscarModel(String any, String dni, int model, EinaEnqVirtualService s)
	{
		return s.getModel(model,dni,any,s);
	}

	
		
	
}