<%-- 
    Document   : select-option
    Created on : Jun 4, 2019, 4:11:07 PM
    Author     : Christian Carrillo Zúñiga
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <label for="">${param.label}</label>
    <select id="select" class="form-control" name="genero" required>
        <option value="" selected disabled hidden>Elija una opción</option>
        <c:forEach var="option" items="${options}">
            <option value="${option.getId()}">
                ${option.getNombre()}
            </option>
        </c:forEach>
    </select>
</div>
