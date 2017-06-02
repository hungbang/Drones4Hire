<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="OrdersListCtrl">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="copper.admin.pages.orders.title"/></h2>
			<ol class="breadcrumb">
				<li class="active"><i class="fa fa-money"></i> <spring:message code="copper.admin.pages.index.menu.orders.caption"/></li>
			</ol>
			<fieldset>
				<form name="orderSearchForm" class="form-horizontal">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-search"/> <spring:message code="copper.admin.pages.common.serch.criteria.caption"/></h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<div class="row">
									<div class="col-lg-3">
										<label class="control-label" for="id"><spring:message code="copper.admin.pages.common.form.field.id.label"/></label>
										<input class="form-control" type="number" id="id" name="id" data-ng-model="orderSearchCriteria.id">
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="driverId"><spring:message code="copper.admin.pages.common.form.field.driverId.label"/></label>
										<input class="form-control" type="number" id="driverId" name="driverId" data-ng-model="orderSearchCriteria.driverId">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="passengerId"><spring:message code="copper.admin.pages.common.form.field.passengerId.label"/></label>
										<input class="form-control" type="number" id="passengerId" name="passengerId" data-ng-model="orderSearchCriteria.passengerId">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="status"><spring:message code="copper.admin.pages.common.form.field.status.label"/></label>
							            <select class="form-control" id="status" name="status" data-ng-model="orderSearchCriteria.status">
							            	<option value="NEW"><spring:message code="copper.admin.orders.NEW"/></option>
							            	<option value="IN_PROGRESS"><spring:message code="copper.admin.orders.IN_PROGRESS"/></option>
							            	<option value="CAR_NOT_FOUND"><spring:message code="copper.admin.orders.CAR_NOT_FOUND"/></option>
							            	<option value="COMPLETED"><spring:message code="copper.admin.orders.COMPLETED"/></option>
							            	<option value="CANCELED_BY_DRIVER"><spring:message code="copper.admin.orders.CANCELED_BY_DRIVER"/></option>
							            	<option value="CANCELED_BY_PASSENGER"><spring:message code="copper.admin.orders.CANCELED_BY_PASSENGER"/></option>
							            	<option value="EXTERNAL_SERVICE"><spring:message code="copper.admin.orders.EXTERNAL_SERVICE"/></option>
							            	<option value="PREORDER"><spring:message code="copper.admin.orders.PREORDER"/></option>
							            </select>
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="carType"><spring:message code="copper.admin.pages.users.details.userForm.field.car.type.label"/></label>
										<select name="carTypeId" class="form-control" data-ng-model="orderSearchCriteria.carTypeId">
											<option data-ng-repeat="carType in listCarTypesResult.carTypes" value="{{carType.id}}">
												{{
													(carType.name == 'BUDGET') ? ('EasyFly') :
													((carType.name == 'STANDARD') ? ('GoodFly') :
													((carType.name == 'BUSINESS') ? ('BizFly') :
													((carType.name == 'PREMIUM') ? ('Premium') :
													('BigFly'))))
												}}
											</option>
										</select>
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="createdAtAfter"><spring:message code="copper.admin.pages.common.form.field.createdAtAfter.label"/></label>
										<input class="form-control" type="datetime-local" id="createdAtAfter" name="createdAtAfter" data-ng-model="createdAtAfter">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="createdAtBefore"><spring:message code="copper.admin.pages.common.form.field.createdAtBefore.label"/></label>
										<input class="form-control" type="datetime-local" id="createdAtBefore" name="createdAtBefore" data-ng-model="createdAtBefore">
				                    </div>
								</div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="copper.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"></i></a>
								<a href="" data-ng-click="searchOrders(0)"><spring:message code="copper.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
			    </form>
			</fieldset>
			<fieldset>
				<sec:authorize access="hasAnyRole('ADMIN')">
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							<spring:message code="copper.admin.pages.common.button.actions"/>&nbsp;<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="" data-ng-click="openPasswordCheckPopup()"><spring:message code="copper.admin.pages.common.button.download.csv"/></a></li>
						</ul>
					</div>
				</sec:authorize>
                <label class="search"><span class="error">{{orderSearchResult.totalResults}}</span> <spring:message code="copper.admin.pages.common.orders.total.amount"/>&nbsp;<spring:message code="copper.admin.pages.common.orders.total.price"/>&nbsp;<span class="success">{{orderSearchResult.totalPrice * currency.exchangeRate | number:2}} {{currency.code}}</span></label>
                <br/><br/>
			    <table class="table table-bordered table-hover table-striped tablesorter">
			        <thead>
			            <tr>
			                <th class="text-center">
			                    <spring:message code="copper.admin.pages.common.column.id.caption"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.users.role.DRIVER"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.users.role.PASSENGER"/>
			                </th>
			                <th>
			                	<spring:message code="copper.admin.pages.common.column.car.type.caption"/>
							</th>
			                <th>
			                    <spring:message code="copper.admin.pages.common.column.from.caption"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.pages.common.column.to.caption"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.pages.common.column.status.caption"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.pages.common.column.total.caption"/>
			                </th>
			                <th>
			                    <spring:message code="copper.admin.pages.common.column.created.caption"/>
			                </th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr data-ng-repeat="order in orderSearchResult.results | orderBy:predicate:reverse">
			                <td class="text-center"><a href="" data-ng-click="openOrderReceipt(order.id)">{{order.id}}</a></td>
			                <td><a href="#/users/{{order.driverId}}/view">{{order.driverId}}</a></td>
			                <td><a href="#/users/{{order.passengerId}}/view">{{order.passengerId}}</a></td>
			                <td>
								{{
									(order.carType.name == 'BUDGET') ? ('EasyFly') :
									((order.carType.name == 'STANDARD') ? ('GoodFly') :
									((order.carType.name == 'BUSINESS') ? ('BizFly') :
									((order.carType.name == 'PREMIUM') ? ('Premium') :
									('BigFly'))))
								}}
							</td>
			                <td>{{order.addressFrom}}</td>
			                <td>{{order.addressTo}}</td>
			                <td class="minw100" data-ng-switch on="order.status">
			                	<span data-ng-switch-when="NEW"><spring:message code="copper.admin.orders.NEW"/></span>
			                	<span data-ng-switch-when="IN_PROGRESS"><spring:message code="copper.admin.orders.IN_PROGRESS"/></span>
			                	<span data-ng-switch-when="CAR_NOT_FOUND" class="error"><spring:message code="copper.admin.orders.CAR_NOT_FOUND"/></span>
			                	<span data-ng-switch-when="COMPLETED" class="success"><spring:message code="copper.admin.orders.COMPLETED"/></span>
			                	<span data-ng-switch-when="CANCELED_BY_DRIVER"><spring:message code="copper.admin.orders.CANCELED_BY_DRIVER"/></span>
			                	<span data-ng-switch-when="CANCELED_BY_PASSENGER"><spring:message code="copper.admin.orders.CANCELED_BY_PASSENGER"/></span>
			                	<span data-ng-if="order.status == 'CANCELED_BY_PASSENGER' && order.driverId == null"> (<spring:message code="copper.admin.orders.during_search"/>)</span>
			                	<span data-ng-switch-when="FAILED"><spring:message code="copper.admin.orders.FAILED"/></span>
			                	<span data-ng-switch-when="PAID"><spring:message code="copper.admin.orders.PAID"/></span>
			                	<span data-ng-switch-when="OFFLINE_PROCESSING"><spring:message code="copper.admin.orders.OFFLINE_PROCESSING"/></span>
			                	<span data-ng-switch-when="EXTERNAL_SERVICE"><spring:message code="copper.admin.orders.EXTERNAL_SERVICE"/></span>
			                	<span data-ng-switch-when="PREORDER"><spring:message code="copper.admin.orders.PREORDER"/></span>
			                </td>
			                <td class="minw80">
			                	<span data-ng-show="order.totalPrice != null">{{order.totalPrice * currency.exchangeRate | number:2}} {{currency.code}}</span>
			               	</td>
			                <td>{{order.createdAt | date:'HH:mm dd-MM-yyyy'}}</td>
						</tr>
						<tr data-ng-show="!orderSearchResult.results.length">
			                <td colspan="10" class="no-results"><spring:message code="copper.admin.pages.common.serch.noresults"/></td>
						</tr>
				    </tbody>
			    </table>
			    <pagination
			    	page="orderSearchCriteria.page"
			    	page-size="orderSearchCriteria.pageSize"
			    	results="orderSearchResult"
			    	page-sizes="orderListPageSizes"
			    	search="searchOrders(page, pageSize)" />
			</fieldset>
		</div>
	</div>
</div>