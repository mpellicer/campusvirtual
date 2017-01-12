package cat.udl.asic.einaenqvirtual.api;

import java.util.List;

public class M2Factory extends MFactory
{
	
	public Model buscarModel(String any, String dni, int model, EinaEnqVirtualService s)
	{
		return s.getModelEnqGrp(model,dni,any,s);
	}


}