'use strict';

angular.module('wkSearchResult').controller('SearchResultCtrl',
		function ($scope, $rootScope, $location, $route, $routeParams, LocalStorageService, $http) {

			var currentPage = 1;
			$scope.topicLists = [];
			$scope.hideReadMore = false;


			function loadTopic() {
				console.log('search result module : key=' + $routeParams.keyWord)
				$http.get("/search/" + currentPage + "?keyWord=" + $routeParams.keyWord).success(function (data) {
					if (data.topicResults != null && data.topicResults.length > 0) {
						_.each(data.topicResults, function (result) {
							$scope.topicLists.push(result);
						})
						currentPage += 1;
					} else {
						$scope.hideReadMore = true;
					}

				}).error(function () {
					$scope.hideReadMore = true;
				});
			}

			$scope.loadMore = function () {
				loadTopic();
			}

			loadTopic();
		});
