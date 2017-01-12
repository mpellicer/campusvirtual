package cat.udl.asic.einaenqvirtual.api;

import java.util.*;
import java.io.*;


public class Property {

	
	public static Properties sysProperties;
	
	public Property (){}

	public void SetConfiguration(String InitParameter){
				
		try{
			
			String configPath = InitParameter;			
			sysProperties = new Properties();			
			sysProperties.load(new FileInputStream(configPath+"/system.properties"));
		}
		catch(IOException ex){	ex.printStackTrace();   }
		
		
	}
	
	
	
	
}