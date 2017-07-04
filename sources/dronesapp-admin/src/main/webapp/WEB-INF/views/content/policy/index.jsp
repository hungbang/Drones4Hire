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

        </div>
    </div>
</div>