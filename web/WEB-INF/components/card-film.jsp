<%-- 
    Document   : card-film
    Created on : Jun 3, 2019, 3:31:06 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="elements.FormActionButton"%>

<%
    // components paths
    HashMap<String, String> cardFilmComponent = new HashMap();
    cardFilmComponent.put("formActionButton", "/WEB-INF/components/form-action-button.jsp");
    cardFilmComponent.put("svgImgLike", "/WEB-INF/components/svg-img-like.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("cardFilmComponent", cardFilmComponent);
        
    // context for formActionButtons components
    List<FormActionButton> formActionButtons = new ArrayList();
    
    formActionButtons.add(new FormActionButton(
            "actualizarPelicula", "GET", "", "primary", "Actualizar"));
    formActionButtons.add(new FormActionButton(
            "eliminarPelicula", "POST", "", "danger", "Eliminar"));
    
    // setting formActionButtons attribute as pageContext
    request.setAttribute("formActionButtons", formActionButtons);
%>

<div class="card bg-secondary mb-2">
    <div class="row no-gutters">
        <div class="col-md-3">
            <img class="card-img" 
                 alt="${param.imagen}"
                 src="${pageContext.request.contextPath}/resources/images/peliculas/${param.imagen}"
            >
        </div>
        <div class="col-md-7">
            <div class="card-body">
                <h5 class="card-title"><span class="badge badge-dark">${param.id}</span> &nbsp ${param.nombre}</h5>
                <p class="card-text text-muted mb-4">${param.descripcion}</p>
                
                <div class="bottom-align-text">
                      
                    <jsp:include page="${cardFilmComponent.svgImgLike}">
                        <jsp:param name="rotate" value="" />
                        <jsp:param name="likesDislikes" value="${param.likes}" />
                    </jsp:include>
                    
                    <jsp:include page="${cardFilmComponent.svgImgLike}">
                        <jsp:param name="rotate" value="rotate-180" />
                        <jsp:param name="likesDislikes" value="${param.dislikes}" />
                    </jsp:include>
                    
                </div>
            </div>
        </div>
        <div class="col-md-2 d-flex align-items-center">                        
            <div class="container">
                <h6 class="card-title text-dark text-center">${param.nombreGenero}</h6>
                                
                <c:forEach var="formActionButton" items="${formActionButtons}">
                    <jsp:include page="${cardFilmComponent.formActionButton}">
                        <jsp:param name="action" value="${formActionButton.getAction()}" />
                        <jsp:param name="method" value="${formActionButton.getMethod()}" />
                        <jsp:param name="value" value="${param.id}" />
                        <jsp:param name="btnType" value="${formActionButton.getBtnType()}" />
                        <jsp:param name="btnName" value="${formActionButton.getBtnName()}" />
                    </jsp:include>
                </c:forEach>
                
            </div>                                            
        </div>
    </div>
</div>
