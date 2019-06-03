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
                 src="${pageContext.request.contextPath}/resources/images/${param.imagen}"
            >
        </div>
        <div class="col-md-7">
            <div class="card-body">
                <h5 class="card-title">${param.nombre}</h5>
                <p class="card-text text-muted mb-4">${param.descripcion}</p>
                <div class="bottom-align-text">
                    <div class="svg-img-like-container">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet" focusable="false" class="svg-img-like"><g>
                        <path d="M1 21h4V9H1v12zm22-11c0-1.1-.9-2-2-2h-6.31l.95-4.57.03-.32c0-.41-.17-.79-.44-1.06L14.17 1 7.59 7.59C7.22 7.95 7 8.45 7 9v10c0 1.1.9 2 2 2h9c.83 0 1.54-.5 1.84-1.22l3.02-7.05c.09-.23.14-.47.14-.73v-1.91l-.01-.01L23 10z" class="style-scope yt-icon"></path>
                        </g></svg><span class="like-counter text-dark">${param.likes}</span>
                    </div>
                    <div class="svg-img-like-container">
                        <svg viewBox="0 0 24 24" preserveAspectRatio="xMidYMid meet" focusable="false" class="svg-img-like rotate-180"><g>
                        <path d="M1 21h4V9H1v12zm22-11c0-1.1-.9-2-2-2h-6.31l.95-4.57.03-.32c0-.41-.17-.79-.44-1.06L14.17 1 7.59 7.59C7.22 7.95 7 8.45 7 9v10c0 1.1.9 2 2 2h9c.83 0 1.54-.5 1.84-1.22l3.02-7.05c.09-.23.14-.47.14-.73v-1.91l-.01-.01L23 10z" class="style-scope yt-icon"></path>
                        </g></svg><span class="like-counter text-dark">${param.unlikes}</span> 
                    </div>
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
