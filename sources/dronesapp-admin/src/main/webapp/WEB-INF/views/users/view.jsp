<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="UserDetailsController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1  class="page-header">User profile</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 tab_block">
			<p style="color: grey">
  				<a href="" class="tab2" data-ng-click="tabs[0].active = true" data-ng-class="{active: tabs[0].active}">Personal</a>
  				<a href="" class="tab2" data-ng-click="tabs[1].active = true" data-ng-class="{active: tabs[1].active}">Company</a>
  				<a href="" class="tab2" data-ng-click="tabs[2].active = true" data-ng-class="{active: tabs[2].active}">Notifications</a>
  				<a href="" class="tab2" data-ng-click="tabs[3].active = true" data-ng-class="{active: tabs[3].active}">Messages</a>
  				<a href="" class="tab2" data-ng-click="tabs[4].active = true" data-ng-class="{active: tabs[4].active}" data-ng-show="feedbacks">Feedbacks</a>
  				<a href="" class="tab2" data-ng-click="tabs[5].active = true" data-ng-class="{active: tabs[5].active}" data-ng-show="license.id">License & Insurance</a>
			</p>
			<tabset justified="true">
				<tab active="tabs[0].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="personalForm">
								<fieldset>
									<div class="form-group">
										<label>Roles</label><br/>
										<span class="badge" data-ng-repeat="group in user.groups">{{group.role}}</span>
									</div>	
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.photo.label"/></label><br/> 										
										<img class="img-thumbnail" width="100%" alt="Not available" data-ng-src="{{user.photoURL}}">
									</div>	
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
										<input class="form-control" data-ng-model="user.email" type="text" disabled />
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
										<input type="checkbox" data-ng-model="user.enabled" />
										<label><spring:message code="drones.admin.pages.common.form.field.confirmed.label"/></label> 
										<input type="checkbox" data-ng-model="user.confirmed" />
									</div>
									<button class="btn btn-primary action pull-right" data-ng-click="editUser(user.id)"><spring:message code="drones.admin.pages.common.button.save"/></button>
								</fieldset>
							</form>
						</div>
						<div class="col-lg-6">
							<form name="locationForm">
								<fieldset>
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
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab  active="tabs[1].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="companyForm">
								<fieldset>
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
										</div>	
										<button class="btn btn-primary action pull-right" data-ng-click="editCompany(company.userId)"><spring:message code="drones.admin.pages.common.button.save"/></button>
									
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab active="tabs[2].active">
					<div class="row">
						<div class="col-lg-6">
							<form name="notificationsForm">
								<fieldset>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.projectAward.label"/></label> 
										<input type="checkbox" data-ng-model="settings.projectAward" />
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.bidPlaced.label"/></label> 
										<input type="checkbox" data-ng-model="settings.bidPlaced" />
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.paymentReceived.label"/></label> 
										<input type="checkbox" data-ng-model="settings.paymentReceived" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.projectUpdate.label"/></label> 
										<input type="checkbox" data-ng-model="settings.projectUpdate" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.staff.label"/></label> 
										<input type="checkbox" data-ng-model="settings.staff" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.dronesNews.label"/></label> 
										<input type="checkbox" data-ng-model="settings.dronesNews" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.plainEmail.label"/></label> 
										<input type="checkbox" data-ng-model="settings.plainEmail" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.monthlyNews.label"/></label> 
										<input type="checkbox" data-ng-model="settings.monthlyNews" />
									</div>		
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.marketing.label"/></label> 
										<input type="checkbox" data-ng-model="settings.marketing" />
									</div>
									<div class="form-group">
										<label><spring:message code="drones.admin.pages.common.form.field.settings.deals.label"/></label> 
										<input type="checkbox" data-ng-model="settings.deals" />
									</div>
									<button class="btn btn-primary action pull-right" data-ng-click="editSettings(settings.userId)"><spring:message code="drones.admin.pages.common.button.save"/></button>		
								</fieldset>
							</form>
						</div>
					</div>
				</tab>
				<tab  active="tabs[3].active">
					<div class="row">
						<div class="col-lg-6">
							<form action="">
								<div class="form-group">
									<label>New message</label> 
									<textarea data-ng-model="message.message" rows="10" style="width: 100%;"></textarea>
								</div>
								<button class="btn btn-primary action pull-right" data-ng-click="sendMessage(message)">Send</button>			
							</form>
						</div>
						<div class="col-lg-6">
							<label>Messages</label>
							<div class="well" data-ng-repeat="message in messages | orderBy:'id':true">
			                    <p>
			                    	<b>{{message.type}} {{message.createdAt | date}}</b>
			                    	<br/>
			                    	<i>{{message.message}}</i>
			                    </p>
			                </div>
						</div>
					</div>
				</tab>
				<tab  active="tabs[4].active">
					<div class="row" data-ng-repeat="feedback in feedbacks | orderBy:'id':true">
						<div class="col-lg-10">
							<div class="well">
								<p>
									<b>
										<a data-ng-href="/admin/#/users/{{feedback.fromUser.id}}/view">{{feedback.fromUser.firstName}} {{feedback.fromUser.lastName}}</a>
										{{feedback.createdAt | date}}
										<i data-ng-repeat="star in feedback.stars" class="fa fa-star" aria-hidden="true"></i>
										<i data-ng-if="feedback.halfStar" class="fa fa-star-half-o" aria-hidden="true"></i>
										( <i>{{feedback.mark}}</i> )
									</b>
									<br/>
									<i>{{feedback.comment}}</i>
								</p>
								<a class="black-link" style="float: right; padding-bottom: 10px" data-ng-href="/admin/#/projects/{{feedback.projectId}}/view">open project</a>
							</div>
						</div>
						<div class="col-lg-2">
							<div class="text-center">
								<button class="btn btn-sm btn-success" data-ng-click="openFeedbackModal(feedback)">Update</button>
								<button class="btn btn-sm btn-danger" ng-really-message="Do you really want to delete?" ng-really-click="deleteFeedback(feedback.id)">Delete</button>
							</div>
						</div>
					</div>
				</tab>
				<tab  active="tabs[5].active">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label>License</label><br/> 										
								<img class="img-thumbnail" width="100%" alt="Not available" data-ng-src="{{license.licenseURL}}">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label>Insurance</label><br/> 										
								<img class="img-thumbnail" width="100%" alt="Not available" data-ng-src="{{license.insuranceURL}}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label>Verified</label> 
								<input type="checkbox" data-ng-model="license.verified" />
							</div>
							<button class="btn btn-primary action pull-right" data-ng-click="editLicense(license)">Save</button>	
						</div>
					</div>
				</tab>
			</tabset>
		</div>
	</div>
</div>