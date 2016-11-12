'use strict';

angular.module('wkSuperstar').controller('VipPageCtrl',
	function ($scope, $rootScope, $location, $route, $http, UserService, DEFAULT_IMG) {
		console.log("wkSuperstar- VipPageCtrl load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		
		$scope.noVipWarn = false;
		$scope.hideReadMore = false;
		
		var init = function(){
			$http.get("/user/follow/list/vip").success(function(data){
				if(data != null && data.length > 0){
					$scope.vips = data;
					$scope.currentVip = $scope.vips[0];
				}else{
					$scope.noVipWarn = true;
					$scope.hideReadMore = true;
				}
			}).error(function(data){
				$scope.noVipWarn = true;
				$scope.hideReadMore = true;
			});
		}
		$scope.loadVipDetail = function(vip){
			$scope.currentVip = vip;
		}
		init();
	});
