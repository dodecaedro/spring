angular.module('TransferServiceApp', [
  'TransferServiceApp.controllers',
  'TransferServiceApp.services',
  'ngRoute'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
	when("/customers", {templateUrl: "partials/customers.html", controller: "customersController"}).
	when("/customers/:id", {templateUrl: "partials/customer.html", controller: "customerController"}).
	otherwise({redirectTo: '/customers'});
}]);