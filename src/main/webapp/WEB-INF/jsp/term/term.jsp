<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="duck.magistr.com/jsp/taglib/functions" prefix="f"%>

<ui:html title="title.term">
    <ui:navbar />
    <%-- MODEL ATTR: group, termMap--%>
    <div class="container">
        <table class="table table-sm">
            <tbody>
            <tr class="table-primary">
                <th scope="row" style="width: 50%">Terminus</th><th scope="row" style="width: 50%">Термин</th>
            </tr>
            <tr>
                <td>Deutsch</td><td>Русский</td>
            </tr>
            <tr>
                <td>${termMap.get("DE").name}<br>${termMap.get("DE").grammar}</td><td>${termMap.get("RU").name}<br>${termMap.get("RU").grammar}</td>
            </tr>
            <tr class="table-primary">
                <th scope="row">Fachgebiet</th><th scope="row">Область</th>
            </tr>
            <tr>
                <td>${not empty termMap.get("DE").area ? termMap.get("DE").area : ""}</td><td>${not empty termMap.get("RU").area ? termMap.get("RU").area : ""}</td>
            </tr>
            <tr class="table-primary">
                <th scope="row">Kurzdefinition</th><th scope="row">Краткое определение</th>
            </tr>
            <tr>
                <td>${not empty termMap.get("DE").description ? termMap.get("DE").description : ""}</td><td>${not empty termMap.get("RU").description ? termMap.get("RU").description : ""}</td>
            </tr>
            <tr class="table-primary">
                <th scope="row">Bilder</th><th scope="row">Картинки</th>
            </tr>
            <tr>
                <td><%-- !!!!!!!!!!!!!!! IMAGES !!!!!!!!!!!!!!! --%></td>
            </tr>
            <tr class="table-primary">
                <th scope="row">Biespiele</th><th scope="row">Примеры</th>
            </tr>
            <c:set var="examples" value="${f:termGroupExamples(group)}" />
            <c:forEach var="row" items="${examples}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td>${column}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <tr class="table-primary">
                <th scope="row">Autor(en) des Eintrags</th><th scope="row">Авторы</th>
            </tr>
            <tr>
                <td>${group.author}</td>
            </tr>
            </tbody>
        </table>
    </div>

</ui:html>
