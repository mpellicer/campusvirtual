package cat.udl.asic.app.einaenqvirtual;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.util.Locale;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import cat.udl.asic.app.einaenqvirtual.GuardarBean;
import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.application.Application;



import org.apache.myfaces.custom.popup.HtmlPopup;
import java.io.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import java.lang.*;
import javax.faces.component.UIComponent;

public class GestorIncidencies implements ActionListener {
		
	public static Assignatura assig;	
	public static EinaEnqVirtualService s;	
	public static String dni;
	
	private String bundleId = null;
	
	
	
	public void omplirParametre(Assignatura assig,EinaEnqVirtualService s,String dni)
	{
		this.assig=assig;					
		this.s=s;
		this.dni=dni;
	}
	
	public void processAction(ActionEvent event) 
	{
		// Recuperem el id del boto per saber en quina pestanya estem
		String boto = event.getComponent().getId();

//		s.EnviarMail();
		
	}
	
}