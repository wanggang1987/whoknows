'use strict';

angular.module('wkLogin').controller('LoginCtrl',
	function ($scope, $http, $rootScope, $location, UserService, $log, LocalStorageService) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
		$scope.loggingError = false;
		$scope.forgotPasswordRequest = function () {
			$scope.$broadcast('event:forgotPassword');
		};
		
		
		$scope.regist = function(){
			$location.path("/regist");
		};
		
		$scope.closeWarn = function(){
			$scope.loggingError = false;
		}
		$scope.login = function(){
			
			console.log("login username "+ $scope.loginInfo)
			
			$http.post('/login', $scope.loginInfo).success(function (data) {
				$log.debug('Got the following response.');
				$scope.loggingIn = true;
				console.log(data)
				LocalStorageService.put("userName", data.username, true); 
				LocalStorageService.put("userId", data.id, true); 
				UserService.initialize(true).then(function () {
					$scope.loggingError = false;
					$rootScope.$broadcast('event:login:success');
				});
				
			}).error(function () {
				$scope.loggingError = true;
				$scope.loggingIn = false;
				$scope.$broadcast('event:login:failure');
			});
		}
	});
