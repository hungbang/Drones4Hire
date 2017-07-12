'use strict';

DronesAdmin.controller('MapsPageController', [ '$scope', '$http', '$window', '$modal', '$filter', function($scope, $http, $window, $modal, $filter) {

    var START_LATITUDE = 42.8;
    var START_LONGITUDE = -119.2;
    var ZOOM = 4;
    var MIN_ZOOM = 2;

    var PROJECT_ABBR = 'P';
    var CLIENT_ABBR = 'C';
    var PILOT_ABBR = 'DP';
    var ITEM = PROJECT_ABBR;

    var COLORS = {
        NEW: '0b62a4',
        PENDING: 'edc240',
        IN_PROGRESS: '7A92A3',
        COMPLETED: '4da74d',
        CANCELLED: 'afd8f8',
        BLOCKED: 'cb4b4b'
    };

    var initSearchCriteria = function () {
        $scope.sc = {};
        $scope.sc.topLeftCoordinates = {};
        $scope.sc.bottomRightCoordinates = {};
        $scope.sc.topLeftCoordinates.latitude = 90;
        $scope.sc.topLeftCoordinates.longitude = -180;
        $scope.sc.bottomRightCoordinates.latitude = -90;
        $scope.sc.bottomRightCoordinates.longitude = 180;

        $scope.sc.isEmpty = true;
    };

    var newMarker = function (r) {
        return {
            id: r.id,
            from: {
                latitude: r.coordinates.latitude, longitude: r.coordinates.longitude
            },
            options: {
                title: r.status + '\n' + 'ID: ' + r.id + '\n' + 'TITLE: ' + r.title
            },
            icon: getImage(r.status)
        }
    };

    var getImage = function (status) {
        return new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=" + ITEM + "|" + COLORS[status],
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

    $scope.openSearchCriteriaModal = function (sc) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/project-search-criteria.html',
            scope: $scope,
            resolve: {
                'sc': function () {
                    return sc;
                }
            },
            controller: function ($scope, $modalInstance) {

                var initPickers = function () {
                    angular.element('#createdAtAfter').datetimepicker({
                        locale: 'ru',
                        format: 'YYYY-MM-DD HH:mm',
                        icons: {
                            time: "fa fa-clock-o",
                            calendar: "fa fa-clock-o",
                            date: "fa fa-calendar",
                            up: "fa fa-arrow-up",
                            down: "fa fa-arrow-down",
                            previous: "fa fa-arrow-left",
                            next: "fa fa-arrow-right"
                        }
                    });
                    angular.element('#createdAtBefore').datetimepicker({
                        useCurrent: false,
                        locale: 'ru',
                        format: 'YYYY-MM-DD HH:mm',
                        icons: {
                            time: "fa fa-clock-o",
                            calendar: "fa fa-clock-o",
                            date: "fa fa-calendar",
                            previous: "fa fa-arrow-left",
                            next: "fa fa-arrow-right",
                            up: "fa fa-arrow-up",
                            down: "fa fa-arrow-down"
                        }
                    });

                    angular.element('#createdAtAfter').on("dp.change", function (e) {
                        angular.element('#createdAtBefore').data("DateTimePicker").minDate(e.date);
                    });
                    angular.element('#createdAtBefore').on("dp.change", function (e) {
                        angular.element('#createdAtAfter').data("DateTimePicker").maxDate(e.date);
                    });
                };

                $scope.search = function(){

                    var OFFSET = new Date().getTimezoneOffset()*60*1000;

                    if(angular.element('#createdAtAfter')[0].value){
                        $scope.sc.createdAtAfter = new Date(Date.parse(angular.element('#createdAtAfter')[0].value) + OFFSET);
                    }

                    if(angular.element('#createdAtBefore')[0].value){
                        $scope.sc.createdAtBefore = new Date(Date.parse(angular.element('#createdAtBefore')[0].value) + OFFSET);
                    }
                    $http.post('projects/search/map', $scope.sc).success(function(data) {
                        $scope.sr = data;
                        $scope.cleanMap();
                        $scope.sc.isEmpty = false;
                        $scope.cancel();
                        data.results.forEach(function (project, index, projects) {
                            if(project.coordinates) {
                                $scope.map.orderMarkers.push(newMarker(project));
                            }
                        });
                    }).error(function() {
                        alertify.error('Failed to search projects');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                (function init() {
                    $scope.createdAtAfter = $filter('date')($scope.sc.createdAtAfter, "yyyy-MM-dd HH:mm");
                    $scope.createdAtBefore =  $filter('date')($scope.sc.createdAtBefore, "yyyy-MM-dd HH:mm");

                    setTimeout(initPickers, 400);
                })();
            }
        }).result.then(function () {
        }, function () {
        });
    };

    (function init() {
        $scope.map = {
            center: {
                latitude: START_LATITUDE,
                longitude: START_LONGITUDE
            },
            zoom: ZOOM,
            options: {
                minZoom: MIN_ZOOM,
                cluster: {
                    minimumClusterSize : 2,
                    zoomOnClick: true,
                    maxZoom:10,
                    averageCenter: true,
                    clusterClass: 'cluster-icon',
                    styles: [{
                        textColor: 'white',
                        url: 'http://s15.postimg.org/fuaqrrot3/rsz_cluster_resize.png',
                        height: 40,
                        width: 40,
                        textSize: 11,
                        fontWeight: 'normal'
                    }]
                }
            },
            events: {
                bounds_changed: function (map) {
                    /*var bounds = map.getBounds();
                    $scope.sc.topLeftLatitude = (bounds.f.f);
                    $scope.sc.topLeftLongitude = (bounds.b.b);
                    $scope.sc.bottomRightLatitude = (bounds.f.b);
                    $scope.sc.bottomRightLongitude = (bounds.b.f);
                    $scope.search();*/
                }
            },
            control: {}
        };

        $scope.map.orderMarkers = [];

        initSearchCriteria();

        $scope.search();
    })();

}]);