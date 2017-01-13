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
	<table  width="90%" bgcolor="#d3d8e8" cellspacing="1"> <!-- Enmarquen les dues taules dins d'una -->
	<tr><td bgcolor="#F0F0F0">

	<table width="100%">
	<tr><td valign="top">

	<h:outputText styleClass="chefAlert" value="#{ActesTool.missatge}" />	
	<h2><h:outputText value="#{msgs.profesor}" /></h2>
		<center>	
				<table width="90%" cellspacing=1 cellpadding=4>                                 
					<tr><td bgcolor="#d3d8e8" width="20%" align="right">  <b> <h:outputText value="#{msgs.prof_dni}" />: </b></td><td bgcolor="#e3e8f8" align="left" ><h:outputText value="#{ActesTool.dni}" /></td></tr>
					<tr><td  bgcolor="#d3d8e8" align="right">  <b> <h:outputText value="#{msgs.prof_nom}" /> </b> </td><td bgcolor="#e3e8f8" align="left" > <h:outputText  value="#{ActesTool.nom}" /></td></tr>
				</table>
		<br />
		</center>
	</td></tr>
	
	<tr><td valign="top" >
	<h2><h:outputText value="#{msgs.actadela}" /></h2>
		<center>

				<table width="90%"  cellspacing=1 cellpadding=4>                                 
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
	<table width="90%" bgcolor="#d3d8e8" cellspacing="1"> <!-- Enmarquen les dues taules dins d'una -->
	<tr><td bgcolor="#F0F0F0">

	<table>
	<tr><td valign="top">
	<h2>Acta completa</h2>

	<%--Missatge explicatiu --%>
	<h:outputText value = "#{msgs.instruc}" /><br />

	<h:outputText styleClass="chefAlert" value="#{ActesTool.missatge}" />	
	<%--Taula d'alertes--%>
	<h:dataTable  value="#{ActesTool.llistalinies}" var="liniaacta" rowClasses="chefAlert">		 
		<h:column>
				<h:outputText value="#{liniaacta.msgerror}"/>
		</h:column>
	</h:dataTable>


	
	<%--Barra de navegacio--%>
	
	<p align="left">
	   	<h:outputText value="#{msgs.grup_agrupa}"/>
	       	
       	<h:selectOneMenu valueChangeListener="#{ActesTool.canvia}" onchange="this.form.submit()" value="#{ActesTool.selite}" immediate="true">
		<f:selectItem itemLabel="5" itemValue="5"/>
			<f:selectItem itemLabel="10" itemValue="10"/>
			<f:selectItem itemLabel="15" itemValue="15"/>
			<f:selectItem itemLabel="30" itemValue="30"/>
			<f:selectItem itemLabel="60" itemValue="60"/>
			<f:selectItem itemLabel="100" itemValue="100"/>
		</h:selectOneMenu>
		<br>
       	<h:commandLink action="#{ActesTool.procAnterior}" >
 	       <h:graphicImage title="#{msgs.grup_anterior}" url="image/#{ActesTool.anterior}" rendered="#{ActesTool.anterior!='nogif.gif'}"/>
   	   	</h:commandLink>
       	<h:outputText value="#{msgs.grup_pagina} #{ActesTool.numPag}/#{ActesTool.numPagTotal}"/>
 		<h:commandLink action="#{ActesTool.procSeguent}" >
           <h:graphicImage title="#{msgs.grup_seguent}" url="image/#{ActesTool.seguent}" rendered="#{ActesTool.seguent!='nogif.gif'}"/>
	    </h:commandLink>
 	</p>
	
	<%-- Mostra les dades dins la taula --%>
	
	<h:dataTable first="#{ActesTool.numReg}" rows="#{ActesTool.numPerPag}" value="#{ActesTool.llistalinies}" var="liniaacta" rowClasses="borderGrayBackground,claseBoto">		 

		<h:column>
				<h:outputText styleClass="chefAlert" value="#{liniaacta.numError}"/>
				<h:graphicImage url="image/#{liniaacta.inc}" rendered="#{liniaacta.inc!='no.gif'}"/>
				
		</h:column>

		<h:column>
			<f:facet name="header">
		<h:outputText value="#{msgs.expedient}"/>
			<%-- 	<h:commandLink title="#{msgs.ordexpedient}" action="#{ActesTool.ordenaCol1}"> 	
					<h:outputText value="#{msgs.expedient}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd1}"/>
				</h:commandLink>						--%>
			</f:facet>
			<h:outputText value="#{liniaacta.expedient}" />
		</h:column>

		<h:column>
			<f:facet name="header">
			<h:outputText value="#{msgs.codpla}"/>
			<%--	<h:commandLink title="#{msgs.ordcodpla}" action="#{ActesTool.ordenaCol2}"> 	
					<h:outputText value="#{msgs.codpla}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd2}"/>
				</h:commandLink>		--%>				
			</f:facet>
			<h:outputText value="#{liniaacta.codipla}" />			
		</h:column>

		<h:column>
			<f:facet name="header">
			<h:outputText value="#{msgs.dni}"/>
			<%-- 	<h:commandLink title="#{msgs.orddni}" action="#{ActesTool.ordenaCol3}"> 	
					<h:outputText value="#{msgs.dni}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd3}"/>
				</h:commandLink>--%>						
			</f:facet>
			<h:outputText value="#{liniaacta.dni}" />
		</h:column>
			
		<h:column>
			<f:facet name="header">
				<h:outputText value="#{msgs.alumne}"/>
				<%--<h:commandLink title="#{msgs.ordalumne}" action="#{ActesTool.ordenaCol4}"> 	
					<h:outputText value="#{msgs.alumne}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd4}"/>
				</h:commandLink>--%>						
			</f:facet>
			<h:outputText value="#{liniaacta.alumne}" />
		</h:column>

		<h:column>
			<f:facet name="header">
			<h:outputText value="#{msgs.tipus}"/>
		<%-- 	<h:commandLink title="#{msgs.ordtipus}" action="#{ActesTool.ordenaCol5}"> 	
					<h:outputText value="#{msgs.tipus}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd5}"/>
				</h:commandLink>						--%>
			</f:facet>
				<h:outputText  value="#{liniaacta.tipus}" />				
		</h:column>

		<h:column>
			<f:facet name="header">
					<h:outputText value="#{msgs.conv}"/>
			<%--	<h:commandLink title="#{msgs.ordconv}" action="#{ActesTool.ordenaCol6}"> 	
					<h:outputText value="#{msgs.conv}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd6}"/>
				</h:commandLink>						--%>
			</f:facet>
			<center>
				<h:outputText  value="#{liniaacta.convocatoria}" />			
			</center>
		</h:column>


		<h:column>
			<f:facet name="header">
			<h:outputText value="#{msgs.qual}"/>
				<%-- <h:commandLink title="#{msgs.ordqual}" action="#{ActesTool.ordenaCol7}"> 	
					<h:outputText value="#{msgs.qual}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd7}"/>
				</h:commandLink>	--%>					
			</f:facet>
			
			 <h:selectOneMenu value="#{liniaacta.qualificacio}" >
				<f:selectItems value="#{ActesTool.opcionsQual}" />
			</h:selectOneMenu> 
		</h:column> 
		
		<h:column>
			<f:facet name="header">
			<h:outputText value="#{msgs.nota}"/>
			<%--<h:commandLink title="#{msgs.ordnota}" action="#{ActesTool.ordenaCol8}"> 	
					<h:outputText value="#{msgs.nota}"/>
					<h:graphicImage url="image/#{ActesTool.strOrd8}"/>
				</h:commandLink>--%>						
			</f:facet>
			<h:inputText size="3" value="#{liniaacta.nota}" />						

		</h:column>

		<h:column>
				<h:commandLink title="#{msgs.expneteja}" action="#{liniaacta.neteja}"> 	
					<h:outputText value="#{msgs.neteja}"/>
				</h:commandLink>						
		</h:column>
		
	</h:dataTable>
	<h:commandButton value="#{msgs.desa}" action="#{ActesTool.desaCanvis}" />
	<h:commandButton value="#{msgs.cancela}" action="#{ActesTool.principal}" />
	</td></tr>
	</table>
	
	</td></tr>
	</table>
	</center>
	</h:form>
	</sakai:view_container>
</f:view> 