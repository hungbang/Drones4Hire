var DronesApp = angular.module('DronesApp', ['ngAria', 'ngAnimate',  'ngMessages', 'ngCookies']);

DronesApp.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.defaults.headers.common['Authorization'] = 'Bearer ' +
        'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwidXNlcm5hbWUiOiJjbGllbnQiLCJlbWFpbCI6ImNsaWVudEBnbWFpbC5jb20iLCJlbmFibGVkIjp0cnVlLCJyb2xlcyI6WyJST0xFX0NMSUVOVCJdLCJleHAiOjE0OTY3NjQ2NDV9.jH3-uK4j5QnekiS9sbt7AQgrOnrDW5KhOamVFzUmIoyQD-QaI05z4Iwc97h-WKvbgvtTRduFVMsNNK1p2uNdKQ';
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

    (function init() {
        $scope.getCreditCards();
    })();
}]);
