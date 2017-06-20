<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectDetailsController">
	<div class="row">
		<div class="col-lg-12">
			<h1><spring:message code="drones.admin.pages.projects.view.title"/></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<div class="form-group">
				<label><spring:message code="drones.admin.pages.common.project.photo.label"/></label><br/> 										
				<img height="90%" width="90%" alt="<spring:message code="drones.admin.pages.common.project.photo.label"/>" data-ng-src="{{project.imageURL}}">
			</div>
			<div class="form-group" data-ng-if="comments.length">
				<label><spring:message code="drones.admin.pages.common.column.comments.caption"/></label>
				<div data-ng-repeat="comment in comments">
					<h4>{{comment.user.firstName}} {{comment.user.lastName}}</h4>
					<div>{{comment.comment}}</div>
					<button class="btn btn-danger" data-ng-click="deleteComment(comment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
			<div class="form-group" data-ng-if="bids.length">
				<label><spring:message code="drones.admin.pages.common.column.bids.caption"/></label>
				<div data-ng-repeat="bid in bids">
					<h4>{{bid.user.firstName}} {{bid.user.lastName}}</h4>
					<div>{{bid.comment}}</div>
					<div>{{bid.amount}} {{bid.currency}}</div>
					<button class="btn btn-danger" data-ng-click="deleteBid(bid.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
			<div class="form-group" data-ng-if="project.attachments.length">
				<label><spring:message code="drones.admin.pages.common.column.attachments.caption"/></label>
				<div data-ng-repeat="attachment in project.attachments">
					<a href="{{attachment.attachmentURL}}">{{attachment.title}}</a>
					<button class="btn btn-danger" data-ng-click="deleteAttachment(attachment.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
				</div>
			</div>
		</div>
		<div class="col-lg-6">
			<form name="projectForm">
				<fieldset>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.title.label"/></label> 
						<input class="form-control" data-ng-model="project.title" type="text" disabled/>
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
						<label><spring:message code="drones.admin.pages.project.form.service.label"/></label> 
						<input class="form-control" data-ng-model="project.service.category.name" type="text" disabled />
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.duration.label"/></label>
						<input class="form-control" type="text" data-ng-model="project.duration.title" disabled/>
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.address.label"/></label> 
						<input class="form-control" type="text" data-ng-model="project.location.address" />
					</div>
					<div class="form-group">
						<label><spring:message code="drones.admin.pages.project.form.budget.label"/></label> 
						<input class="form-control" type="text" data-ng-model="project.budget.title" disabled/>
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
					<button class="btn btn-primary btn-warning" data-ng-click="blockProject(project.id)"><spring:message code="drones.admin.pages.common.button.project.block"/></button>
					<button class="btn btn-primary btn-danger" data-ng-click="deleteProject(project.id)"><spring:message code="drones.admin.pages.common.button.delete"/></button>
					<button class="btn btn-primary action pull-right" data-ng-click="editProject(project.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
				</fieldset>
			</form>
		</div>
	</div>
</div>