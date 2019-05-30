<%-- 
    Document   : button-go-to
    Created on : May 19, 2019, 8:38:33 PM
    Author     : ciber
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a class="btn btn-block btn-md btn-${param.btnColor} mt-2" 
    href="<%= request.getContextPath()%>/${param.path}"
>
    <strong>${param.btnName}</strong>
</a>
