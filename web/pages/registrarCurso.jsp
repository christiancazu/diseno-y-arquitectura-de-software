<%-- 
    Document   : registrarCurso
    Created on : May 29, 2019, 4:13:11 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%@page import="elements.FormGroup"%>

<jsp:include page='../components/common/head.jsp'>
    <jsp:param name="title" value="Registrar alumno" />
</jsp:include>

<jsp:include page='../components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-danger" />
</jsp:include>

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

<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4 class="card-title text-center">Cursos</h4>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Registrar curso</h5>
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
                </div>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-2">
        <div class="col-md-8">
            <div id="alert" class="alert alert-dismissible fade d-none" role="alert">
                <h6 id="alert-message" class="text-center"></h6>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
    </div>    
</div>

<jsp:include page='../components/common/foot.jsp'/>

<script>
    $(document).ready(function () {
        let success = "<%= request.getAttribute("success")%>"
        console.log(success)
        if (success !== "null") {
            let $alert = $('#alert')
            $alert.alert()
            $alert.removeClass('d-none')
            $alert.addClass(
                    success === "true"
                    ? 'alert-success'
                    : 'alert-danger')
            $alert.addClass('show')

            $('#alert-message').append(
                    success === "true"
                    ? "El curso ha sido registrado satisfactoriamente."
                    : "El curso no ha podido ser registrado.")
        }
    });
</script>

