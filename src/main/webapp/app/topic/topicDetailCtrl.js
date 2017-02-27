'use strict';

angular.module('wkTopic').controller('TopicDetailCtrl',
		function ($scope, $rootScope, $location, $http, UserService, DEFAULT_IMG, DEFAULT_PAGE, TINYMCE, LocalStorageService) {

			$scope.tagEmptyWarn = false;
			$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			var currentPage = 1;

			var topicId = $location.search().id;

			$http.get("/topic/" + topicId).success(function (data) {
				$scope.topic = data;
				$scope.hideReadMore = $scope.topic.replys == null || $scope.topic.replys.length < DEFAULT_PAGE.TOPIC_REPLY_PER_PAGE;

			}).error(function () {

			});

			$scope.uploadImgSuccess = function (imgId) {
				if ($scope.content == undefined) {
					$scope.content = '';
				}
				tinyMCE.activeEditor.execCommand('mceInsertContent', false, '<img style="max-width:100%" src="/img/' + imgId + '" />');
			}

			$scope.tinymceOptions1 = {
				resize: true,
				menubar: false,
				statusbar: false,
				height: 350,
				content_style: ".mce-content-body {font-size:13px;}",
				width: '100%',
				language_url: TINYMCE.LANG_URL,
				plugins: ["link", "code", "textcolor"],
				toolbar: "undo redo | bold italic subscript superscript strikethrough underline | bullist numlist | forecolor backcolor | link mybutton | removeformat",
				setup: function (editor) {
					editor.addButton('mybutton', {
						type: 'button',
						title: 'Insert image',
						icon: 'image',
						id: 'mybutton'
					});
					editor.on('init', function (e) {
						$("#mybutton").on("click", function () {
							$scope.$broadcast('event:upload:topic:img');
						});
					});
				}
			}

			var loadReplyByPage = function (page) {
				$http.get("/reply/list/" + topicId + "/" + page).success(function (data) {
					if (data.length > 0) {
						_.each(data, function (reply) {
							$scope.topic.replys.push(reply)
						})
						$scope.hideReadMore = data.length < DEFAULT_PAGE.TOPIC_REPLY_PER_PAGE;
					} else {
						$scope.hideReadMore = true;
					}

				}).error(function () {
					$scope.hideReadMore = true;
				})
			}

			$scope.replyQuestion = function () {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
				}
				var parm = {
					"user_id": UserService.getCurrent().id,
					"topic_id": topicId,
					"content": $scope.content
				};
				$http.put("/reply", parm).success(function (data) {
					currentPage = 1;
					$scope.topic.replys = [];
					$scope.content = '';
					loadReplyByPage(currentPage)
					$scope.hideReadMore = false;
				})
			}

			$scope.loadMore = function () {
				currentPage = currentPage + 1;
				loadReplyByPage(currentPage);
			}


			//-------------
			$scope.request = {};

			$scope.loadComments = function (cacheData, paging) {
				$http.get("/comment/list/" + cacheData.reply.id + '/' + paging.currentPage).success(function (data) {
					cacheData.commentLists.comments = data.comments;
					cacheData.commentLists.paging = data.paging;
				});
			}
			$scope.expandCommentLists = function (replyDetail) {
				$(".topic-comment-lists .popover.topic-comment-list-" + replyDetail.reply.id).toggle()
				replyDetail.loadCommentsSuccess = false;
				$http.get("/comment/list/" + replyDetail.reply.id + '/' + 1).success(function (data) {
					replyDetail.loadCommentsSuccess = true;
					replyDetail.commentLists = data;
				})
			}
			$scope.createComment = function (replyDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				$http.put("/comment", {
					"user_id": UserService.getCurrent().id,
					"reply_id": replyDetail.reply.id,
					"content": $scope.request.commentContent
				}).success(function () {
					$scope.loadComments(replyDetail, {"currentPage": 1});
					$scope.request.commentContent = '';
					replyDetail.commentCount += 1;
				});
			}

			$scope.fllowTopic = function (topicDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (topicDetail.currentFollowed) {
					$http.post("/follow/topic/disable/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function (data) {
						topicDetail.followCount = topicDetail.followCount - 1;
						topicDetail.currentFollowed = false;
					});

				} else {
					$http.post("/follow/topic/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function (data) {
						topicDetail.followCount += 1;
						topicDetail.currentFollowed = true;
					});
				}
			}

			$scope.calcelComment = function () {
				$scope.request.commentContent = ''
			}
			$scope.likeReply = function (replyDetail) {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
					return;
				}
				if (replyDetail.currentLiked) {
					$http.post("/like/reply/disable/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function (data) {
						replyDetail.likeCount -= 1;
						replyDetail.currentLiked = false;
					});

				} else {
					$http.post("/like/reply/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function (data) {
						replyDetail.likeCount += 1;
						replyDetail.currentLiked = true;
					});
				}
			}
		});
