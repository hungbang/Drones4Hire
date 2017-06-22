'use strict';

DronesAdmin.controller('ProjectsPageController', [ '$scope', '$http', '$location', '$modal', '$upload', '$route', 'PAGE_SIZES', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $modal, $upload, $route, PAGE_SIZES, $cookieStore, LocaleProvider) {
	var DEFAULT_PROJECTS_SEARCH_CRITERIA = {
			'rating' : 5,
			'pageSize' : PAGE_SIZES[0],
			'statuses' : ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED', 'BLOCKED']
	};
	$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
	$scope.projectListPageSizes = PAGE_SIZES;
	
	$scope.loadStatuses = function(){
		$scope.roles = ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED', 'BLOCKED'];
	};

	$scope.searchProjects = function(page, pageSize){

		var OFFSET = new Date().getTimezoneOffset()*60*1000;
		$scope.projectSearchCriteria.page = page;
		if(pageSize){
			$scope.projectSearchCriteria.pageSize = pageSize;
		}

		if(! Array.isArray($scope.projectSearchCriteria.statuses)) {
            $scope.projectSearchCriteria.statuses = [$scope.projectSearchCriteria.statuses];
		}
		
		$http.post('projects/searchProjects', $scope.projectSearchCriteria).success(function(data) {
			$scope.projectSearchResult = data;
			$scope.projectSearchCriteria.page = data.page;
			$scope.projectSearchCriteria.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search projects');
		});
	};

    $scope.loadBudgets = function(){
        $http.get('common/budgets').success(function(data) {
            $scope.budgets = data;
        }).error(function() {
            console.error('Failed to get budgets');
        });
    };

    $scope.loadServiceCategories = function(){
        $http.get('common/categories').success(function(data) {
            $scope.categories = data;
        }).error(function() {
            console.error('Failed to get categories');
        });
    };

	var it = $scope;
	
	$scope.resetSearchCriteria = function(){
		$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
		$scope.searchProjects(1);
	};
		
	$scope.openViewPage = function(id){
		$location.path('/projects/' + id + '/view');
	};

    $scope.openProjectModal = function () {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/project.html',
            controller: function ($scope, $modalInstance) {

                var OFFSET = new Date().getTimezoneOffset() * 60 * 1000;

                $scope.project = {};
                $scope.project.status = 'NEW';
                $scope.project.postProductionRequired = false;

                $scope.loadBudgets = function(){
                    $http.get('common/budgets').success(function(data) {
                        $scope.budgets = data;
                    }).error(function() {
                        console.error('Failed to get budgets');
                    });
                };

                $scope.loadServiceCategories = function(){
                    $http.get('common/categories').success(function(data) {
                        $scope.categories = data;
                    }).error(function() {
                        console.error('Failed to get categories');
                    });
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
                        console.error('Failed to get services');
                    });
                };

                $scope.loadCountries = function(){
                    $http.get('locations/countries').success(function(data) {
                        $scope.countries = data;
                    }).error(function() {
                        console.error('Failed to get countries');
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
                        $http.get('locations/states').success(function (data) {
                            $scope.states = data;
                        }).error(function () {
                            console.error('Failed to get states');
                        });
                    } else {
                        delete $scope.states;
                    }
                };

                $scope.loadDurations = function(){
                    $http.get('common/durations').success(function(data) {
                        $scope.durations = data;
                    }).error(function() {
                        console.error('Failed to get durations');
                    });
                };

                $scope.loadPaidOptions = function(){
                    $http.get('common/paidOptions').success(function(data) {
                        $scope.paidOptions = data;
                    }).error(function() {
                        console.error('Failed to get paid options');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                $scope.createProject = function () {
                    $scope.project.paidOptions = $scope.paidOptions.filter(function (option) {
                        return option.isChecked;
                    });
                    $scope.project.startDate = new Date(Date.parse($scope.project.startDate) + OFFSET);
                    $scope.project.finishDate = new Date(Date.parse($scope.project.finishDate) + OFFSET);
                    $http.post('projects', $scope.project).success(function(data) {
                        $modalInstance.close(true);
                        console.log('Was created');
                    }).error(function() {
                        console.error('Failed to create');
                    });
                };

                (function init() {
                	$scope.loadBudgets();
                	$scope.loadServiceCategories();
                	$scope.loadCountries();
                	$scope.loadDurations();
                	$scope.loadPaidOptions();
                })();
            }
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
	
	$scope.loadProject = function(){
		$http.get('projects/' + $routeParams.id).success(function(data) {
			$scope.project = data;
			$scope.createdAt = new Date($scope.project.createdAt);
			$scope.startDate = new Date($scope.project.startDate);
			$scope.finishDate = new Date($scope.project.finishDate);
		}).error(function(data, status) {
			alert('Failed to load project');
		});
	};

    $scope.loadComments = function(){
        $http.get('projects/' + $routeParams.id + '/comments').success(function(data) {
            $scope.comments = data;
        }).error(function(data, status) {
            alert('Failed to load comments');
        });
    };

    $scope.loadBids = function(){
        $http.get('projects/' + $routeParams.id + '/bids').success(function(data) {
            $scope.bids = data;
        }).error(function(data, status) {
            alert('Failed to load bids');
        });
    };
	
	$scope.editProject = function(){
        $scope.project.createdAt = new Date($scope.createdAt).getTime();
        $scope.project.startDate = new Date($scope.startDate).getTime();
        $scope.project.finishDate = new Date($scope.finishDate).getTime();
		$http.put('projects', $scope.project).success(function(data) {
			alert('Success, changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};

	$scope.blockProject = function () {
	    $scope.project.status = 'BLOCKED';
        $http.put('projects', $scope.project).success(function(data) {
            alert('Success, project was blocked.');
        }).error(function(data, status) {
            alert('Failed to block project!');
        });
    };

    $scope.unblockProject = function () {
        $scope.project.status = 'NEW';
        $http.put('projects', $scope.project).success(function(data) {
            alert('Success, project was unblocked.');
        }).error(function(data, status) {
            alert('Failed to unblock project!');
        });
    };

    $scope.deleteProject = function (id) {
        $http.delete('projects/' + id).success(function(data) {
            alert('Success, project was deleted.');
        }).error(function(data, status) {
            alert('Failed to delete project!');
        });
    };

    $scope.deleteComment = function (id) {
        $http.delete('comments/' + id).success(function(data) {
            alert('Success, comment was deleted.');
        }).error(function(data, status) {
            alert('Failed to delete comment!');
        });
    };

    $scope.deleteAttachment = function (id) {
        $http.delete('projects/results/' + id).success(function(data) {
            alert('Success, attachment was deleted.');
        }).error(function(data, status) {
            alert('Failed to delete attachment!');
        });
    };

    $scope.deleteBid = function (id) {
        $http.delete('bids/' + id + '/retract').success(function(data) {
            alert('Success, bid was deleted.');
        }).error(function(data, status) {
            alert('Failed to delete bid!');
        });
    };

    $scope.loadBudgets = function(){
        $http.get('common/budgets').success(function(data) {
            $scope.budgets = data;
        }).error(function() {
            console.error('Failed to get budgets');
        });
    };

    $scope.loadServiceCategories = function(){
        $http.get('common/categories').success(function(data) {
            $scope.categories = data;
        }).error(function() {
            console.error('Failed to get categories');
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
            console.error('Failed to get services');
        });
    };

    $scope.loadDurations = function(){
        $http.get('common/durations').success(function(data) {
            $scope.durations = data;
        }).error(function() {
            console.error('Failed to get durations');
        });
    };

    (function init(){
        $scope.loadProject();
        $scope.loadBudgets();
        $scope.loadServiceCategories();
        $scope.loadDurations();
        $scope.loadComments();
        $scope.loadBids();
    })();
} ]);
