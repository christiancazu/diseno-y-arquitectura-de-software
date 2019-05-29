<%-- 
    Document   : form-action-button
    Created on : May 15, 2019, 9:01:13 PM
    Author     : christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form 
    action="${param.action}"
    method="${param.method}"
>
    <input 
        type="hidden" 
        name="id" 
        value="${param.value}"
    >                                
    <button class="btn btn-${param.btnType} mx-2">
        ${param.btnName}
    </button>
</form>
    