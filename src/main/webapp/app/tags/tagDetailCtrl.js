'use strict';

angular.module('wkTag').controller('TagDetailCtrl',
	function ($scope, $rootScope, $location, $route, $http, $routeParams, DEFAULT_IMG) {
		console.log("wkTag->TagDetailCtrl")
		
		var currentPage = 1;
		$scope.topicLists = [];
		$scope.hideReadMore = false;
		
		function loadTopic(){
			$http.get("/tag/home/" + $routeParams.tagId + "/" + currentPage).success(function(data){
				$scope.tagFollowCount = data.tagFollowCount
				
				if(data.topicResults != null && data.topicResults.length > 0){
					_.each(data.topicResults, function(result){
						$scope.topicLists.push(result);
					})
					currentPage += 1;
				}else{
					$scope.hideReadMore = true;
				}
			}).error(function(){
				$scope.hideReadMore = true;
			});
		}
		
		$scope.loadMore = function(){
			loadTopic();
		}
		
		var init = function(){
			$scope.defaultTagImg = DEFAULT_IMG.TAG_NO_IMG;
			$http.get("/tag/" + $routeParams.tagId).success(function(data){
				$scope.tag = data;
			})
			loadTopic();
		}
		
		init();
	});
