<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        trimDirectiveWhitespaces="true"
        pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/taglibs.jsp" %>

<div data-ng-controller="MapsPageController" class="container-fluid">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Map</h1>
        </div>
    </div>
    <div class="row" style="display: block;">
        <div class="col-lg-12">
            <div id="map_canvas">
                <ui-gmap-google-map center="map.center" zoom="map.zoom" draggable="true" options="map.options" control="map.control">
                    <ui-gmap-marker ng-repeat="marker in map.orderMarkers" idKey="marker.id" click="getProjectPage(marker.id)" coords="marker.from" options="marker">
                    </ui-gmap-marker>
                </ui-gmap-google-map>
            </div>
        </div>
    </div>
</div>