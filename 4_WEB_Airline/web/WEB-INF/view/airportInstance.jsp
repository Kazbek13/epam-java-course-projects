<%-- 
    Document   : airportInstance
    Created on : Jan 5, 2015, 8:42:00 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="airport_instance.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <form method="POST" action='AirportController'>
            <label for="idIATACode"><fmt:message key="airport_instance.iata_code"/></label> <br/>
            <input type="text" name="idIATACode" id="idIATACode" value="<c:out value="${airportInstance.idIATACode}" />" required="true" maxlength="4" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="title"><fmt:message key="airport_instance.name"/></label> <br/>
            <input type="text" name="title" id="title" value="<c:out value="${airportInstance.title}" />" required="true" maxlength="60" width="330" style="width: 330px" /> <br /> <br /> 
            
            <label for="city"><fmt:message key="airport_instance.city"/></label> <br/>
            <input type="text" name="city" id="city" value="<c:out value="${airportInstance.city}" />" required="true" maxlength="45" width="330" style="width: 330px" /> <br /> <br /> 
            
            <label for="country"><fmt:message key="airport_instance.country"/></label> <br/>
            <input type="text" name="country" id="country" value="<c:out value="${airportInstance.country}" />" required="true" maxlength="45" width="330" style="width: 330px" /> <br /> <br /> 

            <c:if test="${isUpdating}">
                <input type="hidden" name="isUpdating" value ="True"/>
                <input type="hidden" name="oldIATACode" value ="${airportInstance.idIATACode}"/>
            </c:if>

            <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
        </form>
    </body>
</html>
