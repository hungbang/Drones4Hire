DronesAdmin.constant('ROUTES', [ 
{
	url : '/dashboard',
	config : {
		templateUrl : 'dashboard'
	}
}, {
	url : '/users/clients',
	config : {
		templateUrl : 'users/clients'
	}
}, {
	url : '/users/pilots',
	config : {
		templateUrl : 'users/pilots'
	}
}, {
	url : '/users/:id/view',
	config : {
		templateUrl : 'users/view'
	}
}, {
	url : '/projects',
	config : {
		templateUrl : 'projects'
	}
}, {
	url : '/projects/:id/view',
	config : {
		templateUrl : 'projects/view'
	}
},{
	url : '/settings',
	config : {
		templateUrl : 'settings'
	}
},{
	url : '/withdraws',
	config : {
		templateUrl : 'withdraws'
	}
}, {
	url : '/paidoptions',
	config : {
		templateUrl : 'paidoptions'
	}
}, {
	url : '/locations/countries',
	config : {
		templateUrl : 'locations/countries'
	}
}, {
	url : '/map',
	config : {
		templateUrl : 'locations/map'
	}
}, {
	url : '/content/faqs',
	config : {
		templateUrl : 'content/faqs'
	}
}, {
	url : '/content/policy',
	config : {
		templateUrl : 'content/policy'
	}
}, {
	url : '/content/terms',
	config : {
		templateUrl : 'content/terms'
	}
}, {
	url : '/common/services/fees',
	config : {
		templateUrl : 'common/services/fees'
	}
}]);

DronesAdmin.config([ '$routeProvider', 'ROUTES',
	function($routeProvider, ROUTES) {
		angular.forEach(ROUTES, function(value) {
			$routeProvider.when(value.url, value.config);
		});
		$routeProvider.otherwise({
			redirectTo : ROUTES[0].url
		});
	}
]);
