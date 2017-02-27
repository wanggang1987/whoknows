
'use strict';

angular.module('wkTag').directive('hotTagSiderbar', function ($location, UserService, $http, DEFAULT_IMG, LocalStorageService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/tags/directives/hotTagSidbar',
		replace: true,
		scope: {
		},
		link: function (scope) {
			scope.currentPage = 1;
			scope.lastPage = false;
			scope.defaultPeopleIMg = DEFAULT_IMG.TAG_NO_IMG;
			var getTagLists = function (page, keyWord) {
				var tagListUrl = "/hot/tag/" + page;
				if (keyWord != undefined && keyWord != null && $.trim(keyWord) != '') {
					tagListUrl = tagListUrl + "?keyWord=" + keyWord;
				}

				$http.get(tagListUrl).success(function (data) {
					scope.tags = data;
					scope.lastPage = scope.tags.length < 5;
				});
			}

			getTagLists(1);

			scope.searchTag = function () {
				scope.currentPage = 1;
				getTagLists(1, scope.hotTagSearchKeyWord);

			}

			scope.prePage = function () {
				scope.currentPage = scope.currentPage <= 1 ? 1 : scope.currentPage - 1;
				getTagLists(scope.currentPage, scope.hotTagSearchKeyWord);
			}

			scope.nextPage = function () {
				scope.currentPage = scope.currentPage + 1;
				getTagLists(scope.currentPage, scope.hotTagSearchKeyWord);
			}

			scope.fllowTag = function (tag) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (tag.currentFollowed) {
					$http.post("/follow/tag/disable/" + UserService.getCurrent().id + "/" + tag.tagID).success(function (data) {
						tag.currentFollowed = false;
						tag.followCount -= 1;
					});
				} else {
					$http.post("/follow/tag/" + UserService.getCurrent().id + "/" + tag.tagID).success(function (data) {
						tag.currentFollowed = true;
						tag.followCount += 1;
					});
				}

			}

		}
	};

});