<%-- 
    Document   : registroAlumno
    Created on : May 8, 2019, 3:08:23 PM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro Alumno</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/estilo.css" />
    </head>
    <body>
        <div class="titulo">Web Colegio</div>   
        
        <form action="registroAlumno" method="post">
            
            <label class="loginTitle">Registro Alumno</label>  <br><br>
            <label class="loginLabel">Nombres:</label>
            <input class="loginControl" type="text" name="txtNombres" required>  <br>
            <label class="loginLabel">Apellidos</label>
            <input class="loginControl" type="text" name="txtApellidos" required>  <br>
            <label class="loginLabel">Edad</label>
            <input class="loginControl" type="number" name="txtEdad" required>  <br>
            <input class="loginButton" type="submit" value="Registrar">
        </form>
        
        <!-- mensaje resultado al intentar registrar alumno -->
        <% if (request.getAttribute("mensaje") != null) { %>
        <h1><%= request.getAttribute("mensaje") %></h1>        
        <% } %>
        
        <a href="index.html">
            <button>Volver</button>
        </a>

    </body>
</html>
