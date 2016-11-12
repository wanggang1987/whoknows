'use strict';

angular.module('wkSuperstar').controller('VipPageCtrl',
	function ($scope, $rootScope, $location, $route, $http, UserService, DEFAULT_IMG) {
		console.log("wkSuperstar- VipPageCtrl load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		$scope.currentVip = null;
		$scope.noVipWarn = false;
		$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
		
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
//		$http.get("/user/follow/vip").success();
		var loadVipInfo = function(id){
			$http.get("/user/" + id).then(function(data){
				$scope.currentVipDetail = data.data;
			})
		}
		
		
		$scope.closeNoVipWarn = function(){
			$scope.noVipWarn = false;
		}
		
		$scope.fllowVip = function(vip){
			if(vip.currentFollowed){
				$http.post("/follow/user/disable/" + UserService.getCurrent().id + "/" + vip.userID).success(function(data){
					vip.followCount = vip.followCount > 0 ? vip.followCount - 1 : 0;
					vip.currentFollowed = false;
				})
			}else{
				$http.post("/follow/user/" + UserService.getCurrent().id + "/" + vip.userID).success(function(data){
					vip.followCount += 1;
					vip.currentFollowed = true;
				})
			}
			
		}
		
		$scope.loadVipDetail = function(vip){
			$scope.currentVip = vip;
			loadVipInfo($scope.currentVip.userID);
		}
	});
