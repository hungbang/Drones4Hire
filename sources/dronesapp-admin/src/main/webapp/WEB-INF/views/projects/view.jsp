<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="UserDetailsController">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="copper.admin.pages.users.view.title"/></h2>
			<ol class="breadcrumb">
				<li><i class="fa fa-users"></i> <spring:message code="copper.admin.pages.index.menu.users.caption"/></li>
				<li class="active"><spring:message code="copper.admin.pages.common.breadcrumbs.view"/></li>
			</ol>
		</div>
	</div>
	<form name="userForm">
		<div class="row">
			<div class="col-lg-12">
				<div class="navbar navbar-inverse">
                    <div class="container" style="float: left;">
                        <div class="navbar-header">
                            <div class="navbar-brand" style="color: #5cb85c;">{{(user.accountBalance.amount) * user.country.currency.exchangeRate | number:2}} / <span style="color: white;"> {{(user.accountBalance.bonusAmount) * user.country.currency.exchangeRate | number:2}}</span> <b style="font-size: 12pt;">{{user.country.currency.code}}</b></div>
                        </div>
                        <div>
                            <ul class="nav navbar-nav">
	                                <li class="dropdown">
	                                    <a href="" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-dollar"></i> <spring:message code="copper.admin.pages.users.view.menu.balance"/> <b class="caret"></b></a>
	                                    <ul class="dropdown-menu">
	                                    	<sec:authorize access="hasAnyRole('ADMIN', 'AGENT')">
	                                        <li><a href="" data-ng-click="changeBalance(user.id, user.country.currency.code)"><spring:message code="copper.admin.pages.common.button.change"/></a></li>
	                                        <li><a href="" data-ng-really-message="<spring:message code="copper.admin.pages.users.view.menu.balance.repay.confirmation"/>" data-ng-really-click="repayUserDebt(user.id)"><spring:message code="copper.admin.pages.users.view.menu.balance.debt"/></a></li>
	                                        </sec:authorize>
	                                        <li><a href="" data-ng-click="viewBalanceTransactions(user.accountBalance.id, securedUser)"><spring:message code="copper.admin.pages.users.view.menu.balance.transactions"/></a></li>
	                                    </ul>
	                                </li>
                                
                                <sec:authorize access="hasAnyRole('ADMIN', 'AGENT')">
	                                <li class="dropdown">
	                                    <a href="" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-unlock-alt"></i> <spring:message code="copper.admin.pages.users.view.menu.moderation"/> <b class="caret"></b></a>
	                                    <ul class="dropdown-menu">
	                                        <li><a href="" data-ng-show="!STATUS_ACTIVE" type="submit" data-ng-click="acceptUser(user.id)"><spring:message code="copper.admin.pages.users.inactive.edit.button.accept"/></a></li>
											<li><a href="" data-ng-show="!STATUS_BLOCKED" data-ng-click="blockUser(user.id)"><spring:message code="copper.admin.pages.users.inactive.edit.button.block"/></a></li>
											<li><a href="" data-ng-show="!STATUS_REJECTED"  data-ng-really-message="<spring:message code="copper.admin.pages.users.inactive.edit.alert.reject"/>" data-ng-really-click="rejectUser(user.id)"><spring:message code="copper.admin.pages.users.inactive.edit.button.reject"/></a></li>
											<li><a href="" data-ng-show="!device.inBlacklist" data-ng-really-message="<spring:message code="copper.admin.pages.users.inactive.edit.alert.blacklist"/>" data-ng-really-click="addDeviceToBlacklist(user.id)" data-ng-show="device"><spring:message code="copper.admin.pages.users.inactive.edit.button.blacklist"/></a></li>
	                                   		<li><a href="" data-ng-show="STATUS_CHECK_PHONE" data-ng-click="confirmUserPhone(user.id)"><spring:message code="copper.admin.pages.users.inactive.edit.button.confirm_phone"/></a></li>
	                                    </ul>
	                                </li>
                                </sec:authorize>
                                <li><a href="" data-ng-click="contactUser(user.id)"><i class="fa fa-phone"></i> <spring:message code="copper.admin.pages.users.inactive.edit.button.contact"/></a>
                                </li>
                                <li><a href="" data-ng-click="viewUserEvents(user.id)"><i class="fa fa-eye"></i> <spring:message code="copper.admin.pages.users.inactive.edit.button.events"/></a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
			</div>
		</div>		
		<div class="row" data-ng-show="user.role != 'AGENT'">
			<div class="col-lg-4">
				<div class="form-group" style="position: relative;">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.user.photo.label"/></label> 										
					<img data-ng-file-drop ng-model="userPhoto" ng-mouseenter="userPhotoStyle={display:'block'}" ng-mouseleave="userPhotoStyle={display:'none'}" class="drop-box drop-box-img" data-drag-over-class="{accept:'dragover', reject:'dragover', delay:100}" accept="*.*" data-ng-file-change="uploadUserPhoto(userPhoto, user.id)" alt="<spring:message code="copper.admin.pages.users.details.userForm.field.user.photo.label"/>" class="img-responsive center" data-ng-src="{{user.photoUrl}}">
					<div class="upload-text" ng-style="userPhotoStyle" ng-mouseenter="userPhotoStyle={display:'block'}"><spring:message code="copper.admin.pages.users.details.userForm.field.photo.drop"/></div>
				</div>				
			</div>
			<div class="col-lg-4" data-ng-show="user.role == 'DRIVER'">
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.license.photo.label"/></label>					
					<img data-ng-file-drop ng-model="licensePhoto" ng-mouseenter="licensePhotoStyle={display:'block'}" ng-mouseleave="licensePhotoStyle={display:'none'}" class="drop-box drop-box-img" data-drag-over-class="{accept:'dragover', reject:'dragover', delay:100}" accept="*.*" data-ng-file-change="uploadLicensePhoto(licensePhoto, user.id)" alt="<spring:message code="copper.admin.pages.users.details.userForm.field.license.photo.label"/>" class="img-responsive" data-ng-src="{{license.photoUrl}}">
					<div class="upload-text" ng-style="licensePhotoStyle" ng-mouseenter="licensePhotoStyle={display:'block'}"><spring:message code="copper.admin.pages.users.details.userForm.field.photo.drop"/></div>
				</div>
			</div>
			<div class="col-lg-4" data-ng-show="user.role == 'DRIVER'">
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.photo.label"/></label> 					
					<img data-ng-file-drop ng-model="carPhoto" ng-mouseenter="carPhotoStyle={display:'block'}" ng-mouseleave="carPhotoStyle={display:'none'}" class="drop-box drop-box-img" data-drag-over-class="{accept:'dragover', reject:'dragover', delay:100}" accept="*.*" data-ng-file-change="uploadCarPhoto(carPhoto, user.id)" alt="<spring:message code="copper.admin.pages.users.details.userForm.field.car.photo.label"/>" class="img-responsive" data-ng-src="{{car.photoUrl}}">
					<div class="upload-text" ng-style="carPhotoStyle" ng-mouseenter="carPhotoStyle={display:'block'}"><spring:message code="copper.admin.pages.users.details.userForm.field.photo.drop"/></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<div class="form-group">
					<span data-ng-repeat="tag in user.tags.split('#')" data-ng-if="tag" class="badge tag">
						<span>#</span>{{tag}}
						<button type="button" class="tag" data-ng-click="deleteUserTag(user.id, tag)">×</button>
					</span>
					<span class="badge tag_add" style="background: #5cb85c;" data-ng-click="openAddUserTagModal(user.id)"><spring:message code="copper.admin.pages.common.button.tag"/><button type="button" class="tag">+</button></span>
				</div>
				<div class="form-group">
					<div style="float: left;">
						<span class="label label-success ng-binding" data-ng-show="STATUS_ACTIVE">A</span>
						<span class="label label-primary ng-binding" data-ng-show="STATUS_CHECK_PHONE">CP</span>
						<span class="label label-primary ng-binding" data-ng-show="STATUS_CHECK_LICENSE">CL</span>
						<span class="label label-warning ng-binding" data-ng-show="STATUS_BLOCKED">B</span>
						<span class="label label-danger ng-binding" data-ng-show="STATUS_REJECTED">R</span>
						<span class="label label-black ng-binding" data-ng-show="device.inBlacklist">BL</span>
					</div>
					<div style="float: right;">	
						<i class="fa fa-star" data-ng-show="user.rating > 0.5"></i>
			        	<i class="fa fa-star" data-ng-show="user.rating > 1.5"></i>
			        	<i class="fa fa-star" data-ng-show="user.rating > 2.5"></i>
			        	<i class="fa fa-star" data-ng-show="user.rating > 3.5"></i>
			        	<i class="fa fa-star" data-ng-show="user.rating > 4.5"></i>
			       		({{user.rating | number:1}})
			       		<button type="button" class="btn btn-success btn-xs" data-ng-click="viewUserRating(user.id)"><spring:message code="copper.admin.pages.common.button.view"/></button>
			       		<button type="button" class="btn btn-warning btn-xs" data-ng-click="editUserRating(user)"><spring:message code="copper.admin.pages.common.button.edit"/></button>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.common.form.field.role.label"/></label> 
					<input class="form-control" data-ng-model="user.role" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.firstName.label"/></label> 
					<input class="form-control" data-ng-model="user.firstName" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.lastName.label"/></label> 
					<input class="form-control" data-ng-model="user.lastName" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.email.label"/> (<spring:message code="copper.admin.pages.users.details.userForm.field.isEmailSubscribe.label"/>&nbsp;<input type="checkbox" data-ng-model="user.emailSubscribe" />)</label> 
					<input class="form-control" data-ng-model="user.email" type="text" disabled/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.phone.label"/></label> 
					<input class="form-control" data-ng-model="user.phone" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.country.label"/></label> 
					<input class="form-control" data-ng-model="user.country.code" type="text" disabled/>
				</div>
				<div class="form-group" data-ng-show="user.role == 'DRIVER'">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.city.label"/></label> 
					<select name="cityId" class="form-control" data-ng-model="user.cityId" data-ng-options="city.id as city.city for city in listCitiesResult.cities" disabled>
                	</select>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.locale.label"/></label> 
					<input class="form-control" data-ng-model="user.locale" type="text" disabled/>
				</div>
				<div class="form-group" data-ng-show="user.platform">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.platform.label"/></label> 
					<input class="form-control" data-ng-model="user.platform" type="text" disabled/>
				</div>
				<sec:authorize access="hasAnyRole('ADMIN', 'AGENT')">
				<button class="btn btn-primary action" data-ng-click="editUser(user.id)"><spring:message code="copper.admin.pages.common.button.save"/></button>
				</sec:authorize>
			</div>
			<div class="col-lg-4" data-ng-show="user.role == 'DRIVER'">
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.license.id.label"/></label> 
					<input class="form-control" data-ng-model="license.licenseId" type="text"/>
				</div>
				<sec:authorize access="hasAnyRole('ADMIN', 'AGENT')">
				<button class="btn btn-primary action" data-ng-click="editLicense(user.id)"><spring:message code="copper.admin.pages.common.button.save"/></button>
				</sec:authorize>
			</div>
			<div class="col-lg-4" data-ng-show="user.role == 'DRIVER'">
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.type.label"/> <!-- (<spring:message code="copper.admin.pages.common.accept_lower_ranks.label"/>&nbsp;<input type="checkbox" data-ng-model="car.acceptLowerRank" />) --></label> 
					<sec:authorize access="hasAnyRole('ADMIN', 'AGENT', 'OPERATOR')">
						<select name="carType" class="form-control" data-ng-model="car.carType.id">
							<option data-ng-repeat="carType in listCarTypesResult.carTypes" data-ng-selected="carType.id == car.carType.id" value="{{carType.id}}">
								{{
									(carType.name == 'BUDGET') ? ('EasyFly') :
									((carType.name == 'STANDARD') ? ('GoodFly') :
									((carType.name == 'BUSINESS') ? ('BizFly') :
									((carType.name == 'PREMIUM') ? ('Premium') :
									('BigFly'))))
								}}
							</option>
						</select>
                	</sec:authorize>
                	<sec:authorize access="hasAnyRole('PARTNER')">
						<select name="carType" class="form-control" data-ng-model="car.carType.id" data-ng-options="carType.id as carType.name for carType in listCarTypesResult.carTypes | orderBy:'id'" disabled></select>
                	</sec:authorize>
					<div class="error" data-ng-show="userForm.carType.$invalid && userForm.submitted">
						<small class="error" data-ng-show="userForm.carType.$error.required"><spring:message code="copper.admin.pages.users.details.userForm.field.car.type.invalid.required"/></small> 
					</div>
				</div>
				<sec:authorize access="hasAnyRole('ADMIN', 'AGENT', 'OPERATOR')">
				<div class="form-group" data-ng-if="showAcceptedCarTypes">
					<label><spring:message code="copper.admin.pages.common.form.field.accept_car_types.label"/></label>
					<div data-ng-repeat="carType in listCarTypesResult.carTypes | orderBy:'id'" ><input type="checkbox" data-ng-model="acceptedCarTypes[carType.id]" />
						{{
							(carType.name == 'BUDGET') ? ('EasyFly') :
							((carType.name == 'STANDARD') ? ('GoodFly') :
							((carType.name == 'BUSINESS') ? ('BizFly') :
							((carType.name == 'PREMIUM') ? ('Premium') :
							('BigFly'))))
						}}
					</div>
				</div>
				</sec:authorize>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.model.label"/></label> 
					<input class="form-control" data-ng-model="car.model" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.year.label"/></label> 
					<input class="form-control" data-ng-model="car.productionYear" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.regnumber.label"/></label> 
					<input class="form-control" data-ng-model="car.registrationNumber" type="text"/>
				</div>
				<div class="form-group">
					<label><spring:message code="copper.admin.pages.users.details.userForm.field.car.seats.label"/></label> 
					<input class="form-control" data-ng-model="car.seats" type="text"/>
				</div>
				<sec:authorize access="hasAnyRole('ADMIN', 'AGENT', 'OPERATOR')">
					<button class="btn btn-primary action" data-ng-click="editCar(user.id)"><spring:message code="copper.admin.pages.common.button.save"/></button>
				</sec:authorize>
			</div>
		</div>
	</form>
</div>