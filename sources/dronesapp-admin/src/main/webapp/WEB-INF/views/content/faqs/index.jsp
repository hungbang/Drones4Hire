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
            <table class="details-table table table-striped">
                <th>
                    Question
                </th>
                <th>
                </th>
                <tr data-ng-repeat="faq in faqs track by $index">
                    <td data-ng-if="faq">
                        <a href=""><div data-ng-click="faq.show = !faq.show" class="question" data-ng-bind-html="faq.question"></div></a>
                        <div class="answer" data-ng-show="faq.show" data-ng-bind-html="faq.answer"></div>
                    </td>
                    <td class="col-lg-2" data-ng-if="faq" style="text-align: right">
                        <button class="btn btn-sm btn-success" data-ng-click="openFaqModal(faq)">Update</button>
                        <button class="btn btn-sm btn-danger" data-ng-click="deleteFaq(faq.id)">Delete</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>