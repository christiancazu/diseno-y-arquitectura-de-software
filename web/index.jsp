<%-- 
    Document   : index
    Created on : May 12, 2019, 9:36:48 PM
    Author     : Christian Carrillo Zúñiga
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

    component.put("formGroup", "/WEB-INF/components/form-group.jsp");
    component.put("buttonForm", "/WEB-INF/components/button-form.jsp");
    component.put("buttonGoTo", "/WEB-INF/components/button-go-to.jsp");
    component.put("alertMessage", "/WEB-INF/components/alert-message.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Usuario", "usuario", "", "text", "usuario", "text-usuario", true));
    formGroups.add(
            new FormGroup("Contraseña", "contrasena", "", "password", "contraseña", "text-contraseña", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Inicio"
    navbarBgColor="bg-transparent"
    navbarActiveLink="inicio"
    bgBody="img-utp-bg"
>
<t:mainContentTemplate>

    <t:mainCardTemplate 
        cardTitle="Iniciar Sesión"
        cardBg="card-login"
    >
        <form action="autenticarEmpleado" method="POST">                       

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
                <jsp:param name="value" value="Ingresar" />
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
                <jsp:param name="entidad" value="usuario" />
                <jsp:param name="accion" value="identificado" />
            </jsp:include>
        </c:if>
    </t:mainCardTemplate>

</t:mainContentTemplate>

</t:baseTemplate>
