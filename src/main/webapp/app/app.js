'use strict';

angular.module('WhoKnows', ['ngResource', 'ngCookies', 'ngRoute', 'ngSanitize', 
	'wkCommon',
	'wkTopic',
	'wkSuperstar',
	'wkSearchResult'
]);

angular.module('WhoKnows').config(['$httpProvider','$locationProvider', function ($httpProvider, $locationProvider) {
		$httpProvider.interceptors.push('permissionCheckInterceptor');
	}
])
