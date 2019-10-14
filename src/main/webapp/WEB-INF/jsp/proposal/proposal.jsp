<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.magistr.duck.common.enums.Sex" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib tagdir="/WEB-INF/tags/security" prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<ui:html title="title.proposal">
    <ui:navbar/>
    <%-- MODEL ATTR: proposal--%>
    <div class="container">
        <table id="proposal-table" class="table table-sm mb-0" data-id="${proposal.id}">
            <tbody>
            <tr class="table-primary">
                <th scope="row" style="width: 100%"><spring:message code="label.proposal.term"/></th>
            </tr>
            <tr>
                <td>${proposal.term}</td>
            </tr>
            <tr class="table-primary">
                <th scope="row"><spring:message code="label.proposal.problem"/></th>
            </tr>
            <tr>
                <td>${proposal.problem}</td>
            </tr>
            <tr class="table-primary">
                <th scope="row"><spring:message code="label.proposal.context"/></th>
            </tr>
            <tr>
                <td>
                    <ui:link text="label.download" url="/proposal/download/context/${proposal.id}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <c:if test="${not empty proposal.contextUrl}">
                        <ui:link text="label.external.link" url="${proposal.contextUrl}"/>
                    </c:if>
                </td>
            </tr>
            <tr class="table-primary">
                <th scope="row"><spring:message code="label.proposal.image"/></th>
            </tr>
            <tr>
                <td><ui:link text="label.download" url="/proposal/download/images/${proposal.id}"/></td>
            </tr>
            <tr class="table-primary">
                <th scope="row"><spring:message code="label.proposal.other-docs"/></th>
            </tr>
            <tr>
                <td><ui:link text="label.download" url="/proposal/download/docs/${proposal.id}"/></td>
            </tr>
            </tbody>
        </table>
        <table class="table table-sm" >
            <tbody>
            <tr class="table-primary">
                <th scope="row" style="width: 18%"><spring:message code="label.proposal.f"/></th>
                <th scope="row" style="width: 18%"><spring:message code="label.proposal.i"/></th>
                <th scope="row" style="width: 18%"><spring:message code="label.proposal.o"/></th>
                <th scope="row" style="width: 18%"><spring:message code="label.proposal.nickname"/></th>
                <th scope="row" style="width: 10%"><spring:message code="label.proposal.sex"/></th>
                <th scope="row" style="width: 18%"><spring:message code="label.proposal.e-mail"/></th>
            </tr>
            <tr>
                <td><ui:or-else obj="${proposal.lastName}"/></td>
                <td><ui:or-else obj="${proposal.firstName}"/></td>
                <td><ui:or-else obj="${proposal.patronymic}"/></td>
                <td><ui:or-else obj="${proposal.nickname}"/></td>
                <td>
                    <c:if test="${not empty proposal.sex}">
                        <c:choose>
                            <c:when test="${proposal.sex eq Sex.MALE}">
                                <spring:message code="label.proposal.male"/>
                            </c:when>
                            <c:when test="${proposal.sex eq Sex.FEMALE}">
                                <spring:message code="label.proposal.female"/>
                            </c:when>
                        </c:choose>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty proposal.email}">
                        <ui:link text="${proposal.email}" url="mailto:${proposal.email}"/>
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</ui:html>
