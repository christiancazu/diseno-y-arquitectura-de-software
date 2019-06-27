<%-- 
    Document   : alert-message
    Created on : May 31, 2019, 2:00:59 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%
    String successMessage = "ha sido " + request.getParameter("accion") + " satisfactoriamente.";
    String failedMessage = "no ha podido ser " + request.getParameter("accion");
    
    request.setAttribute("successMessage", successMessage);
    request.setAttribute("failedMessage", failedMessage);
%>

<div class="alert alert-dismissible show mt-2
     ${param.isSuccess                  
       ? "alert-success" 
       : "alert-danger"}"
     role="alert"
     >
    <h6 class="text-center my-0">
        El ${param.entidad} ${param.isSuccess ? successMessage : failedMessage}
    </h6>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>