<%-- 
    Document   : index
    Created on : Dec 31, 2014, 4:45:56 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="index.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    
    <h3><fmt:message key="index.menu"/></h3>
    <c:if test="${employee != null}">
        <c:if test="${employee.position == 'admin'}">
            <ul>
                <li><a href="flights"><fmt:message key="index.link.flights"/></a></li>
                <li><a href="destinations"><fmt:message key="index.link.destinations"/></a></li>
                <li><a href="aircrafts"><fmt:message key="index.link.aircrafts"/></a></li>
                <li><a href="flightPersonnel"><fmt:message key="index.link.flight_personnel"/></a></li>
            </ul>
        </c:if>
        <c:if test="${employee.position == 'dispatcher'}">
            <ul>
                <li><a href="shuttleFlights"><fmt:message key="index.link.shuttle_flights"/></a></li>
            </ul>
        </c:if>
    </c:if >
    <c:if test="${employee == null}">
        <h4><fmt:message key="index.login_prompt"/></h4>
    </c:if>
    
</body>
</html>
