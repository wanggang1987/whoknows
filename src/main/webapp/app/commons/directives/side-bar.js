'use strict';

angular.module('WhoKnows').directive('wkSideBar', function ($location, $window, UserService) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/side-bar',
		scope: {},
		replace: true,
		link: function (scope, elem) {

		}
	};
});