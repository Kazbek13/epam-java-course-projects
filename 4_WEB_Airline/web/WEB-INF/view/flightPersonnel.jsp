<%-- 
    Document   : flightPersonnel
    Created on : Jan 18, 2015, 11:32:04 AM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="flight_personnel.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <table border=1>
            <thead>
                <tr>
                    <th><fmt:message key="flight_personnel.name"/></th>
                    <th><fmt:message key="flight_personnel.surname"/></th>
                    <th><fmt:message key="flight_personnel.position"/></th>
                    <th colspan=1><fmt:message key="crud.action"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <td><c:out value="${employee.name}" /></td>
                        <td><c:out value="${employee.surname}" /></td>
                        <td><fmt:message key="flight_personnel.${employee.position}"/></td>
                        <td><a href="EmployeeController?action=delete&id=<c:out value="${employee.id}" />" onclick="return confirm('<fmt:message key="crud.action.delete.confirm"/>')"><fmt:message key="crud.action.delete"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="EmployeeController?action=insert"><fmt:message key="flight_personnel.add"/></a></p>
    </body>
</html>
