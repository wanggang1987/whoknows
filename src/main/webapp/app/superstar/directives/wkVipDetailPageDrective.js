
'use strict';

angular.module('wkSuperstar').directive('wkVipDetailPage', function ($location, $log, $http, UserService, DEFAULT_IMG) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/wkVipDetailPageDrective',
		replace: true,
		scope: {
			vip: '='
		},
		link: function (scope) {
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			
			
			var loadVipInfo = function(id){
				$http.get("/user/" + id).then(function(data){
					scope.currentVipDetail = data.data;
				})
			}
			
			scope.fllowVip = function(vip){
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
			loadVipInfo(scope.vip.userID);
		}
	};

});