/**
 * Created by ignacio on 21/10/14.
 */
angular.module('menuApp', [])
    .controller('MenuController', ['$scope','$http', function($scope,$http) {
        $scope.getMem = function() {
            $http.get("/memory")
                .success(function(response) {
                    $scope.memo = true;
                    $scope.totalMem = response.totalMemory;
                    $scope.usedMem = response.usedMemory;
                    $scope.freeMem = response.freeMemory;
                })
                .error(function(response){
                    $scope.memo = undefined;
                    console.log("error: "+response);
                });
        };
    }]);