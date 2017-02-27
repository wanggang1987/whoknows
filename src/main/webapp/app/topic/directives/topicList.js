
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log, $http, DEFAULT_IMG, UserService, DEFAULT_PAGE, LocalStorageService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList',
		replace: true,
		scope: {
			topicLists: '=',
			loadMoreData: '&',
			hideReadMore: '=',
			refreshFollowCount: '&'
		},
		link: function (scope, element, attr) {
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			scope.defaultPerPage = DEFAULT_PAGE.TOPIC_PER_PAGE
			scope.request = {};
			scope.loadComments = function (cacheData, paging) {
				$http.get("/comment/list/" + cacheData.reply.id + '/' + paging.currentPage).success(function (data) {
					cacheData.commentLists.comments = data.comments;
					cacheData.commentLists.paging = data.paging;
				});
			}
			scope.expandCommentLists = function (replyDetail) {
				element.find(".topic-comment-lists .popover.topic-comment-list-" + replyDetail.reply.id).toggle()
				replyDetail.loadCommentsSuccess = false;
				$http.get("/comment/list/" + replyDetail.reply.id + '/' + 1).success(function (data) {
					replyDetail.loadCommentsSuccess = true;
					replyDetail.commentLists = data;
				})
			}
			scope.createComment = function (replyDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				$http.put("/comment", {
					"user_id": UserService.getCurrent().id,
					"reply_id": replyDetail.reply.id,
					"content": scope.request.commentContent
				}).success(function () {
					scope.loadComments(replyDetail, {"currentPage": 1});
					scope.request.commentContent = '';
					replyDetail.commentCount += 1;
				});
			}

			scope.fllowTopic = function (topicDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (topicDetail.currentFollowed) {
					$http.post("/follow/topic/disable/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function (data) {
						topicDetail.followCount = topicDetail.followCount > 0 ? topicDetail.followCount - 1 : 0;
						topicDetail.currentFollowed = false;
						if (scope.refreshFollowCount) {
							scope.refreshFollowCount();
						}

					});

				} else {
					$http.post("/follow/topic/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function (data) {
						topicDetail.followCount += 1;
						topicDetail.currentFollowed = true;
						if (scope.refreshFollowCount) {
							scope.refreshFollowCount();
						}
					});
				}
			}

			scope.calcelComment = function () {
				scope.request.commentContent = ''
			}

			scope.likeReply = function (replyDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (replyDetail.currentLiked) {
					$http.post("/like/reply/disable/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function (data) {
						replyDetail.likeCount = replyDetail.likeCount > 0 ? replyDetail.likeCount - 1 : 0;
						replyDetail.currentLiked = false;
					});

				} else {
					$http.post("/like/reply/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function (data) {
						replyDetail.likeCount += 1;
						replyDetail.currentLiked = true;
					});
				}
			}

			scope.loadMore = function () {
				scope.loadMoreData();
			}

			scope.toggelExpandReply = function (reply) {
				if (reply.shortContent != undefined && reply.shortContent != null) {
					reply.shortContent.fullAble = !reply.shortContent.fullAble;
				}
			}

		}
	};

});