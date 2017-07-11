<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="UsersPageController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">{{title}}</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<fieldset>
				<form name="userSearchForm" class="form-horizontal">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-search"></i> <spring:message code="drones.admin.pages.common.serch.criteria.caption"/>
							</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<div class="row">
									<div class="col-lg-3">
										<label class="control-label" for="username"><spring:message code="drones.admin.pages.common.form.field.username.label"/></label>
										<input class="form-control" type="text" id="username" name="username" data-ng-model="sc.username">
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="firstName"><spring:message code="drones.admin.pages.common.form.field.firstName.label"/></label>
										<input class="form-control" type="text" id="firstName" name="firstName" data-ng-model="sc.firstName">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="lastName"><spring:message code="drones.admin.pages.common.form.field.lastName.label"/></label>
										<input class="form-control" type="text" id="lastName" name="lastName" data-ng-model="sc.lastName">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="email"><spring:message code="drones.admin.pages.common.form.field.email.label"/></label>
										<input class="form-control" type="text" id="email" name="email" data-ng-model="sc.email">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="confirmed">Confirmed</label>
										<select class="form-control" id="confirmed" name="confirmed" data-ng-model="sc.confirmed">
							            	<option value="true">true</option>
							            	<option value="false">false</option>
							            </select>
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="enabled">Enabled</label>
										<select class="form-control" id="enabled" name="enabled" data-ng-model="sc.enabled">
							            	<option value="true">true</option>
							            	<option value="false">false</option>
							            </select>
				                    </div>
				                    <div class="col-lg-3" data-ng-if="isPilot()">
										<label class="control-label" for="licenseVerified">License verified</label>
										<select class="form-control" id="licenseVerified" name="licenseVerified" data-ng-model="sc.licenseVerified">
							            	<option value="true">true</option>
							            	<option value="false">false</option>
							            </select>
				                    </div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"/></a>
								<a href="" data-ng-click="searchUsers(1)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"/></a>
							</div>
						</div>
					</div>
				</div>
			  </form>
			</fieldset>
		</div>
	</div>
	<div class="row">
		<div class="btn-group col-lg-6">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				Actions&nbsp;<span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<li><a href="" class="black-link" data-ng-click="downloadCSV()">download csv</a></li>
			</ul>
		</div>
		<div class="col-lg-6">
			<label class="search"><spring:message code="drones.admin.pages.common.users_found.label"/>: {{sr.totalResults}}</label>
		</div>
	</div>
	<br/>
	<div class="row">
		<div class="col-lg-12">
			<table class="table table-bordered table-hover table-striped tablesorter">
		        <thead>
		            <tr>
		            	<th class="text-center">
		                 	ID
		                </th>
		                <th>
		                    Username
		                </th>
		                <th>
		                    Email
		                </th>
		                <th>
		                    Name
		                </th>
						<th data-ng-if="isPilot()">
							Rating
						</th>
		                <th>
		                    Confirmed
		                </th>
		                <th>
		                    Enabled
		                </th>
		                <th>
		                    Registration
		                </th>
		                <th class="text-center">
		                    Options
		                </th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr data-ng-repeat="user in sr.results | orderBy:predicate:reverse">
		            	<td class="text-center">{{user.id}}</td>
		                <td>{{user.username}}</td>
		                <td class="long">{{user.email}}</td>
		                <td>{{user.firstName}} {{user.lastName}}</td>
		                <td data-ng-if="isPilot()">{{user.rating}}</td>
		                <td>{{user.confirmed}}</td>
		                <td>{{user.enabled}}</td>
		                <td>{{user.createdAt | date}}</td>
						<td class="text-center">
							<a class="btn btn-success btn-xs options" href="#/users/{{user.id}}/view" target="_blank"><i class="fa fa-search"></i></a>
						</td>
					</tr>
					<tr data-ng-show="!sr.results.length">
		                <td colspan="9" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
			    </tbody>
		    </table>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<pagination
				    	page="sc.page"
				    	page-size="sc.pageSize"
				    	results="sr"
				    	page-sizes="pageSizes"
				    	search="searchUsers(page, pageSize)" />
		</div>
	</div>
</div>