<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ui:html>

<c:url var="signup" value="/registration/signup" />
<form:form method="POST" action="${signup}">
    <div>
        <input name="name" type="text" required="required" maxlength="30" size="30" placeholder="login">
    </div>
    <div>
        <input name="password" type="text" required="required" size="30" placeholder="password">
    </div>
    <div>
        <input name="retypePassword" type="text" required="required" size="30" placeholder="password">
    </div>
    <div>
        <input type="submit" value="sign up">
    </div>
    <sec:csrfInput/>
</form:form>

</ui:html>
