<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="FaqsPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">FAQs</h1>
        </div>
    </div>
    <div class="row">
    	<div class="col-lg-6">
			<button class="btn btn-default btn-success" data-ng-click="openFaqModal()">Create FAQ</button>
		</div>
        <div class="col-lg-6">
            <label class="search">FAQs found: {{ faqs.length }}</label>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-lg-12">
        	<table class="table table-bordered table-hover table-striped tablesorter">
        		<thead>
	                <tr>
	                    <th style="width: 70%">
	                        Question / Answer
	                    </th>
	                    <th>
	                       	Locale
	                    </th>
	                    <th>
	                        Order
	                    </th>
	                    <th class="text-center">
	                        Options
	                    </th>
	                </tr>
                </thead>
        		<tbody>
        			<tr data-ng-repeat="faq in faqs | orderBy:'order'">
	        			<td>
		        			<h4>{{faq.question}}</h4>
		                   	<p ng-bind-html="faq.answer"></p>
		        		</td>
		        		<td>
		        			{{faq.locale}}
		        		</td>
		        		<td>
		        			{{faq.order}}
		        		</td>
		        		<td class="text-center">
		        			<button class="btn btn-sm btn-success" data-ng-click="openFaqModal(faq)">Update</button>
		                    <button class="btn btn-sm btn-danger" ng-really-message="Do you really want to delete?" ng-really-click="deleteFaq(faq.id)">Delete</button>
		        		</td>
	        		<tr>
	        		<tr data-ng-show="!faqs.length">
		                <td colspan="4" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
					</tr>
        		</tbody>
        	</table>
        </div>
    </div>
</div>