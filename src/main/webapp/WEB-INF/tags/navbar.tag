<%@tag pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<nav class="navbar sticky-top navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <a class="navbar-brand" href="#">
        <img  src='<c:url value="/static/img/duck-img.svg" />' width="30" height="30" alt="" >
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#content"
        aria-controls="content" aria-expanded="false" aria-label="Menu">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="content">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href='<c:url value="/profile.html" />'>
                    <spring:message code="label.navbar.profile" />
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/term.html" />'>
                    <spring:message code="label.navbar.term" />
                </a>
            </li>
        </ul>
        <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="langDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <spring:message code="label.navbar.lang" />
            </a>
            <div class="dropdown-menu" aria-labelledby="langDropdown">
                <a class="dropdown-item" href="?language=en">English</a>
                <a class="dropdown-item" href="?language=de">Deutsch</a>
                <a class="dropdown-item" href="?language=ru">Русский</a>
            </div>
        </div>
        <form class="form-inline" method="post" action='<c:url value="/logout" />'>
            <button class="btn btn-light my-2 my-sm-0" style="border: 1px solid #2196f3;" type="submit">
                <spring:message code="label.navbar.logout" />
            </button>
            <sec:csrf />
        </form>
    </div>
</nav>
