'use strict';

DronesAdmin.directive('invalid', [ function() {
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function(scope, element, attr, ngModelCtl) {
			ngModelCtl.$setValidity('invalid', false);
		}
	};
} ]);

DronesAdmin.directive('pagination', [ function() {
	return {
		restrict : 'E',
		templateUrl : 'resources/templates/pagination.jsp',
		scope : {
			page : '=page',
			pageSize : '=pageSize',
			pageSizes : '=pageSizes',
			results : '=results',
			search : '&search'
		},
		controller : function($scope, $element, $attrs, $transclude) {
			$scope.range = function(min, max, step) {
				step = (step == undefined) ? 1 : step;

				var result = [];

				for (var i = min; i <= Math.ceil(max); i += step) {
					result.push(i);
				}

				return result;
			};
		},
		link : function(scope, element, attr) {
		}
	};
} ]);

DronesAdmin.directive('ngReallyClick', [ function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			element.bind('click', function() {
				var message = attrs.ngReallyMessage;
				if (message && confirm(message)) {
					scope.$apply(attrs.ngReallyClick);
				}
			});
		}
	};
}]);

DronesAdmin.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
