'use strict';

angular.module('wkSuperstar').controller('VipDetailPageCtrl',
		function ($scope, $rootScope, $location, $route, $routeParams, $http, UserService, DEFAULT_IMG, LocalStorageService) {
			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}

			var currentPage = 1;
			$scope.paperLists = [];
			$scope.hideReadMore = false;
			$scope.currentVipDetail = null;


			var initData = function () {
				currentPage = 1;
				$scope.paperLists = [];
				$scope.hideReadMore = false;
				$scope.currentVipDetail = null;
			}

			var loadPaper = function () {
				$http.get("/paper/list/" + $routeParams.id + "/" + currentPage).success(function (data) {
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
				$http.get("/vip/" + $routeParams.id).success(function (data) {
					$scope.currentVipDetail = data;
				});
			}

			$scope.loadMore = function () {
				loadPaper();
			}

			var init = function () {
				loadVipDetail();
				loadPaper();
			}

			init();
		});
