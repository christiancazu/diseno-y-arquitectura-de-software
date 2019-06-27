<%-- 
    Document   : select-option
    Created on : Jun 4, 2019, 4:11:07 PM
    Author     : Christian Carrillo Z��iga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label>${param.labelText}</label>
    <select class="form-control" 
        id="${param.selectId}"        
        name="${param.selectName}" 
        required
    >        
        <option value="" selected disabled hidden>Elija una opci�n</option>
        <c:forEach var="option" items="${options}">
            <option value="${option.getId()}">
                ${option.getNombre()}
            </option>
        </c:forEach>
    </select>
</div>