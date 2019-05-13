<%-- 
    Document   : navbar
    Created on : May 12, 2019, 10:54:07 PM
    Author     : Christian Carrillo Zúñiga
--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top fixed-top py-0">
    <div class="container">
        <a class="navbar-brand" href="#">
            <div class="navbar-nav">
                <div class="logo-container">
                    <img src="<%= request.getContextPath()%>/resources/images/utp.png" class="img-logo-size" alt="logo">
                </div>                
                <h4 class="ml-4 mt-2">Universidad Tecnológica del Perú</h4>
            </div>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%= request.getContextPath()%>">Inicio
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Alumnos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cursos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Administradores</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
