'use strict';

DronesAdmin.controller('DashboardCtrl', [ '$scope', '$http','$location', '$timeout', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $timeout, $cookieStore, LocaleProvider) {
	
	$scope.loadDashboard = function() {
		$http.get('dashboard/overview').success(function(data) {
			$scope.overview = data;
			
			Morris.Donut({
				  element: 'roles-ratio-graph',
				  data: [
				    {label: "Clients", value: $scope.overview['clientsTotal']},
				    {label: "Pilots", value: $scope.overview['pilotsTotal']}
				  ]
			});
			
			Morris.Area({
			  element: 'projects-graph',
			  data: [
			    { y: '2006', a: 100, b: 90 },
			    { y: '2007', a: 75,  b: 65 },
			    { y: '2008', a: 50,  b: 40 },
			    { y: '2009', a: 75,  b: 65 },
			    { y: '2010', a: 50,  b: 40 },
			    { y: '2011', a: 75,  b: 65 },
			    { y: '2012', a: 100, b: 90 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Created', 'Finished']
			});
			
		}).error(function(data, status) {
			alertify.error('Failed to load dashboard');
		});
	};
	
	
	
	(function init() {
		$scope.loadDashboard();
	})();
} ]);
