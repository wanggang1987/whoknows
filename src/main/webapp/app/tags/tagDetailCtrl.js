'use strict';

angular.module('wkTag').controller('TagDetailCtrl',
	function ($scope, $rootScope, $location, $route, $window, DEFAULT_IMG) {
		console.log("wkTag->TagDetailCtrl")
		$scope.defaultTagImg = DEFAULT_IMG.TAG_NO_IMG;
	});
