<%-- 
    Document   : form-check
    Created on : May 13, 2019, 1:00:09 AM
    Author     : Christian Carrillo Z��iga
--%>
<div class="form-check">
    <input class="form-check-input" 
        type="radio" 
        name="${param.name}"
        id="${param.id}"
        value="${param.value}"
    >
    <label class="form-check-label" 
        for="${param.id}"
    >
        ${param.label}
    </label>
</div>
