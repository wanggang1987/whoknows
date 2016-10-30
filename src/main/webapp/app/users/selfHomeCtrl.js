'use strict';

angular.module('wkCommon').controller('SelfHomeCtrl',
	function ($scope, $rootScope, $location, $route, $http, UserService) {
		console.log("wkCommon- SelfHomeCtrl load.")
		
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		
		$http.get("/user/" + UserService.getCurrent().id).then(function(data){
			$scope.user = data.data;
		})
		
	});
