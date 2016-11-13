
'use strict';

var wkCommon = angular.module('wkCommon', ['ngRoute', 'ngFileUpload']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/selfPage', {
		templateUrl: 'app/users/selfHomePage',
		controller: 'SelfHomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/topic', {
		templateUrl: 'app/topic/topicPage',
		controller: 'TopicCtrl',
		data: {
			standalonePage: true
		}
	}).when('/setting', {
		templateUrl: 'app/setting/settingPage',
		controller: 'SettingCtrl',
		data: {
			standalonePage: true
		}
	}).when('/contact', {
		templateUrl: 'app/contact/contact',
	}).when('/error', {
		templateUrl: 'error',
		data: {
			standalonePage: true
		}
	}).otherwise({
        redirectTo: '/topic'
    });
});

