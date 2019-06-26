<%-- 
    Document   : navbar
    Created on : May 12, 2019, 10:54:07 PM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark ${param.bgNavbar} static-top fixed-top py-0">
    <div class="container">
        <a class="navbar-brand" href="<%= request.getContextPath()%>">
            <div class="navbar-nav">
                <div class="logo-container">
                    <img src="<%= request.getContextPath()%>/resources/images/utp.png" class="img-logo-size" alt="logo">
                </div>
            </div>
        </a>
        <h4 class="mt-2 text-light">Universidad Tecnológica del Perú</h4>        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link ${param.inicioActiveLink}" href="<%= request.getContextPath()%>">
                        Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.alumnosActiveLink}" href="<%= request.getContextPath()%>/alumnos">
                        Alumnos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.cursosActiveLink}" href="#">
                        Cursos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.administradoresActiveLink}" href="#">
                        Administradores
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
