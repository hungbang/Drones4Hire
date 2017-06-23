<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="DashboardCtrl" class="container-fluid">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Dashboard</h1>
		</div>
	</div>
	<div class="row">
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-users fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">{{overview['usersTotal']}}<small data-ng-if="!overview">Loading..</small></div>
                            <div>Users registered</div>
                        </div>
                    </div>
                </div>
                <a href="#/users/clients">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-green">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-suitcase fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">{{overview['projectsTotal']}}<small data-ng-if="!overview">Loading..</small></div>
                            <div>Projects created</div>
                        </div>
                    </div>
                </div>
                <a href="#/projects">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-yellow">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-money fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">{{overview['withdrawRequestsTotal']}}<small data-ng-if="!overview">Loading..</small></div>
                            <div>Withdraw requests</div>
                        </div>
                    </div>
                </div>
                <a href="#/withdraws">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-lg-3 col-md-6">
            <div class="panel panel-red">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-id-card fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">{{overview['polotsToVerifyTotal']}}<small data-ng-if="!overview">Loading..</small></div>
                            <div>Pilots to verify</div>
                        </div>
                    </div>
                </div>
                <a href="#/users/pilots">
                    <div class="panel-footer">
                        <span class="pull-left">View Details</span>
                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                        <div class="clearfix"></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="row">
		<div class="col-lg-6">
			<div class="panel panel-primary">
			  <div class="panel-heading">
				<h3 class="panel-title">Role fragmentation</h3>
			  </div>
			  <div class="panel-body" style="height: 450px;">
			  	<small data-ng-if="!overview">Loading..</small>
				<div id="roles-ratio-graph"></div>
			  </div>
			</div>
		</div>
		<div class="col-lg-6">
			<div class="panel panel-primary">
			  <div class="panel-heading">
				<h3 class="panel-title">Project statuses</h3>
			  </div>
			  <div class="panel-body"  style="height: 450px;">
			  	<small data-ng-if="!overview">Loading..</small>
				<div id="projects-graph"></div>
			  </div>
			</div>
		</div>
	</div>
</div>