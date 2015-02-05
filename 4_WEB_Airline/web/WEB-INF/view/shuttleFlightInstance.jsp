<%-- 
    Document   : shuttleFlightInstance
    Created on : Jan 9, 2015, 4:35:23 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="shuttle_flight_instance.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    <form method="POST" action='ShuttleFlightsController'>

        <input type="hidden" name="page" value ="shuttleFlightInstance"/>

        <fmt:message key="shuttle_flight_instance.date"/> </br>
        <input type="text" name="date"value="" required="true" maxlength="10" width="330" style="width: 330px" /> </br> </br>

        <fmt:message key="shuttle_flight_instance.flight"/> </br>
        <select name="flight" required="true" width="330" style="width: 330px">
            <c:forEach items="${flights}" var="flight">
                <option value="<c:out value="${flight.flightNumber}"/>">
                    <c:out value="${flight.flightNumber}" />
                    <c:out value="- ${flight.departureAirport.city}" />
                    <c:out value="- ${flight.arrivalAirport.city}" />
                    <c:out value=" (${flight.departureTime}" />
                    <c:out value="- ${flight.arrivalTime})" />
                </option>
            </c:forEach>
        </select>
        </br>
        </br>

        <fmt:message key="shuttle_flight_instance.aircraft"/> </br>
        <select name="aircraft" required="true" width="330" style="width: 330px">
            <c:forEach items="${aircrafts}" var="aircraft">
                <option value="<c:out value="${aircraft.idReg}"/>">
                    <c:out value="${aircraft.manufacturer}" />
                    <c:out value="${aircraft.modelType}" />
                    <c:out value=" (${aircraft.idReg})"/>
                    <fmt:message key="shuttle_flight_instance.seats"/>
                    <c:out value="${aircraft.numSeats}" />
                </option>
            </c:forEach>
        </select>
        </br>
        </br>


        <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
    </form>
</body>
</html>