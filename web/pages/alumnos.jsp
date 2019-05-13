<%-- 
Document   : alumnos
Created on : May 13, 2019, 12:00:55 AM
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
                    <h5 class="card-title">Consultar alumnos</h5>
                    <p class="card-text">Listar por:</p>
                    <form action="alumnos" method="POST">                   

                        <jsp:include page='../components/form-check.jsp'>
                            <jsp:param name="value" value="porNombres" />
                            <jsp:param name="id" value="porNombres" />
                            <jsp:param name="label" value="nombres" />
                            <jsp:param name="name" value="tipoListado" />
                        </jsp:include>

                        <jsp:include page='../components/form-check.jsp'>
                            <jsp:param name="value" value="porApellidos" />
                            <jsp:param name="id" value="porApellidos" />
                            <jsp:param name="label" value="apellidos" />
                            <jsp:param name="name" value="tipoListado" />
                        </jsp:include>

                        <jsp:include page='../components/form-check.jsp'>
                            <jsp:param name="value" value="porTodos" />
                            <jsp:param name="id" value="porTodos" />
                            <jsp:param name="label" value="todos" />
                            <jsp:param name="name" value="tipoListado" />                            
                        </jsp:include>

                        <jsp:include page='../components/form-group.jsp'>
                            <jsp:param name="name" value="text" />
                            <jsp:param name="value" value="<%= request.getAttribute("text")%>" />
                                       <jsp:param name="type" value="text" />
                                <jsp:param name="placeholder" value="texto a buscar" />
                                <jsp:param name="id" value="text-filtro" />
                        </jsp:include>

                        <jsp:include page='../components/button-form.jsp'>
                            <jsp:param name="color" value="primary" />
                            <jsp:param name="value" value="Listar" />
                        </jsp:include>

                    </form>                    
                </div>
            </div>
        </div>
    </div>

    <%
    List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
            if ((alumnos != null) && !alumnos.isEmpty()) {
            %>
            <div class="row justify-content-center mt-4">
                <div class="col-md-8">
                    <table class="table table-striped text-center table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Edad</th>
                                <th class="text-center">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            for (Alumno a : alumnos) {
                            %>
                            <tr> 
                                <td><%= a.getId()%></td>
                                <td><%= a.getNombre()%></td>
                                <td><%= a.getApellido()%></td>
                                <td><%= a.getEdad()%></td>
                                <td class="d-flex justify-content-center">
                                    <form action="actualizarAlumno">
                                        <input type="hidden" name="id" value="<%= a.getId()%>">
                                        <button class="btn btn-success mx-2">
                                            Actualizar
                                        </button>
                                    </form>
                                    <form action="eliminarAlumno" method="POST">
                                        <input type="hidden" name="id" value="<%= a.getId()%>">
                                        <button class="btn btn-danger mx-2">
                                            Eliminar
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <%
                            }
                            }
                            Boolean result = (Boolean) request.getAttribute("result");
                            if (result != null && !result) {
                            %>
                        <div class="row justify-content-center mt-4">
                            <div class="alert alert-warning col-md-8" role="alert">
                                La busqueda no produjo resultados
                            </div>
                        </div>
                        <%
                        }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>

            <jsp:include page='../components/common/foot.jsp'/>
            <script>
                $(document).ready(function() {
                    let tipo = "<%= request.getAttribute("tipo")%>";

                    if (tipo == "null" || tipo == "") {
                        $("#porTodos").prop('checked', true);
                    } else {
                        $("#" + tipo).prop('checked', true);
                    }
                });
            </script>
