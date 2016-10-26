
'use strict';

var wkCommon = angular.module('wkCommon', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/', {
		templateUrl: 'app/home.html',
		controller: 'HomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/selfPage', {
		templateUrl: 'app/users/selfHomePage.html',
		controller: 'SelfHomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/topic', {
		templateUrl: 'app/topic/topicPage.html',
		controller: 'TopicCtrl',
		data: {
			standalonePage: true
		}
	}).when('/setting', {
		templateUrl: 'app/setting/settingPage.html',
		controller: 'SettingCtrl',
		data: {
			standalonePage: true
		}
	}).when('/error', {
		templateUrl: 'error',
		data: {
			standalonePage: true
		}
	}).otherwise({
        redirectTo: '/'
    });
});

