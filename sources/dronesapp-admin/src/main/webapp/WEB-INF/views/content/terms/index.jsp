<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="TermsPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Terms <button class="btn btn-default btn-primary" data-ng-click="openTermModal()">Create</button></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <label class="search">Terms found: {{ terms.length }}</label>
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
                <tr data-ng-repeat="term in terms track by $index">
                    <td data-ng-if="term">
                        <a href=""><div data-ng-click="term.show = !term.show" data-ng-bind-html="term.text.split('<p>')[0]"></div></a>
                        <div class="text-header" data-ng-show="term.show" data-ng-bind-html="term.text"></div>
                    </td>
                    <td class="col-lg-2" data-ng-if="term" style="text-align: right;">
                        <button class="btn btn-sm btn-success" data-ng-click="openTermModal(term)">Update</button>
                        <button class="btn btn-sm btn-danger" data-ng-click="deleteTerm(term.id)">Delete</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>