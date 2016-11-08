'use strict';

angular.module('wkTopic').controller('TopicCtrl',
	function ($scope, $rootScope, $location, $route, $http, $window, UserService, LocalStorageService) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
		if(!UserService.isSignedIn()){
			$location.path("/login");
			return;
		}
		
		var keyWord = LocalStorageService.get("homeSearchKeyWord") != undefined && LocalStorageService.get("homeSearchKeyWord") != null ? LocalStorageService.get("homeSearchKeyWord") : 'd';
		var currentPage = 1;
		$scope.topicLists = [];
		$scope.hideReadMore = false;
		
		function loadTopic(page, keyWord){
			$http.get("/search/" + page + "?keyWord=" + keyWord).success(function(data){
				console.log(page + "-" + keyWord )
				console.log(data)
				if(data.topicResults != null && data.topicResults.length > 0){
					_.each(data.topicResults, function(result){
						$scope.topicLists.push(result);
						console.log($scope.topicLists.length)
					})
					currentPage += 1;
				}else{
					$scope.hideReadMore = true;
				}
			}).error(function(){
				$scope.hideReadMore = true;
			});
		}
		
		loadTopic(currentPage, keyWord);
		
		$scope.loadMore = function(){
			loadTopic(currentPage, keyWord);
		}
	});
