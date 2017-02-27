
'use strict';

angular.module('wkSuperstar').directive('hotSuperstarSiderbar', function ($location, $log, $http, UserService, DEFAULT_IMG, LocalStorageService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/leftSidebarHotSuperstar',
		replace: true,
		scope: {
			star: '='
		},
		link: function (scope) {
			scope.currentPage = 1;
			scope.lastPage = false;
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			var getVipLists = function (page, keyWord) {
				var vipListUrl = "/hot/vip/" + page;
				if (keyWord != undefined && keyWord != null && $.trim(keyWord) != '') {
					vipListUrl = vipListUrl + "?keyWord=" + keyWord;
				}

				$http.get(vipListUrl).then(function (data) {
					scope.vips = data.data;
					scope.lastPage = scope.vips.length < 5;
				});
			}


			getVipLists(1, scope.keyWordVip);

			scope.searchHotVip = function () {
				scope.currentPage = 1;
				getVipLists(1, scope.keyWordVip);
			}

			scope.prePage = function () {
				scope.currentPage = scope.currentPage <= 1 ? 1 : scope.currentPage - 1;
				getVipLists(scope.currentPage, scope.keyWordVip);
			}

			scope.nextPage = function () {
				scope.currentPage = scope.currentPage + 1;
				getVipLists(scope.currentPage, scope.keyWordVip);
			}

			scope.fllowVip = function (vip) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (vip.currentFollowed) {
					$http.post("/follow/user/disable/" + UserService.getCurrent().id + "/" + vip.userID).success(function (data) {
						vip.followCount = vip.followCount > 0 ? vip.followCount - 1 : 0;
						vip.currentFollowed = false;
					})
				} else {
					$http.post("/follow/user/" + UserService.getCurrent().id + "/" + vip.userID).success(function (data) {
						vip.followCount += 1;
						vip.currentFollowed = true;
					})
				}

			}
		}
	};

});