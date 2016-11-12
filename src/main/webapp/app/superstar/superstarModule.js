
'use strict';

var wkCommon = angular.module('wkSuperstar', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/creteVipDoc', {
		templateUrl: 'app/superstar/createVipDoc',
		controller: 'CreateVipDocCtrl',
		data: {
			standalonePage: true
		}
	})
});

