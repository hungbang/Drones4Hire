<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div class="modal-header">
	<h3>
		Cancel withdraw
	</h3>
</div>
<div class="modal-body">
	<form name="withdrawCancelForm">
		<div class="form-group">
			<label>ID</label> 
			<input type="text" class="form-control" data-ng-model="request.id" disabled></input>
		</div>
		<div class="form-group">
			<label>Status</label> 
			<textarea class="form-control" data-ng-model="request.status" disabled></textarea>
		</div>
		<div class="form-group">
			<label>Amount({{request.currency}})</label> 
			<input type="text" class="form-control" data-ng-model="request.amount" disabled></input>
		</div>
		<div class="form-group">
			<label>Comment</label> 
			<textarea class="form-control" data-ng-model="request.comment" disabled></textarea>
		</div>
		<div class="form-group">
			<label>Cancelation comment</label> 
			<textarea class="form-control" rows="4" data-ng-model="request.adminComment" required></textarea>
		</div>
	</form>
</div>
<div class="modal-footer">
	<button class="btn btn-danger" data-ng-click="cancel(request)"  data-ng-disabled="withdrawCancelForm.$invalid">
    	Cancel
    </button>
    <button class="btn btn-primary" data-ng-click="close()">
    	Close
    </button>
</div>