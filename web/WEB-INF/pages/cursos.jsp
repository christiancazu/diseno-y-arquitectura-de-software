<%-- 
    Document   : cursos
    Created on : May 29, 2019, 2:36:44 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

<%@page import="entidades.Curso" %>
<%@page import="elements.FormActionButton" %>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formActionButton", "../components/form-action-button.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formActionButtons components
    List<FormActionButton> formActionButtons = new ArrayList();
    formActionButtons.add(new FormActionButton(
            "actualizarCurso", "GET", "", "success", "Actualizar"));
    formActionButtons.add(new FormActionButton(
            "eliminarCurso", "POST", "", "danger", "Eliminar"));

    // setting formActionButtons attribute as pageContext
    request.setAttribute("formActionButtons", formActionButtons);
%>

<t:baseTemplate 
    pageTitle="Cursos"
    navbarActiveLink="cursos"
    navbarBgColor="bg-danger"
>

<t:mainContentTemplate>
    
    <t:mainCardTemplate cardTitle="Cursos">
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

        <c:if 
            test="${not empty cursos}" 
            var="v" 
            scope="request"
        >    
            <div id="cursos-list-container" class="mt-4">
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
        </c:if> 
    </t:mainCardTemplate>
    
</t:mainContentTemplate>
    
</t:baseTemplate>
