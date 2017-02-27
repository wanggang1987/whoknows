
'use strict';

angular.module('wkAboutUs', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/contact', {
		templateUrl: 'app/contact/contact',
		controller: 'AboutUsCtrl',
		data: {
			standalonePage: true
		}
	});
});

