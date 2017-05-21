'use strict';

angular.module('wkRegist').controller('RegistCtrl',
		function ($scope, $rootScope, $location, $route, $http, UserService) {
			if (UserService.isSignedIn()) {
				$location.path("/");
				return;
			}
			$scope.processing = false;
			$scope.registSuccess = false;
			$scope.registError = false;
			$scope.regist = function () {
				$scope.processing = true;
				$http.put("/user", $scope.registInfo).success(function () {
					console.log("regist success");
					$scope.registSuccess = true;
					$scope.registError = false;
					$scope.processing = false;
				}).error(function () {
					console.log("regist error");
					$scope.registSuccess = false;
					$scope.registError = true;
					$scope.processing = false;
				});
			}

			$scope.closeRegistErrorWarn = function () {
				$scope.registError = false;
			}
		});
