<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<spring:message var="studentsLabel" code="group.students"/>

<%-- MODEL ATTR: profileGroup, lectors, students --%>
<section class="section">
    <article class="panel is-info">
        <p class="panel-heading">${studentsLabel}</p>
        <c:forEach var="studentProfile" items="${students}">
            <div class="panel-block">
                <span class="panel-icon"><i class="fas fa-user-graduate" aria-hidden="true"></i></span>
                <p>
                    <span class="is-size-5">${studentProfile.name}</span>
                    <a class="is-italic" href="mailto:${studentProfile.email}">${studentProfile.email}</a>
                </p>
            </div>
        </c:forEach>
    </article>
</section>
