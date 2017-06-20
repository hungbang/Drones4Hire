<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="PaidOptionController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Paid Options</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<label class="search">Paid Options found: {{paidOptions.length}}</label>
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
		                    Title
		                </th>
		                <th>
		                    Description
		                </th>
		                <th>
		                    Price
		                </th>
		                 <th>
		                    Modified At
		                </th>
		                 <th>
		                    Created At
		                </th>
		                <th class="text-center">
		                    Edit
		                </th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr data-ng-repeat="option in paidOptions | orderBy:predicate:reverse">
		            	<td class="text-center">{{option.id}}</td>
		                <td>{{option.title}}</td>
		                <td>{{option.description}}</td>
		                <td>{{option.price}} {{option.currency}}</td>
		                <td>{{option.modifiedAt | date}}</td>
		                <td>{{option.createdAt | date}}</td>
						<td class="text-center">
							<a class="btn btn-success btn-xs options" data-ng-click="openEditPaidOptionModal(option)" target="_blank"><i class="fa fa-search"></i></a>
						</td>
					</tr>
					<tr data-ng-show="!paidOptions.length">
		                <td colspan="9" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
			    </tbody>
		    </table>
		</div>
	</div>
</div>