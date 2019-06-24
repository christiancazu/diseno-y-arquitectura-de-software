<%-- 
    Document   : encuestaPeliculas
    Created on : Jun 5, 2019, 12:57:37 PM
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
    component.put("cardFilmEncuesta", "/WEB-INF/components/card-film-encuesta.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);
%>

<t:baseTemplate
    pageTitle="Encuestas"
    navbarBgColor="bg-danger"
    navbarActiveLink="encuestas"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate cardTitle="Encuesta películas"> 
            
            <%-- alert message --%>    
            <c:if 
                test="${not empty success}" 
                var="isSuccess" 
                scope="request"
                >                     
                <jsp:include page="${component.alertMessage}">
                    <jsp:param name="isSuccess" value="${success}" />
                    <jsp:param name="entidad" value="encuesta" />
                    <jsp:param name="accion" value="registrada" />
                </jsp:include>
            </c:if>
            
            <form action="encuestas/registrar" method="POST">                 

                <c:forEach var="fullPelicula" items="${fullPeliculas}">

                    <%-- card film --%>
                    <jsp:include page="${component.cardFilmEncuesta}">
                        <jsp:param name="id" value="${fullPelicula.getPelicula().getId()}" />
                        <jsp:param name="nombre" value="${fullPelicula.getPelicula().getNombre()}" />
                        <jsp:param name="descripcion" value="${fullPelicula.getPelicula().getDescripcion()}" />
                        <jsp:param name="imagen" value="${fullPelicula.getPelicula().getImagen()}" />
                        <jsp:param name="nombreGenero" value="${fullPelicula.getPelicula().getGenero().getNombre()}" />
                    </jsp:include>

                </c:forEach>
                
                <%-- button Registrar --%>
                <jsp:include page="${component.buttonForm}">
                    <jsp:param name="color" value="info" />
                    <jsp:param name="value" value="Registrar encuesta" />
                </jsp:include>

                <%-- button Volver --%>
                <jsp:include page="${component.buttonGoTo}">
                    <jsp:param name="btnColor" value="primary" />
                    <jsp:param name="path" value="peliculas" />
                    <jsp:param name="btnName" value="Volver" />
                </jsp:include> 
                
            </form>

        </t:mainCardTemplate>
        
    </t:mainContentTemplate>

</t:baseTemplate>

<script>
    $(document).ready(() => {
        pathNameFixed = location.pathname.replace(/\/encuestas\/registrar/, '/encuestas')
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, pathNameFixed].join('')
            window.history.replaceState({}, "", baseUrl)
        } 
    })
    
    // #TODO: OPTIMIZATION
    $("div[id^=svg-]").click(function () {
        
        idToString = this.id

        $dataId = $(this).attr("data-id");

        if (idToString.includes("svg-l")) {
            $('#' + idToString + '[data-id="' + $dataId + '"]' + ' svg').css("fill", "#3498DB")
            
            idToStringAlter = idToString.replace(/-.*l/, '-disl')
            
            $('#' + idToStringAlter + '[data-id="' + $dataId + '"]' + ' svg').css("fill", "#999")
                
            $("input[name^=" + $(this).attr("data-id") + "]").filter("input[value='N']").removeAttr("checked")
            $("input[name^=" + $(this).attr("data-id") + "]").filter("input[value='S']").attr("checked", "checked")     
        } else {                
            $('#' + idToString + '[data-id="' + $dataId + '"]' + ' svg').css("fill", "#3498DB")

            idToStringAlter = idToString.replace(/-.*disl/, '-l')

            $('#' + idToStringAlter + '[data-id="' + $dataId + '"]' + ' svg').css("fill", "#999")

            $("input[name^=" + $(this).attr("data-id") + "]").filter("input[value='S']").removeAttr("checked")
            $("input[name^=" + $(this).attr("data-id") + "]").filter("input[value='N']").attr("checked", "checked")
        }         
        // console.log($("input[name^=" + $(this).attr("data-id") + "]").val())       
    });
    
</script>