<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ui:html title="title.term">

<ui:navbar />

<div class="container mt-4 mb-2">
    <div class="row justify-content-between">
        <div class="col-auto mr-auto">
            <h2><spring:message code="label.navbar.term" /></h2>
        </div>
        <div class="col-auto">
            <c:url value="/term/edit.html" var="newTerm" />
            <form:form method="POST" action="${newTerm}">
                <button class="btn btn-outline-primary" type="submit">
                    <spring:message code="label.term.newterm" />
                </button>
            </form:form>
        </div>
    </div>
</div>

<div class="container">
    <form id="searchForm">
        <div class="form-row justify-content-between">
            <div class="col-4">
                <div class="input-group mb-2">
                    <div class="input-group-prepend">
                        <div class="input-group-text p-0 m-0">
                            <img src='<c:url value="/static/img/flag-de.png" />' alt="">
                        </div>
                    </div>
                    <input class="form-control" id="deSearch" type="search"
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
                    <input class="form-control" id="ruSearch" type="search"
                        placeholder='<spring:message code="label.term.search" />'>
                </div>
            </div>
            <div class="col-auto ml-auto">
                <button class="btn btn-light" id="searchButton" type="submit">
                    <spring:message code="label.term.search" />
                </button>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <div class="card border-primary mb-3">
        <div class="card-header"><h5><spring:message code="label.term.translation" /></h5></div>
        <div class="card-body">
            <table class="table table-bordered table-striped" id="termTable">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">Deutsch</th>
                        <th scope="col">Русский</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- AJAX GENERATE -->
                    <c:forEach var="term" items="${terms}">
                        <tr>
                            <td>${term.name}</td>
                            <td>${term.translation.name}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<c:url var="send" value="/term/send" />
<form:form method="POST" action="${send}" modelAttribute="term">
    <div>
        <form:label path="name">Term RU</form:label>
        <form:input path="name"/>
    </div>
    <div>
        <form:label path="translation.name">Term DE</form:label>
        <form:input path="translation.name"/>
    </div>
    <div>
        <form:label path="termInfo.grammar">Grammar RU</form:label>
        <form:input path="termInfo.grammar"/>
    </div>
    <div>
        <form:label path="translation.termInfo.grammar">Grammar DE</form:label>
        <form:input path="translation.termInfo.grammar"/>
    </div>
    <div>
        <input type="submit" value="send form" />
    </div>
    <sec:csrf />
</form:form>

</ui:html>

<c:url value="/term/search" var="search" />
<script>
    jQuery(document).ready(function($) {
        $("#searchForm").submit(function(event) {
            enableSearchButton(false);
            event.preventDefault();
            searchRequest();
        });
    });

    function searchRequest() {
        let search = {
            "deTerm" : $("#deSearch").val(),
            "ruTerm" : $("#ruSearch").val()
        }

        const token = "${_csrf.token}";
        const header = "${_csrf.headerName}";

        $(document).ajaxSend(function(e,xhr,options) {
            xhr.setRequestHeader(header, token);
        });
        $.ajax({
            type : "POST",
            accept: "application/json",
            contentType : "application/json",
            url : "${search}",
            data : JSON.stringify(search),
            dataType : "json",
            success : function(data) {
                display(data);
            },
            done : function(e) {
                enableSearchButton(true);
            }
        });
    }

    function enableSearchButton(flag) {
        $("#searchButton").prop("disabled", flag);
    }

    function display(data) {
        $('#termTable tbody').empty();
        let tableBody = $('#termTable').find('tbody');
        $.each(data, function(key, value){
            tableBody.append($('<tr>').append('<td>' + key + '</td>').append('<td>' + value + '</td>'));
        });
    }
</script>
