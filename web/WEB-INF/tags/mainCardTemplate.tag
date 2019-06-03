<%-- 
    Document   : mainCardTemplate
    Created on : May 31, 2019, 2:45:44 AM
    Author     : Christian Carrillo Zúñiga
--%>
<%@tag 
    description="main card container" 
    pageEncoding="UTF-8"
%>

<%@attribute name="cardTitle" required="true" %>
<%@attribute name="cardBg" %>

<div class="card ${cardBg}">
    <div class="card-header">
        <h4 class="card-title text-center">${cardTitle}</h4>
    </div>
    <div class="card-body">
            
        <jsp:doBody />

    </div>
</div>
        