'use strict';

DronesAdmin.controller('ProjectsPageController', [ '$scope', '$http', '$location', '$modal', '$upload', '$route', '$interval', '$filter', 'PAGE_SIZES', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $modal, $upload, $route, $interval, $filter, PAGE_SIZES, $cookieStore, LocaleProvider) {

	var DEFAULT_PROJECTS_SEARCH_CRITERIA = {
			'rating' : 5,
			'pageSize' : PAGE_SIZES[0],
			'statuses' : ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED', 'EXPIRED', 'BLOCKED']
	};
	$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
	$scope.projectListPageSizes = PAGE_SIZES;

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
	
	$scope.loadStatuses = function(){
		$scope.roles = ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED', 'EXPIRED', 'BLOCKED'];
	};

	$scope.searchProjects = function(page, pageSize){

        var OFFSET = new Date().getTimezoneOffset()*60*1000;
        $scope.projectSearchCriteria.page = page;
        if(pageSize){
            $scope.projectSearchCriteria.pageSize = pageSize;
        }

        if(angular.element('#createdAtAfter')[0].value){
            $scope.projectSearchCriteria.createdAtAfter = new Date(Date.parse(angular.element('#createdAtAfter')[0].value) + OFFSET);
        }

        if(angular.element('#createdAtBefore')[0].value){
            $scope.projectSearchCriteria.createdAtBefore = new Date(Date.parse(angular.element('#createdAtBefore')[0].value) + OFFSET);
        }

        if(! Array.isArray($scope.projectSearchCriteria.statuses)) {
            $scope.projectSearchCriteria.statuses = [$scope.projectSearchCriteria.statuses];
        }

        $http.post('projects/search', $scope.projectSearchCriteria).success(function(data) {
            $scope.projectSearchResult = data;
            $scope.projectSearchCriteria.page = data.page;
            $scope.projectSearchCriteria.pageSize = data.pageSize;
        }).error(function() {
            alertify.error('Failed to search projects');
        });
    };

    $scope.downloadCSV = function(callback){
        var OFFSET = new Date().getTimezoneOffset()*60*1000;

        if(! Array.isArray($scope.projectSearchCriteria.statuses)) {
            $scope.projectSearchCriteria.statuses = [$scope.projectSearchCriteria.statuses];
        }
        var result;
        $http.post('projects/csv', $scope.projectSearchCriteria).success(function(csv) {
            var blob = new Blob([csv], { type: 'text/csv' });
            var csvUrl = URL.createObjectURL(blob);
            angular.element("<a/>").attr({
                'download': 'projects.csv',
                'href': csvUrl
            })[0].click();
            result = true;
        }).error(function() {
            console.error('Failed to download CSV');
            result = false;
        }).finally(function () {
            if (callback) {
                callback(result);
            }
        });
    };

    $scope.loadBudgets = function(){
        $http.get('common/budgets').success(function(data) {
            $scope.budgets = data;
        }).error(function() {
            alertify.error('Failed to get budgets');
        });
    };

    $scope.loadServiceCategories = function(){
        $http.get('common/categories').success(function(data) {
            $scope.categories = data;
        }).error(function() {
            alertify.error('Failed to get categories');
        });
    };

	var it = $scope;
	
	$scope.resetSearchCriteria = function(){
		$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
        angular.element('#createdAtAfter')[0].value = null;
        angular.element('#createdAtBefore')[0].value = null;
		$scope.searchProjects(1);
	};
		
	$scope.openViewPage = function(id){
		$location.path('/projects/' + id + '/view');
	};

    $scope.openProjectModal = function () {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/project.html',
            scope: $scope,
            controller: function ($scope, $modalInstance) {

                var OFFSET = new Date().getTimezoneOffset() * 60 * 1000;

                $scope.project = {};
                $scope.project.status = 'NEW';
                $scope.project.postProductionRequired = false;
                $scope.project.bidAmount = 0.0;
                $scope.project.location = {};
                $scope.project.location.coordinates = {};

                var initPickers = function () {
                    angular.element('#startDate').datetimepicker({
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
                    angular.element('#finishDate').datetimepicker({
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

                    angular.element('#startDate').on("dp.change", function (e) {
                        angular.element('#finishDate').data("DateTimePicker").minDate(e.date);
                    });
                    angular.element('#finishDate').on("dp.change", function (e) {
                        angular.element('#startDate').data("DateTimePicker").maxDate(e.date);
                    });
                };

                $scope.loadBudgets = function(){
                    $http.get('common/budgets').success(function(data) {
                        $scope.budgets = data;
                    }).error(function() {
                        alertify.error('Failed to get budgets');
                    });
                };

                $scope.loadServiceCategories = function(){
                    $http.get('common/categories').success(function(data) {
                        $scope.categories = data;
                    }).error(function() {
                        alertify.error('Failed to get categories');
                    });
                };

                $scope.getClientById = function () {
                    delete $scope.client;
                    if($scope.project.clientId) {
                        $http.get('users/' + $scope.project.clientId).success(function (data) {
                            $scope.client = data;
                        }).error(function () {
                            alertify.error('Failed to get client by id ' + id);
                        });
                    }
                };

                $scope.getPilotById = function () {
                    delete $scope.pilot;
                    if($scope.project.pilotId) {
                        $http.get('users/' + $scope.project.pilotId).success(function (data) {
                            $scope.pilot = data;
                        }).error(function () {
                            alertify.error('Failed to get pilot by id ' + id);
                        });
                    }
                };

                $scope.loadServices = function(){
                    $http.get('common/services').success(function(data) {
                        $scope.services = data.filter(function (service) {
                            if(service.category.id == $scope.project.categoryId)
                            {
                                return true;
                            }
                            return false;
                        });
                    }).error(function() {
                        alertify.error('Failed to get services');
                    });
                };

                $scope.loadCountries = function(){
                    $http.get('locations/countries/list').success(function(data) {
                        $scope.countries = data;
                    }).error(function() {
                        alertify.error('Failed to get countries');
                    });
                };

                $scope.loadStates = function(){
                    var isUSA = false;
                    for(var i = 0; i < $scope.countries.length; i++) {
                        if($scope.project.location.country.id == $scope.countries[i].id && $scope.countries[i].name.includes('United States')) {
                            isUSA = true;
                        }
                    }
                    if(isUSA) {
                        $http.get('locations/states/list').success(function (data) {
                            $scope.states = data;
                        }).error(function () {
                            alertify.error('Failed to get states');
                        });
                    } else {
                        delete $scope.states;
                    }
                };

                $scope.loadDurations = function(){
                    $http.get('common/durations').success(function(data) {
                        $scope.durations = data;
                    }).error(function() {
                        alertify.error('Failed to get durations');
                    });
                };

                $scope.loadPaidOptions = function(){
                    $http.get('common/paidOptions').success(function(data) {
                        $scope.paidOptions = data;
                    }).error(function() {
                        alertify.error('Failed to get paid options');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                var initPicker = $interval(function () {
                    if(angular.element('#startDate')[0].value){
                        var startDate = new Date(Date.parse(angular.element('#startDate')[0].value));
                        $scope.project.startDate = $filter('date')(startDate, "yyyy-MM-dd HH:mm")
                    }
                }, 100);

                $scope.createProject = function () {
                    if(angular.element('#startDate')[0].value){
                        var startDate= new Date(Date.parse(angular.element('#startDate')[0].value) + OFFSET);
                        $scope.project.startDate = new Date(Date.parse(startDate) + OFFSET);
                    }

                    if(angular.element('#finishDate')[0].value){
                        var finishDate = new Date(Date.parse(angular.element('#finishDate')[0].value) + OFFSET);
                        $scope.project.finishDate = new Date(Date.parse(finishDate) + OFFSET);
                    }
                    $scope.project.paidOptions = $scope.paidOptions.filter(function (option) {
                        return option.isChecked;
                    });

                    $scope.project.attachments = $scope.attachs;

                    $http.post('projects', $scope.project, {headers: {'BidAmount': $scope.project.bidAmount}}).success(function(data) {
                        alertify.success('Was created');
                        $scope.cancel();
                        $scope.searchProjects(1);
                    }).error(function() {
                        alertify.error('Failed to create');
                    });
                };

                $scope.$on('place_changed', function (e, place) {
                    $scope.project.location.coordinates.latitude = place.geometry.location.lat();
                    $scope.project.location.coordinates.longitude = place.geometry.location.lng();
                    $scope.project.location.address = place.formatted_address;
                });

                $scope.$on("$destroy",function(){
                    if (angular.isDefined(initPicker)) {
                        $interval.cancel(initPicker);
                    }
                });

                $scope.attachs = [];

                $scope.addAttachment = function (file) {
                    $scope.attachs.push(file);
                };

                $scope.removeAttachment = function (index) {
                    $scope.attachs.splice(index, 1);
                };

                $scope.uploadAttachment = function (fileToUpload) {
                    var attach = initProjectAttach();
                    attach.title = fileToUpload.name.split('.')[0];
                    var fd = new FormData();
                    fd.append('file', fileToUpload);
                    $http.post('upload?file=', fd, {
                        headers: {
                            'Content-Type' : undefined,
                            'FileType': attach.type
                        },
                        transformRequest : angular.identity
                    }).success(function(data) {
                        attach.attachmentURL = data.url;
                        $scope.addAttachment(attach);
                    }).error(function() {
                        alertify.error('Failed to upload file');
                    });
                };

                function initProjectAttach() {
                    var attach = {};
                    attach.type = 'PROJECT_ATTACHMENT';
                    attach.title = null;
                    attach.attachmentURL = null;
                    return attach;
                }

                (function init() {
                    setTimeout(initPickers, 800);
                	$scope.loadBudgets();
                	$scope.loadServiceCategories();
                	$scope.loadCountries();
                	$scope.loadDurations();
                	$scope.loadPaidOptions();
                })();
            }
        }).result.then(function () {
        }, function () {
        });
    };
	
	(function init(){
		$scope.loadStatuses();
		$scope.loadBudgets();
		$scope.loadServiceCategories();
		$scope.searchProjects(1);
	})();
	
} ]);

DronesAdmin.controller('ProjectDetailsController', [ '$scope', '$http', '$location', '$routeParams', '$modal', '$route', '$upload', function($scope, $http, $location, $routeParams, $modal, $route, $upload) {

    $scope.tabs = [{ active: true }, { active: false }, { active: false }, { active: false }, { active: false }];

    $scope.attach = {};

    var STATUSES_FOR_BID_ACTIONS = [
        'NEW',
        'PENDING',
        'IN_PROGRESS'
    ];

    $scope.bidPreparedToDelete = false;

	$scope.loadProject = function(){
		$http.get('projects/' + $routeParams.id).success(function(data) {
			$scope.project = data;
			$scope.createdAt = new Date($scope.project.createdAt);
			$scope.startDate = new Date($scope.project.startDate);
			$scope.finishDate = new Date($scope.project.finishDate);

            $scope.loadFeedbacks();
			
		}).error(function(data, status) {
			alertify.error('Failed to load project');
		});
	};

    $scope.loadComments = function(){
        $http.get('projects/' + $routeParams.id + '/comments').success(function(data) {
            $scope.comments = data;
        }).error(function(data, status) {
            alertify.error('Failed to load comments');
        });
    };

    $scope.loadBids = function(){
        $http.get('projects/' + $routeParams.id + '/bids').success(function(data) {
            $scope.bids = data;
        }).error(function(data, status) {
            alertify.error('Failed to load bids');
        });
    };

	$scope.editProject = function(){
        $scope.project.createdAt = new Date($scope.createdAt).getTime();
        $scope.project.startDate = new Date($scope.startDate).getTime();
        $scope.project.finishDate = new Date($scope.finishDate).getTime();
		$http.put('projects', $scope.project).success(function(data) {
			alertify.success('Success, changes were saved.');
		}).error(function(data, status) {
			alertify.error('Failed to save changes!');
		});
	};

    $scope.$on('place_changed', function (e, place) {
        $scope.project.location.coordinates.latitude = place.geometry.location.lat();
        $scope.project.location.coordinates.longitude = place.geometry.location.lng();
        $scope.project.location.address = place.formatted_address;
    });

	$scope.blockProject = function () {
	    $scope.project.status = 'BLOCKED';
        $http.put('projects', $scope.project).success(function(data) {
            alertify.success('Success, project was blocked.');
        }).error(function(data, status) {
            alertify.error('Failed to block project!');
        });
    };

    $scope.unblockProject = function () {
        $scope.project.status = 'NEW';
        $http.put('projects', $scope.project).success(function(data) {
            alertify.success('Success, project was unblocked.');
        }).error(function(data, status) {
            alertify.error('Failed to unblock project!');
        });
    };

    $scope.deleteProject = function (id) {
        $http.delete('projects/' + id).success(function(data) {
            $location.url('/projects');
            alertify.success('Success, project was deleted.');
        }).error(function(data, status) {
            alertify.error('Failed to delete project!');
        });
    };

    $scope.deleteComment = function (id) {
        $http.delete('comments/' + id).success(function(data) {
            $scope.loadComments();
            alertify.success('Success, comment was deleted.');
        }).error(function(data, status) {
            alertify.error('Failed to delete comment!');
        });
    };

    $scope.deleteAttachment = function (id) {
        $http.delete('projects/attachments/' + id).success(function(data) {
            $scope.loadProject();
            alertify.success('Success, attachment was deleted.');
        }).error(function(data, status) {
            alertify.error('Failed to delete attachment!');
        });
    };

    $scope.deleteBid = function (id) {
        $http.delete('bids/' + id + '/retract').success(function(data) {
            $scope.loadProject();
            $scope.loadBids();
            alertify.success('Success, bid was deleted.');
        }).error(function(data, status) {
            alertify.error('Failed to delete bid!');
        });
    };

    $scope.loadBudgets = function(){
        $http.get('common/budgets').success(function(data) {
            $scope.budgets = data;
        }).error(function() {
            alertify.error('Failed to get budgets');
        });
    };

    $scope.loadServiceCategories = function(){
        $http.get('common/categories').success(function(data) {
            $scope.categories = data;
        }).error(function() {
            alertify.error('Failed to get categories');
        });
    };

    $scope.loadServices = function(){
        $http.get('common/services').success(function(data) {
            $scope.services = data.filter(function (service) {
                if(service.category.id == $scope.project.service.category.id)
                {
                    return true;
                }
                return false;
            });
        }).error(function() {
            alertify.error('Failed to get services');
        });
    };

    $scope.loadDurations = function(){
        $http.get('common/durations').success(function(data) {
            $scope.durations = data;
        }).error(function() {
            alertify.error('Failed to get durations');
        });
    };

    $scope.uploadAttachment = function (fileToUpload) {
        initProjectAttach();
        $scope.attach.title = fileToUpload.name.split('.')[0];
        var fd = new FormData();
        fd.append('file', fileToUpload);
        $http.post('upload?file=', fd, {
            headers: {
                'Content-Type' : undefined,
                'FileType': $scope.attach.type
            },
            transformRequest : angular.identity
        }).success(function(data) {
            $scope.attach.attachmentURL = data.url;
            $http.post('projects/attachments', $scope.attach).success(function(data) {
                $scope.loadProject();
            }).error(function() {
            });
        }).error(function() {
            alertify.error('Failed to upload file');
        });
    };


    $scope.loadFeedbacks = function() {
        $http.get('projects/' + $scope.project.id + '/feedbacks').success(function(data) {
            $scope.feedbacks = data;
            $scope.feedbacks.filter(function (feedback) {
                var intNum = parseInt(feedback.mark);
                if(intNum != feedback.mark)
                {
                    feedback.halfStar = true;
                }
                feedback.stars = [];
                for(var i = 0; i < intNum; i++)
                {
                    feedback.stars.push(i);
                }
            })
        }).error(function(data, status) {
            alertify.error('Failed to load feedbacks');
        });
    };

    $scope.deleteFeedback = function(id) {
        $http.delete('users/feedbacks/' + id).success(function(data) {
            $route.reload();
        }).error(function(data, status) {
            alertify.error('Failed to delete feedback');
        });
    };

    $scope.openFeedbackModal = function (feedback) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/feedback.html',
            scope: $scope,
            resolve: {
                'feedback': function () {
                    return feedback;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.feedback = feedback;

                $scope.updateFeedback = function(feedback){
                    $http.put('users/feedbacks', feedback).success(function(data) {
                        alertify.success('Feedback successfully updated');
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update feedback');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                (function init() {
                })();
            }
        }).result.then(function () {
        }, function () {
        });
    };

    function initProjectAttach() {
        $scope.attach.type = 'PROJECT_ATTACHMENT';
        $scope.attach.title = null;
        $scope.attach.projectId = $scope.project.id;
        $scope.attach.attachmentURL = null;
    }

    (function init(){
        $scope.loadComments();
        $scope.loadBids();
        $scope.loadProject();
        $scope.loadBudgets();
        $scope.loadServiceCategories();
        $scope.loadDurations();
    })();
} ]);
