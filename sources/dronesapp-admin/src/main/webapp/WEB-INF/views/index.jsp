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

        <title><spring:message code="drones.admin.pages.index.title"/></title>
    </head>
    <body>
    	<!--  div id="loading-indicator"><div class='spinner'></div></div-->
        <!--  Admin template: http://startbootstrap.com/templates/sb-admin  -->
        <div id="wrapper">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div id="navigation-header" class="navbar-header" data-ng-controller="ProfileCtrl">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    	<div class="logo"></div>
                    	<a class="navbar-brand" href="#/dashboard" style="margin-left: 30px;">
	                    	<spring:message code="drones.admin.pages.admin.panel.caption"/>
	                    </a>
                 </div>

                <div id="navigation-menu" class="collapse navbar-collapse">
                    <!--  TODO: Navigation links  -->
	                    <ul class="nav navbar-nav side-nav" data-ng-controller="NavbarController">
	                    	<li data-ng-class="{active : isActive('/dashboard')}">
	                            <a href="#/dashboard"><i class="fa fa-dashboard"></i> <spring:message code="drones.admin.pages.index.menu.dashboard.caption"/></a>
	                        </li>
							<li data-ng-class="{active : isActive('/users')}">
	                            <a href="#/users"><i class="fa fa-users"></i> <spring:message code="drones.admin.pages.index.menu.users.caption"/></a>
	                        </li>
	                        <li data-ng-class="{active : isActive('/projects')}">
	                            <a href="#/projects"><i class="fa fa-fighter-jet"></i> <spring:message code="drones.admin.pages.index.menu.project.caption"/></a>
	                        </li>
	                        <!-- li data-ng-class="{active : isActive('/paidoptions')}">
	                            <a href="#/paidoptions"><i class="fa fa-credit-card"></i> <spring:message code="drones.admin.pages.index.menu.paidoptions.caption"/></a>
	                        </li-->
	                    </ul>
                    <ul class="nav navbar-nav navbar-right navbar-user" data-ng-controller="NavigationCtrl">
                        <li class="dropdown user-dropdown">
                            <a href="#/settings"><i class="fa fa-gear"></i> <spring:message code="drones.admin.pages.settings.title"/></a>
                        </li>
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





