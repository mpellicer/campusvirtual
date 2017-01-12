<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>





<f:view>
<sakai:view_container>


<table>
<tr>
<td width="40%" align="center"  height="80%">

<h:form id="error">
<h:outputText value="Error: Falta introduir opcions" escape="false" style="font-weight: bold;font-size: 15px;color: #555555"/>
<h:outputText value="<br>" escape="false"/>
<h:outputText value="<br/>" escape="false"/>
<h:commandButton value="Tornar" action="tornar" /> 

</h:form>
</td>
</tr>
</table>


</sakai:view_container>
</f:view>

