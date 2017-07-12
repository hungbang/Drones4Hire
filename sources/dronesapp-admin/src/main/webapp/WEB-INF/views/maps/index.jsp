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
                    <ui-gmap-marker ng-repeat="marker in map.orderMarkers" idKey="marker.id" click="getProjectPage(marker.id)" coords="marker.from" options="marker">
                    </ui-gmap-marker>
                        <%--<ui-gmap-markers models="map.orderMarkers" coords="'from'" idkey="'id'" doCluster="true" type="cluster" typeOptions="map.clusterOptions" click="getProjectPage(id)">
                        </ui-gmap-markers>--%>
                </ui-gmap-google-map>
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