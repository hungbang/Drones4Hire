<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div class="modal-header">
	<h3>
		Edit Paid Option
	</h3>
</div>
<div class="modal-body">
	<form name="optionForm">
		<div class="form-group">
			<label>ID</label> 
			<input type="text" class="form-control" data-ng-model="option.id" disabled></input>
		</div>
		<div class="form-group">
			<label>Title</label> 
			<input type="text" class="form-control" data-ng-model="option.title" required></input>
		</div>
		<div class="form-group">
			<label>Description</label> 
			<textarea class="form-control" data-ng-model="option.description" required></textarea>
		</div>
		<div class="form-group">
			<label>Price</label> 
			<input type="text" class="form-control" data-ng-model="option.price" required></input>
		</div>
		<div class="form-group">
			<label>Currency</label> 
			<input type="text" class="form-control" data-ng-model="option.currency" required></input>
		</div>
	</form>
</div>
<div class="modal-footer">
	<button class="btn btn-success" data-ng-click="editPaidOption(option)"  data-ng-disabled="optionForm.$invalid">
    	Save
    </button>
    <button class="btn btn-primary" data-ng-click="cancel()">
    	Cancel
    </button>
</div>