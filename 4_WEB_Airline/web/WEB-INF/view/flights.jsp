<%-- 
    Document   : flights
    Created on : Dec 30, 2014, 12:24:22 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="flights.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <table border=1>
            <thead>
                <tr>
                    <th><fmt:message key="flights.flight_num"/></th>
                    <th><fmt:message key="flights.dep_airport"/></th>
                    <th><fmt:message key="flights.arr_airport"/></th>
                    <th><fmt:message key="flights.dep_time"/></th>
                    <th><fmt:message key="flights.arr_time"/></th>
                    <th colspan=2><fmt:message key="crud.action"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${flights}" var="flight">
                    <tr>
                        <td><c:out value="${flight.flightNumber}" /></td>
                        <td>
                            <c:out value="${flight.departureAirport.title}" />
                            <c:out value="-" />
                            <c:out value="${flight.departureAirport.idIATACode}" />
                            <c:out value="(${flight.departureAirport.city}," />
                            <c:out value="${flight.departureAirport.country})" />
                        </td>
                        <td>
                            <c:out value="${flight.arrivalAirport.title}" />
                            <c:out value="-" />
                            <c:out value="${flight.arrivalAirport.idIATACode}" />
                            <c:out value="(${flight.arrivalAirport.city}," />
                            <c:out value="${flight.arrivalAirport.country})" />
                        </td>
                        <td><c:out value="${flight.departureTime}" /></td>
                        <td><c:out value="${flight.arrivalTime}" /></td>
                        <td><a href="FlightController?action=edit&flight=<c:out value="${flight.flightNumber}"/>"><fmt:message key="crud.action.update"/></a></td>
                        <td><a href="FlightController?action=delete&flight=<c:out value="${flight.flightNumber}" />" onclick="return confirm('<fmt:message key="crud.action.delete.confirm"/>')"><fmt:message key="crud.action.delete"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="FlightController?action=insert"><fmt:message key="flights.add"/></a></p>
    </body>
    </html>
