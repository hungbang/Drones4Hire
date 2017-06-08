'use strict';

DronesAdmin.controller('UsersPageController', [ '$scope', '$http', '$location', '$modal', '$upload', '$route', 'PAGE_SIZES', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $modal, $upload, $route, PAGE_SIZES, $cookieStore, LocaleProvider) {
	var DEFAULT_USER_SEARCH_CRITERIA = {
			'rating' : 5,
			'pageSize' : PAGE_SIZES[0],
			'roles' : ['PILOT', 'CLIENT']
	};
	$scope.userSearchCriteria = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
	$scope.userListPageSizes = PAGE_SIZES;
	
	$scope.loadRoles = function(){
		$scope.roles = ["ROLE_ADMIN", "ROLE_PILOT", "ROLE_CLIENT"];
	};

	$scope.searchUsers = function(page, pageSize){

		var OFFSET = new Date().getTimezoneOffset()*60*1000;
		$scope.userSearchCriteria.page = page;
		if(pageSize){
			$scope.userSearchCriteria.pageSize = pageSize;
		}
//
//		if($scope.userSearchCriteria.role != null)
//		{
//			$scope.userSearchCriteria.roles = null;
//		}
		
		$http.post('users/searchUsers', $scope.userSearchCriteria).success(function(data) {
			$scope.userSearchResult = data;
			$scope.userSearchCriteria.page = data.page;
			$scope.userSearchCriteria.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search users');
		});
	};

	var it = $scope;
	
	$scope.resetSearchCriteria = function(){
		$scope.userSearchCriteria = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
		$scope.searchUsers(0);
	};
		
	$scope.openViewPage = function(id){
		$location.path('/users/' + id + '/view');
	};
	
	(function init(){
		$scope.loadRoles();
		$scope.searchUsers(0);
	})();
	
} ]);

DronesAdmin.controller('UserDetailsController', [ '$scope', '$http', '$location', '$routeParams', '$modal', '$route', '$upload', function($scope, $http, $location, $routeParams, $modal, $route, $upload) {
	
	$scope.loadLocationsData = function() {
		$http.get('locations/states').success(function(data) {
			$scope.listSatesResult = data;
		}).error(function(data, status) {
			alert('Failed to load states');
		});
		$http.get('locations/countries').success(function(data) {
			$scope.listCountriesResult = data;
		}).error(function(data, status) {
			alert('Failed to load countries');
		});
	};
	
	$scope.loadUser = function() {
		$http.get('users/' + $routeParams.id).success(function(data) {
			$scope.user = data;
		}).error(function(data, status) {
			alert('Failed to load user');
		});
	};
	
	$scope.loadCompanyData = function() {
		$http.get('users/' + $routeParams.id + '/companies').success(function(data) {
			$scope.company = data;
		}).error(function(data, status) {
			alert('Failed to load company');
		});
	};
	
	$scope.loadSettingsData = function() {
		$http.get('users/' + $routeParams.id + '/notifications').success(function(data) {
			$scope.settings = data;
		}).error(function(data, status) {
			alert('Failed to load notification settings');
		});
	};
	
	(function init(){
		$scope.loadLocationsData();
		$scope.loadUser();
		$scope.loadCompanyData();
		$scope.loadSettingsData();
	})();
	
	$scope.editUser = function(id){
		$http.put('users/' + id, $scope.user).success(function(data) {
			alert('Success, user changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};
	
	$scope.editLocation = function(id) {
		$http.put('locations/' + id, $scope.user.location).success(function(data) {
			alert('Success, location changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};
	
	$scope.editCompany = function(userId) {
		$http.put('users/' + userId + "/companies", $scope.company).success(function(data) {
			alert('Success, company changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};
	
	$scope.editSettings = function(userId) {
		$http.put('users/' + userId + "/notifications", $scope.settings).success(function(data) {
			alert('Success, notification settings changes were saved.');
		}).error(function(data, status) {
			alert('Failed to save changes!');
		});
	};
	
	$scope.contactUser = function(userId){
		$http.get('users/' + userId + '/contact/messages').success(function(data) {
			$modal.open({
				templateUrl : 'resources/templates/users/contactUserModal.jsp',
				resolve : {
					'messages' : function(){
						return data;
					},
					'userId' : function(){
						return userId;
					}
				},
				controller : function($scope, $modalInstance, messages, userId){
					$scope.messages = messages;
					$scope.supportMessage = {};
					
					$scope.sendEmail = function() {
						$scope.supportMessage.userId = userId;
						$scope.supportMessage.type = 'EMAIL';
						
						$http.post('users/contact', $scope.supportMessage).success(function(data) {
						}).error(function() {
							alert('Failed to send Email');
						});
						
						$modalInstance.close(0);
					};
					
					$scope.close = function(){
						$modalInstance.close(0);
					};
				}
			});
		}).error(function() {
			console.error('Failed to load user contact messages');
		});
	};
	
} ]);
