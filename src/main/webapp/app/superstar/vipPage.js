'use strict';

angular.module('wkSuperstar').controller('VipPageCtrl',
	function ($scope, $rootScope, $location, $route, $http, UserService) {
		console.log("wkSuperstar- VipPageCtrl load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		$scope.currentVip = null;
		$scope.noVipWarn = false;
		
		$scope.vips = [
					{
					    "pricture": null,
					    "name": "test-name",
					    "userID": 7,
					    "followCount": 0,
					    "currentFollowed": false
					  },
					  {
						    "pricture": null,
						    "name": "t@T",
						    "userID": 6,
						    "followCount": 0,
						    "currentFollowed": false
						}
		               ];
		$scope.currentVip = $scope.vips[0]
//		$http.get("/user/follow/vip").success();
		
		$scope.closeNoVipWarn = function(){
			$scope.noVipWarn = false;
		}
		
	});
