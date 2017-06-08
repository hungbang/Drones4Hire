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
					<textarea class="form-control" data-ng-model="user.summary"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.introduction.label"/></label> 
					<textarea class="form-control" data-ng-model="user.introduction"/>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.enabled.label"/></label> 
					<input type="checkbox" data-ng-model="user.enabled" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.confirmed.label"/></label> 
					<input type="checkbox" data-ng-model="user.confirmed" />
				</div>
				<button class="btn btn-primary action pull-right" data-ng-click="editUser(user.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.country.label"/></label><br/> 										
					<select name="countryId" class="form-control" data-ng-model="user.location.country.id" data-ng-options="country.id as country.name for country in listCountriesResult"></select>
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.state.label"/></label><br/> 										
					<select name="stateId" class="form-control" data-ng-model="user.location.state.id" data-ng-options="state.id as state.name for state in listSatesResult"></select>
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.city.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="user.location.city" />
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.address.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="user.location.address" />
				</div>					
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.postcode.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="user.location.postcode" />
				</div>	
				<button class="btn btn-primary action pull-right" data-ng-click="editLocation(user.location.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.company.name.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="company.name" />
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.company.contactName.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="company.contactName" />
				</div>
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.company.email.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="company.contactEmail" />
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.country.label"/></label><br/> 										
					<select name="company.countryId" class="form-control" data-ng-model="company.country.id" data-ng-options="country.id as country.name for country in listCountriesResult"></select>
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.company.webURL.label"/></label><br/> 										
					<input type="text" class="form-control" data-ng-model="company.webURL" />
					<a href="{{company.webURL}}" target="_blank">{{company.webURL}}</a>
				</div>	
				<button class="btn btn-primary action pull-right" data-ng-click="editCompany(company.userId)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.settings.projectAward.label"/></label> 
					<input type="checkbox" data-ng-model="settings.projectAward" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.bidPlaced.label"/></label> 
					<input type="checkbox" data-ng-model="settings.bidPlaced" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.paymentReceived.label"/></label> 
					<input type="checkbox" data-ng-model="settings.paymentReceived" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.projectUpdate.label"/></label> 
					<input type="checkbox" data-ng-model="settings.projectUpdate" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.staff.label"/></label> 
					<input type="checkbox" data-ng-model="settings.staff" /> &nbsp; &nbsp; &nbsp;
				</div>	
				<div class="form-group">
					<label><spring:message code="drones.admin.pages.common.form.field.settings.dronesNews.label"/></label> 
					<input type="checkbox" data-ng-model="settings.dronesNews" /> &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.plainEmail.label"/></label> 
					<input type="checkbox" data-ng-model="settings.plainEmail" /> &nbsp; &nbsp; &nbsp; 
					<label><spring:message code="drones.admin.pages.common.form.field.settings.monthlyNews.label"/></label> 
					<input type="checkbox" data-ng-model="settings.monthlyNews" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.marketing.label"/></label> 
					<input type="checkbox" data-ng-model="settings.marketing" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					<label><spring:message code="drones.admin.pages.common.form.field.settings.deals.label"/></label> 
					<input type="checkbox" data-ng-model="settings.deals" /> &nbsp; &nbsp; &nbsp;
				</div>	
				<button class="btn btn-primary action pull-right" data-ng-click="editSettings(settings.userId)"><spring:message code="drones.admin.pages.common.button.save"/></button>
			</div>
		</div>
	</form>
</div>