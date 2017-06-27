<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="CountriesPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Countries</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <fieldset>
                <form name="projectSearchForm" class="form-horizontal">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <i class="fa fa-search"></i> <spring:message code="drones.admin.pages.common.serch.criteria.caption"/>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="list-group">
                                <div class="row">
                                    <div class="col-lg-3">
                                        <label class="control-label" for="name">Name</label>
                                        <input class="form-control" type="text" id="name" name="name" data-ng-model="sc.name">
                                    </div>
                                    <div class="col-lg-3">
                                        <label class="control-label" for="code">Code</label>
                                        <input class="form-control" type="text" id="code" name="code" data-ng-model="sc.code">
                                    </div>
                                    <div class="col-lg-3">
										<label class="control-label" for="enabled">License required</label>
										<select class="form-control" id="licenseRequired" name="licenseRequired" data-ng-model="sc.licenseRequired">
							            	<option value="true">true</option>
							            	<option value="false">false</option>
							            </select>
				                    </div>
                                </div>
                            </div>
                            <div class="text-right">
                                <a href="" data-ng-click="resetSearchCriteria()" class="clear-form"><spring:message code="drones.admin.pages.common.button.clear"/>&nbsp;<i class="fa fa-times-circle"></i></a>
                                <a href="" data-ng-click="searchCountries(1)"><spring:message code="drones.admin.pages.common.button.search"/>&nbsp;<i class="fa fa-arrow-circle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <label class="search">Countries found: {{sr.totalResults}}</label>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-bordered table-hover table-striped tablesorter">
                <thead>
                <tr>
                    <th class="text-center">
                        ID
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Code
                    </th>
                    <th>
                        License required
                    </th>
                    <th class="text-center">
                        Options
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr data-ng-repeat="result in sr.results | orderBy:predicate:reverse">
                    <td class="text-center">{{result.id}}</td>
                    <td>{{result.name}}</td>
                    <td>{{result.code}}</td>
                    <td>{{result.licenseRequired}}</td>
                    <td class="text-center">
                        <a data-ng-click="openCountryModal(result)" class="btn btn-success btn-xs options" href=""><i class="fa fa-search"></i></a>
                    </td>
                </tr>
                <tr data-ng-show="!sr.results.length">
                    <td colspan="5" class="no-results"><spring:message code="drones.admin.pages.common.serch.noresults"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <pagination
                    page="sc.page"
                    page-size="sc.pageSize"
                    results="sr"
                    page-sizes="pageSizes"
                    search="searchCountries(page, pageSize)" />
        </div>
    </div>
</div>