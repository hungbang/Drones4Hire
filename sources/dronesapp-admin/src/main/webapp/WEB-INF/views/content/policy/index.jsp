<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="PolicyPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Privacy Policy</h1>
        </div>
    </div>
    <div class="row">
    	<div class="col-lg-6">
			<button class="btn btn-default btn-success" data-ng-click="openPolicyModal()">Create policy</button>
		</div>
        <div class="col-lg-6">
            <label class="search">Policy found: {{ policy.length }}</label>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-lg-12">
        	<table class="table table-bordered table-hover table-striped tablesorter">
        		<thead>
	                <tr>
	                    <th style="width: 80%">
	                       	Text
	                    </th>
	                    <th>
	                       	Locale
	                    </th>
	                    <th class="text-center">
	                        Options
	                    </th>
	                </tr>
                </thead>
        		<tbody>
        			<tr data-ng-repeat="policyItem in policy">
	        			<td>
		                   	<p ng-bind-html="policyItem.text"></p>
		        		</td>
		        		<td>
		        			{{policyItem.locale}}
		        		</td>
		        		<td class="text-center">
		                    <button class="btn btn-sm btn-success" data-ng-click="openPolicyModal(policyItem)">Update</button>
                        	<button class="btn btn-sm btn-danger" ng-really-message="Do you really want to delete?" ng-really-click="deletePolicy(policyItem.id)">Delete</button>
		        		</td>
	        		<tr>
	        		<tr data-ng-show="!policy.length">
		                <td colspan="3" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
        		</tbody>
        	</table>
        </div>
    </div>
</div>