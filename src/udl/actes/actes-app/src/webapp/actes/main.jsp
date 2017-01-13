<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 

<% response.setContentType("text/html; charset=UTF-8"); %>

<f:loadBundle basename="es.udl.asic.tool.actes.bundle.Messages" var="msgs"/>
<f:view>
	<sakai:view_container title="#{msgs.title_name}">

	<h:form>
	<br />
	<center>
	<table width="90%" bgcolor="#d3d8e8" cellspacing="1"> <!-- Enmarquen les dues taules dins d'una -->
	<tr><td bgcolor="#F0F0F0">

	<table>
	<tr><td valign="top">

	<h:inputHidden value="#{ActesTool.nombreLinies}"  />
	
	
		<h2><h:outputText value="#{msgs.title_name}" rendered="#{ActesTool.existActes}" /></h2>
			
		<h:dataTable rendered="#{ActesTool.existActes}"  value="#{ActesTool.llistaactes}" var="acta_actual" rowClasses="borderGrayBackground,claseBoto">		 
	
			<h:column>
				<f:facet name="header">
						<h:outputText value="#{msgs.grup}"/>
				</f:facet>
				<h:commandLink title = "#{msgs.anar_acta}" action="#{acta_actual.actaCompleta}">
					<h:outputText value="#{acta_actual.nom}" />			
				</h:commandLink>
			</h:column> 
			<h:column>
				<f:facet name="header">
						<h:outputText value="#{msgs.codgrup}"/>
				</f:facet>
				<h:outputText value="#{acta_actual.grup}" />			
			</h:column>
			<h:column>
				<f:facet name="header">
						<h:outputText value="#{msgs.numord}"/>
				</f:facet>
				<h:outputText value="#{acta_actual.numord}" />			
			</h:column>
			
			<h:column>
				<f:facet name="header">
						<h:outputText value="#{msgs.convo}"/>
				</f:facet>
				<h:outputText value="#{acta_actual.convo}" />			
			</h:column>
			<!-- CANVIS CODI 18 Gener: 
	     		He afegit una columna que pinta l'any acadèmic per a evitar confusions 
	 		-->
			<h:column>
				<f:facet name="header">
						<h:outputText value="#{msgs.any}"/>
				</f:facet>
				<h:outputText value="#{acta_actual.any}" />			
			</h:column>
	
		</h:dataTable>

		<h:outputText value="#{msgs.noteacces}" rendered="#{!ActesTool.existActes}" />

	</td></tr>
	</table>
	
	</td></tr>
	</table>
	</center>

	</h:form>
	</sakai:view_container>
</f:view> 