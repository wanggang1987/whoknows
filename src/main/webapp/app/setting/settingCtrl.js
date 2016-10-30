'use strict';

angular.module('wkCommon').controller('SettingCtrl',
	function ($scope, $rootScope, $location, UserService, $http) {
		console.log("wkCommon- SettingCtrl load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		$http.get("/user/" + UserService.getCurrent().id).then(function(data){
			$scope.setUser = data.data;
		})
		$scope.resetPasswd = function(){
			$scope.$broadcast('event:resetPassword');
		}
		//---------------base info
		$scope.showSaveBaseInfoSuccess = false;
		$scope.showSaveBaseInfoError = false;
		
		$scope.saveBaseInfo = function(){
			$http.post("/user", $scope.setUser).success(function(){
				$scope.showSaveBaseInfoSuccess = true;
				$scope.showSaveBaseInfoError = false;
			}).error(function(){
				$scope.showSaveBaseInfoSuccess = false;
				$scope.showSaveBaseInfoError = true;
			});
			
		}
		$scope.closeBaseInfoSuccessPanel = function(){
			$scope.showSaveBaseInfoSuccess = false;
		}
		
		$scope.closeBaseInfoWarnPanel = function(){
			$scope.showSaveBaseInfoError = false;
		}
		
		//---------------pass info
		$scope.showSavePassInfoSuccess = false;
		$scope.showSavePassInfoError = false;
		$scope.savePassInfo = function(){
			$http.post("/user", $scope.setUser).success(function(){
				$scope.showSavePassInfoSuccess = true;
				$scope.showSavePassInfoError = false;
			}).error(function(){
				$scope.showSavePassInfoSuccess = false;
				$scope.showSavePassInfoError = true;
			});
		}
		$scope.closePassInfoSuccessPanel = function(){
			$scope.showSavePassInfoSuccess = false;
		}
		
		$scope.closePassInfoWarnPanel = function(){
			$scope.showSavePassInfoError = false;
		}
		
	});
