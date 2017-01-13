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
	<table bgcolor="#d3d8e8" cellspacing="1"> <!-- Enmarquen les dues taules dins d'una -->
	<tr><td bgcolor="#F0F0F0">

	<table>
	<tr><td valign="top">
	<h:outputText value="#{msgs.noenrera}" />
	<br />
	<br />
	
	<h:commandButton action="#{ActesTool.printVersion}" value="#{msgs.impllisalu}" />
	<h:selectBooleanCheckbox value="#{ActesTool.withStudents}" /><h:outputText value="#{msgs.inclourealu}" />
	
	<h:outputText styleClass="chefAlert" value="#{ActesTool.missatge}" />	
	<h2><h:outputText value="#{msgs.profesor}" /></h2>
		<center>	
				<table width="700px" cellspacing=1 cellpadding=4>                                 
					<tr><td bgcolor="#d3d8e8" width="20%" align="right">  <b> <h:outputText value="#{msgs.prof_dni}" />: </b></td><td bgcolor="#e3e8f8" align="left" ><h:outputText value="#{ActesTool.dni}" /></td></tr>
					<tr><td  bgcolor="#d3d8e8" align="right">  <b> <h:outputText value="#{msgs.prof_nom}" /> </b> </td><td bgcolor="#e3e8f8" align="left" > <h:outputText  value="#{ActesTool.nom}" /></td></tr>
				</table>
		<br />
		</center>
	</td></tr>
	
	<tr><td valign="top" >
	<h2><h:outputText value="#{msgs.actadela}" /></h2>
		<center>

				<table width="700px"  cellspacing=1 cellpadding=4>                                 
					<tr><td bgcolor="#d3d8e8" width="20%" align="right">  <b> <h:outputText value="#{msgs.acta_any}" /> </b></td><td bgcolor="#e3e8f8" align="left" ><h:outputText value="#{ActesTool.acta_actual.anyaca}" /></td></tr>
					<tr><td bgcolor="#d3d8e8" align="right">  <b> <h:outputText value="#{msgs.acta_codi}" /> </b> </td><td bgcolor="#e3e8f8" align="left" > <h:outputText  value="#{ActesTool.acta_actual.ass_codnum}" /></td></tr>
					<tr><td bgcolor="#d3d8e8" align="right">  <b> <h:outputText value="#{msgs.acta_assig}" /> </b> </td><td  bgcolor="#e3e8f8" align="left" > <h:outputText  value="#{ActesTool.acta_actual.nom_ass}" /></td></tr>
					<tr><td bgcolor="#d3d8e8"  align="right">  <b> <h:outputText value="#{msgs.acta_grup}" /> </b> </td><td bgcolor="#e3e8f8" align="left" > <h:outputText   value="#{ActesTool.acta_actual.nom_gas}" /></td></tr>
					<tr><td bgcolor="#d3d8e8"  align="right">  <b> <h:outputText value="#{msgs.acta_convo}" /> </b> </td><td bgcolor="#e3e8f8" align="left" > <h:outputText   value="#{ActesTool.acta_actual.nomtco}" /></td></tr>
				</table>

		</center>
		<br />		
		</td></tr>
	</table>
	
	</td></tr>
	</table>
	
	</center>
	<br />
	<center>
	<table width="700px" bgcolor="#d3d8e8" cellspacing="1"> <!-- Enmarquen les dues taules dins d'una -->
	<tr><td bgcolor="#F0F0F0">

	<table>
	<tr><td valign="top">
	<h2>Acta completa</h2>
	
	<%-- Mostra les dades dins la taula --%>
	
	<h:dataTable value="#{ActesTool.llistalinies}" var="liniaacta" rowClasses="borderGrayBackground,claseBoto">		 


		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.expedient}"/>
			</f:facet>
			<h:outputText value="#{liniaacta.expedient}" />
		</h:column>

		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.codpla}"/>
			</f:facet>
			<h:outputText value="#{liniaacta.codipla}" />			
		</h:column>

		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.dni}"/>
			</f:facet>
			<h:outputText value="#{liniaacta.dni}" />
		</h:column>
			
		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.alumne}"/>
			</f:facet>
			<h:outputText value="#{liniaacta.alumne}" />
		</h:column>

		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.tipus}"/>
			</f:facet>
				<h:outputText  value="#{liniaacta.tipus}" />				
		</h:column>

		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.conv}"/>
			</f:facet>
				<h:outputText  value="#{liniaacta.convocatoria}" />			
		</h:column>


		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.qual}"/>
			</f:facet>
				<h:outputText value="#{liniaacta.nomqualificacio}" />
		</h:column> 
		
		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.nota}"/>
			</f:facet>
			<h:outputText value="#{liniaacta.nota}" />						
		</h:column>

		
	</h:dataTable>
	<h:commandButton value="#{msgs.inici}" action="#{ActesTool.principal}" />
	<h:commandButton value="#{msgs.tornaacta}" action="#{ActesTool.actaCompleta}" />
	</td></tr>
	</table>
	
	</td></tr>
	</table>
	</center>
	</h:form>
	</sakai:view_container>
</f:view> 