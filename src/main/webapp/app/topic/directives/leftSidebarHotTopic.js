
'use strict';

angular.module('wkTopic').directive('hotTopicSiderbar', function ($location, $log) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/leftSidebarHotTopic.html',
		replace: true,
		scope: {
			topic: '='
		},
		link: function (scope) {
			console.log("wkTopic->directives->hotTopicSiderbar: topic: " + scope.topic);
		}
	};

});