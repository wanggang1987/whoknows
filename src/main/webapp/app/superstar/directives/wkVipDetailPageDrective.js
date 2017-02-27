
'use strict';

angular.module('wkSuperstar').directive('wkVipDetailPage', function ($location, $log, $http, UserService, DEFAULT_IMG, LocalStorageService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/wkVipDetailPageDrective',
		replace: true,
		scope: {
			currentVipDetail: '=',
			papers: '=',
			hideReadMore: '=',
			loadMoreData: '&'
		},
		link: function (scope) {
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;

			scope.fllowVip = function (vip) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (vip.currentFollowed) {
					$http.post("/follow/user/disable/" + UserService.getCurrent().id + "/" + vip.userDetail.id).success(function (data) {
						vip.followCount = vip.followCount > 0 ? vip.followCount - 1 : 0;
						vip.currentFollowed = false;
					})
				} else {
					$http.post("/follow/user/" + UserService.getCurrent().id + "/" + vip.userDetail.id).success(function (data) {
						vip.followCount += 1;
						vip.currentFollowed = true;
					})
				}
			}

			scope.likePaper = function (paper) {
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
			scope.loadMore = function () {
				scope.loadMoreData();
			}

			scope.toggelExpandReply = function (paper) {
				if (paper.shortContent != undefined && paper.shortContent != null) {
					paper.shortContent.fullAble = !paper.shortContent.fullAble;
				}
			}
		}
	};

});