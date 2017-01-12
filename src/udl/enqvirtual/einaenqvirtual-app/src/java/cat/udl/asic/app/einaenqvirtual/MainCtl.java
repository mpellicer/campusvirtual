package cat.udl.asic.app.einaenqvirtual;
import javax.faces.event.ValueChangeEvent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.util.Locale;

import org.sakaiproject.tool.cover.ToolManager;
import org.sakaiproject.tool.api.Placement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import org.sakaiproject.tool.api.Placement;
import org.sakaiproject.site.api.ToolConfiguration;
import org.sakaiproject.site.cover.SiteService;
import java.util.*;
import cat.udl.asic.einaenqvirtual.api.*;


import org.sakaiproject.user.api.User;


public class MainCtl {


	private GetModelCtl getModelCtl;
	private String inici;
	public String s;
	public String dniP;
	public ArrayList<SelectItem> alumnes;
		
	
	public String getInici(){
	
		String model = getModelCtl.inici(dniP);
		return model;
		
	}
	
	public void setInici(String i)
	{
		this.inici = i;
	}
	
	public void setGetModelCtl(GetModelCtl g)
	{

		this.getModelCtl = g;
	}
	
	public GetModelCtl getGetModelCtl()
	{
		return this.getModelCtl;
	}

	public void setDniP(String dni)
	{
		this.dniP = dni;
	}
	
	public String getDniP()
	{
		
		return this.dniP;
	}
	
	public void setAlumnes (ArrayList<SelectItem> alumnes)
	{
		this.alumnes = alumnes;
	}
	
	public ArrayList<SelectItem> getAlumnes()
	{
		
		ArrayList<SelectItem> lAlumnes = getModelCtl.alumnes();
		return lAlumnes;
	}
	
	
		
}
