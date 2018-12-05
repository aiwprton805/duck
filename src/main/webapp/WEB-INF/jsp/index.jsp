<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ui:html>

<h1>Duck</h1>
<h2>This project is union four universities.</h2>

<div>
    <c:url var="login" value="/login.html"></c:url>
    <a href="${login}">login</a>
</div>
<div>
    <c:url var="registration" value="/registration.html"></c:url>
    <a href="${registration}">registration</a>
</div>

</ui:html>
