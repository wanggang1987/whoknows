'use strict';

angular.module('wkAboutUs').controller('AboutUsCtrl',
		function ($scope, $http) {
			$http.get("/properties/aboutUs").success(function (data) {
				$scope.content = data.content;
			})
		});
