<%-- 
    Document   : index
    Created on : Jun 13, 2019, 11:31:22 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@page import="HTMLbuilders.FormGroupBuilder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="HTMLcomponents.FormGroup"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<%
    // components paths
    HashMap<String, String> component = new HashMap();

    component.put("formGroup", "WEB-INF/components/form-group.jsp");
    component.put("buttonForm", "/WEB-INF/components/button-form.jsp");
    component.put("buttonGoTo", "/WEB-INF/components/button-go-to.jsp");
    component.put("alertMessage", "/WEB-INF/components/alert-message.jsp");
    component.put("selectOption", "/WEB-INF/components/select-option.jsp");

    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroupBuilder()
                    .inputId("ga")
                    .inputName("nombre")
                    .inputPlaceholder("nombre")
                    .inputType("text")
                    .inputValue("")
                    .labelText("inser name:")
                    .build());

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:htmlBaseTemplate
    pageTitle="Inicio"
    navbarBgColor="bg-transparent"
    navbarActiveLink="inicio"
    >
    <t:basicContentTemplate contentTitle="inicio">

        <c:forEach var="fg" items="${formGroups}">

            <jsp:include page="${component.formGroup}">
                <jsp:param name="inputId" value="${fg.inputId}" />
                <jsp:param name="inputName" value="${fg.inputName}" />
                <jsp:param name="inputPlaceholder" value="${fg.inputPlaceholder}" />
                <jsp:param name="inputType" value="${fg.inputType}" />
                <jsp:param name="inputValue" value="${fg.inputValue}" />
                <jsp:param name="labelText" value="${fg.labelText}" />
            </jsp:include>       

        </c:forEach>

    </t:basicContentTemplate>

</t:htmlBaseTemplate>