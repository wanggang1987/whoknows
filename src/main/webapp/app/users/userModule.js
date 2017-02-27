
'use strict';

var wkCommon = angular.module('wkUser', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/selfPage', {
		templateUrl: 'app/users/selfHomePage',
		controller: 'SelfHomeCtrl',
		data: {
			standalonePage: true
		}
	}).when('/user/:id', {
		templateUrl: 'app/users/userProfilePage',
		controller: 'UserProfileCtrl',
		data: {
			standalonePage: true
		}
	});
});

