'use strict';

DronesAdmin.controller('ProjectsPageController', [ '$scope', '$http', '$location', '$modal', '$upload', '$route', 'PAGE_SIZES', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $modal, $upload, $route, PAGE_SIZES, $cookieStore, LocaleProvider) {
	var DEFAULT_PROJECTS_SEARCH_CRITERIA = {
			'rating' : 5,
			'pageSize' : PAGE_SIZES[0],
			'statuses' : ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED']
	};
	$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
	$scope.projectListPageSizes = PAGE_SIZES;
	
	$scope.loadStatuses = function(){
		$scope.roles = ['NEW', 'PENDING', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED'];
	};

	$scope.searchProjects = function(page, pageSize){

		var OFFSET = new Date().getTimezoneOffset()*60*1000;
		$scope.projectSearchCriteria.page = page;
		if(pageSize){
			$scope.projectSearchCriteria.pageSize = pageSize;
		}
		
		$http.post('projects/search', $scope.projectSearchCriteria).success(function(data) {
			$scope.projectSearchResult = data;
			$scope.projectSearchCriteria.page = data.page;
			$scope.projectSearchCriteria.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search projects');
		});
	};

	var it = $scope;
	
	$scope.resetSearchCriteria = function(){
		$scope.projectSearchCriteria = angular.copy(DEFAULT_PROJECTS_SEARCH_CRITERIA);
	};
		
	$scope.openViewPage = function(id){
		$location.path('/projects/' + id + '/view');
	};
	
	(function init(){
		$scope.loadStatuses();
		$scope.searchProjects(0);
	})();
	
} ]);

DronesAdmin.controller('ProjectDetailsController', [ '$scope', '$http', '$location', '$routeParams', '$modal', '$route', '$upload', function($scope, $http, $location, $routeParams, $modal, $route, $upload) {
	
	$scope.loadProject = function(){
		$http.get('projects/' + $routeParams.id).success(function(data) {
			$scope.project = data;
		}).error(function(data, status) {
			alert('Failed to load project');
		});
	};
	
	(function init(){
		$scope.loadProject();
	})();
	
	$scope.editProject = function(id){
		$http.put('projects/' + id, $scope.user).success(function(data) {
			alert('Success, changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};
} ]);
