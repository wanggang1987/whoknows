'use strict';

angular.module('wkSuperstar').controller('CreateVipDocCtrl',
	function ($scope, $rootScope, $location, UserService, $http, LocalStorageService, ROLE_TYPE) {
		console.log("wkSuperstar- CreateVipDocCtrl  load.");
		
		var init = function(){
			if(!UserService.isSignedIn() || !UserService.hasPermission(ROLE_TYPE.SITE_VIP)){
				$location.path("/");
				return;
			}
			$scope.tinymceOptions1 = {
					resize: false,
					menubar: false,
					statusbar: false,
					height: 350,
					plugins: ["image"],
				    file_browser_callback: function(field_name, url, type, win) {
				            if(type=='image') alert(url +"->" + field_name +"->" + type +"->" + win);
				    }
				}
		}
		
		$scope.createVipDoc = function(){
			if(!UserService.isSignedIn() || !UserService.hasPermission(ROLE_TYPE.SITE_VIP)){
				$location.path("/");
				return;
			}
			
			$scope.req.user_id = UserService.getCurrent().id;
			$http.put("/paper", $scope.req).success(function(){
				console.log("create topic success");
				$location.path("/");
			}).error(function(){
				console.log("crete topic error")
			});
		}
		
		
		init();
		
	});
