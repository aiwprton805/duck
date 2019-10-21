<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="appContextURL" value="/"/>

<spring:message var="groupLabel" code="group.group"/>
<spring:message var="accessCodeLabel" code="group.access-code"/>
<spring:message var="lectorLabel" code="group.lector"/>

<%-- MODEL ATTR: lectors, students, profileGroup --%>
<ui:html title="title.group" withNavbar="true">
    <sec:csrfMetaTags/>
    <section class="hero is-primary">
        <div class="hero-body">
            <div class="container">
                <div class="level">
                    <div class="level-left">
                            <h1 class="title">${groupLabel} ${profileGroup.name}</h1>
                    </div>
                    <div class="level-right">
                        <sec:authorize access="hasRole('lector')">
                            <h1 class="title">${accessCodeLabel}
                                <span id="group-token">${profileGroup.token}</span>
                                <a id="refresh-group-token-link" data-profile-group-id="${profileGroup.id}"
                                   data-app-context-path="${appContextURL}">
                                    <span class="icon"><i class="fas fa-sync-alt"></i></span>
                                </a>
                            </h1>
                        </sec:authorize>
                    </div>
                </div>
                <c:forEach var="lector" items="${lectors}">
                    <h2 class="subtitle">
                        <span>${lectorLabel} ${lector.name}</span>
                        <a class="is-underline" href="mailto:${lector.email}"><small><em>${lector.email}</em></small></a>
                    </h2>
                </c:forEach>
            </div>
        </div>
    </section>
    <sec:authorize access="hasRole('lector')">
        <jsp:include page="lector-group.jsp"/>
    </sec:authorize>
</ui:html>

<script defer src='<c:url value="/static/js/axios/axios.min.js"/>'></script>
<script defer src='<c:url value="/static/js/duck/group/group.js"/>'></script>
