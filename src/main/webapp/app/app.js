'use strict';

angular.module('WhoKnows', ['ngResource', 'ngCookies', 'ngRoute', 'ngSanitize', 
	'wkCommon',
	'wkTopic',
	'wkSuperstar',
	'wkSearchResult',
	'wkLogin',
	'wkRegist',
	'wkSetting',
	'wkTag'
]);

angular.module('WhoKnows').config(['$httpProvider','$locationProvider', function ($httpProvider, $locationProvider) {
		$httpProvider.interceptors.push('permissionCheckInterceptor');
	}
])
.constant( 'DEFAULT_IMG', { 'PEOPLE_NO_IMG' : '../../images/people-no-img-default.png',
							'TAG_NO_IMG' : '../../images/tag-no-img-default.png'} )
.constant( 'DEFAULT_PAGE', { 'TOPIC_PER_PAGE' : 10,
	'TOPIC_REPLY_PER_PAGE' : 10} );