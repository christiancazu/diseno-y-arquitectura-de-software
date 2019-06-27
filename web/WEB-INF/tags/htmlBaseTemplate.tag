<%-- 
    Document   : htmlBaseTemplate
    Created on : May 30, 2019, 1:43:25 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@tag 
    description="HTML base template: include head, body, foot" 
    pageEncoding="UTF-8"
%>

<%@attribute name="navbarBgColor" %>
<%@attribute name="navbarActiveLink" %>
<%@attribute name="pageTitle" %>
<%@attribute name="bgBody" %>

<jsp:include page='/WEB-INF/components/common/head.jsp'>
    <jsp:param name="pageTitle" value="${pageTitle}" />
    <jsp:param name="bgBody" value="${bgBody}" />
</jsp:include>

<jsp:include page='/WEB-INF/components/common/navbar.jsp'>
    <jsp:param name="navbarBgColor" value="${navbarBgColor}" />
    <jsp:param name="navbarActiveLink" value="${navbarActiveLink}" />
</jsp:include>

<jsp:doBody />

<jsp:include page='/WEB-INF/components/common/foot.jsp' />