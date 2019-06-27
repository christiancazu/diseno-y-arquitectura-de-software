<%-- 
    Document   : navbar
    Created on : May 12, 2019, 10:54:07 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>

<%
    Map<String, String> links = new LinkedHashMap();

    links.put("inicio", request.getContextPath());
    links.put("foo", "foo");
    links.put("bar", "bar");

    request.setAttribute("links", links);
%>

<nav class="navbar navbar-expand-lg navbar-dark ${param.navbarBgColor} static-top fixed-top py-0 primary-color">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath()%>">
            <div class="navbar-nav">
                <div class="logo-container">
                    <img src="<%= request.getContextPath()%>/assets/images/utp.png" class="img-logo-size" alt="logo">
                </div>
            </div>
        </a>
        <h4 class="title-app text-light">Universidad Tecnológica del Perú</h4>        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <c:forEach var="link" items="${links}">
                    <li class="nav-item">
                        <a class="nav-link text-capitalize
                           ${param.navbarActiveLink == link.key ? "active" : ""}" 
                           href="${link.value}"
                           >
                            ${link.key}
                        </a>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</nav>