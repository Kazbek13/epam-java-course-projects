<%-- 
    Document   : header.jsp
    Created on : Jan 5, 2015, 2:02:47 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/DateTag.tld" prefix="dateTag" %>
<!DOCTYPE html>
    <fmt:setBundle basename="strings"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.title}</title>
    </head>
    <body>
        
        <c:if test="${employee != null}">
            <fmt:message key="header.salutation"/>
            <c:out value="${employee.name}!"/>
            |
            <a href="logout"><fmt:message key="header.logout"/></a>
        </c:if>
        <c:if test="${employee == null}">
            <a href="login"><fmt:message key="header.login"/></a>
        </c:if>
            |
        <a href="setlang?lang=en">EN</a>
        <a href="setlang?lang=ru">RU</a>
            |
        <fmt:message key="header.time"/>
        <dateTag:today format="HH:mm"/>
        <hr>
        
        <h2>${param.title} | <a href="index"><fmt:message key="header.main"/></a></h2>
