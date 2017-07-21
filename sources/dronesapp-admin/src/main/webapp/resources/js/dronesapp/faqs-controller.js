'use strict';

DronesAdmin.controller('FaqsPageController', [ '$scope', '$http', '$modal', '$route', function($scope, $http, $modal, $route) {

    $scope.tabs = [{ active: true }, { active: false }];

    $scope.ROLES = ['ROLE_CLIENT', 'ROLE_PILOT'];

    $scope.activeTab = 0;

    $scope.switchRoles = function (tab) {
        $scope.activeTab = tab;
        $scope.getFaqs($scope.ROLES[tab])
    };

    $scope.getFaqs = function(role){
        $http.get('content/faqs/all?role=' + role).success(function(data) {
            $scope.faqs = data;
        }).error(function() {
            alertify.error('Failed to load FAQs');
        });
    };

    $scope.getGroups = function(){
        $http.get('users/groups/all').success(function(data) {
            $scope.groups = data;
        }).error(function() {
            alertify.error('Failed to load groups');
        });
    };

    $scope.deleteFaq = function(id){
        $http.delete('content/faqs/' + id).success(function(data) {
            $route.reload();
            alertify.success('FAQ successfully deleted');
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
                    $http.post('content/faqs', faq).success(function(data) {
                        $scope.getFaqs($scope.ROLES[$scope.activeTab]);
                        $scope.cancel();
                    }).error(function() {
                        alertify.error('Failed to create FAQ');
                    });
                };

                $scope.updateFaq = function(faq){
                    $http.put('content/faqs', faq).success(function(data) {
                        $scope.cancel();
                        alertify.success('FAQ successfully updated');
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
        $scope.getFaqs($scope.ROLES[0]);
        $scope.getGroups();
    })();

}]);