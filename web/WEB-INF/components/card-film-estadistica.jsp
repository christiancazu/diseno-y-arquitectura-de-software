<%-- 
    Document   : card-film-estadistica
    Created on : Jun 5, 2019, 5:20:03 PM
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
                    <span class="badge badge-light">votos: ${param.total}</span>
                    likes: ${param.likes/param.total*100}% &nbsp&nbsp
                    dislikes: ${param.dislikes/param.total*100}%
                    <div class="progress bg-danger">
                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20"
                             aria-valuemin="0" aria-valuemax="100" style="width: ${param.likes/param.total*100}%">
                        </div>
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

