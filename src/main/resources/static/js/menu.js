/**
 * Created by ignacio on 21/10/14.
 */
angular.module('menuApp', [])
    .controller('MenuController', ['$scope','$http', function($scope,$http) {
        $scope.getMem = function() {
            $http.get("/memory")
                .success(function(response) {
                    $scope.memo = true;
                    $scope.fs = false;
                    $scope.ps = false;
                    $scope.totalMem = response.totalMemory;
                    $scope.usedMem = response.usedMemory;
                    $scope.freeMem = response.freeMemory;
                })
                .error(function(status){
                    $scope.memo = undefined;
                    console.log("error: "+response);
                });
        };
        $scope.getFileSystemUsage = function() {
            $http.get("/filesystem")
                .success(function (response) {
                    console.log(response);
                    $scope.fileSystems = response;
                    $scope.memo = false;
                    $scope.ps = false;
                    $scope.fs = true;
                })
                .error(function(response) {
                    $scope.memo = false;
                    $scope.fs = false;
                    console.log("error: " + response);
                });
        };
        $scope.getProcess = function () {
            $http.get("/process")
                .success(function (response) {
                    console.log(response);
                    $scope.process = response;
                    $scope.fs = false;
                    $scope.memo = false;
                    $scope.ps = true;
                })
                .error(function(response){
                    $scope.memo = false;
                    $scope.fs = false;
                    $scope.ps = false;
                    console.log("error: " + response);
                });
        };
    }]);