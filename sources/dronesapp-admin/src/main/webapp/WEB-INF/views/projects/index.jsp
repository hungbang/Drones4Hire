<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ProjectsPageController" id="projects">
	<div class="row">
		<div class="col-lg-12">
			<h2><spring:message code="drones.admin.pages.projects.title"/></h2>
			<ol class="breadcrumb">
				<li class="active"><spring:message code="drones.admin.pages.index.menu.project.caption"/></li>
			</ol>
			<fieldset>
				<form name="projectSearchForm" class="form-horizontal">
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
										<label class="control-label" for="title"><spring:message code="drones.admin.pages.common.form.field.title.label"/></label>
										<input class="form-control" type="text" id="title" name="title" data-ng-model="projectSearchCriteria.title">
				                    </div>
									<div class="col-lg-2">
										<label class="control-label" for="summary"><spring:message code="drones.admin.pages.common.form.field.summary.label"/></label>
										<input class="form-control" type="text" id="summary" name="summary" data-ng-model="projectSearchCriteria.summary">
				                    </div>
				                    <div class="col-lg-2">
										<label class="control-label" for="status"><spring:message code="drones.admin.pages.common.column.status.caption"/></label>
										<select class="form-control" id="status" name="status" data-ng-model="projectSearchCriteria.status">
							            	<option value="NEW">NEW</option>
							            	<option value="IN_PROGRESS">IN_PROGRESS</option>
							            	<option value="COMPLETED">COMPLETED</option>
							            	<option value="CANCELLED">CANCELLED</option>
							            	<option value="PENDING">PENDING</option>
							            </select>
				                    </div>
				               </div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"/></a>
								<a href="" data-ng-click="searchProjects(0)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"/></a>
							</div>
						</div>
				</div>
			  </form>
			</fieldset>
			<fieldset>
                <label class="search"><spring:message code="drones.admin.pages.common.projects_found.label"/>: {{projectSearchResult.totalResults}}</label>
                <br/><br/>
			    <table class="table table-bordered table-hover table-striped tablesorter">
			        <thead>
			            <tr>
			            	<th class="text-center">
			                    <spring:message code="drones.admin.pages.common.column.id.caption"/>
			                </th>
			                   <th>
			                    <spring:message code="drones.admin.pages.common.form.field.title.label"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.clientId.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.pilotId.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.duration.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.budget.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.postProdaction.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.startDate.caption"/>
			                </th>
			                <th>
			                    <spring:message code="drones.admin.pages.common.column.finishDate.caption"/>
			                </th>
			                <th class="text-center">
			                    <spring:message code="drones.admin.pages.common.column.status.caption"/>
			                </th>
			                <th class="text-center">
			                    <spring:message code="drones.admin.pages.common.column.more_details.caption"/>
			                </th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr data-ng-repeat="project in projectSearchResult.results | orderBy:predicate:reverse">
			            	<td class="text-center">{{project.id}}</td>
			            	<td>{{project.title}}</td>
			                <td class="long">{{project.clientId}}</td>
			                <td>{{project.pilotId}}</td>
			                <td>{{project.duration.title}}</td>
			                <td>{{project.budget.title}} {{project.budget.min}} - {{project.budget.max}} {{project.budget.currency}}</td>
			                <td>{{project.postProductionRequired}}</td>
			                <td>{{project.startDate | date:'HH:mm dd-MM-yyyy'}}</td>
			                <td>{{project.finishDate | date:'HH:mm dd-MM-yyyy'}}</td>
			                <td>{{project.status}}</td>
							<td class="text-center">
								<a class="btn btn-success btn-xs options" href="#/projects/{{project.id}}/view" target="_blank"><i class="fa fa-search"></i></a>
							</td>
						</tr>
						<tr data-ng-show="!projectSearchResult.results.length">
			                <td colspan="12" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
						</tr>
				    </tbody>
			    </table>
			    <pagination
				    	page="projectSearchCriteria.page"
				    	page-size="projectSearchCriteria.pageSize"
				    	results="projectSearchResult"
				    	page-sizes="projectListPageSizes"
				    	search="searchProjects(page, pageSize)" />
			</fieldset>
		</div>
	</div>
</div>