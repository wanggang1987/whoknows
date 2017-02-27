
'use strict';

angular.module('wkUser').directive('wkUserProfile', function ($location, $log, $http, DEFAULT_IMG) {

	return {
		restrict: 'AE',
		templateUrl: 'app/users/directives/userProfileDirectie',
		replace: true,
		scope: {
			user: '=',
			enableEditUser: '='
		},
		link: function (scope) {
			var currentPage = 1;
			var dataUrl = "";
			scope.defaultPeropleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			scope.topicLists = [];
			scope.hideReadMore = false;

			scope.loadCountInfo = function () {
				$http.get("/user/home/count/into/" + scope.user.id).success(function (data) {
					scope.contInfo = data;
				}).error(function () {
					scope.contInfo = {"followCount": 0, "createTopicCount": 0, "replyCount": 0}
				});
			}

			scope.loadMore = function () {
				$http.get(dataUrl + currentPage).success(function (data) {
					if (data.topicResults != null && data.topicResults.length > 0) {
						_.each(data.topicResults, function (result) {
							scope.topicLists.push(result);
						})
						currentPage += 1;
					} else {
						scope.hideReadMore = true;
					}
				}).error(function () {
					scope.hideReadMore = true;
				});
			}

			var clearData = function (url) {
				currentPage = 1;
				dataUrl = url;
				scope.topicLists = [];
				scope.hideReadMore = false;
			}

			scope.myFllowTopic = function () {
				clearData("/user/follow/" + scope.user.id + "/");
				scope.loadMore()
			}

			scope.myTopic = function () {
				clearData("/user/topic/" + scope.user.id + "/");
				scope.loadMore();
			}

			scope.myReply = function () {
				clearData("/user/reply/" + scope.user.id + "/");
				scope.loadMore();
			}

			var init = function () {
				if (scope.enableEditUser) {
					$(".self-home-page-picture").hover(function () {
						$('.ProfileAvatarEditor-tip').css('opacity', '100');
					}, function () {
						$('.ProfileAvatarEditor-tip').css('opacity', '0');
					});
					$(".self-home-page-picture").click(function () {
						scope.$broadcast('event:crop:img');
					})
				}

				scope.loadCountInfo();

				scope.myFllowTopic();
			}

			scope.showEditTitle = function () {
				scope.showTitleEdit = true;
			}

			scope.showEditCompany = function () {
				scope.showCompanyEdit = true;
			}
			scope.showEditEdu = function () {
				scope.showEduEdit = true;
			}
			scope.showEditSin = function () {
				scope.showSinEdit = true;
			}
			scope.saveUserInfo = function (editType) {
				if (!scope.enableEditUser) {
					return;
				}
				$http.post("/user", scope.user).then(function () {
					if (editType === 'companyName') {
						scope.showCompanyEdit = false;
					} else if (editType === 'title') {
						scope.showTitleEdit = false;
					} else if (editType === 'education') {
						scope.showEduEdit = false;
					} else if (editType === 'signature') {
						scope.showSinEdit = false;
					}
					editType = false;
				})
			}

			scope.refreshImg = function (imgId) {
				if (!scope.enableEditUser) {
					return;
				}
				scope.user.picture = "/img/" + imgId;
			}

			init();
		}
	};

});