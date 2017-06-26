'use strict';

DronesAdmin.controller('CountriesPageController', [ '$scope', '$http', '$route', '$modal', 'PAGE_SIZES', function($scope, $http, $route, $modal, PAGE_SIZES) {

    var DEFAULT_COUNTRIES_SEARCH_CRITERIA = {
        'pageSize' : PAGE_SIZES[0],
    };

    $scope.sc = angular.copy(DEFAULT_COUNTRIES_SEARCH_CRITERIA);
    $scope.pageSizes = PAGE_SIZES;

    $scope.searchCountries = function(page, pageSize)
    {
        $scope.sc.page = page;
        if(pageSize) $scope.sc.pageSize = pageSize;
        if(!$scope.sc.licenseRequired) {
            $scope.sc.licenseRequired = undefined;
        }
        $http.post('locations/countries/search', $scope.sc).success(function(data)
        {
            $scope.sr = data;
            $scope.sc.page = data.page;
            $scope.sc.pageSize = data.pageSize;
        }).error(function() {
            alertify.error('Failed to search countries');
        });
    };

    $scope.resetSearchCriteria = function() {
        $scope.sc = angular.copy(DEFAULT_COUNTRIES_SEARCH_CRITERIA);
        $scope.searchCountries(1);
    };

    $scope.openCountryModal = function (country) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/country.html',
            scope: $scope,
            resolve: {
                'country': function () {
                    return country;
                }
            },
            controller: function ($scope, $modalInstance, country) {

                $scope.country = {};
                angular.copy(country, $scope.country);

                $scope.updateCountry = function () {
                    $http.put('locations/countries', $scope.country).success(function(data)
                    {
                        $scope.searchCountries(1);
                        $scope.cancel();
                    }).error(function () {
                        alertify.error('Failed to update country');
                    });
                };

                $scope.cancel = function () {
                    $modalInstance.close(false);
                };

                (function init() {
                })();
            }
        }).result.then(function () {
        }, function () {
        });
    };

    (function init() {
        $scope.searchCountries(1);
    })();

}]);