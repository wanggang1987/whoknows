'use strict';

angular.module('wkTopic').controller('CreateTopicCtrl',
	function ($scope, $rootScope, $location, $route, $window) {
	 	$scope.tinymceOptions1 = {
	                    handle_event_callback : function(e) {
	                        console.log(e);
	                    }
	                };
	                $scope.tinymceModel = "niceMCE"//初始化绑定的值
		console.log("wkCommon- wkTopic.TopicCtrl  load.")
	});
