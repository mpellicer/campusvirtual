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

public class VGrupEnqGrpBuit extends HtmlPanelTab {
	
	public void setId(String id) {
		super.setId(id);
	}
	public VGrupEnqGrpBuit()
	{}
	
	public VGrupEnqGrpBuit(String id)
	{
			
			// Per a buscar els missatges de sortida
			ResourceBundle messageBundle = ResourceBundle.getBundle("cat.udl.asic.app.einaenqvirtual.Messages");
			
		
			this.setId(id);
			this.setLabel(" ");
			
			HtmlPanelGrid pan = new HtmlPanelGrid();
			pan.setColumns(1);
			
			HtmlOutputText htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue("");			
			pan.getChildren().add(htmlTextContestat);
			
			
			htmlTextContestat = new HtmlOutputText(); 
			htmlTextContestat.setValue(messageBundle.getString ("noGrupsAssignats"));
			htmlTextContestat.setStyle("font-weight: bold;font-size: 17px;color: red;font-style: italic;");
			pan.getChildren().add(htmlTextContestat);
			
			
			this.getChildren().add(pan);
			
			
		
		
	}
}