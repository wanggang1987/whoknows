'use strict';

angular.module('wkCommon').filter('to_trusted', ['$sce', function ($sce) {
		return function (text) {
			return $sce.trustAsHtml(text);
		}
	}])