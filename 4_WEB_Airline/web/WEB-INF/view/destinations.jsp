<%-- 
    Document   : destinations
    Created on : Jan 5, 2015, 7:39:36 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
<fmt:setBundle basename="strings"/>
    <fmt:message key="destinations.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <table border=1>
            <thead>
                <tr>
                    <th><fmt:message key="destinations.id_airport"/></th>
                    <th><fmt:message key="destinations.field_title"/></th>
                    <th><fmt:message key="destinations.city"/></th>
                    <th><fmt:message key="destinations.country"/></th>
                    <th colspan=2><fmt:message key="crud.action"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${airports}" var="airport">
                    <tr>
                        <td><c:out value="${airport.idIATACode}" /></td>
                        <td><c:out value="${airport.title}" /></td>
                        <td><c:out value="${airport.city}" /></td>
                        <td><c:out value="${airport.country}" /></td>
                        <td><a href="AirportController?action=edit&airport=<c:out value="${airport.idIATACode}"/>"><fmt:message key="crud.action.update"/></a></td>
                        <td><a href="AirportController?action=delete&airport=<c:out value="${airport.idIATACode}" />" onclick="return confirm('<fmt:message key="crud.action.delete.confirm"/>')"><fmt:message key="crud.action.delete"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="AirportController?action=insert"><fmt:message key="destinations.add"/></a></p>
    </body>
</html>
