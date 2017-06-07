var DronesApp = angular.module('DronesApp', ['ngAria', 'ngAnimate',  'ngMessages', 'ngCookies']);

DronesApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['Authorization'] = 'Bearer ' +
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwidXNlcm5hbWUiOiJjbGllbnQiLCJlbWFpbCI6ImNsaWVudEBnbWFpbC5jb20iLCJlbmFibGVkIjp0cnVlLCJyb2xlcyI6WyJST0xFX0NMSUVOVCJdLCJleHAiOjE0OTY4NDUzNTV9.YBYCroXeR1mpp65B8RtbPRCYWcfshx34aObSXAka0pk7jezkV-czi2WLm3MQ8qPk02QI6NtX4GniheRPX9l69w';
}]);

DronesApp.controller('PaymentController', [ '$scope', '$http', '$location',
    function($scope, $http, $location) {

    $scope.title = 'Payment service';
    $scope.newCard = false;
    $scope.newTransaction = false;
    $scope.card = {};

    $scope.changeNewCardStatus = function () {
        $scope.newCard = ! $scope.newCard;
    };

    $scope.changeGetCardStatus = function (existsCard) {
        existsCard.newTransaction = ! existsCard.newTransaction;
        $scope.newTransaction = ! $scope.newTransaction;
    };

    $scope.getToken = function() {
        $http.get('http://localhost:8080/drones-api/api/v1/payments/client_token').then(function successCallback(rs) {
            $scope.client_token = rs.data;
            braintree.setup($scope.client_token,
                'custom', {id: 'checkout'});
        });
    };

    $scope.getCreditCards = function() {
        $http.get('http://localhost:8080/drones-api/api/v1/cards').then(function successCallback(rs) {
            $scope.creditCards = rs.data;
        });
    };

    $scope.createCard = function(card) {
        $http.post('http://localhost:8080/drones-api/api/v1/cards', card).then(function successCallback(rs) {
            $scope.creditCards.push(rs.data);
            $scope.newCard = false;
            alertify.success('Was created');
        });
    };

    $scope.deleteCard = function(token) {
        $http.delete('http://localhost:8080/drones-api/api/v1/cards/' + token).then(function successCallback(rs) {
            alertify.success('Was deleted');
        });
    };

    $scope.updateCard = function(card) {
        $http.put('http://localhost:8080/drones-api/api/v1/cards/', card).then(function successCallback(rs) {
            alertify.success('Was updated');
        });
    };

    $scope.sale = function(token, amount) {
        $http.post('http://localhost:8080/drones-api/api/v1/payments/' + token, amount).then(function successCallback(rs) {
            alertify.success('Was payed');
        });
    };

    var criteria = {};
    $scope.projectToJson = "";

    $scope.getUserProjects = function () {
        $http.post('http://localhost:8080/drones-api/api/v1/projects/search', criteria).then(function successCallback(rs) {
            $scope.projects = rs.data.results;
            if(rs.data.results.length) {
                $scope.projectToJson = JSON.stringify(rs.data.results[0]).split('{').join('\n{\n').split('}').join('\n}\n')
                    .split('[').join('\n[\n').split(']').join('\n]\n');
            }
        });
    };

    $scope.createProject = function(projectInJson) {
        $http.post('http://localhost:8080/drones-api/api/v1/projects', projectInJson).then(function successCallback(rs) {
            alertify.success('Project was created');
        });
    };

    $scope.deleteProject = function(id) {
        $http.delete('http://localhost:8080/drones-api/api/v1/projects/' + id).then(function successCallback(rs) {
            alertify.success('Project was deleted');
        });
    };

    (function init() {
        $scope.getCreditCards();
        $scope.getUserProjects();
    })();
}]);
