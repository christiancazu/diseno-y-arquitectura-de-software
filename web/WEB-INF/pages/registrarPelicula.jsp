<%-- 
    Document   : registrarPelicula
    Created on : Jun 3, 2019, 6:25:24 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.HashMap" %>

<%@page import="elements.FormGroup" %>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formGroup", "../components/form-group.jsp");
    component.put("buttonForm", "../components/button-form.jsp");
    component.put("buttonGoTo", "../components/button-go-to.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");
    component.put("selectOption", "../components/select-option.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Nombre:", "nombre", "", "text", "nombre", "text-nombre", true));
    formGroups.add(
            new FormGroup("Descripci�n", "descripcion", "", "text", "descripci�n", "text-descripcion", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Registrar pelicula"
    navbarActiveLink="peliculas"
    navbarBgColor="bg-danger"
    >

    <t:mainContentTemplate>

        <t:mainCardTemplate cardTitle="Registrar pelicula">
            
            <%-- alert message --%>    
            <c:if 
                test="${not empty success}" 
                var="isSuccess" 
                scope="request"
                >                     
                <jsp:include page="${component.alertMessage}">
                    <jsp:param name="isSuccess" value="true" />
                    <jsp:param name="entidad" value="pelicula" />
                    <jsp:param name="accion" value="registrada" />
                </jsp:include>
            </c:if>
            
            <form action="peliculas/registrar" method="POST" enctype="multipart/form-data">                       

                <%-- formGroups --%>
                <c:forEach var="formGroup" items="${formGroups}">
                    <jsp:include page="${component.formGroup}">
                        <jsp:param name="label" value="${formGroup.getLabel()}" />
                        <jsp:param name="name" value="${formGroup.getName()}" />
                        <jsp:param name="value" value="${formGroup.getValue()}" />
                        <jsp:param name="type" value="${formGroup.getType()}" />
                        <jsp:param name="placeholder" value="${formGroup.getPlaceholder()}" />
                        <jsp:param name="id" value="${formGroup.getId()}" />
                        <jsp:param name="required" value="${formGroup.isRequired()}" />
                    </jsp:include>
                </c:forEach>

                <%-- select-option component --%>    
                <c:set var="options" value="${generos}" scope="request" />
                <jsp:include page="${component.selectOption}">
                    <jsp:param name="label" value="G�nero" />
                </jsp:include>

                <div class="form-group">
                    <label for="">Subir im�gen:</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="input-group-text">Subir im�gen:</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="img-input" name="imagen"  accept=".jpg,.jpeg,.png" required
                                   aria-describedby="input-group-text">
                            <label id="file-label" class="custom-file-label" for="img-input">elija una im�gen</label>
                        </div>

                        <div class="container mt-2">
                            <div class="row justify-content-center">
                                <img id="img-preview" src="#" alt="" class="img-preview"/>
                            </div>
                        </div>
                    </div>
                </div>

                <%-- button Registrar --%>
                <jsp:include page="${component.buttonForm}">
                    <jsp:param name="color" value="info" />
                    <jsp:param name="value" value="Registrar" />
                </jsp:include>

                <%-- button Volver --%>
                <jsp:include page="${component.buttonGoTo}">
                    <jsp:param name="btnColor" value="primary" />
                    <jsp:param name="path" value="peliculas" />
                    <jsp:param name="btnName" value="Volver" />
                </jsp:include> 

            </form>
            
        </t:mainCardTemplate> 

    </t:mainContentTemplate>  

</t:baseTemplate>

<script>
    $(document).ready(() => {
        pathNameFixed = location.pathname.replace(/\/peliculas\/peliculas/, '/peliculas')
        if (!window.location.search) {
            baseUrl = [location.protocol, '//', location.host, pathNameFixed].join('')
            window.history.replaceState({}, "", baseUrl)
        }        
    })
    
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#img-preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#img-input").change(function () {
        readURL(this);
        $('#file-label').text(() => {
            return $(this).val() === null ? "Elija una imagen" : "Modificar imagen"
        })
    })
</script>
