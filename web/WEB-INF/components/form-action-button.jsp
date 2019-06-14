<%-- 
    Document   : form-action-button
    Created on : May 15, 2019, 9:01:13 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form     
    action="<c:url value="${param.formAction}" />"
    method="${param.formMethod}"
>
    <input 
        type="hidden" 
        name="id" 
        value="${param.inputHiddenValue}"
    > 
     <input 
        type="hidden" 
        name="prueba" 
        value="${param.inputHiddenValuePrueba}"
    >
    <button class="btn btn-block btn-${param.btnType} mb-2">
        ${param.btnName}
    </button>
</form>    