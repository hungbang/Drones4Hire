<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="ServiceFeesPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Service fees</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <button class="btn btn-default btn-success" data-ng-click="openServiceFeeModal()">Create service fee</button>
        </div>
        <div class="col-lg-6">
            <label class="search">Service fees found: {{ serviceFees.length }}</label>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-bordered table-hover table-striped tablesorter">
                <thead>
                <tr>
                    <th>
                        Type
                    </th>
                    <th>
                        Percentage
                    </th>
                    <th>
                        Fixed
                    </th>
                    <th>
                        Currency
                    </th>
                    <th class="text-center">
                        Options
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr data-ng-repeat="serviceFee in serviceFees">
                    <td>
                        {{serviceFee.type}}
                    </td>
                    <td>
                        {{serviceFee.percentage}}
                    </td>
                    <td>
                        {{serviceFee.fixed}}
                    </td>
                    <td>
                        {{serviceFee.currency}}
                    </td>
                    <td class="text-center">
                        <button class="btn btn-sm btn-success" data-ng-click="openServiceFeeModal(serviceFee)">Update</button>
                        <button class="btn btn-sm btn-danger" ng-really-message="Do you really want to delete?" ng-really-click="deleteServiceFee(serviceFee.id)">Delete</button>
                    </td>
                <tr>
                <tr data-ng-show="!serviceFees.length">
                    <td colspan="5" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>