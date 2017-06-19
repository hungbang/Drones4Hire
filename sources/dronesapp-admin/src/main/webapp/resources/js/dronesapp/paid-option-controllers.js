'use strict';

DronesAdmin.controller('PaidOptionController', [ '$scope', '$http', '$route', '$modal', function($scope, $http, $route, $modal) {
	
	$scope.loadPaidOptions = function()
	{
		$http.get('paidoptions/list').success(function(data) 
		{
			$scope.paidOptions = data;
		}).error(function() {
			console.error('Failed to load paid options!');
		});
	};
	
	$scope.openEditPaidOptionModal = function(option){
		$modal.open({
			templateUrl : 'resources/templates/paidoption/editPaidOptionModal.jsp',
			resolve : {
				'option' : function(){
					return angular.copy(option);
				}
			},
			controller : function($scope, $modalInstance, option){
				
				$scope.option = option;

				$scope.editPaidOption = function(option){
					$http.put('paidoptions/' + option.id, option).success(function(data) {
						$modalInstance.close(true);
					}).error(function(data, status) {
						alert('Failed to edit paid option!');
						$modalInstance.close(false);
					});
				};
				
				$scope.cancel = function(){
					$modalInstance.close(false);
				};
			}
		}).result.then(function(status) {
			if(status)
			{
				$scope.loadPaidOptions();
			}
        });
	};
	
	(function init() {
		$scope.loadPaidOptions();
	})();
	
}]);