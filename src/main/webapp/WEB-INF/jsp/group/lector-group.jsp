<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:url var="appContextURL" value="/"/>
<c:url var="deFlagImgURL" value="/static/img/flag-de.png"/>
<c:url var="ruFlagImgURL" value="/static/img/flag-ru.png"/>

<spring:message var="studentsLabel" code="group.students"/>
<spring:message var="selectTermLabel" code="group.select-term"/>
<spring:message var="termLabel" code="group.term"/>

<%-- MODEL ATTR: profileGroup, lectors, students --%>
<section id="lector-app" class="section" data-app-context-url="${appContextURL}" data-group-id="${profileGroup.id}">
    <article class="panel is-info">
        <p class="panel-heading">${studentsLabel}</p>
        <c:forEach var="studentProfile" items="${students}">
            <student-component @term-added="excludeTerm" @student-term-excluded="getTerms" :key="${studentProfile.id}"
                               :profile-id="${studentProfile.id}" :terms="terms" inline-template>
                <div>
                    <div :class="{'is-active': isActive}" class="panel-block">
                        <span class="panel-icon"><i class="fas fa-user-graduate" aria-hidden="true"></i></span>
                        <p>
                            <a @click="switchActiveStudent"
                               :class="[{'has-text-link': isActive}, {'has-text-dark': !isActive}]"
                               class="is-underline is-size-5">${studentProfile.name}</a>
                            <a class="is-italic" href="mailto:${studentProfile.email}">${studentProfile.email}</a>
                        </p>
                        <div class="select ml-auto">
                            <select v-model="selectedTermId">
                                <option disabled value="0">${selectTermLabel}</option>
                                <option v-for="term in terms" :key="term.id" :value="term.id">{{term.name}}</option>
                            </select>
                        </div>
                        <button @click="postAddTerm" class="button is-success" style="margin-left: 0.75em;">
                            <span>${termLabel}</span>
                            <span class="icon"><i class="fas fa-plus-circle"></i></span>
                        </button>
                        <span class="panel-icon is-right-panel-icon">
                            <a @click="removeStudent"><i class="fas fa-times"></i></a>
                        </span>
                    </div>
                    <term-component @term-removed="excludeStudentTerm" v-for="studentTerm in studentTerms"
                                    :key="studentTerm.id" :student-term="studentTerm" :student-profile-id="profileId" inline-template>
                        <div class="panel-block">
                            <span class="panel-icon"><i class="fas fa-book" aria-hidden="true"></i></span>
                            <p>{{studentTerm.name}}</p>
                            <span :class="[studentTerm.status === 'PROCESSING' ? 'has-text-warning' : 'has-text-success', 'icon', 'is-medium', 'ml-auto']">
                                <i :class="[studentTerm.status === 'PROCESSING' ? 'fas' : 'far', 'fa-lg', 'fa-lightbulb']"></i>
                            </span>
                            <figure class="image is-32x32">
                                <img v-if="studentTerm.lang === 'DE'" src="${deFlagImgURL}" alt="studentTerm.lang">
                                <img v-else-if="studentTerm.lang === 'RU'" src="${ruFlagImgURL}" alt="studentTerm.lang">
                            </figure>
                            <span class="panel-icon is-right-panel-icon">
                                <a @click="removeTerm"><i class="fas fa-times"></i></a>
                            </span>
                        </div>
                    </term-component>
                </div>
            </student-component>
        </c:forEach>
    </article>
</section>

<script defer src='<c:url value="/static/js/axios/axios.min.js"/>'></script>
<script defer src='<c:url value="/static/js/duck/group/lector-group.js"/>'></script>
