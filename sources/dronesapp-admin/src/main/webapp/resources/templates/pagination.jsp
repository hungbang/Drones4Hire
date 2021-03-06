<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div class="drones-pagination">
	<form name="searchForm" class="form-horizontal">
		<div class="row">
			<label class="col-sm-1 col-md-offset-7 control-label">
				<spring:message code="drones.admin.pages.common.paging.page"/>
			</label>
			<div class="col-sm-1">
				<select class="form-control input" style="padding-left: 0;"
					data-ng-model="page"
					data-ng-change="search({page:page})">
					<option data-ng-repeat="page in range(1, results.totalResults / results.pageSize)"
						value="{{page}}">{{page}}</option>
				</select>
			</div>
			<label class="col-sm-2 control-label">
				<spring:message code="drones.admin.pages.common.paging.perpage"/>
			</label>
			<div class="col-sm-1">
				<select class="form-control input" style="padding-left: 0;"
					data-ng-model="pageSize"
					data-ng-change="search({page:1, pageSize:pageSize})">
					<option data-ng-repeat="pageSize in pageSizes"
						value="{{pageSize}}">{{pageSize}}</option>
				</select>
			</div>
		</div>
	</form>
</div>
