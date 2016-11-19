'use strict';

angular.module('wkRegist').controller('RegistCtrl',
	function ($scope, $rootScope, $location, $route, $http) {
		console.log("wkCommon- wkRegist.RegistCtrl  load.")
		if(UserService.isSignedIn()){
			$location.path("/");
			return;
		}
		
		$scope.registSuccess = false;
		$scope.registError = false;
		$scope.regist = function(){
			$http.put("/user", $scope.registInfo).success(function(){
				console.log("regist success");
				$scope.registSuccess = true;
				$scope.registError = false;
			}).error(function(){
				console.log("regist error");
				$scope.registSuccess = false;
				$scope.registError = true;
			});
		}
		
		$scope.closeRegistErrorWarn = function(){
			$scope.registError = false;
		}
	});
