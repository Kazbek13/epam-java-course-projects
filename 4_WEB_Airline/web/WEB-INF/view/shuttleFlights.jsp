<%-- 
    Document   : shuttleFlights
    Created on : Jan 7, 2015, 10:55:00 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="shuttle_flights.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    <table border=1>
        <thead>
            <tr>
                <th><fmt:message key="shuttle_flights.date"/></th>
                <th><fmt:message key="shuttle_flights.flight_num"/></th>
                <th><fmt:message key="shuttle_flights.aircraft_reg"/></th>
                <th><fmt:message key="shuttle_flights.cabin_crew"/></th>
                <th colspan=1><fmt:message key="crud.action"/></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${shuttleFlights}" var="shuttleFlight">
                <tr>
                    <td><c:out value="${shuttleFlight.idFlightDate}" /></td>
                    <td><c:out value="${shuttleFlight.idFlight.flightNumber}" /></td>
                    <td><c:out value="${shuttleFlight.aircraft.idReg}" /></td>
                    <c:if test="${shuttleFlight.hasCabinCrew}">
                        <td><a href="ShuttleFlightsController?action=editCrew&flight=<c:out value="${shuttleFlight.idFlight.flightNumber}"/>&date=<c:out value="${shuttleFlight.idFlightDate}"/>"><fmt:message key="shuttle_flights.cabin_crew.edit"/></a></td>
                    </c:if>
                    <c:if test="${!shuttleFlight.hasCabinCrew}">
                        <td><a href="ShuttleFlightsController?action=editCrew&flight=<c:out value="${shuttleFlight.idFlight.flightNumber}"/>&date=<c:out value="${shuttleFlight.idFlightDate}"/>"><fmt:message key="shuttle_flights.cabin_crew.assign"/></a></td>
                    </c:if>
                    <td><a href="ShuttleFlightsController?action=delete&flight=<c:out value="${shuttleFlight.idFlight.flightNumber}"/>&date=<c:out value="${shuttleFlight.idFlightDate}"/>" onclick="return confirm('<fmt:message key="crud.action.delete.confirm"/>')"><fmt:message key="crud.action.delete"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="ShuttleFlightsController?action=insert"><fmt:message key="shuttle_flights.add"/></a></p>
</body>
</html>
