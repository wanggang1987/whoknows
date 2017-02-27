'use strict';

angular.module('wkTag').controller('TagDetailCtrl',
		function ($scope, $rootScope, $location, $route, $http, $routeParams, DEFAULT_IMG, UserService, LocalStorageService) {

			var currentPage = 1;
			$scope.topicLists = [];
			$scope.hideReadMore = false;

			function loadTopic() {
				$http.get("/tag/home/" + $routeParams.tagId + "/" + currentPage).success(function (data) {
					if (data.tag != null) {
						$scope.tag = data.tag;
						$scope.tag.tagFollowCount = data.tagFollowCount;
						$scope.tag.currentFollowed = data.currentFollowed;
					}
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

			$scope.fllowTag = function (tag) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (tag.currentFollowed) {
					$http.post("/follow/tag/disable/" + UserService.getCurrent().id + "/" + tag.id).success(function (data) {
						tag.currentFollowed = false;
						tag.tagFollowCount -= 1;
					});
				} else {
					$http.post("/follow/tag/" + UserService.getCurrent().id + "/" + tag.id).success(function (data) {
						tag.currentFollowed = true;
						tag.tagFollowCount += 1;
					});
				}

			}

			$scope.loadMore = function () {
				loadTopic();
			}

			var init = function () {
				$scope.defaultTagImg = DEFAULT_IMG.TAG_NO_IMG;
				loadTopic();
			}

			init();
		});
