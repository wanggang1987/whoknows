'use strict';

angular.module('wkTopic').controller('TopicDetailCtrl',
	function ($scope, $rootScope, $location, $http, UserService, DEFAULT_IMG) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		
		$scope.tagEmptyWarn = false;
		$scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
		var currentPage = 1;
		
		var topicId = $location.search().id;
		
		
		$http.get("/topic/" + topicId).then(function(data){
			$scope.topic = data.data;
		});
		
		
		$scope.tinymceOptions1 = {
				resize: false,
				menubar: false,
				statusbar: false,
				height: 350,
				plugins: ["image"],
			    file_browser_callback: function(field_name, url, type, win) {
			            if(type=='image') alert(url +"->" + field_name +"->" + type +"->" + win);
			    }
		}
		
		var loadReplyByPage = function(page){
			$http.get("/reply/list/" + topicId + "/" + page).success(function(data){
				console.log(data)
				if(data.length > 0){
					_.each(data, function(reply){
						$scope.topic.replys.push(reply)
					})
				}else{
					$scope.hideReadMore = true;
				}
				
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
			console.log(currentPage)
			loadReplyByPage(currentPage);
		}
	});
