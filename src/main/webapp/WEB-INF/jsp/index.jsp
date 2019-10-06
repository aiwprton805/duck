<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ui:html>
    <header class="bg-image"><%--NO Background Image!!!--%>
    </header>

    <div class="container">
        <sec:authorize var="isAuthorize" access="isAuthenticated()" />
        <c:choose>
            <c:when test="${isAuthorize}">
                <div class="row mt-3">
                    <div class="col-auto ml-auto">
                        <ui:link text="label.navbar.profile" url="/profile" />
                    </div>
                </div>
            </c:when>
            <c:when test="${not isAuthorize}">
                <div class="row mt-3">
                    <div class="col-auto ml-auto">
                        <ui:link text="label.login.login" url="/login" />
                    </div>
                    <div class="col-auto">
                        <ui:link text="label.login.registration" url="/registration" />
                    </div>
                </div>
            </c:when>
        </c:choose>

        <h2 class="h-text text-center">Willkommen auf der Informations- und Kommunikationsplattform zur
            Energiewende für Deutsch und russischsprachige Länder.</h2>
    </div>

</ui:html>
