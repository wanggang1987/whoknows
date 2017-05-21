'use strict';

angular.module('WhoKnows', ['ngResource', 'ngCookies', 'ngRoute', 'ngSanitize', 'ngFileUpload', 'angular-img-cropper',
	'wkCommon',
	'wkTopic',
	'wkSuperstar',
	'wkSearchResult',
	'wkLogin',
	'wkRegist',
	'wkSetting',
	'wkTag',
	'wkUser',
	'wkAboutUs',
	'wkAdmin'
]);

angular.module('WhoKnows').config(['$httpProvider', '$locationProvider', function ($httpProvider, $locationProvider) {
		$httpProvider.interceptors.push('permissionCheckInterceptor');
	}
])
		.constant('DEFAULT_IMG', {'PEOPLE_NO_IMG': '../../images/people-no-img-default.png',
			'TAG_NO_IMG': '../../images/tag-no-img-default.png'})
		.constant('DEFAULT_PAGE', {'TOPIC_PER_PAGE': 10,
			'TOPIC_REPLY_PER_PAGE': 10})
		.constant('ROLE_TYPE', {'SITE_ADMIN': 'SITE_ADMIN',
			'SITE_USER': 'SITE_USER',
			'SITE_VIP': 'SITE_VIP'})
		.constant('TINYMCE', {'LANG_URL': '../../components/tinymce-lan/zh_CN.js'});