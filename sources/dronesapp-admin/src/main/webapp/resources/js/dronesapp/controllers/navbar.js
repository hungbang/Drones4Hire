'use strict';

DronesAdmin.controller('NavbarController', [ '$scope', '$location', function($scope, $location) {
	$scope.isActive = function(view) {
		return view === $location.path();
	};
} ]);