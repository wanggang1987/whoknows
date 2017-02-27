'use strict';

angular.module('wkUser').controller('UserProfileCtrl',
		function ($scope, $rootScope, $location, $route, $http, $routeParams) {
			var init = function () {
				$http.get("/user/" + $routeParams.id).then(function (data) {
					$scope.visitUser = data.data;
				})
			}

			init();


		});
