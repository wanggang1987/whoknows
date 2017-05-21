'use strict';

angular.module('wkSuperstar').controller('vipPaperDetailCtrl',
		function ($scope, $rootScope, $location, $route, $routeParams, $http, UserService, DEFAULT_IMG, LocalStorageService) {
			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}

			var init = function () {
				$http.get("/paper/" + $routeParams.id).success(function (data) {
					$scope.paper = data;
				})
			}

			$scope.likePaper = function (paper) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (paper.currentLiked) {
					$http.post("/like/paper/disable/" + UserService.getCurrent().id + "/" + paper.paper.id).success(function (data) {
						paper.likeCount = paper.likeCount > 0 ? paper.likeCount - 1 : 0;
						paper.currentLiked = false;
					})
				} else {
					$http.post("/like/paper/" + UserService.getCurrent().id + "/" + paper.paper.id).success(function (data) {
						paper.likeCount += 1;
						paper.currentLiked = true;
					})
				}
			}
			init();
		});
