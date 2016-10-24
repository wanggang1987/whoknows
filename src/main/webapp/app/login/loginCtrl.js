'use strict';

angular.module('wkLogin').controller('LoginCtrl',
	function ($scope, $rootScope, $location, $route, $window) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
		
		$scope.forgotPasswordRequest = function () {
			$scope.$broadcast('event:forgotPassword');
		};
		$scope.regist = function(){
			$location.path("/regist");
		}
	});
