var TransferServiceApp = angular.module('TransferServiceApp.services', []);

TransferServiceApp.factory('transferServiceAPIservice', function($http) {
  var transferServiceAPI = {};

  transferServiceAPI.getCustomers = function() {
    return $http({
      method: 'get',
      url: 'customers.json'
    });
  }

  transferServiceAPI.getCustomer = function(id) {
    return $http({
      method: 'get',
      url: 'customers/' + id + '.json'
    });
  }

  return transferServiceAPI;
});