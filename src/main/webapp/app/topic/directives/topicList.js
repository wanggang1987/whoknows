
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log, $http, LocalStorageService, DEFAULT_IMG) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList',
		replace: true,
		scope: {
			topic: '='
		}, 
		link: function (scope, element, attr) {
			
			function loadTopic(page, keyWord){
				$http.get("/search/" + page + "?keyWord=" + keyWord).then(function(data){
					scope.topicLists = data.data.topicResults;
					_.each(scope.topicLists, function(result){
						if(result.topic.content.length > 100){
							result.topic.displayContent = result.topic.content.substr(0, 100) + "...";
							result.topic.commentListsExpandAble = true;
						}else{
							result.topic.displayContent = result.topic.content;
							result.topic.commentListsExpandAble = false;
						}
						
						if(result.topicUser.picture == null){
							result.topicUser.picture = DEFAULT_IMG.PEOPLE_NO_IMG;
						}
					})
				});
			}
			
			
			
			
			//可以点击 显示全部
			scope.expandTopicContentAble = true;
			if(LocalStorageService.get("homeSearchKeyWord") != undefined 
					&& LocalStorageService.get("homeSearchKeyWord") != null){
				loadTopic(1, LocalStorageService.get("homeSearchKeyWord"));
			}else{
				loadTopic(1, 'a');
			}
			
			scope.expandTopicContent = function(topic){
				scope.expandTopicContentAble = false;
				result.topic.displayContent = topic.content;
			}
			scope.collapseTopicContent = function(topic){
				scope.expandTopicContentAble = true;
				topic.topicContent = topic.content.substr(0, 100) +"...";
			}
			
			scope.loadComments = function(topic){
				console.log("-->>>>>>>>topic id:" + topic +"  reply-id:" );
				
			}
			scope.expandCommentLists = function(topic){
				element.find(".topic-comment-lists .popover.topic-comment-list-" + topic.id).toggle()
				topic.loadCommentsSuccess = false;
				
				topic.commentLists = {
				   "paging":{"currentPage":1, "perPage":10, "totalCount":1117},
				     "data": [{
				    "id": 1,
				    "author":{"user_id": 7,
					    "firstName": "wwww1"},
				    "content": "测试评论1",
				    "action": "pending",
				    "rank": 0,
				    "create_time": 1477732412000,
				    "update_time": 1477732412000
				  },{
					    "id": 2,
					    "author":{"user_id": 7,
						    "firstName": "wwww2"},
					    "content": "测试评论2",
					    "action": "pending",
					    "rank": 0,
					    "create_time": 1477732412000,
					    "update_time": 1477732412000
					  },{
						    "id": 1,
						    "author":{"user_id": 7,
							    "firstName": "wwww2"},
						    "content": "测试评论3",
						    "action": "pending",
						    "rank": 0,
						    "create_time": 1477732412000,
						    "update_time": 1477732412000
						  }]
					};
				_.each(topic.commentLists.data, function(comment){
					if(comment.author.img == undefined || comment.author.img == null){
						comment.author.img = DEFAULT_IMG.PEOPLE_NO_IMG;
					}
				});
				topic.loadCommentsSuccess = true;
			}
			
			console.log("wkTopic->directives->topicList: topic: " + scope.topic);
		}
	};

});