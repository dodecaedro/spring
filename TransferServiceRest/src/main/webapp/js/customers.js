function Customers($scope, $http) {
    $http.get('customers.json').
        success(function(data) {
            $scope.customers = data;
        });
}