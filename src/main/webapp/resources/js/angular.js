angular.module('products', []).controller('productsCtrl', function($scope) {
    $scope.orderByMe = function(x) {
        $scope.myOrderBy = x;
    }
});