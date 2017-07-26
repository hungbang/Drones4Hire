<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="MapsPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <div id="map_canvas">
                <ui-gmap-google-map center="map.center" zoom="map.zoom" draggable="true" options="map.options" control="map.control" events="map.events">
                    <%--<ui-gmap-marker ng-repeat="marker in map.orderMarkers" idKey="marker.id" click="getProjectPage(marker.id)" coords="marker.from" options="marker">
                    </ui-gmap-marker>--%>
                        <ui-gmap-markers models="map.orderMarkers" coords="'from'" icon="'icon'" idkey="'id'" doCluster="true" type="cluster" typeOptions="map.options.cluster" options="'options'" click="getPage(type, id)">
                        </ui-gmap-markers>
                </ui-gmap-google-map>
            </div>
            <div data-ng-show="lookForProjects || lookForUsers" id="legend">
                <ul>
                    <li data-ng-show="lookForProjects" style="color: #0b62a4">New</li>
                    <li data-ng-show="lookForProjects" style="color: #edc240">Pending</li>
                    <li data-ng-show="lookForProjects" style="color: #7A92A3">In progress</li>
                    <li data-ng-show="lookForProjects" style="color: #4da74d">Completed</li>
                    <li data-ng-show="lookForProjects" style="color: #afd8f8">Cancelled</li>
                    <li data-ng-show="lookForProjects" style="color: #cb4b4b">Expired</li>
                    <li data-ng-show="lookForProjects" style="color: #9440ed">Blocked</li>
                    <li data-ng-show="lookForUsers" style="color: #3ebcc7">Pilots</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <input type="button" data-ng-disabled="!lookForProjects" class="btn btn-default btn-primary" data-ng-click="openSearchCriteriaModal(sc)" value="Search"/>
            <input data-ng-show="!sc.isEmpty" type="button" class="btn btn-default btn-danger" data-ng-click="clear()" value="Clear"/>
            <div class="options-block">
                <div>
                    <i data-ng-click="lookForProjects = !lookForProjects;switchSearch()" data-ng-class="{'fa fa-square-o': !lookForProjects, 'fa fa-check-square-o drones-turquoise': lookForProjects}" aria-hidden="true"></i>
                    Projects
                </div>
                <div>
                    <i data-ng-click="lookForUsers = !lookForUsers;switchSearch()" data-ng-class="{'fa fa-square-o': !lookForUsers, 'fa fa-check-square-o drones-turquoise': lookForUsers}" aria-hidden="true"></i>
                    Pilots
                </div>
            </div>
        </div>
    </div>
</div>