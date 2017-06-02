'use strict';

DronesAdmin.controller('OrdersListCtrl', [ '$scope', '$http', '$modal', 'PAGE_SIZES', '$cookieStore', 'LocaleProvider', function($scope, $http, $modal, PAGE_SIZES, $cookieStore, LocaleProvider) {
	var DEFAULT_ORDER_SEARCH_CRITERIA = {
			'pageSize' : PAGE_SIZES[0]
	};
	$scope.orderSearchCriteria = angular.copy(DEFAULT_ORDER_SEARCH_CRITERIA);
	$scope.orderListPageSizes = PAGE_SIZES;
	
	var OFFSET = new Date().getTimezoneOffset()*60*1000;
	
	$scope.searchOrders = function(page, pageSize){
		$scope.orderSearchCriteria.page = page;
		if(pageSize){
			$scope.orderSearchCriteria.pageSize = pageSize;
		}
		
		if($scope.createdAtAfter){
			$scope.orderSearchCriteria.createdAtAfter = new Date(Date.parse($scope.createdAtAfter) + OFFSET);
		}
		
		if($scope.createdAtBefore){
			$scope.orderSearchCriteria.createdAtBefore = new Date(Date.parse($scope.createdAtBefore) + OFFSET);
		}

		$http.post('orders/search' + LocaleProvider.getQueryParams(), $scope.orderSearchCriteria).success(function(data) {
			$scope.orderSearchResult = data;
			$scope.orderSearchCriteria.page = data.page;
			$scope.orderSearchCriteria.pageSize = data.pageSize;
		}).error(function() {
			console.error('Failed to search orders');
		});
	};
	
	$scope.listCarTypes = function(page, pageSize){
		$http.get('cars/types/list').success(function(data) {
			$scope.listCarTypesResult = data;
		}).error(function(data, status) {
			alert('Failed to load car types');
		});
	};
	
	$scope.setupCurrency = function(){
		if(LocaleProvider.getEconomicalGroup())
		{
			$scope.currency = LocaleProvider.getEconomicalGroup().currency;
		}
		else
		{
			$http.get('users/secured').success(function(user) {
				$scope.currency = user.country.currency;
			});
		}
	};
	
	(function init(){
		$scope.searchOrders(0);
		$scope.listCarTypes();
		$scope.setupCurrency();
	})();

	$scope.resetSearchCriteria = function(){
		$scope.orderSearchCriteria = angular.copy(DEFAULT_ORDER_SEARCH_CRITERIA);
		$scope.createdAtAfter = null;
		$scope.createdAtBefore = null;
	};
	
	$scope.openOrderReceipt = function(id){
		$http.get('orders/' + id + '/receipt').success(function(order) {
			$modal.open({
				templateUrl : 'resources/templates/orders/viewOrderReceipt.jsp',
				resolve : {
					'order' : function(){
						return order;
					}
				},
				controller : function($scope, $modalInstance, order)
				{
					$scope.order = order;
					$scope.close = function(){
						$modalInstance.close(0);
					};
				}
			});
		}).error(function() {
			console.error('Failed to load order');
		});
	};

	var it = $scope;

	$scope.openPasswordCheckPopup = function () {
		$modal.open({
			templateUrl : 'resources/templates/security/csvDownloadPassCheck.jsp',
			controller : function($scope, $modalInstance) {
				$scope.config = { "password" : { "isValid": true } };

				$scope.checkAndDownload = function(){
					it.downloadCSV(window.btoa($scope.config.password.value), function (result) {
						if (result) {
							$scope.cancel();
						} else {
							$scope.config.password.isValid = false;
						}
					});
				};

				$scope.cancel = function() {
					$modalInstance.close(0);
				}
			}
		});
	};

	$scope.downloadCSV = function(password, callback){
		var result;
		$http.post('orders/csv?password=' + password + LocaleProvider.getQueryParams(), $scope.orderSearchCriteria).success(function(csv) {
			var blob = new Blob([csv], { type: 'text/csv' });
			var csvUrl = URL.createObjectURL(blob);
			angular.element("<a/>").attr({
		     		'download': 'orders.csv',
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
} ]);
