'use strict';

angular.module('WhoKnows').directive('wkSideBar', function ($location, $window, UserService) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/side-bar.html',
		scope: {},
		replace: true,
		link: function (scope, elem) {
			
			scope.sideBarSetting = function(){
				console.log("<><>")
				UserService.initialize().then(function () {
					if(UserService.isSignedIn()){
						$location.path("/setting");
					}else{
						$location.path("/");
					}
				});
			}
			
		}
	};
});