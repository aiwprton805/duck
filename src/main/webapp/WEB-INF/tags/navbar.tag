<%@tag pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:url var="logoURL" value="/static/img/duck-img.svg"/>
<c:url var="profileURL" value="/profile"/>
<c:url var="termsURL" value="/terms"/>
<c:url var="logoutURL" value="/logout"/>

<spring:message var="profileLabel" code="navbar.profile"/>
<spring:message var="termsLabel" code="navbar.termbase"/>
<spring:message var="langLabel" code="navbar.lang"/>
<spring:message var="logoutLabel" code="navbar.logout"/>

<nav id="main-navbar" class="navbar is-fixed-top is-light" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="#">
            <img src="${logoURL}" width="30" height="30" alt="">
        </a>
        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false"
           data-target="main-menu" :class="{'is-active': burgerIsActive}" @click="switchBurgerActive">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
    <div class="navbar-menu" :class="{'is-active': burgerIsActive}">
        <div class="navbar-start">
            <a class="navbar-item" href="${profileURL}">${profileLabel}</a>
            <a class="navbar-item" href="${termsURL}">${termsLabel}</a>
        </div>
        <div class="navbar-end">
            <div id="navbar-lang" class="navbar-item has-dropdown is-hoverable">
                <a class="navbar-link" href="#">${langLabel}</a>
                <div class="navbar-dropdown">
                    <a class="navbar-item" href="?language=en">English</a>
                    <a class="navbar-item" href="?language=de">Deutsch</a>
                    <a class="navbar-item" href="?language=ru">Русский</a>
                </div>
            </div>
            <div class="navbar-item">
                <form method="post" action="${logoutURL}">
                    <div class="control">
                        <button class="button" type="submit">
                            <span>${logoutLabel}</span>
                            <span class="icon"><i class="fas fa-sign-out-alt"></i></span>
                        </button>
                    </div>
                    <sec:csrfInput/>
                </form>
            </div>
        </div>
    </div>
</nav>

<script defer src='<c:url value="/static/js/vue/vue.js" />'></script>
<script defer src='<c:url value="/static/js/duck/navbar.js" />'></script>
