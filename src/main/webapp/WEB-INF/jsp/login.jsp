<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href='<c:url value="/static/css/duck.css"/>'>

<c:url var="signinURL" value="/login/signin"/>
<c:url var="signupURL" value="/registration"/>
<spring:message var="loginTitle" code="login.title"/>
<spring:message var="loginError" code="login.error"/>
<spring:message var="usernameLabel" code="login.username"/>
<spring:message var="passwordLabel" code="login.password"/>
<spring:message var="usernamePlaceholder" code="login.username-placeholder"/>
<spring:message var="passwordPlaceholder" code="login.password-placeholder"/>
<spring:message var="remebermeLabel" code="login.rememberme"/>
<spring:message var="signinButton" code="login.signin"/>
<spring:message var="signupButton" code="login.signup"/>

<ui:html title="title.login">
    <div class="section">
        <div class="columns is-centered">
            <div class="column is-one-third">
                <div class="box <%--login-box--%>">
                    <h1 class="title is-4 has-text-centered">${loginTitle}</h1>
                    <c:if test="${param.error == true}">
                        <h2 class="subtitle is-4 has-text-centered has-text-danger">${loginError}</h2>
                    </c:if>
                    <form method="post" action="${signinURL}">
                        <div class="field">
                            <label class="label" for="username">${usernameLabel}</label>
                            <div class="control has-icons-left">
                                <input id="username" name="name" class="input" type="text"
                                       placeholder="${usernamePlaceholder}">
                                <span class="icon is-left"><i class="fas fa-user-alt"></i></span>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label" for="password">${passwordLabel}</label>
                            <div class="control has-icons-left">
                                <input id="password" name="password" class="input" type="password"
                                       placeholder="${passwordPlaceholder}">
                                <span class="icon is-left"><i class="fas fa-key"></i></span>
                            </div>
                        </div>
                        <div class="field">
                            <div class="control">
                                <label class="checkbox" for="remember-me">
                                    <input id="remember-me" name="rememberMe" type="checkbox">
                                        ${remebermeLabel}
                                </label>
                            </div>
                        </div>
                        <div class="field is-grouped is-grouped-multiline">
                            <div class="control">
                                <button class="button is-primary" type="submit">
                                    <span>${signinButton}</span>
                                    <span class="icon"><i class="fas fa-sign-in-alt"></i></span>
                                </button>
                            </div>
                            <div class="control">
                                <a class="button" href="${signupURL}">${signupButton}</a>
                            </div>
                        </div>
                        <sec:csrfInput/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</ui:html>
