<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectDetailsController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"><spring:message code="drones.admin.pages.projects.view.title"/></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label><spring:message code="drones.admin.pages.common.project.photo.label"/></label><br/> 										
				<img height="90%" width="90%" alt="<spring:message code="drones.admin.pages.common.project.photo.label"/>" data-ng-src="{{project.imageURL}}">
			</div>
			<div class="form-group" data-ng-if="comments.length">
				<label class="header-content"><spring:message code="drones.admin.pages.common.column.comments.caption"/></label>
				<div data-ng-repeat="comment in comments">
					<span class="comment-author-text">{{comment.user.firstName}} {{comment.user.lastName}}: </span>
					<span class="comment-text">{{comment.comment}}</span>
					<button class="btn btn-sm btn-danger" data-ng-click="deleteComment(comment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
			<hr/>
			<div class="form-group" data-ng-if="bids.length">
				<label class="header-content"><spring:message code="drones.admin.pages.common.column.bids.caption"/></label>
				<div data-ng-repeat="bid in bids">
					<span class="comment-author-text">{{bid.user.firstName}} {{bid.user.lastName}}: </span>
					<span class="comment-text">{{bid.comment}}</span>
					<span> ({{bid.amount}} {{bid.currency}}) </span>
					<button class="btn btn-sm btn-danger" data-ng-click="deleteBid(bid.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
			<hr/>
			<div class="form-group" data-ng-if="project.attachments.length">
				<label><spring:message code="drones.admin.pages.common.column.attachments.caption"/></label>
				<div data-ng-repeat="attachment in project.attachments">
					<a href="{{attachment.attachmentURL}}">{{attachment.title}}</a>
					<button class="btn btn-sm btn-danger" data-ng-click="deleteAttachment(attachment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<form name="projectForm">
				<fieldset>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.title.label"/></label> 
						<input class="form-control" data-ng-model="project.title" type="text"/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.common.column.status.caption"/></label>
						<input class="form-control" type="text" data-ng-model="project.status" disabled/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.summary.label"/></label> 
						<input class="form-control" data-ng-model="project.summary" type="text"/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.clientId.label"/></label> 
						<input class="form-control" data-ng-model="project.clientId" type="text" disabled/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.pilotId.label"/></label> 
						<input class="form-control" data-ng-model="project.pilotId" type="text" disabled/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.category.label"/></label>
						<select class="form-control validation" data-ng-model="project.service.category"
								data-ng-options="category.name for category in categories track by category.id"
								data-ng-change="loadServices()"></select>
					</div>
					<div class="form-group" data-ng-if="services.length">
						<label><spring:message code="drones.admin.pages.project.form.service.label"/></label>
						<select class="form-control validation" data-ng-model="project.service.id">
							<option data-ng-value="service.id" data-ng-repeat="service in services">{{service.name}}</option>
						</select>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.duration.label"/></label>
						<select class="form-control validation" data-ng-model="project.duration" data-ng-options="duration.title for duration in durations track by duration.id"></select>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.address.label"/></label> 
						<input class="form-control" type="text" data-ng-model="project.location.address" />
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.budget.label"/></label>
						<select class="form-control validation" data-ng-model="project.budget" data-ng-options="budget.title for budget in budgets track by budget.id"></select>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.common.column.createDate.caption"/></label>
						<input class="form-control" type="text" data-ng-model="createdAt" disabled/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.common.column.startDate.caption"/></label>
						<input class="form-control" type="text" data-ng-model="startDate"/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.common.column.finishDate.caption"/></label>
						<input class="form-control" type="text" data-ng-model="finishDate"/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.postProdaction.label"/></label> 
						<input type="checkbox" data-ng-model="project.postProductionRequired" />
					</div>
					<button data-ng-if="project.status != 'BLOCKED'" class="btn btn-primary btn-warning" data-ng-click="blockProject()"><spring:message code="drones.admin.pages.common.button.project.block"/></button>
					<button data-ng-if="project.status == 'BLOCKED'" class="btn btn-primary btn-primary" data-ng-click="unblockProject()"><spring:message code="drones.admin.pages.common.button.project.unblock"/></button>
					<button class="btn btn-primary btn-danger" data-ng-click="deleteProject(project.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
					<button class="btn btn-primary action pull-right" data-ng-click="editProject()"><spring:message code="drones.admin.pages.common.button.save"/></button>
				</fieldset>
			</form>
		</div>
	</div>
</div>