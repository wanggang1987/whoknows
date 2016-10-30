
'use strict';

var wkCommon = angular.module('wkTopic', ['ngRoute', 'ui.tinymce']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/creteTopic', {
		templateUrl: 'app/topic/createTopic.html',
		controller: 'CreateTopicCtrl',
		data: {
			standalonePage: true
		}
	}).when('/topicDetail', {
		templateUrl: 'app/topic/topicDetail.html',
		controller: 'TopicDetailCtrl',
		data: {
			standalonePage: true
		}
	})
});

