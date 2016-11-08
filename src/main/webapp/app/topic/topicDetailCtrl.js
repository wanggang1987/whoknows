'use strict';

angular.module('wkTopic').controller('TopicDetailCtrl',
	function ($scope, $rootScope, $location, $http, UserService, DEFAULT_IMG, DEFAULT_PAGE) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		
		$scope.tagEmptyWarn = false;
		$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
		var currentPage = 1;
		
		var topicId = $location.search().id;
		
		$http.get("/topic/" + topicId).success(function(data){
			$scope.topic = data;
			$scope.hideReadMore = $scope.topic.replys == null || $scope.topic.replys.length < DEFAULT_PAGE.TOPIC_REPLY_PER_PAGE;
			
		}).error(function(){
			
		});
		
		$scope.tinymceOptions1 = {
				resize: false,
				menubar: false,
				statusbar: false,
				height: 200
//				plugins: ["image"],
//			    file_browser_callback: function(field_name, url, type, win) {
//			            if(type=='image') alert(url +"->" + field_name +"->" + type +"->" + win);
//			    }
		}
		
		var loadReplyByPage = function(page){
			$http.get("/reply/list/" + topicId + "/" + page).success(function(data){
				console.log(data)
				if(data.length > 0){
					_.each(data, function(reply){
						$scope.topic.replys.push(reply)
					})
					$scope.hideReadMore = data.length < DEFAULT_PAGE.TOPIC_REPLY_PER_PAGE;
				}else{
					$scope.hideReadMore = true;
				}
				
			}).error(function(){
				$scope.hideReadMore = true;
			})
		}
		
		$scope.replyQuestion = function(){
			if(!UserService.isSignedIn()){
				$location.path("/login");
			}
			var parm ={
				"user_id" : UserService.getCurrent().id,
				"topic_id" : topicId, 
				"content" : $scope.content
			};
			$http.put("/reply", parm).success(function(data){
				currentPage = 1;
				$scope.topic.replys = [];
				$scope.content = '';
				loadReplyByPage(currentPage)
				$scope.hideReadMore = false;
			})
		}
		
		$scope.loadMore = function(){
			currentPage = currentPage + 1;
			loadReplyByPage(currentPage);
		}
		
		
		//-------------
		$scope.request ={};
		
		$scope.loadComments = function(cacheData, paging){
			console.log("-->>>>>>>>topic id:   reply-id:");
			console.log(cacheData)
			console.log(paging)
			$http.get("/comment/list/" + cacheData.reply.id + '/' + paging.currentPage).success(function(data){
				cacheData.commentLists.comments = data.comments;
				cacheData.commentLists.paging = data.paging;
				console.log(cacheData.commentLists.paging);
			});
		}
		$scope.expandCommentLists = function(replyDetail){
			$(".topic-comment-lists .popover.topic-comment-list-" + replyDetail.reply.id).toggle()
			replyDetail.loadCommentsSuccess = false;
			$http.get("/comment/list/" + replyDetail.reply.id + '/' + 1).success(function(data){
				replyDetail.loadCommentsSuccess = true;
				replyDetail.commentLists = data;
			})
		}
		$scope.createComment = function(replyDetail){
			if(!UserService.isSignedIn()){
				$location.path("/login");
				return;
			}
			$http.put("/comment", {
				"user_id": UserService.getCurrent().id,
				"reply_id": replyDetail.reply.id,
				"content": $scope.request.commentContent
			}).success(function(){
				$scope.loadComments(replyDetail, {"currentPage" : 1});
				$scope.request.commentContent = '';
			});
		}
		
		$scope.fllowTopic = function(topicDetail){
			if(!UserService.isSignedIn()){
				$location.path("/login");
				return;
			}
			if(topicDetail.cancelFllow){
				topicDetail.followCount = topicDetail.followCount - 1;
				topicDetail.cancelFllow = false;
			}else{
				$http.post("/follow/topic/" + UserService.getCurrent().id + "/" + topicDetail.topic.id).success(function(data){
					topicDetail.followCount += 1;
					topicDetail.cancelFllow = true;
				});
			}
		}
		
		$scope.calcelComment = function(){
			$scope.request.commentContent = ''
		}
		$scope.likeReply = function(replyDetail){
			if(!UserService.isSignedIn()){
				$location.path("/login");
				return;
			}
			if(replyDetail.cancelLike){
				replyDetail.likeCount -= 1;
				replyDetail.cancelLike = false;
			}else{
				$http.post("/like/reply/" + UserService.getCurrent().id + "/" + replyDetail.reply.id).success(function(data){
					replyDetail.likeCount += 1;
					replyDetail.cancelLike = true;
				});
			}
		}
	});
