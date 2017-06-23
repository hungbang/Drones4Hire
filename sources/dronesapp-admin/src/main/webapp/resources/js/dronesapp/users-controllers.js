'use strict';

DronesAdmin.controller('UsersPageController', [ '$scope', '$http', '$route', 'PAGE_SIZES', function($scope, $http, $route, PAGE_SIZES) {
	
	var DEFAULT_USER_SEARCH_CRITERIA = {
			'pageSize' : PAGE_SIZES[0],
			'role' : (isPilot() ? "ROLE_PILOT" : "ROLE_CLIENT")
	};
	
	$scope.sc = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
	$scope.pageSizes = PAGE_SIZES;
	$scope.title = isPilot() ? "Pilots" : "Clients";
	
	$scope.searchUsers = function(page, pageSize)
	{
		$scope.sc.page = page;
		if(pageSize) $scope.sc.pageSize = pageSize;
		$http.post('users/search', $scope.sc).success(function(data) 
		{
			$scope.sr = data;
			$scope.sc.page = data.page;
			$scope.sc.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search users');
		});
	};
	
	function isPilot() {
		return $route.current.loadedTemplateUrl.endsWith('pilots');
	};

	$scope.resetSearchCriteria = function() {
		$scope.sc = angular.copy(DEFAULT_USER_SEARCH_CRITERIA);
		$scope.searchUsers(1);
	};
		
	(function init() {
		$scope.searchUsers(1);
	})();
	
}]);

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
