'use strict';

angular.module('wkTopic').controller('CreateTopicCtrl',
	function ($scope, $rootScope, $location, UserService, $http, LocalStorageService) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		
		$scope.tinymceOptions1 = {
			resize: false,
			menubar: false,
			statusbar: false,
			height: 350,
		}
		
		$scope.createQuestion = function(){
			if(!UserService.isSignedIn()){
				LocalStorageService.put('LastPage', '/creteTopic', true);
				$location.path("/login");
			}
			var topic = {
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
