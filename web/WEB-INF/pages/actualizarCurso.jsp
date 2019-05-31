<%-- 
    Document   : actualizarCurso
    Created on : May 31, 2019, 3:59:19 AM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

<%@page import="entidades.Curso"%>
<%@page import="elements.FormGroup" %>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formGroup", "../components/form-group.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);
    
    // get curso to be set on formGroups inputs
    Curso curso = (Curso) request.getAttribute("curso");

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", curso.getNombre(), "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Docente", "docente", curso.getDocente(), "text", "docente", "text-docente", true));
    formGroups.add(
            new FormGroup("Lugar", "lugar", curso.getLugar(), "text", "lugar", "text-lugar", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Actualizar cursos"
    navbarActiveLink="cursos"
    navbarBgColor="bg-danger"
>

<t:mainContentTemplate>
    
    <t:mainCardTemplate cardTitle="Actualizar curso">
        <form action="actualizarCurso" method="POST">                       

            <input id="id-curso" type="hidden" name="id" value="${curso.id}">
            
            <%-- formGroups --%>
            <c:forEach var="formGroup" items="${formGroups}">
                <jsp:include page="${component.formGroup}">
                    <jsp:param name="label" value="${formGroup.getLabel()}" />
                    <jsp:param name="name" value="${formGroup.getName()}" />
                    <jsp:param name="value" value="${formGroup.getValue()}" />
                    <jsp:param name="type" value="${formGroup.getType()}" />
                    <jsp:param name="placeholder" value="${formGroup.getPlaceholder()}" />
                    <jsp:param name="id" value="${formGroup.getId()}" />
                    <jsp:param name="required" value="${formGroup.isRequired()}" />
                </jsp:include>
            </c:forEach>

            <%-- button Registrar --%>
            <jsp:include page="${component.buttonForm}">
                <jsp:param name="color" value="primary" />
                <jsp:param name="value" value="Actualizar" />
            </jsp:include>

            <%-- button Volver --%>
            <jsp:include page="${component.buttonGoTo}">
                <jsp:param name="btnColor" value="outline-primary" />
                <jsp:param name="path" value="cursos" />
                <jsp:param name="btnName" value="Volver" />
            </jsp:include> 

        </form> 

        <c:if 
            test="${not empty success}" 
            var="isSuccess" 
            scope="request"
            >                     
            <div class="alert alert-dismissible show mt-2
                 ${isSuccess                  
                ? "alert-success" 
                : "alert-danger"}"
                 role="alert"
                 >
                <h6 class="text-center">
                    El curso ${isSuccess
                       ? "ha sido actualizado satisfactoriamente." 
                       : "no ha podido ser actualizado."}
                </h6>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </t:mainCardTemplate>
    
</t:mainContentTemplate>

</t:baseTemplate>

<script>
    $(document).ready(function () {
        if (!window.location.search) {
            let id = $('#id-curso').val()
            baseUrl = [location.protocol, '//', location.host, location.pathname].join('');
            window.history.replaceState({}, "", baseUrl + "?id=" + id);
        }    
    }) 
</script>