<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ui:html title="title.term">
    <ui:navbar/>

    <div class="container mt-4 mb-2">
        <div class="row justify-content-between">
            <div class="col-auto mr-auto">
                <h2>Term Database</h2>
            </div>
            <div class="col-auto">
                <button id="new-term-btn" class="btn btn-outline-primary" type="button">
                    <spring:message code="label.term.newterm"/>
                </button>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="form-row justify-content-between">
            <div class="col-4">
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text p-0 m-0">
                            <img src='<c:url value="/static/img/flag-de.png" />' alt="">
                        </div>
                    </div>
                    <input id="de-search" class="form-control" type="search"
                           placeholder='<spring:message code="label.term.search" />'>
                </div>
            </div>
            <div class="col-4">
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text p-0 m-0">
                            <img src='<c:url value="/static/img/flag-ru.png" />' alt="">
                        </div>
                    </div>
                    <input id="ru-search" class="form-control" type="search"
                           placeholder='<spring:message code="label.term.search" />'>
                </div>
            </div>
            <div class="col-auto ml-auto">
                <button id="search-btn" class="btn btn-light" type="button">
                    <spring:message code="label.term.search"/>
                </button>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="card border-primary mb-3">
            <div class="card-header"><h5><spring:message code="label.term.translation"/></h5></div>
            <div class="card-body">
                <table id="term-table" class="table table-bordered table-striped">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">Deutsch</th>
                        <th scope="col">Русский</th>
                    </tr>
                    </thead>
                    <tbody>
                        <%-- AJAX GENERATE --%>
                    <c:forEach var="group" items="${groups}">
                        <tr data-groupId="${group.id}">
                            <c:forEach var="term" items="${group.terms}">
                                <td data-termId="${term.id}">${term.name}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</ui:html>
<script src='<c:url value="/static/js/duck/term/terms.js" />'></script>
