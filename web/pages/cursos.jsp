<%-- 
    Document   : cursos
    Created on : May 29, 2019, 2:36:44 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%@page import="entidades.Curso"%>
<%@page import="elements.FormActionButton"%>

<jsp:include page='../components/common/head.jsp'>
    <jsp:param name="title" value="Cursos" />
</jsp:include>

<jsp:include page='../components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-danger" />
    <jsp:param name="cursosActiveLink" value="active" />
</jsp:include>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formActionButton", "../components/form-action-button.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);
%>

<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="row justify-content-center">
                <div class="col-6">
                    <%-- button Registrar --%>
                    <jsp:include page="${component.buttonGoTo}">
                        <jsp:param name="btnColor" value="primary" />
                        <jsp:param name="path" value="registrarCurso" />
                        <jsp:param name="btnName" value="Registrar" />
                    </jsp:include> 
                </div>
                <div class="col-6">
                    <%-- button Volver --%>
                    <jsp:include page="${component.buttonGoTo}">
                        <jsp:param name="btnColor" value="outline-primary" />
                        <jsp:param name="path" value="" />
                        <jsp:param name="btnName" value="Volver" />
                    </jsp:include> 
                </div>
            </div>
        </div>
    </div>

    <c:if 
        test="${not empty cursos}" 
        var="v" 
        scope="request"
        >    
        <div id="cursos-list-container" class="row justify-content-center mt-4">
            <div class="col-md-8">
                <table class="table table-striped text-center table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Docente</th>
                            <th>Lugar</th>
                            <th class="text-center">Acci�n</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // context for formActionButtons components
                            List<FormActionButton> formActionButtons = new ArrayList();
                            formActionButtons.add(new FormActionButton(
                                    "actualizarAlumno", "GET", "", "success", "Actualizar"));
                            formActionButtons.add(new FormActionButton(
                                    "eliminarAlumno", "POST", "", "danger", "Eliminar"));

                            // setting formActionButtons attribute as pageContext
                            request.setAttribute("formActionButtons", formActionButtons);
                        %>
                        <c:forEach var="curso" items="${cursos}">

                            <tr> 
                                <td>${curso.getId()}</td>
                                <td>${curso.getNombre()}</td>
                                <td>${curso.getDocente()}</td>
                                <td>${curso.getLugar()}</td>
                                <td class="d-flex justify-content-center">
                                    <c:forEach var="formActionButton" items="${formActionButtons}">
                                        <jsp:include page="${component.formActionButton}">
                                            <jsp:param name="action" value="${formActionButton.getAction()}" />
                                            <jsp:param name="method" value="${formActionButton.getMethod()}" />
                                            <jsp:param name="value" value="${curso.getId()}" />
                                            <jsp:param name="btnType" value="${formActionButton.getBtnType()}" />
                                            <jsp:param name="btnName" value="${formActionButton.getBtnName()}" />
                                        </jsp:include>
                                    </c:forEach>
                                </td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>                
    </c:if>
</div>

<jsp:include page='../components/common/foot.jsp'/>
