'use strict';

DronesAdmin.controller('WithdrawController', [ '$scope', '$http', '$route', 'PAGE_SIZES', function($scope, $http, $route, PAGE_SIZES) {
	
	var DEFAULT_WITHDRAW_SEARCH_CRITERIA = {
			'pageSize' : PAGE_SIZES[0]
	};
	
	$scope.sc = angular.copy(DEFAULT_WITHDRAW_SEARCH_CRITERIA);
	$scope.pageSizes = PAGE_SIZES;
	
	$scope.searchWithdraws = function(page, pageSize)
	{
		$scope.sc.page = page;
		if(pageSize) $scope.sc.pageSize = pageSize;
		$http.post('withdraws/search', $scope.sc).success(function(data) 
		{
			$scope.sr = data;
			$scope.sc.page = data.page;
			$scope.sc.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search withdraw requests');
		});
	};
	
	$scope.resetSearchCriteria = function() {
		$scope.sc = angular.copy(DEFAULT_WITHDRAW_SEARCH_CRITERIA);
		$scope.searchWithdraws(0);
	};
		
	(function init() {
		$scope.searchWithdraws(0);
	})();
	
}]);