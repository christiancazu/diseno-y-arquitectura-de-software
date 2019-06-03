<%-- 
    Document   : index
    Created on : May 12, 2019, 9:36:48 PM
    Author     : Christian Carrillo Zúñiga
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

    component.put("formGroup", "/WEB-INF/components/form-group.jsp");
    component.put("buttonForm", "/WEB-INF/components/button-form.jsp");
    component.put("buttonGoTo", "/WEB-INF/components/button-go-to.jsp");
    component.put("alertMessage", "/WEB-INF/components/alert-message.jsp");
    
    // setting component attribute as pageContext
    request.setAttribute("component", component);

    // context for formGroups components
    List<FormGroup> formGroups = new ArrayList();

    formGroups.add(
            new FormGroup("Usuario", "usuario", "", "text", "usuario", "text-usuario", true));
    formGroups.add(
            new FormGroup("Contraseña", "contrasena", "", "password", "contraseña", "text-contraseña", true));

    // setting formGroups attribute as pageContext
    request.setAttribute("formGroups", formGroups);
%>

<t:baseTemplate
    pageTitle="Películas"
    navbarBgColor="bg-danger"
    navbarActiveLink="peliculas"
>
<t:mainContentTemplate>

    <t:mainCardTemplate 
        cardTitle="Películas"
    >
        
    </t:mainCardTemplate>

</t:mainContentTemplate>

</t:baseTemplate>
