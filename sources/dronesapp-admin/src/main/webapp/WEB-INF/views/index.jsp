<%@ page 
    language="java"
    contentType="text/html; charset=UTF-8"
    trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<!DOCTYPE html>
<html data-ng-app="DronesAdmin">
    <head>
        <%@ include file="/WEB-INF/fragments/meta.jsp" %>
        <%@ include file="/WEB-INF/fragments/links.jsp" %>

        <title>Drones4Hire admin</title>
    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div id="navigation-header" class="navbar-header" data-ng-controller="ProfileCtrl">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    	<a class="navbar-brand" href="#/dashboard">
                    		<img src="resources/img/drones_logo.png" class="menu-logo" alt="Drones4Hire"/>
	                    	<span style="line-height: 25px;">Drones4Hire admin</span>
	                    </a>
                 </div>

                <div id="navigation-menu" class="collapse navbar-collapse">
                    <!--  TODO: Navigation links  -->
	                    <ul class="nav navbar-nav side-nav" data-ng-controller="NavbarController">
	                    	<li data-ng-class="{active : isActive('/dashboard')}">
	                            <a href="#/dashboard"><i class="fa fa-dashboard"></i> <spring:message code="drones.admin.pages.index.menu.dashboard.caption"/></a>
	                        </li>
	                        <li>
		                        <a href="javascript:;" data-toggle="collapse" data-target="#users"><i class="fa fa-fw fa-users"></i> Users <i class="fa fa-fw fa-caret-down"></i></a>
		                        <ul id="users" class="collapse">
		                            <li>
		                                <a href="#/users/pilots">Pilots</a>
		                            </li>
		                            <li>
		                                <a href="#/users/clients">Clients</a>
		                            </li>
		                        </ul>
		                    </li>
	                        <li data-ng-class="{active : isActive('/projects')}">
	                            <a href="#/projects"><i class="fa fa-suitcase"></i> Projects</a>
	                        </li>
	                        <li data-ng-class="{active : isActive('/withdraws')}">
	                            <a href="#/withdraws"><i class="fa fa-money"></i> Withdraws</a>
	                        </li>
	                        <li data-ng-class="{active : isActive('/paidoptions')}">
	                            <a href="#/paidoptions"><i class="fa fa-credit-card"></i> Paid options</a>
	                        </li>
	                    </ul>
                    <ul class="nav navbar-right top-nav" data-ng-controller="NavigationCtrl">
                        <li class="dropdown user-dropdown">
                            <a href="<c:url value="/logout" />" data-ng-click="logout()"><i class="fa fa-power-off"></i> <spring:message code="drones.admin.pages.common.button.logout"/></a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div id="page-wrapper" data-ng-view></div>
        </div>
    </body>
</html>





