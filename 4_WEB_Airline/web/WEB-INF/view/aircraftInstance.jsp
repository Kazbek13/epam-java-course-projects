<%-- 
    Document   : aircraftInstance
    Created on : Jan 17, 2015, 10:11:34 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="aircraft_instance.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <form method="POST" action='AircraftController'>
            <label for="idReg"><fmt:message key="aircraft_instance.id_reg"/></label> <br/>
            <input type="text" name="idReg" id="idReg" required="true" maxlength="10" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="manufacturer"><fmt:message key="aircraft_instance.manufacturer"/></label> <br/>
            <input type="text" name="manufacturer" id="manufacturer" required="true" maxlength="45" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="modelType"><fmt:message key="aircraft_instance.model"/></label> <br/>
            <input type="text" name="modelType" id="modelType" required="true" maxlength="45" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numSeats"><fmt:message key="aircraft_instance.seats"/></label> <br/>
            <input type="text" name="numSeats" id="numSeats" required="true" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numPilots"><fmt:message key="aircraft_instance.pilots"/></label> <br/>
            <input type="text" name="numPilots" id="numPilots" required="true" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numNavigators"><fmt:message key="aircraft_instance.navigators"/></label> <br/>
            <input type="text" name="numNavigators" id="numNavigators" required="true" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numRadiomen"><fmt:message key="aircraft_instance.radiomen"/></label> <br/>
            <input type="text" name="numRadiomen" id="numRadiomen" required="true" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numEngineers"><fmt:message key="aircraft_instance.engineers"/></label> <br/>
            <input type="text" name="numEngineers" id="numEngineers" required="true" width="330" style="width: 330px"/> <br /> <br /> 
            
            <label for="numFlightAttendants"><fmt:message key="aircraft_instance.attendants"/></label> <br/>
            <input type="text" name="numFlightAttendants" id="numFlightAttendants" required="true" width="330" style="width: 330px"/> <br /> <br /> 

            <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
        </form>
    </body>
</html>
