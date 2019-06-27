<%-- 
    Document   : basicContentTemplate
    Created on : Jun 27, 2019, 4:39:17 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@tag description="basic content page template" pageEncoding="UTF-8" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@attribute name="contentTitle" required="true" %>

<t:mainContainerTemplate>
    <h4 class="h4-responsive">${contentTitle}</h4>
    <hr>
    <jsp:doBody />
</t:mainContainerTemplate>