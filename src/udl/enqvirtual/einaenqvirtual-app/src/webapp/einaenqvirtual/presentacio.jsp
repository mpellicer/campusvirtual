<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ page session="true" contentType="text/html" language="java" import="org.sakaiproject.tool.api.Placement,org.sakaiproject.site.api.ToolConfiguration,org.sakaiproject.site.cover.SiteService,org.sakaiproject.event.cover.UsageSessionService,org.sakaiproject.event.api.UsageSession,org.sakaiproject.authz.api.AuthzGroup,org.sakaiproject.authz.api.GroupNotDefinedException,org.sakaiproject.authz.api.Role,org.sakaiproject.authz.cover.SecurityService,org.sakaiproject.authz.api.AuthzGroupService,org.sakaiproject.tool.cover.ToolManager,org.sakaiproject.tool.api.Placement,org.sakaiproject.component.cover.ComponentManager" %>



<%
		String rol = "";
		UsageSession uses = UsageSessionService.getSession();
	  	String id_login= uses.getUserEid();
		String id_rol= uses.getUserId();
	  	Placement pla = ToolManager.getCurrentPlacement();
		String id_tool = pla.getContext();
		
		Role srol=null;
		try{
			ComponentManager.get("org.sakaiproject.authz.api.AuthzGroupService");
			AuthzGroupService authzGroupService =  (AuthzGroupService) ComponentManager.get("org.sakaiproject.authz.api.AuthzGroupService");
			AuthzGroup realm = authzGroupService.getAuthzGroup("/site/"+id_tool);

			srol = realm.getUserRole(id_rol);

	    }catch (GroupNotDefinedException e) {System.out.println("[presentacio.jsp - 24] Error Usuari:Site");}
	    
	    try{
			rol=srol.getId();

		}catch(Exception e){System.out.println("[presentacio.jsp - 29] Error al recuperar el rol");}


%>

<f:view>
<sakai:view_container>


<%
if (rol!=null  && rol.equals("maintain")){
%>


	<table>
	<tr>
	<td width="40%" align="center"  height="80%">
	<h:form id="enquesta">
	
	<h:outputText value="<br>" escape="false"/>
	<h:outputText value="Administrador d'Enquestes Virtuals" style="font-weight: bold;font-size: 17px;color: #555555" />
	<h:outputText value="<br><br/>" escape="false"/>
	
	<h:selectOneMenu value="#{MainCtl.dniP}">
		<f:selectItems value="#{MainCtl.alumnes}" />
		
	</h:selectOneMenu>
	
	<h:commandButton value="Entrar" action="inici" />
	
	</h:form>
	</td>
	</tr>
	</table>
	
<%
} else {
%>


	<table>
	
	<tr>
	<td width="40%" align="left"  height="20%">
	<br>
	<b>La UdL posa en funcionament l'enquesta virtual per a facilitar la recollida d'opini� de la comunitat universit�ria sobre aspectes com la doc�ncia. 
	T'animem a participar-hi i aix� millorar el funcionament de la nostra universitat.
	</b>
    </td>
	</tr>
	
	
	
	
	<tr>
	<td width="40%" align="left"  height="20%">
	<h:form id="enquesta">
	
	<h:outputText value="<br>" escape="false"/>
	<h:outputText value="Instruccions:" style="font-weight: bold;font-size: 17px;color: #555555" />
	
	</td>
	</tr>
	<tr>
	<td width="40%" align="left"  height="60%">
	
	<ul>
		<li>
		- Un cop hagis accedit a les enquestes virtuals et trobar�s les enquestes a les que tens acc�s.</li>

		<li>
		- En el cas de les enquestes d'avaluaci� del professorat, per poder enviar una enquesta d'una
  assignatura has de marcar obligat�riament totes les respostes tant de l'assignatura com de
  tot el professorat. Si creus que no pots respondre alguna pregunta marca l'opci� "en blanc".
		</li>    

    	<li>- Si tens alguna incid�ncia amb l'aplicaci� o amb les assignatures i professorat utilitza l'apartat
  Contacta per comunicar-te amb nosaltres, indicant el correu electr�nic en el que vols rebre la
  resposta.</li>
     </ul>
	<br>
	
	
	
	<h:commandButton value="Entrar" action="inici" />
	
	</h:form>
	</td>
	</tr>
	</table>	

<% } %>

</sakai:view_container>
</f:view>

