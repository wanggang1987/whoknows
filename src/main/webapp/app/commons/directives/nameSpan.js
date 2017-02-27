'use strict';

angular.module('wkCommon').directive('wkNameSpan', function ($location, $window, UserService) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/nameSpan',
		replace: true,
		scope: {
			user: '=',
		},
		link: function (scope, elem) {

		}
	};
});