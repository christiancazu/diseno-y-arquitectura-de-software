<%-- 
    Document   : form-action-button
    Created on : May 15, 2019, 9:01:13 PM
    Author     : Christian Carrillo Zúñiga
--%>
<form 
    action="${param.formAction}"
    method="${param.formMethod}"
>
    <input 
        type="hidden" 
        name="id" 
        value="${param.inputHiddenValue}"
    >                                
    <button class="btn btn-block btn-${param.btnType} mb-2">
        ${param.btnName}
    </button>
</form>    