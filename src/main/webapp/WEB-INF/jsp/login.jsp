<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ui:html title="title.login">

<c:if test="${param.error == true}">
    <p>Неверный логин или пароль</p>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center"><spring:message code="title.login" /></h5>
                    <c:url value="/login/sign_in" var="signin" />
                    <form:form method="POST" action="${signin}">
                        <div class="form-group">
                            <div>
                                <label for="username"><spring:message code="label.login.username" /></label>
                                <input class="form-control mb-4" id="username" name="name" type="text"
                                    required="required" maxlength="30"
                                    placeholder='<spring:message code="label.login.placeholder.username" />'>
                            </div>
                            <div>
                                <label for="password"><spring:message code="label.login.password" /></label>
                                <input class="form-control mb-2" id="password" name="password" type="password"
                                    required="required" maxlength="30"
                                    placeholder='<spring:message code="label.login.placeholder.password" />'>
                            </div>
                            <div class="form-check mb-3">
                                <input class="form-check-input" id="remember_me" name="remember_me" type="checkbox">
                                <label class="form-check-label"
                                    for="remember_me"><spring:message code="label.login.rememberme" /></label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-lg btn-block mb-1">
                            <spring:message code="label.login.login" />
                        </button>
                        <sec:csrf />
                    </form:form>
                    <c:url value="/registration.html" var="signup" />
                    <form:form method="POST" action="${signup}">
                        <button type="submit" class="btn btn-outline-primary btn-sm btn-block">
                            <spring:message code="label.login.registration" />
                        </button>
                        <sec:csrf />
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</ui:html>
