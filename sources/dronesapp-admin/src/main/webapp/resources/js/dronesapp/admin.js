var DronesAdmin = angular.module('DronesAdmin', [ 'ngRoute', 'ngCookies', 'ui.bootstrap.modal', 'ui.bootstrap.tabs', 'uiGmapgoogle-maps', 'multi-select', 'ngAutocomplete', 'pubnub.angular.service', 'ngAudio', 'angularFileUpload', 'angular-loading-bar', 'ngSanitize', 'ngCsv', 'textAngular', '720kb.tooltips', 'timer']);

angular.module('DronesAdmin').filter('fromNow', function() {
	return function(date) {
		return moment(date).fromNow();
	}
});

angular.module('DronesAdmin').filter('orderObjectBy', function() {
  return function(items, field, reverse) {
    var filtered = [];
    angular.forEach(items, function(item) {
      filtered.push(item);
    });
    filtered.sort(function (a, b) {
      return (a[field] > b[field] ? 1 : -1);
    });
    if(reverse) filtered.reverse();
    return filtered;
  };
});


DronesAdmin.config(function($httpProvider) {
	$httpProvider.interceptors.push('AuthHttpInterceptor');
	var spinnerFunction = function spinnerFunction(data, headersGetter) {
		$("#loading-indicator").show();
		return data;
	};
	$httpProvider.defaults.transformRequest.push(spinnerFunction);
	$httpProvider.defaults.headers.common = {
		"X-Requested-With" : "XMLHttpRequest"
	};
});

DronesAdmin.factory( 'UserService', function($http, $cookieStore) {
	return {
		currentUser: function() {
			return $http.get('users/current');
		}
	};
	
});

DronesAdmin.service('UserService', function($http, $q) {
	this.searchUsers = function(searchCriteria) {
        var deferred = $q.defer();
        $http.post('json/searchUsers', searchCriteria).success(function(data){
            deferred.resolve(data);               
        }).error(function(data, status, headers, config) {
            deferred.reject("Failed to search users: " + status); 
        });
        return deferred.promise;
    };
});

DronesAdmin.provider('LocaleProvider', function() {
    this.$get = function($cookieStore) {
        return {
            getEconomicalGroup: function() {
                return $cookieStore.get("economicalGroup");
            },
            getCity: function() {
                return $cookieStore.get("city");
            },
            getQueryParams: function() {
            	var eg = $cookieStore.get("economicalGroup");
        		var city = $cookieStore.get("city");
        		var params = "";
        		if(eg != null)
        		{
        			params += "?egId=" + eg.id;
        		}
        		
        		if(city != null)
        		{
        			if(params == "")
        			{
        				params += "?cityId=" + city.id;
        			}
        			else
        			{
        				params += "&cityId=" + city.id;
        			}
        		}
        		return params;
            }
        }
    };
});

DronesAdmin.factory("AuthHttpInterceptor", ['$q', function($q) {
	return {
		responseError : function(response) {
			if (response.status === 401)
			{
				location.reload();
			}
			return $q.reject(response);
		}
	};
}]);

angular.module('DronesAdmin').directive('resizer', function($document) {

    return function($scope, $element, $attrs) {

        $element.on('mousedown', function(event) {
            event.preventDefault();

            $document.on('mousemove', mousemove);
            $document.on('mouseup', mouseup);
//          $document.on('dblclick', dblclick);
        });
        
        function dblclick(event) {
        	 var y = window.innerHeight - 50;
	    	 $element.css({
	             bottom: y + 'px'
	         });
	
	         $($attrs.resizerTop).css({
	             bottom: (y + parseInt($attrs.resizerHeight)) + 'px'
	         });
	         $($attrs.resizerBottom).css({
	             height: y + 'px'
	         });
        }

        function mousemove(event) {

            if ($attrs.resizer == 'vertical') {
                // Handle vertical resizer
                var x = event.pageX;

                if ($attrs.resizerMax && x > $attrs.resizerMax) {
                    x = parseInt($attrs.resizerMax);
                }

                $element.css({
                    left: x + 'px'
                });

                $($attrs.resizerLeft).css({
                    width: x + 'px'
                });
                $($attrs.resizerRight).css({
                    left: (x + parseInt($attrs.resizerWidth)) + 'px'
                });

            } else {
                // Handle horizontal resizer
                var y = Math.max(Math.min(window.innerHeight - event.pageY, window.innerHeight - 50), 35);

                $element.css({
                    bottom: y + 'px'
                });

                $($attrs.resizerTop).css({
                    bottom: (y + parseInt($attrs.resizerHeight)) + 'px'
                });
                $($attrs.resizerBottom).css({
                    height: y + 'px'
                });
            }
        }

        function mouseup() {
            $document.unbind('mousemove', mousemove);
            $document.unbind('mouseup', mouseup);
        }
    };
});

DronesAdmin.directive("rpattern", function() {
    return {
        restrict: "A",
        require: "ngModel",
        link: function(scope, el, attrs, ngModel) {
            var validator, patternValidator,
                pattern = attrs.rpattern,
                required = true;
            
            if( pattern ) {
                if (pattern.match(/^\/(.*)\/$/)) {
                    pattern = new RegExp(pattern.substr(1, pattern.length - 2));
                    patternValidator = function(value) {
                        return validate(pattern, value)
                    };
                }
                else {
                    patternValidator = function(value) {
                        var patternObj = scope.$eval(pattern);
                        if (!patternObj || !patternObj.test) {
                            throw new Error('Expected ' + pattern + ' to be a RegExp but was ' + patternObj);
                        }
                        return validate(patternObj, value);
                    };
                }
            }
            
            ngModel.$formatters.push(patternValidator);
            ngModel.$parsers.push(patternValidator);
            
            attrs.$observe("required", function(newval) {
                required = newval;
                patternValidator(ngModel.$viewValue);
            });
            
            function validate(regexp, value) {
                if( value == null || value === "" || !required || regexp.test(value) ) {
                    ngModel.$setValidity('pattern', true);
                    return value;
                }
                else {
                    ngModel.$setValidity('pattern', false);
                    return;
                }
            }
        }
    };
});

angular.module('DronesAdmin').directive('dropdown', function($document) {
    return function($scope, $element, $attrs) {
        $element.on('hide.bs.dropdown', function(event) {
        	if (window.location.href.indexOf($attrs.dropdown) > -1)
        	{
        		return false;
        	}
        	else
        	{
        		return true;
        	}
        });
    }
});