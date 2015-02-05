<%-- 
    Document   : daoerror
    Created on : Jan 7, 2015, 8:20:39 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="crud_error.title"/></title>
    </head>
    <body>
        <h1><fmt:message key="${err}"/></h1>
        <fmt:message key="crud_error.instruction"/>
    </body>
</html>
