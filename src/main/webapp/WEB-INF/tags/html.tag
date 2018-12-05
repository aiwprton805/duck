<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@attribute name="title"%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
<META charset="utf-8">
<META name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<c:url value="/static/css/bootstrap.min.css" var="bootstrap_css" />
<link rel="stylesheet" href="${bootstrap_css}">

<c:if test="${not empty title}">
    <TITLE><spring:message code="${title}" /></TITLE>
</c:if>

</HEAD>
<BODY>
    <jsp:doBody />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <c:url value="/static/js/jquery-3.3.1.min.js" var="jquery_js" />
    <c:url value="/static/js/popper.min.js" var="popper_js" />
    <c:url value="/static/js/bootstrap.min.js" var="bootstrap_js" />
    <script src="${jquery_js}"></script>
    <script src="${popper_js}"></script>
    <script src="${bootstrap_js}"></script>
</BODY>
</HTML>
