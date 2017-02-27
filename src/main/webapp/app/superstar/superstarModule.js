
'use strict';

var wkCommon = angular.module('wkSuperstar', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/creteVipDoc', {
		templateUrl: 'app/superstar/createVipDoc',
		controller: 'CreateVipDocCtrl',
		data: {
			standalonePage: true
		}
	}).when('/vipDetailPage', {
		templateUrl: 'app/superstar/vipPage',
		controller: 'VipPageCtrl',
		data: {
			standalonePage: true
		}
	}).when('/vipDetail/:id', {
		templateUrl: 'app/superstar/vipDetailPage',
		controller: 'VipDetailPageCtrl',
		data: {
			standalonePage: true
		}
	}).when('/vipPaperDetail/:id', {
		templateUrl: 'app/superstar/vipPaperDetailPage',
		controller: 'vipPaperDetailCtrl',
		data: {
			standalonePage: true
		}
	});
});

