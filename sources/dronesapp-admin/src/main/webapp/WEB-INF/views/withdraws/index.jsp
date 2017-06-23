<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="WithdrawController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Withdraw Requests</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<fieldset>
				<form name="withdrawSearchForm" class="form-horizontal">
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
										<label class="control-label" for="userId">User ID</label>
										<input class="form-control" type="text" id="userId" name="userId" data-ng-model="sc.userId">
				                    </div>
									<div class="col-lg-3">
										<label class="control-label" for="amount">Amount</label>
										<input class="form-control" type="text" id="amount" name="amount" data-ng-model="sc.amount">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="currency">Currency</label>
										<input class="form-control" type="text" id="currency" name="currency" data-ng-model="sc.currency">
				                    </div>
				                    <div class="col-lg-3">
										<label class="control-label" for="status">Status</label>
										<input class="form-control" type="text" id="status" name="status" data-ng-model="sc.status">
				                    </div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"/></a>
								<a href="" data-ng-click="searchWithdraws(0)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"/></a>
							</div>
						</div>
					</div>
				</div>
			  </form>
			</fieldset>
		</div>
	</div>
	<li><a href="" data-ng-click="openPasswordCheckPopup()">Download CSV</a></li>
	<div class="row">
		<div class="col-lg-12">
			<label class="search">Withdraw requests found: {{withdrawRequests.length}}</label>
		</div>
	</div>	
	<div class="row">
		<div class="col-lg-12">
			<table class="table table-bordered table-hover table-striped tablesorter">
		        <thead>
		            <tr>
		            	<th class="text-center">
		                   	Select
		                   	ID
		                </th>
		                <th>
		                    User ID
		                </th>
		                <th>
		                    Amount
		                </th>
		                <th>
		                    Currency
		                </th>
		                <th>
		                    Comment
		                </th>
		                <th>
		                    Status
		                </th>
		                 <th>
		                    Modified At
		                </th>
		                 <th>
		                    Created At
		                </th>
		                <th>
		                    Payment complete
		                </th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr data-ng-repeat="request in withdrawRequests | orderBy:predicate:reverse">
		            	<td class="text-center"><input type="checkbox" data-ng-model="request.id"/>{{request.id}}</td>
		                <td>{{request.userId}}</td>
		                <td>{{request.amount}}</td>
		                <td>{{request.currency}}</td>
		                <td>{{request.comment}}</td>
		                <td>{{request.status}}</td>
		                <td>{{request.modifiedAt | date}}</td>
		                <td>{{request.createdAt | date}}</td>
		               <td class="text-center">
							<a class="btn btn-success btn-xs options" href="" target="_blank"><i class="fa fa-search"></i></a>
						</td>
					</tr>
					<tr data-ng-show="!withdrawRequests.length">
		                <td colspan="9" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
			    </tbody>
		    </table>
		</div>
	</div>
</div>