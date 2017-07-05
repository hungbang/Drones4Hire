'use strict';

DronesAdmin.controller('TermsPageController', [ '$scope', '$http', '$modal', '$route', function($scope, $http, $modal, $route) {


    $scope.getTerms = function(){
        $http.get('content/terms/all').success(function(data) {
            $scope.terms = data;
        }).error(function() {
            alertify.error('Failed to load terms');
        });
    };

    $scope.deleteTerm = function(id){
        $http.delete('content/terms/' + id).success(function(data) {
        	$route.reload();
            alertify.success('Terms successfully deleted');
        }).error(function() {
            alertify.error('Failed to delete term');
        });
    };

    $scope.openTermModal = function (term) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/term.html',
            scope: $scope,
            resolve: {
                'term': function () {
                    return term;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.term = term;

                $scope.createTerm = function(term){
                    $http.post('content/terms', term).success(function(data) {
                        $scope.terms.push(data);
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to create term');
                    });
                };

                $scope.updateTerm = function(term){
                    $http.put('content/terms', term).success(function(data) {
                    	alertify.success('Terms successfully updated');
                    	$scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update term');
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
        $scope.getTerms();
    })();

}]);