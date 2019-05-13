<%-- 
Document   : registrarAlumno
Created on : May 13, 2019, 11:51:37 AM
Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entidades.Alumno"%>
<%@page import="java.util.List"%>

<jsp:include page='../components/common/head.jsp'/>

<jsp:include page='../components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-danger" />
</jsp:include>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card card-alumno">
                <div class="card-header">
                    <h4 class="card-title text-center">Alumnos</h4>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Registrar alumno</h5>
                    <form action="registrarAlumno" method="POST">                   

                        <jsp:include page='../components/form-group.jsp'>
                            <jsp:param name="label" value="Nombre:" />
                            <jsp:param name="name" value="nombre" />
                            <jsp:param name="value" value="" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="placeholder" value="nombre" />
                            <jsp:param name="id" value="text-nombre" />
                            <jsp:param name="required" value="true" />
                        </jsp:include>

                        <jsp:include page='../components/form-group.jsp'>
                            <jsp:param name="label" value="Apellido:" />
                            <jsp:param name="name" value="apellido" />
                            <jsp:param name="value" value="" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="placeholder" value="apellido" />
                            <jsp:param name="id" value="text-apellido" />
                            <jsp:param name="required" value="true" />
                        </jsp:include>

                        <jsp:include page='../components/form-group.jsp'>
                            <jsp:param name="label" value="Edad:" />
                            <jsp:param name="name" value="edad" />
                            <jsp:param name="value" value="" />
                            <jsp:param name="type" value="number" />
                            <jsp:param name="placeholder" value="edad" />
                            <jsp:param name="id" value="text-edad" />
                            <jsp:param name="required" value="true" />
                        </jsp:include>

                        <jsp:include page='../components/button-form.jsp'>
                            <jsp:param name="color" value="primary" />
                            <jsp:param name="value" value="Registrar" />
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
                    ? "El alumno ha sido registrado satisfactoriamente."
                    : "El alumno no ha podido ser registrado.")
        }
    });
</script>