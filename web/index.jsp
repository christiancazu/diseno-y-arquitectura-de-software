<%-- 
    Document   : index
    Created on : May 12, 2019, 9:36:48 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page='components/common/head.jsp'>
    <jsp:param name="title" value="Inicio" />
</jsp:include>

<jsp:include page='components/common/navbar.jsp'>
    <jsp:param name="bgNavbar" value="bg-transparent" />
    <jsp:param name="inicioActiveLink" value="active" />
</jsp:include>

<div class="img-utp-bg" 
     style="background-image: url('<%= request.getContextPath()%>/resources/images/utp-campus.jpg')" />

<jsp:include page='components/common/foot.jsp'/>
