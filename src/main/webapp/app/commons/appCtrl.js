'use strict';

angular.module('wkCommon').controller('wkCommon.appCtrl',
	function ($scope, $rootScope, $cookies, $location, $route, $window, LocalStorageService, UserService, DEFAULT_IMG) {
		$scope.loginIn = false;
		$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
		
		UserService.initialize().then(function () {
			$scope.loginIn = UserService.isSignedIn();
			if($scope.loginIn){
				initUser();
			}
		});
		
		$scope.homeSearch = function(){
			LocalStorageService.put("homeSearchKeyWord", $scope.searchContent, true);
			$location.path('/searchResult');
		}
		
		$rootScope.$on('event:login:success', function () {
			$scope.loginIn = true;
			initUser();
			$rootScope.$on('$locationChangeSuccess', function () {
				$window.location.reload();
			});
			if($cookies.get("firstLogin") != undefined && $cookies.get("firstLogin") != null && $cookies.get("firstLogin") == 'true'){
				$cookies.remove("firstLogin")
				$location.path("/registTagSelect");
			}else if (LocalStorageService.get('LastPage')) {
				var lastPage = LocalStorageService.get('LastPage');
				if (lastPage) {
					LocalStorageService.remove('LastPage');
					$location.path(lastPage);
				}
			} else {
				$location.path('/');
			}
			
		});
		
		$rootScope.$on('event:loginRequired', function () {
			if (!$location.path('/login')) {
				$location.path('/login');
			}
		});
		
		function initUser(){
			$scope.user = UserService.getCurrent();
		}
		$scope.isActive = function (viewLocation) {
		     var active = (viewLocation === $location.path());
		     return active;
		};
		
		$scope.createQuestion = function(){
			$location.path('/creteTopic');
		}
		
		$scope.createVipDoc = function(){
			$location.path('/creteVipDoc');
		}
	});
