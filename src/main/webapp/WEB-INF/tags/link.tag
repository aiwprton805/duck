<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@attribute name="text" required="true" %>
<%@attribute name="url" required="true" %>

<c:url var="curl" value="${url}" />
<spring:message var="message" text="${text}" code="${text}" />
<a href="${curl}">${message}</a>
