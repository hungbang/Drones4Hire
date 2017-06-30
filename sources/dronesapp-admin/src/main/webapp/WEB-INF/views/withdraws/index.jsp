<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="WithdrawController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Withdraw requests</h1>
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
										<label class="control-label" for="currency"><spring:message code="drones.admin.pages.common.column.currency.caption"/></label>
										<select class="form-control" id="currency" name="currency" data-ng-model="sc.currency">
											<option value="USD">USD</option>
										</select>
									</div>
									<div class="col-lg-3">
										<label class="control-label" for="status"><spring:message code="drones.admin.pages.common.column.status.caption"/></label>
										<select class="form-control" id="status" name="status" data-ng-model="sc.status">
											<option value="NEW">NEW</option>
											<option value="NEW">PENDING</option>
											<option value="APPROVED">APPROVED</option>
											<option value="CANCELLED">FUNDED</option>
											<option value="CANCELLED">CANCELED</option>
											<option value="CANCELLED">FAILED</option>
										</select>
									</div>
							</div>
							<div class="text-right">
								<a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"/></a>
								<a href="" data-ng-click="searchWithdraws(1)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"/></a>
							</div>
						</div>
					</div>
				</div>
			  </form>
			</fieldset>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<!--a href="" data-ng-click="openPasswordCheckPopup()">Download CSV</a-->
		</div>
		<div class="col-lg-6">
			<label class="search">Withdraw requests found: {{sr.totalResults}}</label>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<table class="table table-bordered table-hover table-striped tablesorter">
		        <thead>
		            <tr>
		            	<th class="text-center">
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
		                    Admin Comment
		                </th>
		                <th>
		                    Status
		                </th>
		                 <th>
		                    Modified
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
		            <tr data-ng-repeat="request in sr.results | orderBy:predicate:reverse">
		            	<td class="text-center">{{request.id}}</td>
		                <td>{{request.userId}}</td>
		                <td>{{request.amount}}</td>
		                <td>{{request.currency}}</td>
		                <td>{{request.comment}}</td>
		                <td>{{request.adminComment}}</td>
		                <td>{{request.status}}</td>
		                <td>{{request.modifiedAt | date}}</td>
		                <td>{{request.createdAt | date}}</td>
		               <td class="text-center">
						   <button class="btn btn-success btn-xs" data-ng-click="acceptWithdraw(request)" ng-show="request.status == 'NEW'">Accept</button>
						   <button class="btn btn-danger btn-xs" data-ng-click="cancelWithdraw(request)" ng-show="request.status == 'NEW'">Cancel</button>
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
					search="searchWithdraws(page, pageSize)" />
		</div>
	</div>
</div>