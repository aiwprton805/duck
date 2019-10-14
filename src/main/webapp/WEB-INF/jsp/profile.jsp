<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<ui:html>

<ui:navbar />

<h1>Hello, ${user.name}!</h1>
<h2>You have roles</h2>
<table>
    <tr>
        <c:forEach var="role" items="${user.roles}" >
            <td>${role.name}</td>
        </c:forEach>
    </tr>
</table>

<c:url var="logout" value="/logout" />
<form method="POST" action="${logout}">
    <input type="submit" value=<spring:message code="label.navbar.logout" /> />
    <sec:csrf />
</form>

</ui:html>
