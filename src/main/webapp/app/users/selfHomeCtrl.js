'use strict';

angular.module('wkCommon').controller('SelfHomeCtrl',
	function ($scope, $rootScope, $location, $route, $http, UserService) {
		console.log("wkCommon- SelfHomeCtrl load.")
		
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		var currentPage = 1;
		var dataUrl = "";
		$scope.topicLists = [];
		$scope.hideReadMore = false;
		
		
		$http.get("/user/" + UserService.getCurrent().id).then(function(data){
			$scope.user = data.data;
		})
		
		$scope.loadMore = function(){
			$http.get(dataUrl + currentPage).success(function(data){
				if(data != null && data.length > 0){
					_.each(data, function(result){
						$scope.topicLists.push(result);
					})
					currentPage += 1;
				}else{
					$scope.hideReadMore = true;
				}
			}).error(function(){
				$scope.hideReadMore = true;
			});
			console.log("---" + dataUrl + currentPage + " - " + $scope.hideReadMore )
		}
		
		var clearData = function(url){
			currentPage = 1;
			dataUrl = url;
			$scope.topicLists = [];
			$scope.hideReadMore = false;
		}
		
		$scope.myFllowTopic = function(){
			clearData("/user/follow/" + $scope.user.id + "/");
			$scope.loadMore()
		}
		
		$scope.myTopic = function(){
			clearData("/user/topic/" + $scope.user.id + "/" );
			$scope.loadMore();
		}
		
		$scope.myReply = function(){
			clearData("/user/reply/" + $scope.user.id + "/" );
			$scope.loadMore();
		}
		
		$scope.myFllowTopic();
		
	});
