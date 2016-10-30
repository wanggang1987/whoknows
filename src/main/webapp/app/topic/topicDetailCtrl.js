'use strict';

angular.module('wkTopic').controller('TopicDetailCtrl',
	function ($scope, $rootScope, $location, $http) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		$scope.tagEmptyWarn = false;
		
		$http.get("/topic/" + $location.search().id).then(function(data){
			$scope.topic = data.data;
		});
	});
