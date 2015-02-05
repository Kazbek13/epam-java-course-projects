<%-- 
    Document   : flightPersonnelInstance
    Created on : Jan 18, 2015, 11:32:27 AM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="flight_personnel_instance.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <form method="POST" action='EmployeeController'>
            <label for="name"><fmt:message key="flight_personnel_instance.name"/></label> <br/>
            <input type="text" name="name" id="name" required="true" maxlength="45" width="200" style="width: 200px"/> <br /> <br /> 
            
            <label for="surname"><fmt:message key="flight_personnel_instance.surname"/></label> <br/>
            <input type="text" name="surname" id="surname" required="true" maxlength="45" width="200" style="width: 200px"/> <br /> <br /> 
            
            <label for="position"><fmt:message key="flight_personnel_instance.position"/></label> <br/>
            <select name="position" id="position" required="true" width="200" style="width: 200px">
                <option value="pilot">
                    <fmt:message key="flight_personnel.pilot"/>
                </option>
                <option value="attendant">
                    <fmt:message key="flight_personnel.attendant"/>
                </option>
                <option value="radioman">
                    <fmt:message key="flight_personnel.radioman"/>
                </option>
                <option value="engineer">
                    <fmt:message key="flight_personnel.engineer"/>
                </option>
                <option value="navigator">
                    <fmt:message key="flight_personnel.navigator"/>
                </option>
            </select> </br> </br>
            <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
        </form>
    </body>
</html>
