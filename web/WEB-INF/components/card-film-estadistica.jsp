<%-- 
    Document   : card-film-estadistica
    Created on : Jun 5, 2019, 5:20:03 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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

                <div class="row">
                    <div class="bottom-align-text col-12" style="left: .25rem; bottom: .5rem">                        
                        <fmt:formatNumber var="likes" type="number" minFractionDigits="2" maxFractionDigits="2" value="${param.likes/param.total*100}" />
                        <fmt:formatNumber var="dislikes" type="number" minFractionDigits="2" maxFractionDigits="2" value="${param.dislikes/param.total*100}" />

                            <div class="svg-img-like-container" style="margin-right: 4rem">
                                <svg class="svg-img-like" style="fill: #3498DB" viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet" focusable="false">
                                <g>
                                <path d="M1 21h4V9H1v12zm22-11c0-1.1-.9-2-2-2h-6.31l.95-4.57.03-.32c0-.41-.17-.79-.44-1.06L14.17 1 7.59 7.59C7.22 7.95 7 8.45 7 9v10c0 1.1.9 2 2 2h9c.83 0 1.54-.5 1.84-1.22l3.02-7.05c.09-.23.14-.47.14-.73v-1.91l-.01-.01L23 10z"></path>
                                </g>
                                </svg>
                                <span class="badge badge-info mr-2">${param.likes}</span>
                                <span class="like-counter text-dark">${likes}% </span>
                            </div>

                            <div class="svg-img-like-container">
                                <svg class="svg-img-like rotate-180" style="fill: #E74C3C" viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet" focusable="false">
                                <g>
                                <path d="M1 21h4V9H1v12zm22-11c0-1.1-.9-2-2-2h-6.31l.95-4.57.03-.32c0-.41-.17-.79-.44-1.06L14.17 1 7.59 7.59C7.22 7.95 7 8.45 7 9v10c0 1.1.9 2 2 2h9c.83 0 1.54-.5 1.84-1.22l3.02-7.05c.09-.23.14-.47.14-.73v-1.91l-.01-.01L23 10z"></path>
                                </g>
                                </svg>
                                <span class="badge badge-danger mr-2">${param.dislikes}</span>
                                <span class="like-counter text-dark">${dislikes}% </span>
                            </div> 
                        
                        <div class="progress bg-danger">
                            <div class="progress-bar bg-info" role="progressbar" aria-valuenow="20"
                                 aria-valuemin="0" aria-valuemax="100" style="width: ${param.likes/param.total*100}%">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2 d-flex align-items-center">                        
            <div class="container">
                <h6 class="card-title text-dark text-center">${param.nombreGenero}</h6> 
                <h5 class="text-center"><span class="badge badge-light">votos: ${param.total}</span></h5>
            </div>                                            
        </div>
    </div>
</div>

