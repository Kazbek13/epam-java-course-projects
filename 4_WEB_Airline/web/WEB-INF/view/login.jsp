<%-- 
    Document   : login
    Created on : Dec 31, 2014, 2:15:50 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="login.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
        <form action = "login" method="POST">
            <label for="login"><fmt:message key="login.login"/></label> <br/>
            <input type="text" id="login" name="login" required="true" width="150" style="width: 150px"/> <br/> <br/>
            <label for="password"><fmt:message key="login.password"/></label> <br/>
            <input type="password" id="password" name="password" required="true" width="150" style="width: 150px"/> <br/> <br/>
            <input type="submit" name="login_btn" value = "Log in"/>
        </form>
    </body>
</html>
