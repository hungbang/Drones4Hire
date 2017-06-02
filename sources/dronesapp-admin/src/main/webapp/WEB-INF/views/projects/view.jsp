<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectDetailsController">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="drones.admin.pages.projects.view.title"/></h2>
			<ol class="breadcrumb">
				<li class="active"><spring:message code="drones.admin.pages.index.menu.project.caption"/></li>
			</ol>
		</div>
	</div>
	<form name="userForm">
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.project.photo.label"/></label><br/> 										
					<img height="90%" width="90%" alt="<spring:message code="drones.admin.pages.common.project.photo.label"/>" data-ng-src="{{project.imageURL}}">
				</div>				
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.project.form.title.label"/></label> 
					<input class="form-control" data-ng-model="project.title" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.project.form.summary.label"/></label> 
					<input class="form-control" data-ng-model="project.summary" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.project.form.clientId.label"/></label> 
					<input class="form-control" data-ng-model="project.clietnId" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.project.form.pilotId.label"/></label> 
					<input class="form-control" data-ng-model="project.pilotId" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.project.form.service.label"/></label> 
					<input class="form-control" data-ng-model="project.service.name" type="text" />
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
					<label><spring:message code="drones.admin.pages.project.form.postProdaction.label"/></label> 
					<input type="checkbox" data-ng-model="project.postProductionRequired" />
				</div>
				<button class="btn btn-primary action pull-right" data-ng-click="editProject(project.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
		</div>
	</form>
</div>