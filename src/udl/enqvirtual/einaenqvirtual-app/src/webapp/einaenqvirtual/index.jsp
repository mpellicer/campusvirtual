<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ page session="true" contentType="text/html" language="java" import="cat.udl.asic.einaenqvirtual.api.*,javax.faces.component.UIViewRoot,javax.faces.context.FacesContext,javax.faces.application.Application, es.udl.asic.sakaiproject.service.dppa.base.*, java.io.*, java.util.*, java.lang.*, javax.servlet.*, javax.servlet.jsp.*, javax.servlet.http.*, org.sakaiproject.tool.api.Placement, org.sakaiproject.tool.cover.ToolManager" %>

<%
	FacesContext facesContext = FacesContext.getCurrentInstance();
	Application app = facesContext.getApplication();
	String contacta = (String)app.createValueBinding("#{GetModelCtl.contacta}").getValue(facesContext);
	
	
	Property prop = new Property();
		ServletContext context= pageContext.getServletContext();
		//Enumeration e = context.getInitParameterNames();
		//System.out.println("initParameter="+context.getRealPath(context.getInitParameter("contextConfigLocation")).substring(1,context.getRealPath(context.getInitParameter("contextConfigLocation")).lastIndexOf('/')+1));
		prop.SetConfiguration(context.getRealPath(context.getInitParameter("contextConfigLocation")).substring(0,context.getRealPath(context.getInitParameter("contextConfigLocation")).lastIndexOf('/')+1)+"conf");
		//prop.SetConfiguration(context.getRealPath(context.getInitParameter("contextConfigLocation")));
	

%>
<f:loadBundle basename="cat.udl.asic.app.einaenqvirtual.Messages" var="msgs"/>

<f:view>


<t:document>
<t:documentHead>


<style type="text/css">
	A.lnk:LINK {FONT-STYLE: italic; FONT-WEIGHT:bold; FONT-SIZE: 17px; FONT-FAMILY:Verdana,Helvetica; COLOR: #8E3F6C; text-decoration:none;}
	A.lnk:VISITED {FONT-STYLE: italic; FONT-WEIGHT:bold; FONT-SIZE: 17px; FONT-FAMILY:Verdana,Helvetica; COLOR: #8E3F6C; text-decoration:none;}
	A.lnk:HOVER {FONT-STYLE: italic; FONT-WEIGHT:bold; FONT-SIZE: 17px; FONT-FAMILY:Verdana,Helvetica; COLOR: #555555; text-decoration:underline;}
</style>


</t:documentHead>
<t:documentBody>


<sakai:view_container>




<table>
<h:form id="enquesta">

<%
	
	
//System.out.println("################ valor contacta jsp: "+contacta);	
	
if (contacta!=null && contacta.equals("S"))
	{
	%>
	<tr>
		<td>
			<h:outputText value="Formulari de contacte" style="font-weight: bold;font-size: 17px;color: #555555" />
		</td>
	</tr>
	<tr>
		<td>
			<h:outputText value="* Introdueix l'email on vulguis que et responguin i els comentaris" style="font-weight: regular;font-style: italic;font-size: 13px;color: #555555" />
		</td>
	</tr>	
	<tr>
		<td>
			<table>			
				<tr>
					<td valign="top">
						<h:outputText value="Email" style="font-weight: bold;font-size: 13px;color: #555555" />				
					</td>
					<td>
						<h:inputText value="#{GetModelCtl.email}" size="50"  />				
					</td>
				</tr>	
				<tr>
					<td valign="top">
						<h:outputText value="Comentaris" style="font-weight: bold;font-size: 13px;color: #555555" />				
					</td>
					<td valign="top">
						<h:inputTextarea value="#{GetModelCtl.comentaris}" rows="4" cols="75" />				
					</td>
				</tr>
				<tr>
					<td valign="top">&nbsp;</td>
					<td valign="top">
						<h:commandButton value="Enviar" action="#{GetModelCtl.enviarIncidencies}" />
					</td>
				</tr>			
			</table>
		</td>
	</tr>	
	
	<tr>
		<td height="2px" bgcolor="#CBCC03"></td>
	</tr>
	<tr>
		<td height="10px"></td>
	</tr>
	
<%} else {%>
	<tr>
		<td align="left">
			
			
			<h:commandLink action="#{GetModelCtl.gestioContacta}" styleClass="lnk" value="Contacta">
										</h:commandLink>
			
				
		</td>
	</tr>
	<tr>
		<td height="15px"></td>
	</tr>
<%}%>	
	
	
<tr>
	<td width="40%" align="left"  height="80%">

		
		<h:outputText value="#{MainCtl.inici}" style="font-weight: bold;font-size: 17px;color: #555555" />
	</td>		
</tr>
<tr>
	<td height="8px"></td>
</tr>
<tr>		
	<td>
		<t:panelTabbedPane id="panel" 
		    	            width="650" 
							binding="#{GetModelCtl.panel}" >
		</t:panelTabbedPane>	
	</td>
</tr>
</h:form>
</table>


</sakai:view_container>
</t:documentBody>
</t:document>

</f:view>

