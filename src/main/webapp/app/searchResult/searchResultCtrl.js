'use strict';

angular.module('wkSearchResult').controller('SearchResultCtrl',
	function ($scope, $rootScope, $location, $route, LocalStorageService, $http ) {

		var currentPage = 1;
		$scope.topicLists = [];
		$scope.hideReadMore = false;
		
		var keyWord = LocalStorageService.get("homeSearchKeyWord");

		function loadTopic(){
			$http.get("/search/" + currentPage + "?keyWord=" + keyWord).success(function(data){
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

		loadTopic();
	});
