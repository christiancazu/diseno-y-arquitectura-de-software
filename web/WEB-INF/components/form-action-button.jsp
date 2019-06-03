<%-- 
    Document   : form-action-button
    Created on : May 15, 2019, 9:01:13 PM
    Author     : Christian Carrillo Z��iga
--%>
<form 
    action="${param.action}"
    method="${param.method}"
>
    <input 
        type="hidden" 
        name="id" 
        value="${param.value}"
    >                                
    <button class="btn btn-block btn-${param.btnType} mb-2">
        ${param.btnName}
    </button>
</form>
    