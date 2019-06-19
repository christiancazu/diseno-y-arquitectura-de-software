<%-- 
    Document   : visitorDemo
    Created on : Jun 14, 2019, 4:48:04 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%@page import="java.util.HashMap"%>

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formActionButton", "/WEB-INF/components/form-action-button.jsp");
    component.put("alertMessage", "../components/alert-message.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

%>


<t:baseTemplate
    pageTitle="BuilderDemo"
    navbarBgColor="bg-danger"
    navbarActiveLink="builder"
    >
    <t:mainContentTemplate>

        <t:mainCardTemplate
            cardTitle="Patron visitor"
            >

            <%-- alert message --%>    
            <c:if 
                test="${not empty success}" 
                var="isSuccess" 
                scope="request"
                >                     
                <jsp:include page="${component.alertMessage}">
                    <jsp:param name="isSuccess" value="true" />
                    <jsp:param name="entidad" value="test" />
                    <jsp:param name="accion" value="realizado" />
                </jsp:include>
                
                <div class="card shadow my-4 bg-secondary text-light">
                    <div class="row no-gutters">
                        <div class="col-md-4">                           
                            <img src="<c:url value="/resources/img/auto-${autoSelected.getTipo()}.jpg" />" class="card-img" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body" style="padding: 1.25rem 1.25rem 0">
                                <h5 class="card-title"><span class="badge badge-light">PRUEBA DE ACELERACIÓN: </span> Auto ${autoSelected.getTipo()}</h5>
                                <p class="card-text">Resultado de la prueba en ${prueba}</p>
                                <div class="row">
                                    <h4 class="col-sm-12">Aceleración: <span class="badge badge-light">${resultado} Km/h</span></h4>
                                </div>                                
                            </div>
                        </div>
                    </div>            
                </div>
                
            </c:if>           

            <%-- card auto --%>
            <c:forEach var="auto" items="${autos}" varStatus="counter">
                <div class="card shadow my-4">
                    <div class="row no-gutters">
                        <div class="col-md-4">                           
                            <img src="<c:url value="/resources/img/auto-${auto.getMotor() == 'Motor de potencia mínima' ? 'base' : auto.getMotor() == 'Motor de potencia media' ? 'medio' : 'full'}.jpg" />" class="card-img" alt="...">
                        </div>
                        <div class="col-md-6">
                            <div class="card-body" style="padding: 1.25rem 1.25rem 0">
                                <h5 class="card-title"><span class="badge badge-secondary">${auto.getMotor()}</span></h5>
                                <p class="card-text">${auto.getCarroceria()}</p>
                                <div class="row">
                                    <h6 class="col-sm-6 px-0">Ventanillas eléctricas: <span class="badge badge-secondary">${auto.getVentanillasElectricas() ? 'Si' : 'No'}</span></h6>
                                    <h6 class="col-sm-6 px-0">Aire acondicionado: <span class="badge badge-secondary">${auto.getAireAcond() ? 'Si' : 'No'}</span></h6>
                                </div>                                
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div>
                                <p class="card-text text-center my-0 mt-1">Prueba de aceleración:</p>  
                                <div class="container">
                                    <jsp:include page="${component.formActionButton}">
                                        <jsp:param name="formAction" value="/visitor/demo" />
                                        <jsp:param name="formMethod" value="POST" />
                                        <jsp:param name="inputHiddenValue" value="${counter.index}" />
                                        <jsp:param name="inputHiddenValuePrueba" value="plano" />
                                        <jsp:param name="btnType" value="info" />
                                        <jsp:param name="btnName" value="Plano" />
                                    </jsp:include>
                                    <jsp:include page="${component.formActionButton}">
                                        <jsp:param name="formAction" value="/visitor/demo" />
                                        <jsp:param name="formMethod" value="POST" />
                                        <jsp:param name="inputHiddenValue" value="${counter.index}" />
                                        <jsp:param name="inputHiddenValuePrueba" value="curva" />
                                        <jsp:param name="btnType" value="warning" />
                                        <jsp:param name="btnName" value="Curva" />
                                    </jsp:include>
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
    $("#nav-visitor").addClass("active")
</script>