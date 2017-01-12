package cat.udl.asic.app.einaenqvirtual;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class GestorEvents implements ValueChangeListener {
	public void processValueChange(ValueChangeEvent event)
	{		
		//TODO Trobar els altres radiobutton que son a la mateixa assignatura i linea
		
		//TODO Buscar els components radiobuttons trobats i canviar el valor
		//FacesContext.getCurrentInstance().getViewRoot().findComponent("a0gX")
		
		 
		
		FacesContext.getCurrentInstance().renderResponse();
	}
}