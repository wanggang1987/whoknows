'use strict';

angular.module('wkLogin').controller('LoginCtrl',
	function ($scope, $http, $rootScope, $location, UserService, $log) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
		
		$scope.forgotPasswordRequest = function () {
			$scope.$broadcast('event:forgotPassword');
		};
		
		$scope.regist = function(){
			$location.path("/regist");
		};
		
		$scope.login = function(){
			
			console.log("login username "+ $scope.loginInfo)
			
			$http.post('/login', $scope.loginInfo).success(function (data) {
				$log.debug('Got the following response.', data);
				$scope.loggingIn = false;
				UserService.initialize(true).then(function () {
					$rootScope.$broadcast('event:login:success');
				});
			})
			.error(function () {
				$scope.loggingIn = false;
				$scope.loginError = true;
				$scope.$broadcast('event:login:failure');
			});
		}
	});
