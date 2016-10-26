
'use strict';

var wkCommon = angular.module('wkRegist', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/regist', {
		templateUrl: 'app/registration/registPage.html',
		controller: 'RegistCtrl',
		data: {
			standalonePage: true
		}
	});
});

