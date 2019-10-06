<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="editURL" value="/profile/edit"/>

<spring:message var="editMeLabel" code="profile.edit-me"/>

<%-- MODEL ATTR: profile --%>
<ui:html title="title.profile" withNavbar="true">
    <sec:csrfMetaTags/>
    <section class="hero is-primary">
        <div class="hero-body">
            <div class="container">
                <h1 class="title">${profile.name} <small><em>${profile.email}</em></small></h1>
                <h2 class="subtitle is-underline"><a href="${editURL}">${editMeLabel}</a></h2>
            </div>
        </div>
    </section>

    <sec:authorize access="hasRole('admin')">
        <jsp:include page="admin-profile.jsp"/>
    </sec:authorize>
    <sec:authorize access="hasRole('lector')">
        <jsp:include page="lector-profile.jsp"/>
    </sec:authorize>
    <sec:authorize access="hasRole('student')">
        <jsp:include page="student-profile.jsp"/>
    </sec:authorize>
</ui:html>
