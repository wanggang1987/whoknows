'use strict';

angular.module('wkRegist').controller('RegistTagSelectCtrl',
		function ($scope, $rootScope, $location, $http, DEFAULT_IMG, LocalStorageService, UserService) {

			$scope.defaultTagImg = DEFAULT_IMG.TAG_NO_IMG;
			$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;

			$scope.regist = {
				"tags": [],
				"vips": []
			}
			$scope.loginSuccess = false;

			function init() {
				$http.get("/hot/recommend").success(function (data) {
					$scope.vips = data.vips;
					$scope.tags = data.tags;
				});
			}

			$scope.disabledSubmit = function () {
				return !UserService.isSignedIn() || (!_.contains($scope.regist.vips, true)
						&& !_.contains($scope.regist.tags, true));
			}

			$scope.enterWk = function () {
				var user = UserService.getCurrent();
				var parm = {"vips": [], "tags": []};
				for (var index in $scope.regist.tags) {
					if ($scope.regist.tags[index]) {
						parm.tags.push({"tagID": index});
					}
				}
				for (var index in $scope.regist.vips) {
					if ($scope.regist.vips[index]) {
						parm.vips.push({"userID": index});
					}
				}
				$http.post("/follow/recommend/" + user.id, parm).success(function () {
					$location.path("/")
				})
			}

			$scope.randomTag = function () {
				$http.get("/hot/rand/tag").success(function (data) {
					$scope.tags = data;
				})

			}

			$scope.randomVip = function () {
				$http.get("/hot/rand/vip").success(function (data) {
					$scope.vips = data;
				})
			}

			init();

		});
