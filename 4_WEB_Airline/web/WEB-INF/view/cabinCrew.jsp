<%-- 
    Document   : cabinCrew
    Created on : Jan 8, 2015, 2:30:41 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
    <fmt:setBundle basename="strings"/>
    <fmt:message key="cabin_crew.title" var="pageTitle"/>
    <jsp:include flush="true" page="header.jsp">
        <jsp:param name="title" value="${pageTitle}"/>
    </jsp:include>
    <form method="POST" action='ShuttleFlightsController'>
        <c:if test="${needed_pilots > 0}">
            <fmt:message key="cabin_crew.pilots"/>
            </br>
            <c:forEach var = "i" begin = "0" end = "${needed_pilots-1}">
                <select name="pilot${i}" required="true" width="330" style="width: 330px">
                    <c:forEach items="${pilots}" var="pilot" >
                        <option ${assignedPilots[i].id == pilot.id
                                  ? 'selected="selected"' 
                                  : ''
                                  }value ="${pilot.id}" >
                            <c:out value="${pilot.name}" />
                            <c:out value="${pilot.surname}" />
                        </option>
                    </c:forEach>
                </select>
                </br>

            </c:forEach>
            </br>
        </c:if>
        <c:if test="${needed_engineers > 0}">
            <fmt:message key="cabin_crew.engineers"/>
            </br>
            <c:forEach var = "i" begin = "0" end = "${needed_engineers-1}">
                <select name="engineer${i}" required="true" width="330" style="width: 330px">
                    <c:forEach items="${engineers}" var="engineer" >
                        <option ${assignedEngineers[i].id == engineer.id
                                  ? 'selected="selected"' 
                                  : ''
                                  }value ="${engineer.id}" >
                            <c:out value="${engineer.name}" />
                            <c:out value="${engineer.surname}" />
                        </option>
                    </c:forEach>
                </select>
                </br>

            </c:forEach>
            </br>
        </c:if>
        <c:if test="${needed_navigators > 0}">
            <fmt:message key="cabin_crew.navigators"/>
            </br>
            <c:forEach var = "i" begin = "0" end = "${needed_navigators-1}">
                <select name="navigator${i}" required="true" width="330" style="width: 330px">
                    <c:forEach items="${navigators}" var="navigator" >
                        <option ${assignedNavigators[i].id == navigator.id
                                  ? 'selected="selected"' 
                                  : ''
                                  }value ="${navigator.id}" >
                            <c:out value="${navigator.name}" />
                            <c:out value="${navigator.surname}" />
                        </option>
                    </c:forEach>
                </select>
                </br>

            </c:forEach>
            </br>
        </c:if>
        <c:if test="${needed_radiomen > 0}">
            <fmt:message key="cabin_crew.radiomen"/>
            </br>
            <c:forEach var = "i" begin = "0" end = "${needed_radiomen-1}">
                <select name="radioman${i}" required="true" width="330" style="width: 330px">
                    <c:forEach items="${radiomen}" var="radioman" >
                        <option ${assignedRadiomen[i].id == radioman.id
                                  ? 'selected="selected"' 
                                  : ''
                                  }value ="${radioman.id}" >
                            <c:out value="${radioman.name}" />
                            <c:out value="${radioman.surname}" />
                        </option>
                    </c:forEach>
                </select>
                </br>

            </c:forEach>
            </br>
        </c:if>
        <c:if test="${needed_attendants > 0}">
            <fmt:message key="cabin_crew.attendants"/>
            </br>
            <c:forEach var = "i" begin = "0" end = "${needed_attendants-1}">
                <select name="attendant${i}" required="true" width="330" style="width: 330px">
                    <c:forEach items="${attendants}" var="attendant" >
                        <option ${assignedAttendants[i].id == attendant.id
                                  ? 'selected="selected"' 
                                  : ''
                                  }value ="${attendant.id}" >
                            <c:out value="${attendant.name}" />
                            <c:out value="${attendant.surname}" />
                        </option>
                    </c:forEach>
                </select>
                </br>

            </c:forEach>
            </br>
        </c:if>

        <input type="hidden" name="needed_pilots" value ="${needed_pilots}"/>
        <input type="hidden" name="needed_engineers" value ="${needed_engineers}"/>
        <input type="hidden" name="needed_navigators" value ="${needed_navigators}"/>
        <input type="hidden" name="needed_radiomen" value ="${needed_radiomen}"/>
        <input type="hidden" name="needed_attendants" value ="${needed_attendants}"/>
        <input type="hidden" name="flight" value ="${param.flight}"/>
        <input type="hidden" name="date" value ="${param.date}"/>

        <input type="hidden" name="page" value ="cabinCrew"/>
        <input type="submit" value="<fmt:message key="crud.savebutton"/>" />
    </form>
</body>
</html>
