'use strict';

angular.module('wkTopic').controller('TopicCtrl',
		function ($scope, $rootScope, $location, $route, $http, $window, UserService, LocalStorageService) {
			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}

			var currentPage = 1;
			$scope.topicLists = [];
			$scope.hideReadMore = false;
			$scope.noTagWarn = false;

			var initData = function () {
				currentPage = 1;
				$scope.topicLists = [];
				$scope.hideReadMore = false;
			}
			var loadTopic = function () {
				$http.get("/tag/home/" + $scope.currentTag.id + "/" + currentPage).success(function (data) {
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

			$scope.loadTopicByTag = function (tag) {
				$scope.currentTag = tag;
				initData();
				loadTopic();
			}


			var init = function () {
				$http.get("/user/follow/list/tag").success(function (data) {
					if (data != null && data.length > 0) {
						$scope.tags = data;
						$scope.loadTopicByTag($scope.tags[0])
					} else {
						$scope.hideReadMore = true;
						$scope.noTagWarn = true;
					}
				}).error(function () {
					$scope.hideReadMore = true;
					$scope.noTagWarn = true;
				});
			}

			init();

		});
