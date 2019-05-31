<%-- 
    Document   : actualizarAlumno
    Created on : May 31, 2019, 3:17:41 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

<%@page import="entidades.Alumno"%>
<%@page import="elements.FormGroup" %>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formGroup", "../components/form-group.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);
    
    // get alumno to be set on formGroups inputs
    Alumno alumno = (Alumno) request.getAttribute("alumno");

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", alumno.getNombre(), "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Apellido", "apellido", alumno.getApellido(), "text", "apellido", "text-apellido", true));
    formGroups.add(
            new FormGroup("edad", "edad", Integer.toString(alumno.getId()), "number", "edad", "text-edad", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Actualizar alumnos"
    navbarActiveLink="alumnos"
    navbarBgColor="bg-danger"
>

<t:mainContentTemplate>
    
    <t:mainCardTemplate cardTitle="Actualizar alumno">
        <form action="actualizarAlumno" method="POST">                       

            <input id="id-alumno" type="hidden" name="id" value="${alumno.id}">
            
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
                <jsp:param name="path" value="alumnos" />
                <jsp:param name="btnName" value="Volver" />
            </jsp:include> 

        </form> 

        <%-- alert message --%>
        <c:if 
            test="${not empty success}" 
            var="isSuccess" 
            scope="request"
        >                     
            <jsp:include page="${component.alertMessage}">
                <jsp:param name="isSuccess" value="true" />
                <jsp:param name="entidad" value="alumno" />
                <jsp:param name="accion" value="actualizado" />
            </jsp:include>
        </c:if>
    </t:mainCardTemplate>
    
</t:mainContentTemplate>

</t:baseTemplate>

<script>
    <%-- fix parameter in browser after POST update --%>
    $(document).ready(() => {
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, location.pathname].join('')
            window.history.replaceState({}, "", baseUrl + "?id=" + $('#id-alumno').val())
        }    
    }) 
</script>