<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="ui" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<ui:html title="title.proposal">
    <ui:navbar/>
    <%-- MODEL ATTR: --%>
    <div class="container mt-2">
        <c:url var="postProposal" value="/proposal/save"/>
        <form method="post" action="${postProposal}" enctype="multipart/form-data">
            <div class="form-group form-row">
                <label for="i-term" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.term"/>*</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" id="i-term" name="term">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="ta-problem" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.problem"/>*</label>
                <div class="col-md-6">
                    <textarea rows="4" class="form-control" id="ta-problem" name="problem"></textarea>
                </div>
            </div>
            <div class="form-group form-row">
                <label for="i-context-file" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.context"/>*</label>
                <div class="col-md-3">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="i-context-file" name="contextFiles" multiple>
                        <label class="custom-file-label" for="i-context-file"
                               data-browse='<spring:message code="file.browse" />'><spring:message
                                code="file.choose"/></label>
                    </div>
                </div>
                <div class="col-md-3">
                    <input type="url" class="form-control" id="i-context-url" name="contextUrl" placeholder="URL">
                </div>
            </div>
            <div class="form-group form-row">
                <label for="i-image-file" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.image"/></label>
                <div class="col-md-6">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="i-image-file" name="imageFiles" multiple>
                        <label class="custom-file-label" for="i-context-file"
                               data-browse='<spring:message code="file.browse" />'><spring:message
                                code="file.choose"/></label>
                    </div>
                </div>
            </div>
            <div class="form-group form-row">
                <label for="i-other-docs-file" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.other-docs"/></label>
                <div class="col-md-6">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="i-other-docs-file" name="otherFiles" multiple>
                        <label class="custom-file-label" for="i-context-file"
                               data-browse='<spring:message code="file.browse" />'><spring:message
                                code="file.choose"/></label>
                    </div>
                </div>
            </div>
            <fieldset class="form-group">
                <div class="form-row">
                    <legend class="col-form-label col-md-2 pt-0"><spring:message code="label.proposal.name"/>*</legend>
                    <div class="col-md-1">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="r-sex-m" name="sex" value="M" class="custom-control-input">
                            <label class="custom-control-label" for="r-sex-m"><spring:message
                                    code="label.proposal.male"/></label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input type="radio" id="r-sex-f" name="sex" value="F" class="custom-control-input">
                            <label class="custom-control-label" for="r-sex-f"><spring:message
                                    code="label.proposal.female"/></label>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <input type="text" class="form-control" id="i-nickname" name="nickname"
                               placeholder='<spring:message code="label.proposal.nickname" />'>
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control" id="i-f" name="lastName"
                               placeholder='<spring:message code="label.proposal.f" />'>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-md-3 offset-md-2">
                        <input type="text" class="form-control" id="i-i" name="firstName"
                               placeholder='<spring:message code="label.proposal.i" />'>
                    </div>
                    <div class="col-md-3">
                        <input type="text" class="form-control" id="i-o" name="patronymic"
                               placeholder='<spring:message code="label.proposal.o" />'>
                    </div>
                </div>
            </fieldset>
            <div class="form-group form-row">
                <label for="i-email" class="col-md-2 col-form-label"><spring:message
                        code="label.proposal.e-mail"/>*</label>
                <div class="col-md-6">
                    <input type="email" class="form-control" id="i-email" name="mail">
                </div>
            </div>
            <div class="form-group form-row">
                <div class="col-md-6 offset-md-2">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="chb-policy" name="agree">
                        <label class="custom-control-label" for="chb-policy">
                            <spring:message code="label.proposal.agree.prefix"/><ui:link
                                text="label.proposal.agree.link" url="#"/><spring:message
                                code="label.proposal.agree.suffix"/>
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group form-row">
                <div class="col-md-8">
                    <div class="row">
                        <div class="col-auto ml-auto">
                            <button type="submit" class="btn btn-primary"><spring:message
                                    code="label.proposal.btn.send"/></button>
                        </div>
                    </div>
                </div>
            </div>
            <sec:csrfInput/>
        </form>
    </div>


</ui:html>
<script src='<c:url value="/static/js/duck/proposal/add.js" />'></script>
