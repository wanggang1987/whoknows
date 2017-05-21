
'use strict';

var wkCommon = angular.module('wkLogin', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/login', {
		templateUrl: 'app/login/loginPage',
		controller: 'LoginCtrl',
		data: {
			standalonePage: true
		}
	});
});

