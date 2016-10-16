
'use strict';

angular.module('wkSuperstar').directive('hotSuperstarSiderbar', function ($location, $log) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/leftSidebarHotSuperstar.html',
		replace: true,
		scope: {
			star: '='
		},
		link: function (scope) {
			console.log("wkSuperStar->directives->hotSuperStarSiderbar: star: " + scope.star);
		}
	};

});