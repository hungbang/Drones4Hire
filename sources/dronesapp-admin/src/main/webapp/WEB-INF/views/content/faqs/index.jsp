<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="FaqsPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">FAQ <button class="btn btn-default btn-primary" data-ng-click="openFaqModal()">Create</button></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <label class="search">FAQs found: {{ faqs.length }}</label>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="details-table">
                <th>
                </th>
                <tr data-ng-repeat="faq in faqs">
                    <td>
                        <a href=""><div data-ng-click="faq.show = !faq.show" class="question" ng-bind-html="faq.question"></div></a>
                        <span data-ng-show="faq.show" ng-bind-html="faq.answer"></span>
                    </td>
                    <td data-ng-if="faq">
                        <button class="btn btn-sm btn-success" data-ng-click="openFaqModal(faq)">Update</button>
                        <button class="btn btn-sm btn-danger" data-ng-click="deleteFaq(faq.id)">Delete</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>