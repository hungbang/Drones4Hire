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
                        <ui-gmap-markers models="map.orderMarkers" coords="'from'" icon="'icon'" idkey="'id'" doCluster="true" type="cluster" typeOptions="map.options.cluster" options="'options'" click="getProjectPage(id)">
                        </ui-gmap-markers>
                </ui-gmap-google-map>
            </div>
            <div id="legend">
                <ul>
                    <li style="color: #0b62a4">New</li>
                    <li style="color: #edc240">Pending</li>
                    <li style="color: #7A92A3">In progress</li>
                    <li style="color: #4da74d">Completed</li>
                    <li style="color: #afd8f8">Cancelled</li>
                    <li style="color: #cb4b4b">Blocked</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <input type="button" class="btn btn-default btn-primary" data-ng-click="openSearchCriteriaModal(sc)" value="Search"/>
            <input data-ng-show="!sc.isEmpty" type="button" class="btn btn-default btn-danger" data-ng-click="clear()" value="Clear"/>
        </div>
    </div>
</div>