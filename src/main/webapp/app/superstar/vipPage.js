'use strict';

angular.module('wkSuperstar').controller('VipPageCtrl',
		function ($scope, $rootScope, $location, $route, $http, UserService, DEFAULT_IMG, LocalStorageService) {
			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}

			$scope.noVipWarn = false;
			var currentPage = 1;
			$scope.paperLists = [];
			$scope.hideReadMore = false;
			$scope.currentVip = null;
			$scope.currentVipDetail = null;


			var initData = function () {
				currentPage = 1;
				$scope.paperLists = [];
				$scope.hideReadMore = false;
				$scope.currentVip = null;
				$scope.currentVipDetail = null;
			}

			var loadPaper = function () {
				$http.get("/paper/list/" + $scope.currentVip.userID + "/" + currentPage).success(function (data) {
					if (data.papers != null && data.papers.length > 0) {
						_.each(data.papers, function (paper) {
							$scope.paperLists.push(paper);
						});
						currentPage += 1;
						$scope.hideReadMore = data.papers.length < data.paging.perPage;
					} else {
						$scope.hideReadMore = true;
					}
				}).error(function (data) {
					$scope.hideReadMore = true;
				})
			}

			var loadVipDetail = function () {
				$http.get("/vip/" + $scope.currentVip.userID).success(function (data) {
					$scope.currentVipDetail = data;
				});
			}

			var init = function () {
				$http.get("/user/follow/list/vip").success(function (data) {
					if (data != null && data.length > 0) {
						$scope.vips = data;
						$scope.currentVip = $scope.vips[0];
						loadVipDetail();
						loadPaper();
					} else {
						$scope.noVipWarn = true;
						$scope.hideReadMore = true;
					}
				}).error(function (data) {
					$scope.noVipWarn = true;
					$scope.hideReadMore = true;
				});
			}

			$scope.loadMore = function () {
				loadPaper();
			}
			$scope.loadVipDetail = function (vip) {
				initData();
				$scope.currentVip = vip;
				loadVipDetail();
				loadPaper();
			}

			init();
		});
