'use strict';

DronesAdmin.controller('DashboardCtrl', [ '$scope', '$http','$location', '$timeout', '$cookieStore', 'LocaleProvider', function($scope, $http, $location, $timeout, $cookieStore, LocaleProvider) {

	$scope.DAYS_AGO_PROJECTS_STATISTIC = 30;
	
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
			
		}).error(function(data, status) {
			alertify.error('Failed to load dashboard');
		});
	};

	$scope.sc = {};

	$scope.loadProjectsStatistic = function () {
		var currentDate = new Date();
		currentDate.setDate(currentDate.getDate() - $scope.DAYS_AGO_PROJECTS_STATISTIC);
		$scope.sc.createdAtAfter = currentDate;
        $http.post('projects/search/statistic', $scope.sc).success(function(results) {
            $scope.data = [];
            Object.keys(results).sort().forEach(function(key, value) {
            	$scope.data.push({
					createdAt: parseInt(key),
					NEW: results[key]["NEW"].count,
					IN_PROGRESS: results[key]["IN_PROGRESS"].count,
					CANCELLED: results[key]["CANCELLED"].count,
					COMPLETED: results[key]["COMPLETED"].count,
					PENDING: results[key]["PENDING"].count,
					BLOCKED: results[key]["BLOCKED"].count});
            });
            Morris.Area({
                element: 'projects-graph',
                data: $scope.data,
                xkey: 'createdAt',
                ykeys: ['NEW', 'IN_PROGRESS', 'CANCELLED', 'COMPLETED', 'PENDING', 'BLOCKED'],
                labels: ['New', 'In progress', 'Cancelled', 'Completed', 'Pending', 'Blocked'],
				dateFormat: function (date) {
					return new Date(new Date(date).getTime()).toLocaleDateString();
                },
                xLabelAngle: 75,
                lineWidth: 1,
                behaveLikeLine: true
            });
        }).error(function(data, status) {
            alertify.error('Failed to load project statistics');
        });
    };
	
	(function init() {
		$scope.loadDashboard();
		$scope.loadProjectsStatistic();
	})();
} ]);
