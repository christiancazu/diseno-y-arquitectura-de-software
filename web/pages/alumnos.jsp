<%-- 
    Document   : alumnos
    Created on : May 13, 2019, 12:00:55 AM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page import="entidades.Alumno"%>
<%@page import="elements.FormCheck"%>
<%@page import="elements.FormActionButton"%>

<jsp:include page='../components/common/head.jsp'>
    <jsp:param name="title" value="Alumnos" />
</jsp:include>

<jsp:include page='../components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-danger" />
    <jsp:param name="alumnosActiveLink" value="active" />
</jsp:include>

<%
    // components patchs
    String formCheckComponent = "../components/form-check.jsp";
    String formGroupComponent = "../components/form-group.jsp";
    String buttonFormComponent = "../components/button-form.jsp";
    String formActionButtonComponent = "../components/form-action-button.jsp";
    
    // context for formChecks components
    List<FormCheck> formChecks = new ArrayList();
        
    formChecks.add(new FormCheck("nombres", "cbx-nombres", "Nombres", "filtro"));
    formChecks.add(new FormCheck("apellidos", "cbx-apellidos", "Apellidos", "filtro"));
    formChecks.add(new FormCheck("todos", "cbx-todos", "Todos", "filtro"));
%>

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
                    <form action="alumnos" method="GET">                   
                        
                        <%-- formChecks --%>
                        <%
                            for (FormCheck formCheck : formChecks) {                                      
                        %>
                        
                        <jsp:include page="<%= formCheckComponent %>">
                            <jsp:param name="value" value="<%= formCheck.getValue() %>" />
                            <jsp:param name="id" value="<%= formCheck.getId()%>" />
                            <jsp:param name="label" value="<%= formCheck.getLabel()%>" />
                            <jsp:param name="name" value="<%= formCheck.getName()%>" />
                        </jsp:include>
                        
                        <%
                            }
                        %>

                        <%-- form-group label+input --%>
                        <jsp:include page="<%= formGroupComponent %>">
                            <jsp:param name="name" value="text" />
                            <jsp:param name="value" value="<%= request.getAttribute("text")%>" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="placeholder" value="texto a buscar" />
                            <jsp:param name="id" value="text-filtro" />
                            <jsp:param name="required" value="false" />
                        </jsp:include>

                        <%-- button Listar --%>
                        <jsp:include page="<%= buttonFormComponent %>">
                            <jsp:param name="color" value="primary" />
                            <jsp:param name="value" value="Listar" />
                        </jsp:include>
                        
                        <%-- button Registrar --%>
                        <a class="btn btn-block btn-md btn-outline-primary mt-2" 
                            href="registrarAlumno"
                        >
                            Registrar
                        </a>

                    </form>                    
                </div>
            </div>
        </div>
    </div>

    <%
        List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
        if (alumnos != null && !alumnos.isEmpty()) {
    %>
    <div id="alumnos-list-container" class="row justify-content-center mt-4">
        <div class="col-md-6">
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
                    List<FormActionButton> formActionButtons = new ArrayList();
                    formActionButtons.add(new FormActionButton(
                            "actualizarAlumno", "GET", "", "success", "Actualizar"));
                    formActionButtons.add(new FormActionButton(
                            "eliminarAlumno", "POST", "", "danger", "Eliminar"));
                    
                    for (Alumno alumno : alumnos) {
                %>
                    <tr> 
                        <td><%= alumno.getId()%></td>
                        <td><%= alumno.getNombre()%></td>
                        <td><%= alumno.getApellido()%></td>
                        <td><%= alumno.getEdad()%></td>
                        <td class="d-flex justify-content-center">
                            
                            <%
                                for (FormActionButton formActionButton : formActionButtons) {      
                            %>
                                                       
                            <jsp:include page="<%= formActionButtonComponent %>">
                                <jsp:param name="action" value="<%= formActionButton.getAction()%>" />
                                <jsp:param name="method" value="<%= formActionButton.getMethod()%>" />
                                <jsp:param name="value" value="<%= alumno.getId()%>" />
                                <jsp:param name="btnType" value="<%= formActionButton.getBtnType()%>" />
                                <jsp:param name="btnName" value="<%= formActionButton.getBtnName()%>" />
                            </jsp:include>
                            <%
                                }                  
                            %>
  
                        </td>
                    </tr>
                <%
                    }
                %>                    
                </tbody>
            </table>
        </div>
    </div>
    <% 
        } 
    %>
    
    <%        
        if (request.getAttribute("result") != null && !(Boolean)request.getAttribute("result")) {
    %>    
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <div class="alert alert-warning" role="alert">
                La busqueda no produjo resultados
            </div>
        </div>
    </div>
    <%
        }
    %>

</div>

<jsp:include page='../components/common/foot.jsp'/>

<script>
    $(document).ready(() => {
        $('#text-filtro').removeAttr('required')

        let filtro = "<%= request.getAttribute("filtro")%>"

        setCheckboxSelected(filtro)
        
        if($('#alumnos-list-container').length) {
            setArgsOnEliminarAlumno()
        } 
    })
    
    function setCheckboxSelected(filtro) {
        if (filtro === "null" || "") {
            $("#cbx-todos").prop('checked', true)
        } else {
            $("#cbx-" + filtro).prop('checked', true)
        }
    }   
    
    function setArgsOnEliminarAlumno() {        
        $("form").one('submit',function (e) {
            
            if($(this).attr("method") === "POST") {
            
                e.preventDefault()

                $form = $(this)

                appendIdOnForm($form)
                appendTextOnForm($form)

                $(this).submit();
            }
        })   
    }
    
    
    function appendIdOnForm (form) {
        $('<input />', {
            type: 'hidden',
            name: 'text',
            value: $('#text-filtro').val()
        }).appendTo(form);
    }
    
    function appendTextOnForm (form) {
        $('<input />', {
            type: 'hidden',
            name: 'filtro',
            value: $('input[name="filtro"]:checked').val()         
        }).appendTo(form);
    }
    
</script>
