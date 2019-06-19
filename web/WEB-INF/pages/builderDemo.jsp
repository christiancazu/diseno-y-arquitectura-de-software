<%-- 
    Document   : builderDemo
    Created on : Jun 14, 2019, 1:18:56 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.HashMap"%>
<%@page import="HTMLbuilders.FormActionButtonBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="HTMLcomponents.FormActionButton"%>
<%@page import="java.util.List"%>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formActionButton", "/WEB-INF/components/form-action-button.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // buttons context
    List<FormActionButton> formActionButtons = new ArrayList();

    formActionButtons.add(
            new FormActionButtonBuilder()
                    .formAction("/builder/demo")
                    .formMethod("POST")
                    .inputHiddenValue("base")
                    .btnType("success")
                    .btnName("Crear").build());

    formActionButtons.add(
            new FormActionButtonBuilder()
                    .formAction("/builder/demo")
                    .formMethod("POST")
                    .inputHiddenValue("medio")
                    .btnType("warning")
                    .btnName("Crear").build());

    formActionButtons.add(
            new FormActionButtonBuilder()
                    .formAction("/builder/demo")
                    .formMethod("POST")
                    .inputHiddenValue("full")
                    .btnType("info")
                    .btnName("Crear").build());

    request.setAttribute("formActionButtons", formActionButtons);
%>


<t:baseTemplate
    pageTitle="BuilderDemo"
    navbarBgColor="bg-danger"
    navbarActiveLink="builder"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate
            cardTitle="Patron builder"
            >
            
             <%-- alert message --%>    
                <c:if 
                    test="${not empty success}" 
                    var="isSuccess" 
                    scope="request"
                    >                     
                    <jsp:include page="${component.alertMessage}">
                        <jsp:param name="isSuccess" value="true" />
                        <jsp:param name="entidad" value="auto" />
                        <jsp:param name="accion" value="creado" />
                    </jsp:include>
                </c:if>

            <div class="row">
                <c:forEach var="fab" items="${formActionButtons}">
                    <div class="col-sm-4">
                        <div class="card shadow">
                            <img class="card-img-top" src="<c:url value="/resources/img/auto-${fab.inputHiddenValue}.jpg"/>" alt="Card image cap" style="height: 160px">
                            <div class="card-body">
                                <h5 class="card-title text-center">Auto ${fab.inputHiddenValue}</h5>                               
                                <jsp:include page="${component.formActionButton}">
                                    <jsp:param name="formAction" value="${fab.formAction}" />
                                    <jsp:param name="formMethod" value="${fab.formMethod}" />
                                    <jsp:param name="inputHiddenValue" value="${fab.inputHiddenValue}" />
                                    <jsp:param name="btnType" value="${fab.btnType}" />
                                    <jsp:param name="btnName" value="${fab.btnName}" />
                                </jsp:include>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>    

             <%-- card auto --%>
            <c:forEach var="auto" items="${autos}">
                <div class="card shadow my-4">
                    <div class="row no-gutters">
                        <div class="col-md-4">                           
                            <img src="<c:url value="/resources/img/auto-${auto.getMotor() == 'Motor de potencia mínima' ? 'base' : auto.getMotor() == 'Motor de potencia media' ? 'medio' : 'full'}.jpg" />" class="card-img" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body" style="padding: 1.25rem 1.25rem 0">
                                <h5 class="card-title"><span class="badge badge-secondary">${auto.getMotor()}</span></h5>
                                <p class="card-text">${auto.getCarroceria()}</p>
                                <div class="row">
                                    <h6 class="col-sm-6">Ventanillas eléctricas: <span class="badge badge-secondary">${auto.getVentanillasElectricas() ? 'Si' : 'No'}</span></h6>
                                    <h6 class="col-sm-6">Aire acondicionado: <span class="badge badge-secondary">${auto.getAireAcond() ? 'Si' : 'No'}</span></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </t:mainCardTemplate>

    </t:mainContentTemplate>

</t:baseTemplate>

<script>
    $("#nav-builder").addClass("active")
</script>