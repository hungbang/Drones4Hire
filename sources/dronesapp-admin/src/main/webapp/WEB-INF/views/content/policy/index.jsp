<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="PolicyPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Policy <button class="btn btn-default btn-primary" data-ng-click="openPolicyModal()">Create</button></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <label class="search">Policy found: {{ policy.length }}</label>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="details-table table table-striped">
                <th>
                    Text
                </th>
                <th>
                </th>
                <tr data-ng-repeat="policyItem in policy track by $index">
                    <td data-ng-if="policy">
                        <a href=""><div data-ng-click="policyItem.show = !policyItem.show" data-ng-bind-html="policyItem.text.split('<p>')[0]"></div></a>
                        <div class="text-header" data-ng-show="policyItem.show" data-ng-bind-html="policyItem.text"></div>
                    </td>
                    <td class="col-lg-2" data-ng-if="policyItem" style="text-align: right;">
                        <button class="btn btn-sm btn-success" data-ng-click="openPolicyModal(policyItem)">Update</button>
                        <button class="btn btn-sm btn-danger" data-ng-click="deletePolicy(policyItem.id)">Delete</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>