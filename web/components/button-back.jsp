<%-- 
    Document   : button-back
    Created on : May 19, 2019, 8:38:33 PM
    Author     : ciber
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a class="btn btn-block btn-md btn-outline-primary mt-2" 
    href="<%= request.getContextPath()%>/${param.path}"
>
    <strong>Volver</strong>
</a>
