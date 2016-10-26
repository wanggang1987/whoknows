
'use strict';

var wkCommon = angular.module('wkTopic', ['ngRoute']).config(function($routeProvider, $logProvider){
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
	}).when('/error', {
		templateUrl: 'error',
		data: {
			standalonePage: true
		}
	});
});

