<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@attribute name="title"%>
<%@attribute name="withNavbar"%>

<c:url var="duckURL" value="/static/css/duck.css"/>
<c:url var="bulmaURL" value="/static/css/bulma/bulma.min.css" />
<c:url var="fontawesomeURL" value="/static/js/fontawesome/all.min.js" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:if test="${not empty title}">
        <title><spring:message code="${title}" /></title>
    </c:if>
    <link rel="stylesheet" href="${bulmaURL}">
    <link rel="stylesheet" href="${duckURL}">
    <script defer src="${fontawesomeURL}"></script>
</head>
<body class="${not empty withNavbar ? "has-navbar-fixed-top" : ""}">
<c:if test="${not empty withNavbar}">
    <ui:navbar />
</c:if>
<jsp:doBody />
</body>
</html>
