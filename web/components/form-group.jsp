<%-- 
    Document   : form-group
    Created on : May 13, 2019, 1:29:18 AM
    Author     : Christian Carrillo Zúñiga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="form-group">
    <label for="${param.id}">
        ${param.label}
    </label>
    <input class="form-control"
        value="${param.value}"
        type="${param.type}"
        id="${param.id}"
        name="${param.name}"
        placeholder="${param.placeholder}"
        required="${param.required}"
    >
</div>
