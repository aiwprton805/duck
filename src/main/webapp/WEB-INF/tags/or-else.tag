<%@tag pageEncoding="UTF-8"%>
<%@attribute name="obj" required="true" %>
<%@attribute name="other" required="false" %>

${not empty obj ? obj : other}
