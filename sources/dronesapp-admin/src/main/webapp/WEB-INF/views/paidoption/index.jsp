<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="PaidOptionController" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Paid options</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<label class="search">Paid options found: {{paidOptions.length}}</label>
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
		                    Modified
		                </th>
		                <th class="text-center">
		                    Options
		                </th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr data-ng-repeat="option in paidOptions | orderBy:predicate:reverse">
		            	<td class="text-center">{{option.id}}</td>
		                <td>{{option.title}}</td>
		                <td style="max-width: 300px;">
		                	{{option.description}}
		                </td>
		                <td>{{option.price}} {{option.currency}}</td>
		                <td style="min-width: 110px;">{{option.modifiedAt | date}}</td>
						<td class="text-center">
							<a class="btn btn-success btn-xs options" data-ng-click="openEditPaidOptionModal(option)" target="_blank"><i class="fa fa-search"></i></a>
						</td>
					</tr>
					<tr data-ng-show="!paidOptions.length">
		                <td colspan="6" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
			    </tbody>
		    </table>
		</div>
	</div>
</div>