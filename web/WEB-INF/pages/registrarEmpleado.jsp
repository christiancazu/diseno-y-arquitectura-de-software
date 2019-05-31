<%-- 
    Document   : registrarEmpleado
    Created on : May 31, 2019, 3:54:39 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

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

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", "", "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Apellido", "apellido", "", "text", "apellido", "text-apellido", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Registrar empleados"
    navbarActiveLink="empleados"
    navbarBgColor="bg-danger"
>

<t:mainContentTemplate>
    
    <t:mainCardTemplate cardTitle="Registrar empleado">
        <form action="registrarEmpleado" method="POST">                       

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
                <jsp:param name="value" value="Registrar" />
            </jsp:include>

            <%-- button Volver --%>
            <jsp:include page="${component.buttonGoTo}">
                <jsp:param name="btnColor" value="outline-primary" />
                <jsp:param name="path" value="empleados" />
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
                <jsp:param name="entidad" value="empleado" />
                <jsp:param name="accion" value="registrado" />
            </jsp:include>
        </c:if>
    </t:mainCardTemplate>
    
</t:mainContentTemplate>

</t:baseTemplate>
