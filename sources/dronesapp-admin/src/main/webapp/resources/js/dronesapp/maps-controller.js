'use strict';

DronesAdmin.controller('MapsPageController', [ '$scope', '$http', '$window', function($scope, $http, $window) {

    var START_LATITUDE = 39.5;
    var START_LONGITUDE = -98.35;
    var ZOOM = 4;
    var MIN_ZOOM = 2;

    var COLORS = {
        NEW: '69FE6B',
        IN_PROGRESS: '4169e1',
        COMPLETED: 'ff00ff',
        CANCELLED: 'FE7569',
        PENDING: 'ffff00',
        BLOCKED: 'f7f7f7'
    };

    var initSearchCriteria = function () {
        $scope.sc = {};
        $scope.sc.topLeftCoordinates = {};
        $scope.sc.bottomRightCoordinates = {};
        $scope.sc.topLeftCoordinates.latitude = 90;
        $scope.sc.topLeftCoordinates.longitude = -180;
        $scope.sc.bottomRightCoordinates.latitude = -90;
        $scope.sc.bottomRightCoordinates.longitude = 180;
    };

    var newMarker = function (r) {
        return {
            id: r.id,
            from: {
                latitude: r.coordinates.latitude, longitude: r.coordinates.longitude
            },
            title: r.status + '\n' + 'ID: ' + r.id + '\n' + 'TITLE: ' + r.title,
            icon: getImage(r.status)
        }
    };

    var getImage = function (status) {
        return new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + COLORS[status],
            new google.maps.Size(21, 34),
            new google.maps.Point(0,0),
            new google.maps.Point(10, 34));
    };

    $scope.search = function(){
        $http.post('projects/search/map', $scope.sc).success(function(data) {
            $scope.sr = data;
            $scope.cleanMap();
            data.results.forEach(function (project, index, projects) {
                if(project.coordinates) {
                    $scope.map.orderMarkers.push(newMarker(project));
                }
            });
        }).error(function() {
            alertify.error('Failed to search projects');
        });
    };

    $scope.clear = function () {
        initSearchCriteria();
        $scope.search();
    };

    $scope.cleanMap = function () {
        $scope.map.orderMarkers = [];
    };

    $scope.getProjectPage = function (id) {
        $window.open('/admin/#/projects/' + id + '/view', '_blank');
    };

    (function init() {
        $scope.map = {
            center: {
                latitude: START_LATITUDE,
                longitude: START_LONGITUDE
            },
            zoom: ZOOM,
            options: {
                minZoom: MIN_ZOOM
            },
            showOverlay: true,
            control: {}
        };

        $scope.map.orderMarkers = [];

        initSearchCriteria();
        $scope.search();
    })();

}]);