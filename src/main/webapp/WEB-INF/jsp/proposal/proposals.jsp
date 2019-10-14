<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ui:html>
    <ui:navbar/>
    <%-- MODEL ATTR: proposals--%>
    <div class="container">
        <div class="row my-1">
            <div class="col-md-2 mr-md-1">
                <button id="more-btn" type="button" class="btn btn-primary btn-block" disabled><spring:message
                        code="label.proposal.btn.more"/></button>
            </div>
            <div class="col-md-2">
                <button id="remove-btn" type="button" class="btn btn-primary btn-block" disabled><spring:message
                        code="label.proposal.btn.remove"/></button>
            </div>
        </div>
        <table id="proposals-table" class="table table-striped table-bordered table-hover table-sm">
            <thead>
            <tr>
                <th scope="col" style="width: 30%"><spring:message code="label.proposal.term" /></th>
                <th scope="col" style="width: 70%"><spring:message code="label.proposal.problem" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="proposal" items="${proposals}">
                <tr data-id="${proposal.id}">
                    <td>${proposal.term}</td>
                    <td>${proposal.problem}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</ui:html>
<script src='<c:url value="/static/js/duck/proposal/proposals.js" />'></script>
