
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log, $http, LocalStorageService, DEFAULT_IMG, UserService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList',
		replace: true,
		scope: {
			topic: '='
		}, 
		link: function (scope, element, attr) {
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			scope.request ={};
			
			function loadTopic(page, keyWord){
				$http.get("/search/" + page + "?keyWord=" + keyWord).then(function(data){
					scope.topicLists = data.data.topicResults;
					console.log(scope.topicLists)
				});
			}
			
			if(LocalStorageService.get("homeSearchKeyWord") != undefined 
					&& LocalStorageService.get("homeSearchKeyWord") != null){
				loadTopic(1, LocalStorageService.get("homeSearchKeyWord"));
			}else{
				loadTopic(1, 'd');
			}
			
			scope.loadComments = function(cacheData, paging){
				console.log("-->>>>>>>>topic id:   reply-id:");
				console.log(cacheData)
				console.log(paging)
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
				});
			}
			
		}
	};

});