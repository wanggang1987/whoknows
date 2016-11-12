'use strict';

angular.module('wkSuperstar').controller('VipDetailPageCtrl',
	function ($scope, $rootScope, $location, $route, $routeParams, $http, UserService, DEFAULT_IMG) {
		console.log("wkSuperstar- VipPageCtrl load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		$scope.currentVip = {"userID" : $routeParams.id}
	});
