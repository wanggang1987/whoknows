'use strict';

angular.module('wkCommon').controller('wkCommon.appCtrl',
	function ($scope, $rootScope, $location, $route, $window) {
		$scope.loginIn = false; 
		$scope.isActive = function (viewLocation) {
		     var active = (viewLocation === $location.path());
		     return active;
		};
		console.log("wkCommon- wkCommon.appCtrl load.")
	});
