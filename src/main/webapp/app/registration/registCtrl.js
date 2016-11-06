'use strict';

angular.module('wkRegist').controller('RegistCtrl',
	function ($scope, $rootScope, $location, $route, $http) {
		console.log("wkCommon- wkRegist.RegistCtrl  load.")
		$scope.registSuccess = true;
		
		$scope.regist = function(){
			$http.put("/user", $scope.registInfo).success(function(){
				console.log("regist success");
				$scope.registSuccess = true;
				$location.path("/registTagSelect");
				$rootScope.regist={
						"userName" : $scope.registInfo.email,
						"s" :  $scope.registInfo.passwordAgain
				}
			}).error(function(){
				console.log("regist error");
				$scope.registSuccess = false;
			});
		}
		
		$scope.closeWarn = function(){
			$scope.registSuccess = true;
		}
	});
