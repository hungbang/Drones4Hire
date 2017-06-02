<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="UsersPageController" id="users">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="drones.admin.pages.users.title"/></h2>
			<ol class="breadcrumb">
				<li class="active"><spring:message code="drones.admin.pages.index.menu.users.caption"/></li>
			</ol>
			<fieldset>
				<form name="userSearchForm" class="form-horizontal">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-search" /> <spring:message code="drones.admin.pages.common.serch.criteria.caption"/>
							</h3>
						</div>
						<div class="panel-body">
							<div class="list-group">
								<div class="row">
									<div class="col-lg-2">
										<label class="control-label" for="username"><spring:message code="drones.admin.pages.common.form.field.username.label"/></label>
										<input class="form-control" type="text" id="username" name="username" data-ng-model="userSearchCriteria.username">
				                    </div>
									<div class="col-lg-2">
										<label class="control-label" for="firstName"><spring:message code="drones.admin.pages.common.form.field.firstName.label"/></label>
										<input class="form-control" type="text" id="firstName" name="firstName" data-ng-model="userSearchCriteria.firstName">
				                    </div>
				                    <div class="col-lg-2">
										<label class="control-label" for="lastName"><spring:message code="drones.admin.pages.common.form.field.lastName.label"/></label>
										<input class="form-control" type="text" id="lastName" name="lastName" data-ng-model="userSearchCriteria.lastName">
				                    </div>
				                    <div class="col-lg-2">
										<label class="control-label" for="email"><spring:message code="drones.admin.pages.common.form.field.email.label"/></label>
										<input class="form-control" type="text" id="email" name="email" data-ng-model="userSearchCriteria.email">
				                    </div>
				                    <div class="col-sm-2">
										<label class="control-label" for="confirmed"><spring:message code="drones.admin.pages.common.form.field.confirmed.label"/></label>
							           <input class="form-control" type="checkbox" id="confirmed" name="confirmed" data-ng-model="userSearchCriteria.confirmed">
				                    </div>
				                     <div class="col-md-2">
										<label class="control-label" for="enabled"><spring:message code="drones.admin.pages.common.form.field.enabled.label"/></label>
							           <input class="form-control" type="checkbox" id="enabled" name="confirmed" data-ng-model="userSearchCriteria.enabled">
				                    </div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"/></a>
								<a href="" data-ng-click="searchUsers(0)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"/></a>
							</div>
						</div>
					</div>
				</div>
			  </form>
			</fieldset>
			<fieldset>
                <label class="search"><spring:message code="drones.admin.pages.common.users_found.label"/>: {{userSearchResult.totalResults}}</label>
                <br/><br/>
			    <table class="table table-bordered table-hover table-striped tablesorter">
			        <thead>
			            <tr>
			            	<th class="text-center">
			                    <spring:message code="drones.admin.pages.common.column.id.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.username.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.email.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.firstName.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.lastName.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.summary.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.confirmed.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.enabled.caption"/>
			                </th>
			                <th class="text-center">
			                    <spring:message code="drones.admin.pages.common.column.more_details.caption"/>
			                </th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr data-ng-repeat="user in userSearchResult.results | orderBy:predicate:reverse">
			            	<td class="text-center">{{user.id}}</td>
			                <td>{{user.username}}</td>
			                <td class="long">{{user.email}}</td>
			                <td>{{user.firstName}}</td>
			                <td>{{user.lastName}}</td>
			                <td>{{user.summary}}</td>
			                <td>{{user.confirmed}}</td>
			                <td>{{user.enabled}}</td>
							<td class="text-center">
								<a class="btn btn-success btn-xs options" href="#/users/{{user.id}}/view" target="_blank"><i class="fa fa-search"></i></a>
							</td>
						</tr>
						<tr data-ng-show="!userSearchResult.results.length">
			                <td colspan="12" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
						</tr>
				    </tbody>
			    </table>
			    <pagination
				    	page="userSearchCriteria.page"
				    	page-size="userSearchCriteria.pageSize"
				    	results="userSearchResult"
				    	page-sizes="userListPageSizes"
				    	search="searchUsers(page, pageSize)" />
			</fieldset>
		</div>
	</div>
</div>