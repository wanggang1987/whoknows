'use strict';

angular.module('wkSearchResult').controller('SearchResultCtrl',
	function ($scope, $rootScope, $location, $route, $window) {
		$scope.loginIn = true; 
		$scope.isActive = function (viewLocation) {
		     var active = (viewLocation === $location.path());
		     return active;
		};
		console.log("wkCommon- wkSearchResult.TopicCtrl  load.")
	});
