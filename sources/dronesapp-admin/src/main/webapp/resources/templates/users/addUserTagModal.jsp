<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div class="modal-header">
	<h3>
		<spring:message code="copper.admin.pages.users.tags.modal.title"/>
	</h3>
</div>
<div class="modal-body">
	<form name="tagForm">
		<div class="form-group">
			<label><spring:message code="copper.admin.pages.common.form.field.tag.label"/></label> 
			<input type="text" class="form-control" data-ng-model="tag" required></input>
		</div>
	</form>
</div>
<div class="modal-footer">
	<button class="btn btn-success" data-ng-click="addUserTag(id, tag)"  data-ng-disabled="tagForm.$invalid">
    	<spring:message code="copper.admin.pages.common.button.add"/>
    </button>
    <button class="btn btn-primary" data-ng-click="cancel()">
    	<spring:message code="copper.admin.pages.common.button.cancel"/>
    </button>
</div>