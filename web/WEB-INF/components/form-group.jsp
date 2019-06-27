<%-- 
    Document   : form-group
    Created on : May 13, 2019, 1:29:18 AM
    Author     : Christian Carrillo Z��iga
--%>
<div class="form-group">
    <label>${param.labelText}</label>
    <input class="form-control"
        type="${param.inputType}"
        id="${param.inputId}"
        name="${param.inputName}"
        value="${param.inputValue}"       
        placeholder="${param.inputPlaceholder}"
        required="${param.inputRequired}"
    >
</div>