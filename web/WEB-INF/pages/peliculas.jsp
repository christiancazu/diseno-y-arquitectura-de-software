<%-- 
    Document   : peliculas
    Created on : Jun 3, 2019, 6:17:51 PM
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
    component.put("alertMessage", "../components/alert-message.jsp");
    
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
            
            <%-- alert message --%>    
            <c:if 
                test="${not empty success}" 
                var="isSuccess" 
                scope="request"
                >                     
                <jsp:include page="${component.alertMessage}">
                    <jsp:param name="isSuccess" value="true" />
                    <jsp:param name="entidad" value="pelicula" />
                    <jsp:param name="accion" value="eliminada" />
                </jsp:include>
            </c:if>
                
            <c:forEach var="fullPelicula" items="${fullPeliculas}">
                
                <%-- card film --%>
                <jsp:include page="${component.cardFilm}">
                    <jsp:param name="id" value="${fullPelicula.getPelicula().getId()}" />
                    <jsp:param name="nombre" value="${fullPelicula.getPelicula().getNombre()}" />
                    <jsp:param name="descripcion" value="${fullPelicula.getPelicula().getDescripcion()}" />
                    <jsp:param name="imagen" value="${fullPelicula.getPelicula().getImagen()}" />
                    <jsp:param name="nombreGenero" value="${fullPelicula.getPelicula().getGenero().getNombre()}" />
                    <jsp:param name="likes" value="${fullPelicula.getLikes()}" />
                    <jsp:param name="dislikes" value="${fullPelicula.getDislikes()}" />
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

<script>
    $(document).ready(() => {
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, location.pathname].join('')
            window.history.replaceState({}, "", "peliculas")
        }         
        
        assignTextToLabel()
        assignValueToSelect()
    })
</script>