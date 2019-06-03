<%-- 
    Document   : index
    Created on : May 12, 2019, 9:36:48 PM
    Author     : Christian Carrillo Zúñiga
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
    pageTitle="Películas"
    navbarBgColor="bg-danger"
    navbarActiveLink="peliculas"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate cardTitle="Películas"> 
                
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
        <jsp:param name="path" value="crearPelicula" />
        <jsp:param name="btnName" value="Crear" />
    </jsp:include>
    
</t:baseTemplate>
