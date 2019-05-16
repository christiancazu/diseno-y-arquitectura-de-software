<%-- 
    Document   : actualizarAlumno
    Created on : May 13, 2019, 1:06:38 PM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="elements.FormGroup"%>
<%@page import="entidades.Alumno"%>

<jsp:include page='../components/common/head.jsp'/>

<jsp:include page='../components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-danger" />
</jsp:include>

<% 
    Alumno alumno = (Alumno) request.getAttribute("alumno");
    
    // components patchs
    String formGroupComponent = "../components/form-group.jsp";
    String buttonFormComponent = "../components/button-form.jsp";
    
    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", alumno.getNombre(), "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Apellido:", "apellido", alumno.getApellido(), "text", "apellido", "text-apellido", true));
    formGroups.add(
            new FormGroup("Edad:", "edad", alumno.getEdad().toString(), "number", "edad", "text-edad", true));   
%>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card card-alumno">
                <div class="card-header">
                    <h4 class="card-title text-center">Alumnos</h4>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Actualizar alumno: Nº <%= alumno.getId() %></h5>
                    <form action="actualizarAlumno" method="POST">
                        
                        <input type="hidden" name="id" value="<%= alumno.getId() %>">
                        <%-- formGroups --%>
                        <%
                            for (FormGroup formGroup : formGroups) {                                      
                        %>
                        
                        <jsp:include page="<%= formGroupComponent %>">
                            <jsp:param name="label" value="<%= formGroup.getLabel()%>" />
                            <jsp:param name="name" value="<%= formGroup.getName()%>" />
                            <jsp:param name="value" value="<%= formGroup.getValue()%>" />
                            <jsp:param name="type" value="<%= formGroup.getType()%>" />
                            <jsp:param name="placeholder" value="<%= formGroup.getPlaceholder()%>" />
                            <jsp:param name="id" value="<%= formGroup.getId()%>" />
                            <jsp:param name="required" value="<%= formGroup.isRequired()%>" />
                        </jsp:include>
                        
                        <%
                            }
                        %> 

                        <jsp:include page="<%= buttonFormComponent %>">
                            <jsp:param name="color" value="primary" />
                            <jsp:param name="value" value="Actualizar" />
                        </jsp:include>

                        <a class="btn btn-block btn-md btn-outline-primary mt-2" 
                            href="<%= request.getContextPath()%>/alumnos">
                            Volver
                        </a>

                    </form>                    
                </div>
            </div>
        </div>
    </div>

    <div class="row justify-content-center mt-2">
        <div class="col-md-6">
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
    $(document).ready(function() { 
        let success = "<%= request.getAttribute("success")%>"
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
                    ? "El alumno ha sido actualizado satisfactoriamente."
                    : "El alumno no ha podido ser actualizado.")
        }
    });
</script>
