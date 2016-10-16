
'use strict';

var wkCommon = angular.module('wkSuperstar', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/', {
		templateUrl: 'app/home.html',
		controller: 'HomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/selfPage', {
		templateUrl: 'app/selfHomePage/selfHomePage.html',
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

