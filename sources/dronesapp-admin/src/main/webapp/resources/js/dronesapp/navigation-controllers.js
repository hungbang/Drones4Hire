'use strict';

DronesAdmin.controller('NavigationCtrl', [ '$scope', '$http','$location', '$route', '$modal', '$cookieStore', function($scope, $http, $location, $route, $modal, $cookieStore) {
	
} ]);

DronesAdmin.controller('ProfileCtrl', [ '$scope', '$http','$cookies', function($scope, $http, $cookies) {

	$scope.getUsername = function(){
		return decodeURIComponent(escape($cookies['username'])).replace("+", " ");
	};
	
} ]);