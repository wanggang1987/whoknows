
'use strict';

var wkCommon = angular.module('wkSetting', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/setting', {
		templateUrl: 'app/setting/settingPage.html',
		controller: 'SettingCtrl',
		data: {
			standalonePage: true
		}
	});
});

