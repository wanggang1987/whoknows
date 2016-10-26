'use strict';

angular.module('wkCommon').controller('SettingCtrl',
	function ($scope, $rootScope, $location) {
		console.log("wkCommon- SettingCtrl load.")
		
		$scope.resetPasswd = function(){
			$scope.$broadcast('event:resetPassword');
		}
	});
