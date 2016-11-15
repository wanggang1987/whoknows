'use strict';

angular.module('wkRegist').controller('RegistTagSelectCtrl',
	function ($scope, $rootScope, $location, $http, DEFAULT_IMG, LocalStorageService, UserService) {
		console.log("wkCommon- wkRegist.RegistTagSelectCtrl  load.");
		
		$scope.defaultTagImg = DEFAULT_IMG.TAG_NO_IMG;
		$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
		
		$scope.regist ={
			"tags" : [],
			"vips" : []
		}
		$scope.loginSuccess = false;
		
		function init(){
			$http.get("/hot/recommend").success(function(data){
				$scope.vips = data.vips;
				$scope.tags = data.tags;
			});
		}
		
		$scope.disabledSubmit = function(){
			return !UserService.isSignedIn() || (!_.contains($scope.regist.vips, true)
			&& !_.contains($scope.regist.tags, true) );
		}
		
		$scope.enterWk = function(){
			var user = UserService.getCurrent();
			var parm ={"vips": [],
					"tags":[]};
			
			_.each($scope.vips , function(vip){
				if($scope.regist.vips[vip.userID]){
					parm.vips.push(vip);
				}
			});
			_.each($scope.tags, function(tag){
				if($scope.regist.tags[tag.tagID]){
					parm.tags.push(tag);
				}
			})
			$http.post("/follow/recommend/" + user.id, parm).success(function(){
				$location.path("/")
			})
		}
		
		init();

	});
