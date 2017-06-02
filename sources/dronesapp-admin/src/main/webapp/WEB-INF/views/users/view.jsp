<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="UserDetailsController">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="drones.admin.pages.users.view.title"/></h2>
			<ol class="breadcrumb">
				<li class="active"><spring:message code="drones.admin.pages.index.menu.users.caption"/></li>
			</ol>
		</div>
	</div>
	<form name="userForm">
		<div class="row">
			<div class="col-lg-2">
				<div class="navbar navbar-inverse">
                    <div class="container" style="float: left;">
                        <div>
                            <ul class="nav navbar-nav">
                                <li>
                                	<a href="" data-ng-click="contactUser(user.id)"><i class="fa fa-phone"></i> <spring:message code="drones.admin.pages.users.inactive.edit.button.contact"/></a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
			</div>
		</div>		
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.photo.label"/></label><br/> 										
					<img height="90%" width="90%" alt="<spring:message code="drones.admin.pages.common.form.field.photo.label"/>" data-ng-src="{{user.photoURL}}">
				</div>				
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.username.label"/></label> 
					<input class="form-control" data-ng-model="user.username" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.firstName.label"/></label> 
					<input class="form-control" data-ng-model="user.firstName" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.lastName.label"/></label> 
					<input class="form-control" data-ng-model="user.lastName" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.email.label"/></label> 
					<input class="form-control" data-ng-model="user.email" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.summary.label"/></label> 
					<input class="form-control" data-ng-model="user.summary" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.enabled.label"/></label> 
					<input type="checkbox" data-ng-model="user.enabled" />
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.email.label"/></label> 
					<input type="checkbox" data-ng-model="user.confirmed" />
				</div>
				<button class="btn btn-primary action pull-right" data-ng-click="editUser(user.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
		</div>
	</form>
</div>