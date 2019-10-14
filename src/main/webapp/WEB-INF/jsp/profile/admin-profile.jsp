<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:url var="appContextURL" value="/"/>

<spring:message var="prevLabel" code="profile.admin.prev"/>
<spring:message var="nextLabel" code="profile.admin.next"/>
<spring:message var="nameHeader" code="profile.admin.name"/>
<spring:message var="sexHeader" code="profile.admin.sex"/>
<spring:message var="emailHeader" code="profile.admin.e-mail"/>
<spring:message var="termHeader" code="profile.admin.term"/>
<spring:message var="loginHeader" code="profile.admin.username"/>
<spring:message var="rolesHeader" code="profile.admin.roles"/>
<spring:message var="usersMenuLabel" code="profile.admin.users"/>
<spring:message var="proposalsMenuLabel" code="profile.admin.proposals"/>
<spring:message var="guestsMenuItem" code="profile.admin.guests"/>
<spring:message var="studentsMenuItem" code="profile.admin.students"/>
<spring:message var="lectorsMenuItem" code="profile.admin.lectors"/>
<spring:message var="allMenuItem" code="profile.admin.all"/>

<section id="admin-app" class="section" app-context-url="${appContextURL}">
    <div class="columns">
        <div class="column is-2">
            <aside id="menu" class="menu">
                <p class="menu-label">${usersMenuLabel}</p>
                <ul class="menu-list">
                    <li @click="switchMenu('guests')"><a :class="{'is-active': activeMenuItem === 'guests'}">${guestsMenuItem}</a>
                    </li>
                    <li @click="switchMenu('students')"><a :class="{'is-active': activeMenuItem === 'students'}">${studentsMenuItem}</a>
                    </li>
                    <li @click="switchMenu('lectors')"><a :class="{'is-active': activeMenuItem === 'lectors'}">${lectorsMenuItem}</a>
                    </li>
                    <li @click="switchMenu('all')"><a :class="{'is-active': activeMenuItem === 'all'}">${allMenuItem}</a></li>
                </ul>
                <p class="menu-label">${proposalsMenuLabel}</p>
                <ul class="menu-list">
                    <li @click="switchMenu('proposals')"><a :class="{'is-active': activeMenuItem === 'proposals'}">${proposalsMenuLabel}</a>
                    </li>
                </ul>
            </aside>
        </div>
        <div class="column is-10">
            <div class="container">
                <div v-show="loading" class="spinner">
                    <div class="cube1"></div>
                    <div class="cube2"></div>
                </div>
                <div v-show="!loading">
                    <table-component v-if="activeMenuItem === 'proposals'" :rows="tableData" type="proposals"
                                     :headers="['${nameHeader}', '${sexHeader}', '${emailHeader}', '${termHeader}']"
                                     app-context-url="${appContextURL}"></table-component>
                    <table-component v-else-if="activeMenuItem === 'all'" :rows="tableData" type="all"
                                     :headers="['${loginHeader}', '${rolesHeader}', '${nameHeader}', '${emailHeader}']"
                                     app-context-url="${appContextURL}" @update-users-roles="updateUsersRoles"></table-component>
                    <table-component v-else :rows="tableData" type="other"
                                     :headers="['${loginHeader}', '${nameHeader}', '${emailHeader}']"
                                     app-context-url="${appContextURL}"></table-component>
                    <pagination-component :size="size" next-label="${nextLabel}" prev-label="${prevLabel}"></pagination-component>
                </div>
            </div>
        </div>
    </div>
</section>

<script defer src='<c:url value="/static/js/axios/axios.min.js"/>'></script>
<script defer src='<c:url value="/static/js/duck/profile/admin-profile.js"/>'></script>
