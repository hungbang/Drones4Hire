'use strict';

DronesAdmin.controller('PolicyPageController', [ '$scope', '$http', '$modal', function($scope, $http, $modal) {

    $scope.getPolicy = function(){
        $http.get('content/policy/all').success(function(data) {
            $scope.policy = data;
        }).error(function() {
            alertify.error('Failed to load policy');
        });
    };

    $scope.deletePolicy = function(id){
        $http.delete('content/policy/' + id).success(function(data) {
            $scope.policy.filter(function (policy, index) {
                if(policy.id == id) {
                    delete $scope.policy[index];
                }
            })
        }).error(function() {
            alertify.error('Failed to delete policy');
        });
    };

    $scope.openPolicyModal = function (policyItem) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/policy.html',
            scope: $scope,
            resolve: {
                'policyItem': function () {
                    return policyItem;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.policyItem = policyItem;

                $scope.createPolicy = function(policy){
                    $http.post('content/policy', policy).success(function(data) {
                        $scope.policy.push(data);
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to create policy');
                    });
                };

                $scope.updatePolicy = function(policy){
                    $http.put('content/policy', policy).success(function(data) {
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update policy');
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

        $scope.getPolicy();
    })();

}]);