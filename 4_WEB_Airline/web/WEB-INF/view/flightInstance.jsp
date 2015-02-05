<%-- 
    Document   : flightInstance
    Created on : Jan 2, 2015, 9:55:03 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="flight_instance.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    <form method="POST" action='FlightController'>
        <label for="flightNumber"><fmt:message key="flight_instance.flight_number"/></label> <br/>
        <input type="text" name="flightNumber" id="flightNumber" value="<c:out value="${flightInstance.flightNumber}" />" required="true" maxlength="8" width="330" style="width: 330px"/> <br /> <br />

        <label for="departureAirport"><fmt:message key="flight_instance.dep_airport"/></label> <br/>
        <select name="departureAirport" id="departureAirport" required="true" width="330" style="width: 330px">
            <c:forEach items="${airports}" var="airport" >
                <option ${flightInstance.departureAirport.idIATACode == airport.idIATACode
                          ? 'selected="selected"' 
                          : ''
                          } value="<c:out value="${airport.idIATACode}"/>">
                    <c:out value="${airport.title}" />
                    <c:out value="-" />
                    <c:out value="${airport.idIATACode}" />
                    <c:out value="(${airport.city}," />
                    <c:out value="${airport.country})" />
                </option>
            </c:forEach>
        </select>
        <br/>
        <br />

        <label for="arrivalAirport"><fmt:message key="flight_instance.arr_airport"/></label> <br/>
        <select name="arrivalAirport" id="arrivalAirport" required="true" width="330" style="width: 330px">
            <c:forEach items="${airports}" var="airport">
                <option ${flightInstance.arrivalAirport.idIATACode == airport.idIATACode
                          ? 'selected="selected"' 
                          : ''
                          } value="<c:out value="${airport.idIATACode}"/>">
                    <c:out value="${airport.title}" />
                    <c:out value="-" />
                    <c:out value="${airport.idIATACode}" />
                    <c:out value="(${airport.city}," />
                    <c:out value="${airport.country})" />
                </option>
            </c:forEach>
        </select>
        <br/>
        <br />

        <label for="departureTime"><fmt:message key="flight_instance.dep_time"/></label> <br/>
        <input type="text" name="departureTime" id="departureTime" value="<c:out value="${flightInstance.departureTime}" />" required="true" maxlength="8" width="330" style="width: 330px" /> <br /> <br />

        <label for="arrivalTime"><fmt:message key="flight_instance.arr_time"/></label> <br/>
        <input type="text" name="arrivalTime" id="arrivalTime" value="<c:out value="${flightInstance.arrivalTime}" />" required="true" maxlength="8" width="330" style="width: 330px"/> <br /> <br />

        <c:if test="${isUpdating}">
            <input type="hidden" name="isUpdating" value ="True"/>
            <input type="hidden" name="oldFlightNumber" value ="${flightInstance.flightNumber}"/>
        </c:if>

        <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
    </form>
</body>
</html>
