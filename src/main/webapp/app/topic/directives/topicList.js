
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log, $http, DEFAULT_IMG, UserService, DEFAULT_PAGE) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList',
		replace: true,
		scope: {
			topicLists : '=',
			loadMoreData : '&',
			hideReadMore : '=',
			refreshFollowCount : '&'
		}, 
		link: function (scope, element, attr) {
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			scope.defaultPerPage = DEFAULT_PAGE.TOPIC_PER_PAGE
			scope.request ={};
			
			scope.loadComments = function(cacheData, paging){
				console.log("-->>>>>>>>topic id:   reply-id:");
				$http.get("/comment/list/" + cacheData.reply.id + '/' + paging.currentPage).success(function(data){
					cacheData.commentLists.comments = data.comments;
					cacheData.commentLists.paging = data.paging;
					console.log(cacheData.commentLists.paging);
				});
			}
			scope.expandCommentLists = function(replyDetail){
				element.find(".topic-comment-lists .popover.topic-comment-list-" + replyDetail.reply.id).toggle()
				replyDetail.loadCommentsSuccess = false;
				$http.get("/comment/list/" + replyDetail.reply.id + '/' + 1).success(function(data){
					replyDetail.loadCommentsSuccess = true;
					replyDetail.commentLists = data;
				})
			}
			scope.createComment = function(replyDetail){
				if(!UserService.isSignedIn()){
					$location.path("/login");
					return;
				}
				$http.put("/comment", {
					"user_id": UserService.getCurrent().id,
					"reply_id": replyDetail.reply.id,
					"content": scope.request.commentContent
				}).success(function(){
					scope.loadComments(replyDetail, {"currentPage" : 1});
					scope.request.commentContent = '';
					replyDetail.commentCount += 1;
				});
			}
			
			scope.fllowTopic = function(topicDetail){
				if(!UserService.isSignedIn()){
					$location.path("/login");
					return;
				}
				if(topicDetail.currentFollowed){
					$http.post("/follow/topic/disable/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function(data){
						topicDetail.followCount = topicDetail.followCount - 1;
						topicDetail.currentFollowed = false;
						if(scope.refreshFollowCount){
							scope.refreshFollowCount();
						}
						
					});
					
				}else{
					$http.post("/follow/topic/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function(data){
						topicDetail.followCount += 1;
						topicDetail.currentFollowed = true;
						if(scope.refreshFollowCount){
							scope.refreshFollowCount();
						}
					});
				}
			}
			
			scope.calcelComment = function(){
				scope.request.commentContent = ''
			}
			
			scope.likeReply = function(replyDetail){
				if(!UserService.isSignedIn()){
					$location.path("/login");
					return;
				}
				if(replyDetail.currentLiked){
					$http.post("/like/reply/disable/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function(data){
						replyDetail.likeCount -= 1;
						replyDetail.currentLiked = false;
					});
					
				}else{
					$http.post("/like/reply/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function(data){
						replyDetail.likeCount += 1;
						replyDetail.currentLiked = true;
					});
				}
			}
			
			scope.loadMore = function(){
				scope.loadMoreData();
			}
			
			
		}
	};

});