'use strict';

DronesAdmin.controller('MapsPageController', [ '$scope', '$http', '$window', '$modal', '$filter', function($scope, $http, $window, $modal, $filter) {

    var START_LATITUDE = 42.8;
    var START_LONGITUDE = -119.2;
    var ZOOM = 4;
    var MIN_ZOOM = 2;

    // One character !
    var PROJECT_ABBR = 'P';
    var USER_ABBR = 'U';

    var ITEM;

    var PROJECT_MARKER_TYPE = 'PROJECT_MARKER';
    var USER_MARKER_TYPE = 'USER_MARKER';

    var COLORS = {
        NEW: '0b62a4',
        PENDING: 'edc240',
        IN_PROGRESS: '7A92A3',
        COMPLETED: '4da74d',
        CANCELLED: 'afd8f8',
        EXPIRED: 'cb4b4b',
        BLOCKED: '9440ed'
    };

    $scope.lookForProjects = true;
    $scope.lookForUsers = false;

    $scope.projects = {};
    $scope.users = {};

    var initSearchCriteria = function () {
        $scope.projectSc = {};
        $scope.userSc = {};
        $scope.userSc.role = 'ROLE_PILOT';
        $scope.projectSc.topLeftCoordinates = {};
        $scope.projectSc.bottomRightCoordinates = {};
        $scope.projectSc.topLeftCoordinates.latitude = 90;
        $scope.projectSc.topLeftCoordinates.longitude = -180;
        $scope.projectSc.bottomRightCoordinates.latitude = -90;
        $scope.projectSc.bottomRightCoordinates.longitude = 180;

        $scope.projectSc.isEmpty = true;
    };

    var newProjectMarker = function (r) {
        return {
            id: r.id + PROJECT_ABBR,
            from: {
                latitude: r.coordinates.latitude, longitude: r.coordinates.longitude
            },
            options: {
                title: r.status + '\n' + 'ID: ' + r.id + '\n' + 'TITLE: ' + r.title
            },
            icon: getImage(r.status),
            type: PROJECT_MARKER_TYPE
        }
    };

    var newUserMarker = function (r) {
        return {
            id: r.id + USER_ABBR,
            from: {
                latitude: r.location.coordinates.latitude, longitude: r.location.coordinates.longitude
            },
            options: {
                title: r.username + '\n' + 'ID: ' + r.id
            },
            icon: 'https://maps.google.com/mapfiles/kml/shapes/library_maps.png',
            type: USER_MARKER_TYPE
        }
    };

    var getImage = function (status) {
        return new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=" + ITEM + "|" + COLORS[status],
            new google.maps.Size(21, 34),
            new google.maps.Point(0,0),
            new google.maps.Point(10, 34));
    };

    $scope.searchProjects = function(){
        $http.post('projects/search/map', $scope.projectSc).success(function(data) {
            $scope.cleanMap();
            ITEM = PROJECT_ABBR;
            $scope.projects = data;
            data.results.forEach(function (project, index, projects) {
                if(project.coordinates) {
                    $scope.projectMarkers.push(newProjectMarker(project));
                }
            });
            pushMarkers($scope.projectMarkers);
        }).error(function() {
            alertify.error('Failed to search projects');
        });
    };

    $scope.searchUsers = function(){
        $http.post('users/search', $scope.userSc).success(function(data) {
            $scope.cleanMap();
            ITEM = USER_ABBR;
            $scope.users = data;
            $scope.users.results.forEach(function (user, index, projects) {
                if(user.location && user.location.coordinates) {
                    $scope.userMarkers.push(newUserMarker(user));
                }
            });
            pushMarkers($scope.userMarkers);
        }).error(function() {
            alertify.error('Failed to search users');
        });
    };

    $scope.switchSearch = function () {
        $scope.projects = {};
        $scope.users = {};
        if($scope.lookForProjects)
            $scope.searchProjects();
        if($scope.lookForUsers)
            $scope.searchUsers();
        if(!$scope.lookForProjects && !$scope.lookForUsers)
            $scope.cleanMap();
    };

    $scope.clear = function () {
        initSearchCriteria();
        $scope.searchProjects();
    };

    $scope.cleanMap = function () {
        if(!$scope.projects.results && !$scope.users.results) {
            $scope.map.orderMarkers = [];
        }
        $scope.projectMarkers = [];
        $scope.userMarkers = [];
    };

    var pushMarkers = function (markers) {
        $scope.map.orderMarkers.push.apply($scope.map.orderMarkers, markers);
    };

    $scope.getPage = function (type, idMarker) {
        var id = idMarker.substring(0, idMarker.length - 1);
        if(type == PROJECT_MARKER_TYPE)
            $window.open('/admin/#/projects/' + id + '/view', '_blank');
        else
            $window.open('/admin/#/users/' + id + '/view', '_blank');
    };

    $scope.openSearchCriteriaModal = function (sc, lookForUsers) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/project-search-criteria.html',
            scope: $scope,
            resolve: {
                'sc': function () {
                    return sc;
                },
                'lookForUsers': function () {
                    return lookForUsers;
                }
            },
            controller: function ($scope, $modalInstance) {

                var initPickers = function () {
                    angular.element('#createdAtAfter').datetimepicker({
                        locale: 'en',
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
                        locale: 'en',
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

                $scope.cleanMap = function () {
                    if(!$scope.projects.results && !$scope.users.results) {
                        $scope.map.orderMarkers = [];
                    }
                    $scope.projectMarkers = [];
                    $scope.userMarkers = [];
                };

                var pushMarkers = function (markers) {
                    $scope.map.orderMarkers.push.apply($scope.map.orderMarkers, markers);
                };

                $scope.search = function(){

                    $scope.projects = {};
                    $scope.users = {};
                    if($scope.lookForUsers) {
                        $scope.searchUsers();
                    }

                    $scope.projects = {};
                    var OFFSET = new Date().getTimezoneOffset()*60*1000;

                    if(angular.element('#createdAtAfter')[0].value){
                        $scope.projectSc.createdAtAfter = new Date(Date.parse(angular.element('#createdAtAfter')[0].value) + OFFSET);
                    }

                    if(angular.element('#createdAtBefore')[0].value){
                        $scope.projectSc.createdAtBefore = new Date(Date.parse(angular.element('#createdAtBefore')[0].value) + OFFSET);
                    }
                    $http.post('projects/search/map', $scope.projectSc).success(function(data) {
                        $scope.cleanMap();
                        ITEM = PROJECT_ABBR;
                        $scope.projects = data;
                        $scope.projectSc.isEmpty = false;
                        $scope.cancel();
                        data.results.forEach(function (project, index, projects) {
                            if(project.coordinates) {
                                $scope.projectMarkers.push(newProjectMarker(project));
                            }
                        });
                        pushMarkers($scope.projectMarkers);
                    }).error(function() {
                        alertify.error('Failed to search projects');
                    });
                };

                $scope.searchUsers = function(){
                    $http.post('users/search', $scope.userSc).success(function(data) {
                        $scope.cleanMap();
                        ITEM = USER_ABBR;
                        $scope.users = data;
                        $scope.users.results.forEach(function (user, index, projects) {
                            if(user.location && user.location.coordinates) {
                                $scope.userMarkers.push(newUserMarker(user));
                            }
                        });
                        pushMarkers($scope.userMarkers);
                    }).error(function() {
                        alertify.error('Failed to search users');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                (function init() {
                    $scope.createdAtAfter = $filter('date')($scope.projectSc.createdAtAfter, "yyyy-MM-dd HH:mm");
                    $scope.createdAtBefore =  $filter('date')($scope.projectSc.createdAtBefore, "yyyy-MM-dd HH:mm");

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
                    $scope.projectSc.topLeftLatitude = (bounds.f.f);
                    $scope.projectSc.topLeftLongitude = (bounds.b.b);
                    $scope.projectSc.bottomRightLatitude = (bounds.f.b);
                    $scope.projectSc.bottomRightLongitude = (bounds.b.f);
                    $scope.search();*/
                }
            },
            control: {}
        };

        $scope.map.orderMarkers = [];

        initSearchCriteria();

        if($scope.lookForProjects)
            $scope.searchProjects();
        if($scope.lookForUsers)
            $scope.searchUsers();
    })();

}]);