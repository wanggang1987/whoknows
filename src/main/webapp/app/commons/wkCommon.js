'use strict';

angular.module('wkCommon').controller('wkCommon.appCtrl',
	function ($scope, $rootScope, $location, $route, $window, LocalStorageService, UserService) {
		$scope.loginIn = false;
		UserService.initialize().then(function () {
			$scope.loginIn = UserService.isSignedIn();
		});
		
		$rootScope.$on('event:login:success', function () {
			$scope.loginIn = true;
			if (LocalStorageService.get('LastPage')) {
				var lastPage = LocalStorageService.get('LastPage');
				if (lastPage) {
					LocalStorageService.remove('LastPage');
					$location.path(lastPage);
				}
			} else {
				$location.path('/');
			}
		});
		
		$scope.isActive = function (viewLocation) {
		     var active = (viewLocation === $location.path());
		     return active;
		};
		
		$scope.createQuestion = function(){
			$location.path('/creteTopic');
		}
		console.log("wkCommon- wkCommon.appCtrl load.")
	});
