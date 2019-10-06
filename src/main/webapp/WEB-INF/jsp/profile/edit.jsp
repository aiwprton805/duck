<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:url var="editProfileUrl" value="/profile/edit" />

<spring:message var="nameLabel" code="label.proposal.name"/>
<spring:message var="emailLabel" code="label.proposal.e-mail"/>
<spring:message var="saveLabel" code="label.save"/>

<%-- MODEL ATTR: profile --%>
<ui:html title="title.profile" withNavbar="true">
    <section class="section">
        <div class="columns is-centered">
            <div class="column is-one-third">
                <div class="box">
                    <form:form action="${editProfileUrl}" method="post" modelAttribute="profile">
                        <div class="field">
                            <form:label path="name" for="i-name" cssClass="label">${nameLabel}</form:label>
                            <div class="control">
                                <form:input path="name" cssClass="input" id="i-name"/>
                            </div>
                        </div>
                        <div class="field">
                            <form:label path="email" for="i-email" cssClass="label">${emailLabel}</form:label>
                            <div class="control">
                                <form:input path="email" cssClass="input" id="i-email"/>
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <button type="submit" class="button is-primary">${saveLabel}</button>
                            </div>
                        </div>
                        <sec:csrfInput/>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</ui:html>
