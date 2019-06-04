<%-- 
    Document   : peliculas
    Created on : Jun 3, 2019, 6:17:51 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("buttonFloat", "/WEB-INF/components/button-float.jsp");
    component.put("cardFilm", "/WEB-INF/components/card-film.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);
%>

<t:baseTemplate
    pageTitle="Pel�culas"
    navbarBgColor="bg-danger"
    navbarActiveLink="peliculas"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate cardTitle="Pel�culas"> 
                
            <c:forEach var="pelicula" items="${peliculas}">
                
                <%-- card film --%>
                <jsp:include page="${component.cardFilm}">
                    <jsp:param name="id" value="${pelicula.getId()}" />
                    <jsp:param name="nombre" value="${pelicula.getNombre()}" />
                    <jsp:param name="descripcion" value="${pelicula.getDescripcion()}" />
                    <jsp:param name="imagen" value="${pelicula.getImagen()}" />
                    <jsp:param name="nombreGenero" value="${pelicula.getGenero().getNombre()}" />
                </jsp:include>
                
            </c:forEach>

        </t:mainCardTemplate>
        
    </t:mainContentTemplate>

    <%-- button float --%>
    <jsp:include page="${component.buttonFloat}">
        <jsp:param name="path" value="registrarPelicula" />
        <jsp:param name="btnName" value="Registrar" />
    </jsp:include>
    
</t:baseTemplate>

