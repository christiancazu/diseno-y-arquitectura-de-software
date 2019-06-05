<%-- 
    Document   : card-film-encuesta
    Created on : Jun 5, 2019, 1:03:33 PM
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
                        <jsp:param name="attrId" value="${param.id}" />
                        <jsp:param name="rotate" value="" />
                        <jsp:param name="cursor" value="cursor-pointer" />
                        <jsp:param name="likesDislikes" value="" />
                        <jsp:param name="name" value="like-${param.id}" />
                    </jsp:include>

                    <jsp:include page="${cardFilmComponent.svgImgLike}">
                        <jsp:param name="attrId" value="${param.id}" />
                        <jsp:param name="rotate" value="rotate-180" />
                        <jsp:param name="cursor" value="cursor-pointer" />
                        <jsp:param name="likesDislikes" value="" />
                        <jsp:param name="name" value="dislike-${param.id}" />
                    </jsp:include>

                    <div>
                        <input class="d-none" type="radio" name="${param.id}" value="S" required>
                        <input class="d-none" type="radio" name="${param.id}" value="N">
                    </div>

                </div>
            </div>
        </div>
        <div class="col-md-2 d-flex align-items-center">                        
            <div class="container">
                <h6 class="card-title text-dark text-center">${param.nombreGenero}</h6>                
            </div>                                            
        </div>
    </div>
</div>

