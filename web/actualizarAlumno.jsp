<%-- 
    Document   : actualizarAlumno
    Created on : May 8, 2019, 4:26:18 PM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="Entidad.Alumno"%>

<% Alumno alumno = (Alumno) request.getAttribute("alumno");%>

<html>
    <head>
        <title>Registro Alumno</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/estilo.css" />
    </head>
    <body>
        <div class="titulo">Web Colegio</div>   

        <form action="actualizarAlumno" method="post">
            <input type="hidden" name="id" value="<%= alumno.getIdAlumno() %>">

            <label class="loginTitle">Actualizar Alumno</label>  <br><br>
            
            <label class="loginLabel">Nombres:</label>
            <input class="loginControl" type="text" name="txtNombres" required
                   value="<%= alumno.getNombre() %>">  <br>
            
            <label class="loginLabel">Apellidos</label>
            <input class="loginControl" type="text" name="txtApellidos" required
                   value="<%= alumno.getApellido() %>">  <br>
            
            <label class="loginLabel">Edad</label>
            <input class="loginControl" type="number" name="txtEdad" required
                   value="<%= alumno.getEdad() %>">  <br>
            
            <input class="loginButton" type="submit" value="Actualizar">
        </form>

        <!-- mensaje resultado al intentar actualizar alumno -->
        <% if (request.getAttribute("mensaje") != null) {%>
        <h1><%= request.getAttribute("mensaje")%></h1>        
        <% }%>

        <a href="alumnos">
            <button>Volver</button>
        </a>

    </body>
</html>
