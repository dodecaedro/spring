var TransferServiceApp = angular.module('TransferServiceApp.controllers', []);

/* customers controller */
TransferServiceApp.controller('customersController', function($scope, transferServiceAPIservice) {
  $scope.nameFilter = null;
  $scope.customers = [];

  $scope.searchFilter = function (customer) {
    var re = new RegExp($scope.nameFilter, 'i');
    return !$scope.nameFilter || re.test(customer.firstName) || re.test(customer.lastName);
  };

  transferServiceAPIservice.getCustomers().success(function (response) {
    $scope.customers = response;
  });
});


/* customer controller */
TransferServiceApp.controller('customerController', function($scope, $routeParams, transferServiceAPIservice) {
  $scope.id = $routeParams.id;
  $scope.customer = null;

  transferServiceAPIservice.getCustomer($scope.id).success(function (response) {
    $scope.customer = response;
  });
});