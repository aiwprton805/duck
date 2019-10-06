<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:url var="appContextURL" value="/"/>

<spring:message var="groupsPanelTitle" code="profile.lector.groups"/>
<spring:message var="termsPanelTitle" code="profile.lector.terms"/>
<spring:message var="groupNamePlaceholder" code="profile.lector.groupname-placeholder"/>
<spring:message var="modalTitle" code="profile.lector.modal.title"/>
<spring:message var="modalRemoveLabel" code="profile.lector.modal.remove"/>
<spring:message var="modalCancelLabel" code="profile.lector.modal.cancel"/>
<spring:message var="modalWarninglLabel" code="profile.lector.modal.warning"/>
<spring:message var="newTabLabel" code="profile.lector.new"/>
<spring:message var="checkingTabLabel" code="profile.lector.checking"/>
<spring:message var="addGroupLabel" code="profile.lector.add-group"/>
<spring:message var="addTermsLabel" code="profile.lector.add-terms"/>
<spring:message var="hideFieldLabel" code="profile.lector.hide-field"/>
<spring:message var="hideFieldsLabel" code="profile.lector.hide-fields"/>

<section id="student-app" class="section" data-app-context-url="${appContextURL}">
    <div class="columns">
        <div class="column">
            <s-groups-panel-component add-groups-button-text="${addGroupLabel}" hide-input-field-button-text="${hideFieldLabel}" inline-template>
                <div><%--Inline-template components must have exactly one child element--%>
                    <nav class="panel">
                        <p class="panel-heading">${groupsPanelTitle}</p>
                        <s-group-component v-for="group in groups" :key="group.id" :group="group"
                                         @show-modal="showModal" inline-template>
                            <div class="panel-block">
                                <span class="panel-icon"><i class="fas fa-users" aria-hidden="true"></i></span>
                                <a href="#">{{group.name}}</a>
                                <p class="ml-auto"></p>
                                <span class="panel-icon is-right-panel-icon">
                                    <a @click="preLeaveGroup"><i class="fas fa-external-link-alt" aria-hidden="true"></i></a>
                                </span>
                            </div>
                        </s-group-component>
                        <s-group-input-component @join-group="addGroup" v-show="visibleGroupInputComponent"
                                               inline-template>
                            <div class="panel-block">
                                <div class="field is-horizontal">
                                    <div class="field-body">
                                        <div class="field">
                                            <div class="control">
                                                <input v-model="accessCode" class="input" type="text"
                                                       placeholder="ACCESS CODE/КОД ДОСТУПА">
                                            </div>
                                        </div>
                                        <div class="field">
                                            <div class="control">
                                                <button @click="postJoinGroup" class="button is-primary is-outlined">OK</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </s-group-input-component>
                        <div class="panel-block">
                            <button @click="switchVisibilityGroupInputComponent" class="button is-link is-outlined is-fullwidth">
                                {{buttonText}}
                            </button>
                        </div>
                    </nav>
                    <div class="modal" :class="{'is-active': isLeaveGroupModalActive}">
                        <div class="modal-background"></div>
                        <div class="modal-card is-small-modal">
                            <header class="modal-card-head">
                                <p class="modal-card-title">${modalTitle} {{leavingGroup.name}}</p>
                                <button @click="hideModal" class="delete" aria-label="close"></button>
                            </header>
                            <section class="modal-card-body">
                                <h1 class="subtitle is-4 has-text-centered">${modalWarninglLabel}</h1>
                            </section>
                            <footer class="modal-card-foot justify-content-between">
                                <button @click="leaveGroup" class="button is-danger">${modalRemoveLabel}</button>
                                <button @click="hideModal" class="button">${modalCancelLabel}</button>
                            </footer>
                        </div>
                    </div>
                </div>
            </s-groups-panel-component>
        </div>
        <div class="column">
            <s-terms-panel-component inline-template>
                <nav class="panel">
                    <p class="panel-heading">${termsPanelTitle}</p>
                    <p class="panel-tabs">
                        <a @click="switchTab('all')" :class="{'is-active': activeTab === 'all'}">ALL/ВСЕ</a>
                        <a @click="switchTab('de')" :class="{'is-active': activeTab === 'de'}">Deutsch</a>
                        <a @click="switchTab('ru')" :class="{'is-active': activeTab === 'ru'}">Русский</a>
                    </p>
                    <s-term-component v-for="term in terms" :key="term.id" :term="term" inline-template>
                        <div class="panel-block">
                            <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                            <a href="#">{{term.name}}</a>
                        </div>
                    </s-term-component>
                </nav>
            </s-terms-panel-component>
        </div>
    </div>
</section>

<script defer src='<c:url value="/static/js/axios/axios.min.js"/>'></script>
<script defer src='<c:url value="/static/js/duck/profile/student-profile.js"/>'></script>
