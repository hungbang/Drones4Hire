'use strict';

DronesAdmin.controller('WithdrawController', [ '$scope', '$http', '$route', '$modal', 'PAGE_SIZES', function($scope, $http, $route, $modal, PAGE_SIZES) {
	
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
		$scope.searchWithdraws(1);
	};
		

	$scope.acceptWithdraw = function(request) {
		$http.post('withdraws/accept', request).success(function(data) {
			request.status = "PENDING";
			alertify.success('Request accepted!');
		}).error(function(data, status) {
			alertify.error('Failed to accept withdraw!');
		});
	};
	
	$scope.cancelWithdraw = function(request) {
		$modal.open({
			templateUrl : 'resources/templates/withdraw/cancelWithdrawModal.jsp',
			resolve : {
				'request' : function(){
					return request;
				}
			},
			controller : function($scope, $modalInstance, request){
				$scope.request = request;
				$scope.cancel = function(request) {
					$http.post('withdraws/cancel', request).success(function(data) {
						request.status = "CANCELED";
						alertify.success('Request canceled!');
					}).error(function(data, status) {
						alertify.error('Failed to accept withdraw!');
					});
					$modalInstance.close(0);
				};
				$scope.close = function(){
					$modalInstance.close(0);
				};
			}
		});
	};
	
	(function init() {
		$scope.searchWithdraws(1);
	})();
	
}]);