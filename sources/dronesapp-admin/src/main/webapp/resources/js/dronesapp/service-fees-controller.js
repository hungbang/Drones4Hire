'use strict';

DronesAdmin.controller('ServiceFeesPageController', [ '$scope', '$http', '$modal', '$route', function($scope, $http, $modal, $route) {


    $scope.getServiceFees = function(){
        $http.get('common/services/fees/all').success(function(data) {
            $scope.serviceFees = data;
        }).error(function() {
            alertify.error('Failed to load serviceFees');
        });
    };

    $scope.deleteServiceFee = function(id){
        $http.delete('common/services/fees/' + id).success(function(data) {
            $route.reload();
            alertify.success('ServiceFees successfully deleted');
        }).error(function() {
            alertify.error('Failed to delete serviceFee');
        });
    };

    $scope.openServiceFeeModal = function (serviceFee) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/service-fee.html',
            scope: $scope,
            resolve: {
                'serviceFee': function () {
                    return serviceFee;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.serviceFee = serviceFee;

                $scope.createServiceFee = function(serviceFee){
                    $http.post('common/services/fees', serviceFee).success(function(data) {
                        $scope.serviceFees.push(data);
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to create serviceFee');
                    });
                };

                $scope.updateServiceFee = function(serviceFee){
                    $http.put('common/services/fees', serviceFee).success(function(data) {
                        alertify.success('ServiceFees successfully updated');
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update serviceFee');
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
        $scope.getServiceFees();
    })();

}]);