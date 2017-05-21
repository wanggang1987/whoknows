
'use strict';

var wkCommon = angular.module('wkSetting', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/setting', {
		templateUrl: 'app/setting/settingPage',
		controller: 'SettingCtrl',
		data: {
			standalonePage: true
		}
	});
});

