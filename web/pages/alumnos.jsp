<%-- 
    Document   : alumnos
    Created on : May 13, 2019, 12:00:55 AM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

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
    // components paths
    HashMap<String, String> component = new HashMap();
    
    component.put("formCheck", "../components/form-check.jsp");
    component.put("formGroup", "../components/form-group.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("formActionButton", "../components/form-action-button.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);
    
    // context for formChecks components
    List<FormCheck> formChecks = new ArrayList();
        
    formChecks.add(new FormCheck("nombres", "cbx-nombres", "Nombres", "filtro"));
    formChecks.add(new FormCheck("apellidos", "cbx-apellidos", "Apellidos", "filtro"));
    formChecks.add(new FormCheck("todos", "cbx-todos", "Todos", "filtro"));
        
    // setting formChecks attribute as pageContext
    request.setAttribute("formChecks", formChecks);
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
                        <c:forEach var="formCheck" items="${formChecks}">
                            <jsp:include page="${component.formCheck}">
                                <jsp:param name="value" value="${formCheck.getValue()}" />
                                <jsp:param name="id" value="${formCheck.id}" />
                                <jsp:param name="label" value="${formCheck.label}" />
                                <jsp:param name="name" value="${formCheck.getName()}" />
                            </jsp:include>
                        </c:forEach>

                        <%-- form-group label+input --%>
                        <jsp:include page="${component.formGroup}">
                            <jsp:param name="name" value="text" />
                            <jsp:param name="value" value="<%= request.getAttribute("text")%>" />
                            <jsp:param name="type" value="text" />
                            <jsp:param name="placeholder" value="texto a buscar" />
                            <jsp:param name="id" value="text-filtro" />
                            <jsp:param name="required" value="false" />
                        </jsp:include>

                        <%-- button Listar --%>
                        <jsp:include page="${component.buttonForm}">
                            <jsp:param name="color" value="primary" />
                            <jsp:param name="value" value="Listar" />
                        </jsp:include>
                        
                        <%-- button Registrar --%>
                        <a class="btn btn-block btn-md btn-outline-primary mt-2" 
                            href="alumnos/registrar"
                        >
                            Registrar
                        </a>
                        
                    </form>  
                </div>
            </div>
        </div>
    </div>
    
    <c:if 
        test="${not empty alumnos}" 
        var="v" 
        scope="request"
    >    
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
                        // context for formActionButtons components
                        List<FormActionButton> formActionButtons = new ArrayList();
                        formActionButtons.add(new FormActionButton(
                                "alumnos/actualizar", "GET", "", "success", "Actualizar"));
                        formActionButtons.add(new FormActionButton(
                                "alumnos/eliminar", "POST", "", "danger", "Eliminar"));

                        // setting formActionButtons attribute as pageContext
                        request.setAttribute("formActionButtons", formActionButtons);
                    %>
                    <c:forEach var="alumno" items="${alumnos}">

                        <tr> 
                            <td>${alumno.getId()}</td>
                            <td>${alumno.getNombre()}</td>
                            <td>${alumno.getApellido()}</td>
                            <td>${alumno.getEdad()}</td>
                            <td class="d-flex justify-content-center">
                                <c:forEach var="formActionButton" items="${formActionButtons}">
                                    <jsp:include page="${component.formActionButton}">
                                        <jsp:param name="action" value="${formActionButton.getAction()}" />
                                        <jsp:param name="method" value="${formActionButton.getMethod()}" />
                                        <jsp:param name="value" value="${alumno.getId()}" />
                                        <jsp:param name="btnType" value="${formActionButton.getBtnType()}" />
                                        <jsp:param name="btnName" value="${formActionButton.getBtnName()}" />
                                    </jsp:include>
                                </c:forEach>
                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>                
    </c:if>
    
    <c:if 
        test="${not empty result && !result}" 
        var="v" 
        scope="request"
    >
        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="alert alert-warning" role="alert">
                    La busqueda no produjo resultados
                </div>
            </div>
        </div>
    </c:if>
    
</div>

<jsp:include page='../components/common/foot.jsp'/>

<script>   
    $(document).ready(() => {
        fixURL()
        
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
    
    function fixURL() {
        pathNameFixed = location.pathname.replace(/\/alumnos\/eliminar/, '/alumnos')
                
        console.warn(!!window.location.search)
                
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, pathNameFixed].join('')
            window.history.replaceState({}, "", baseUrl)
        }        
    }
    
</script>
