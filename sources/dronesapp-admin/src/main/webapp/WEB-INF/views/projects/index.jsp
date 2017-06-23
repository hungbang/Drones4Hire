<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectsPageController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Projects</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<fieldset>
				<form name="projectSearchForm" class="form-horizontal">
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
										<label class="control-label" for="title"><spring:message code="drones.admin.pages.common.form.field.title.label"/></label>
										<input class="form-control" type="text" id="title" name="title" data-ng-model="projectSearchCriteria.title">
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="clientEmail"><spring:message code="drones.admin.pages.common.column.clientEmail.caption"/></label>
										<input class="form-control" type="text" id="clientEmail" name="clientEmail" data-ng-model="projectSearchCriteria.clientEmail">
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="pilotEmail"><spring:message code="drones.admin.pages.common.column.pilotEmail.caption"/></label>
										<input class="form-control" type="text" id="pilotEmail" name="pilotEmail" data-ng-model="projectSearchCriteria.pilotEmail">
									</div>
				                    <div class="col-lg-3">
										<label class="control-label" for="status"><spring:message code="drones.admin.pages.common.column.status.caption"/></label>
										<select class="form-control" id="status" name="status" data-ng-model="projectSearchCriteria.statuses">
							            	<option value="NEW">New</option>
							            	<option value="IN_PROGRESS">In progress</option>
							            	<option value="COMPLETED">Completed</option>
							            	<option value="CANCELLED">Cancelled</option>
							            	<option value="PENDING">Pending</option>
							            </select>
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="budget"><spring:message code="drones.admin.pages.common.column.budget.caption"/></label>
										<select class="form-control" id="budget" name="budget" data-ng-model="projectSearchCriteria.budgetId">
											<option data-ng-value="budget.id" data-ng-repeat="budget in budgets">{{budget.title}} {{budget.min}} - {{budget.max}} {{budget.currency}}</option>
										</select>
									</div>
									<div class="col-lg-3">
										<label class="control-label" for="city"><spring:message code="drones.admin.pages.common.form.field.city.label"/></label>
										<input class="form-control" type="text" id="city" name="city" data-ng-model="projectSearchCriteria.city">
									</div>
									<div class="col-lg-3">
										<label class="control-label" for="category">Service</label>
										<select class="form-control" id="category" name="category" data-ng-model="projectSearchCriteria.serviceCategoryId">
											<option data-ng-value="category.id" data-ng-repeat="category in categories">{{category.name}}</option>
										</select>
									</div>
				               </div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"></i></a>
								<a href="" data-ng-click="searchProjects(1)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
				</div>
			  </form>
			</fieldset>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<button data-ng-click="openProjectModal()" class="btn btn-success">Create project</button>
		</div>
		<div class="col-lg-6">
			<label class="search"><spring:message code="drones.admin.pages.common.projects_found.label"/>: {{projectSearchResult.totalResults}}</label>
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
		                    Title
		                </th>
		                <th>
		                    Client/Pilot email
		                </th>
		                <th>
		                   	Duration
		                </th>
		                <th>
		                    Budget
		                </th>
		                <th>
		                    Status
		                </th>
						<th>
							Country/City
						</th>
						<th>
							Service
						</th>
						<th>
							Start/End
						</th>
						<th>
		                    Created
		                </th>
		                <th class="text-center">
		                    Options
		                </th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr data-ng-repeat="result in projectSearchResult.results | orderBy:predicate:reverse">
		            	<td class="text-center">{{result.project.id}}</td>
		            	<td>{{result.project.title}}</td>
		                <td>{{result.client.email}}<br/>{{result.pilot.email}}</td>
		                <td>{{result.project.duration.title}} h</td>
		                <td>{{result.project.budget.title}}</td>
		                <td>{{result.project.status}}</td>
		                <td>{{result.project.location.country.name}}<br/>{{result.project.location.city}}</td>
		                <td>{{result.project.service.category.name}}</td>
		                <td style="min-width: 100px;">{{result.project.startDate | date}}<br/>{{result.project.finishDate | date}}</td>
		                <td style="min-width: 100px;">{{result.project.createdAt | date}}</td>
						<td class="text-center">
							<a class="btn btn-success btn-xs options" href="#/projects/{{result.project.id}}/view" target="_blank"><i class="fa fa-search"></i></a>
						</td>
					</tr>
					<tr data-ng-show="!projectSearchResult.results.length">
		                <td colspan="11" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
			    </tbody>
		    </table>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<pagination
				    	page="projectSearchCriteria.page"
				    	page-size="projectSearchCriteria.pageSize"
				    	results="projectSearchResult"
				    	page-sizes="projectListPageSizes"
				    	search="searchProjects(page, pageSize)" />
		</div>
	</div>
</div>