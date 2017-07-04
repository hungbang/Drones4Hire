'use strict';

DronesAdmin.controller('FaqsPageController', [ '$scope', '$http', '$modal', function($scope, $http, $modal) {


    $scope.getFaqs = function(){
        $http.get('content/faqs/all').success(function(data) {
            $scope.faqs = data;
        }).error(function() {
            alertify.error('Failed to load FAQs');
        });
    };

    $scope.deleteFaq = function(id){
        $http.delete('content/faqs/' + id).success(function(data) {
            $scope.faqs.filter(function (faq, index) {
                if(faq.id == id) {
                    delete $scope.faqs[index];
                }
            });
        }).error(function() {
            alertify.error('Failed to delete FAQ');
        });
    };

    $scope.openFaqModal = function (faq) {
        var modalInstance = $modal.open({
            templateUrl: 'resources/templates/modal/faq.html',
            scope: $scope,
            resolve: {
                'faq': function () {
                    return faq;
                }
            },
            controller: function ($scope, $modalInstance) {

                $scope.faq = faq;

                $scope.createFaq = function(faq){
                    faq.order = 0;
                    $http.post('content/faqs', faq).success(function(data) {
                        $scope.faqs.push(data);
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to create FAQ');
                    });
                };

                $scope.updateFaq = function(faq){
                    $http.put('content/faqs', faq).success(function(data) {
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to update FAQ');
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
        $scope.getFaqs();
    })();

}]);