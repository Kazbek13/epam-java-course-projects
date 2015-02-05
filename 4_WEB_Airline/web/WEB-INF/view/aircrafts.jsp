<%-- 
    Document   : aircrafts
    Created on : Jan 17, 2015, 9:36:02 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="aircrafts.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <table border=1>
            <thead>
                <tr>
                    <th><fmt:message key="aircrafts.reg_num"/></th>
                    <th><fmt:message key="aircrafts.manufacturer"/></th>
                    <th><fmt:message key="aircrafts.model"/></th>
                    <th><fmt:message key="aircrafts.seats"/></th>
                    <th><fmt:message key="aircrafts.pilots"/></th>
                    <th><fmt:message key="aircrafts.navigators"/></th>
                    <th><fmt:message key="aircrafts.radiomen"/></th>
                    <th><fmt:message key="aircrafts.engineers"/></th>
                    <th><fmt:message key="aircrafts.attendants"/></th>
                    <th colspan=1><fmt:message key="crud.action"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${aircrafts}" var="aircraft">
                    <tr>
                        <td><c:out value="${aircraft.idReg}" /></td>
                        <td><c:out value="${aircraft.manufacturer}" /></td>
                        <td><c:out value="${aircraft.modelType}" /></td>
                        <td><c:out value="${aircraft.numSeats}" /></td>
                        <td><c:out value="${aircraft.numPilots}" /></td>
                        <td><c:out value="${aircraft.numNavigators}" /></td>
                        <td><c:out value="${aircraft.numRadiomen}" /></td>
                        <td><c:out value="${aircraft.numEngineers}" /></td>
                        <td><c:out value="${aircraft.numFlightAttendants}" /></td>
                        <td><a href="AircraftController?action=delete&aircraft=<c:out value="${aircraft.idReg}" />" onclick="return confirm('<fmt:message key="crud.action.delete.confirm"/>')"><fmt:message key="crud.action.delete"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="AircraftController?action=insert"><fmt:message key="aircrafts.add"/></a></p>
    </body>
</html>
