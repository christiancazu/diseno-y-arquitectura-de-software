<%-- 
    Document   : registrarCurso
    Created on : May 29, 2019, 4:13:11 PM
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

    component.put("formGroup", "../components/form-group.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", "", "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Docente", "docente", "", "text", "docente", "text-docente", true));
    formGroups.add(
            new FormGroup("Lugar", "lugar", "", "text", "lugar", "text-lugar", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Registrar cursos"
    navbarActiveLink="cursos"
    navbarBgColor="bg-danger"
>

<t:mainContentTemplate>
    
    <t:mainCardTemplate cardTitle="Registrar curso">
        <form action="registrarCurso" method="POST">                       

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
                       ? "ha sido registrado satisfactoriamente." 
                       : "no ha podido ser registrado."}
                </h6>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </t:mainCardTemplate>
    
</t:mainContentTemplate>

</t:baseTemplate>
