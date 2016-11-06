'use strict';

angular.module('wkTopic').controller('TopicCtrl',
	function ($scope, $rootScope, $location, $route, $window, UserService) {
		$scope.loginIn = true; 
		$scope.isActive = function (viewLocation) {
		     var active = (viewLocation === $location.path());
		     return active;
		};
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
	});
