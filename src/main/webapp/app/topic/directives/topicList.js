
'use strict';

angular.module('wkTopic').directive('topicList', function ($location, $log, $http, LocalStorageService) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/topicList.html',
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
							result.topicUser.picture = "../../images/people-no-img-default.png";
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
			
			scope.expandCommentLists = function(topic){
				var popverDom = element.find(".topic-comment-lists .popover.topic-comment-list-" + topic.id);
				if(topic.commentListsExpandAble){
					popverDom.css("display","block");
				}else{
					popverDom.css("display","none");
				}
				topic.commentListsExpandAble = !topic.commentListsExpandAble;
				
				
			}
			
			console.log("wkTopic->directives->topicList: topic: " + scope.topic);
		}
	};

});