'use strict';

angular.module('WhoKnows').directive('wkSideBar', function ($location, $window) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/side-bar.html',
		scope: {},
		replace: true,
		link: function (scope, elem) {

		}
	};
});