
'use strict';

var wkCommon = angular.module('wkSuperstar', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/', {
		templateUrl: 'app/home',
		controller: 'HomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/selfPage', {
		templateUrl: 'app/users/selfHomePage',
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

