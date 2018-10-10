<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ui:html>

<ui:language />

<c:if test="${param.error == true}">
    <p>Неверный логин или пароль</p>
</c:if>

<c:url var="sign_in" value="/login/sign_in" />
<form:form method="POST" action="${sign_in}">
    <div>
        <input name="name" type="text" required="required" maxlength="30" size="30" placeholder="login">
    </div>
    <div>
        <input name="password" type="password" required="required" size="30" placeholder="password">
    </div>
    <div>
        <label for="remember_me">Запомнить меня</label>
        <input id="remember_me" name="remember_me" type="checkbox">
    </div>
    <div>
        <input type="submit" value=<spring:message code="label.login" />>
    </div>
    <sec:csrf />
</form:form>

</ui:html>
