<!DOCTYPE html>
<%@page import="Entidad.Alumno"%>
<%@page import="java.util.List"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista Alumnos</title>
        <link rel="stylesheet" type="text/css" href="css/estilo.css" />
    </head>
    <body>
        <h1>Listado de Alumnos</h1>

        <a href="listaAlumno">ver data</a>

        <br><br><br>
        <table>
            <caption class="grilla_titulo"> Lista de Alumnos</caption>	

            <tr class="grilla_cabecera">
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Edad</th>
                <th>Acción</th>
            </tr>
            <!-- 
                Scriplet: son inserciones de codigo java dentro un JSP    <% %>
                Expression: son resultados de codigo java que se va visualizar en el JSP < %=   %>    
            -->
            <%
                List<Alumno> a = (List<Alumno>) request.getAttribute("alumnos");
                if (a != null) {
                    for (Alumno aux : a) {
            %>
            <tr class="grilla_campo"> 
                <td><%= aux.getIdAlumno()%></td>
                <td><%= aux.getNombre()%></td>
                <td><%= aux.getApellido()%></td>
                <td><%= aux.getEdad()%></td>
                <td>
                    <form action="actualizarAlumno" method="get">
                        <input type="hidden" name="id" value="<%= aux.getIdAlumno() %>">
                        <button>Actualizar</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>

        </table>
    </body>
</html>