'use strict';

angular.module('wkRegist').controller('RegistTagSelectCtrl',
	function ($scope, $rootScope, $location, $http, DEFAULT_IMG, LocalStorageService, UserService) {
		console.log("wkCommon- wkRegist.RegistTagSelectCtrl  load.");
		$scope.regist ={
			"tags" : [],
			"vips" : []
		}
		$scope.loginSuccess = false;
		
		function init(){
			var loginInfo ={
					"username" : $rootScope.regist.userName,
					"password": $rootScope.regist.s
				}
				$http.post('/login', loginInfo).success(function (data) {
					LocalStorageService.put("userName", data.username, true); 
					LocalStorageService.put("userId", data.id, true); 
					UserService.initialize(true).then(function () {
						$scope.loginSuccess = true;
					});
					
				})
			
			$http.get("/hot/recommend").success(function(data){
				$scope.vips = data.vips;
				$scope.tags = data.tags;
				
				_.each($scope.vips, function(vip){
					if(vip.picture == undefined || vip.picture == null){
						vip.picture = DEFAULT_IMG.PEOPLE_NO_IMG;
					}
				})
				
				_.each($scope.tags, function(tag){
					if(tag.picture == undefined || tag.picture == null){
						tag.picture = DEFAULT_IMG.TAG_NO_IMG;
					}
				})
			});
		}
		
		$scope.enterWk = function(){
			if($scope.loginSuccess){
				var user = UserService.getCurrent();
				var parm ={"vips": [],
						"tags":[]};
				
				_.each($scope.vips , function(vip){
					if($scope.regist.vips[vip.vipID]){
						parm.vips.push(vip);
					}
				});
				_.each($scope.tags, function(tag){
					if($scope.regist.tags[tag.tagID]){
						parm.tags.push(tag);
					}
				})
				$http.post("/follow/recommend/" + user.id, parm).success(function(){
					$rootScope.$broadcast('event:login:success');
				})
			}
		}
		
		init();

	});
