<%-- 
    Document   : estadisticas
    Created on : Jun 5, 2019, 5:15:46 PM
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
    component.put("cardFilmEstadística", "/WEB-INF/components/card-film-estadistica.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);
%>

<t:baseTemplate
    pageTitle="Estadísticas"
    navbarBgColor="bg-danger"
    navbarActiveLink="estadisticas"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate cardTitle="Estadísticas películas"> 
              
            <c:forEach var="fullPelicula" items="${fullPeliculas}">
                
                <%-- card film --%>
                <jsp:include page="${component.cardFilmEstadística}">
                    <jsp:param name="id" value="${fullPelicula.getPelicula().getId()}" />
                    <jsp:param name="nombre" value="${fullPelicula.getPelicula().getNombre()}" />
                    <jsp:param name="descripcion" value="${fullPelicula.getPelicula().getDescripcion()}" />
                    <jsp:param name="imagen" value="${fullPelicula.getPelicula().getImagen()}" />
                    <jsp:param name="nombreGenero" value="${fullPelicula.getPelicula().getGenero().getNombre()}" />
                    <jsp:param name="likes" value="${fullPelicula.getLikes()}" />
                    <jsp:param name="dislikes" value="${fullPelicula.getDislikes()}" />
                    <jsp:param name="total" value="${fullPelicula.getLikes() + fullPelicula.getDislikes()}" />
                </jsp:include>
                
            </c:forEach>

        </t:mainCardTemplate>
        
    </t:mainContentTemplate>
    
</t:baseTemplate>

<script>
    $(document).ready(() => {
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, location.pathname].join('')
            window.history.replaceState({}, "", "estadisticas")
        }         
    })
</script>