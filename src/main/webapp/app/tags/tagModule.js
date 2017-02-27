
'use strict';

var wkCommon = angular.module('wkTag', ['ngRoute']).config(function ($routeProvider, $logProvider) {
	$routeProvider.when('/tagDetail/:tagId', {
		templateUrl: 'app/tags/tagDetailPage',
		controller: 'TagDetailCtrl'
	})
});

