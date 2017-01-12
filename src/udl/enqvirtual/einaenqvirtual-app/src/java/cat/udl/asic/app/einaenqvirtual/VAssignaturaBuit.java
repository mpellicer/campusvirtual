package cat.udl.asic.app.einaenqvirtual;

import cat.udl.asic.einaenqvirtual.api.*;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlMessages;
import javax.faces.component.html.HtmlForm; //import org.sakaiproject.jsf.component.ViewComponent;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTab;
import org.apache.myfaces.custom.tabbedpane.HtmlPanelTabbedPane;
import java.util.*;
import javax.faces.el.ValueBinding;
import javax.faces.el.MethodBinding;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import java.text.*;

public class VAssignaturaBuit extends HtmlPanelTab {
	
	public void setId(String id) {
		super.setId(id);
	}
	public VAssignaturaBuit()
	{}
	
	public VAssignaturaBuit(String id,EinaEnqVirtualService s,String dni,int mod_codnum)
	{
			
			this.setId(id);
			this.setLabel(" ");
			
			// Per a buscar els missatges de sortida
			ResourceBundle messageBundle = ResourceBundle.getBundle("cat.udl.asic.app.einaenqvirtual.Messages");			
			
			
			// Recuperem els periodes de les convocatories
			// Convocatories per missatge d'error quant convocatories tancades
			List<Convocatoria> lconv = s.getConvocatoria(dni,mod_codnum);														
			
			
			HtmlPanelGrid pan = new HtmlPanelGrid();
			pan.setColumns(1);
			
			HtmlOutputText htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue(".");
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: #FFFFFF;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);
			
			if (lconv.size()==0)	// Si no te cap assignatura matriculada
			{
				
				htmlTextContestat = new HtmlOutputText(); 
				htmlTextContestat.setValue(messageBundle.getString ("noAssigMatriculades"));
				htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
				pan.getChildren().add(htmlTextContestat);
			}
			else
			{
				htmlTextContestat = new HtmlOutputText(); 
				htmlTextContestat.setValue(messageBundle.getString ("periodeTancat"));
				htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
				pan.getChildren().add(htmlTextContestat);
				
				htmlTextContestat = new HtmlOutputText(); 
				htmlTextContestat.setValue(messageBundle.getString ("periodesOberts"));
				htmlTextContestat.setStyle("font-weight: bold;font-size: 15px;color: black;font-style: italic;");
				pan.getChildren().add(htmlTextContestat);
				
				for (int i=0; i<lconv.size(); i++)
				{
					Convocatoria co = lconv.get(i);					        
			        
					htmlTextContestat = new HtmlOutputText(); 
					htmlTextContestat.setValue("- "+co.getDesid1()+": "+co.getPeriode2_ini()+" - "+co.getPeriode2_fi());
					htmlTextContestat.setStyle("font-weight: regular;font-size: 13px;color: black;font-style: italic;");
					pan.getChildren().add(htmlTextContestat);
									
				}
			}
			
			
			
			
			htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue(".");
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: #FFFFFF;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);
			
			
			this.getChildren().add(pan);
			
			
		
		
	}
}