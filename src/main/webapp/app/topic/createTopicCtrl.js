'use strict';

angular.module('wkTopic').controller('CreateTopicCtrl',
	function ($scope, $rootScope, $location, UserService, $http, LocalStorageService) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		$scope.tagEmptyWarn = false;
		
		$scope.closeWarnPanel = function(){
			$scope.tagEmptyWarn = false;
		}
		$http.get("/tag/list").then(function(data){
			var html = '';
			_.each(data.data, function(tag){
				html += "<option value='" + tag.value + "'>" + tag.text + "</option>";
			})
			$(".multipleSelect").html(html);
			$('.multipleSelect').fastselect({"maxItems":5,"placeholder":"请选择标签"});
		})

		$scope.tinymceOptions1 = {
			resize: false,
			menubar: false,
			statusbar: false,
			height: 350,
		}
		
		$scope.createQuestion = function(){
			var tags = $('.multipleSelect').val();
			if(tags == undefined || tags == null || tags.length == 0){
				$scope.tagEmptyWarn = true;
				return;
			}
			if(!UserService.isSignedIn()){
				LocalStorageService.put('LastPage', '/creteTopic', true);
				$location.path("/login");
			}
			var topic = {
				tagId: $('.multipleSelect').val(),
				user_id : UserService.getCurrent().id,
				title : $scope.title,	
				content: $scope.content
			};
			
			$http.put("/topic", topic).success(function(){
				console.log("create topic success");
				$location.path("/");
			}).error(function(){
				console.log("crete topic error")
			});
		}
	});
