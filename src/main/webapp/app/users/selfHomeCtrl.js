'use strict';

angular.module('wkUser').controller('SelfHomeCtrl',
		function ($scope, $rootScope, $location, $route, $http, UserService, DEFAULT_IMG, LocalStorageService) {

			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}


			var init = function () {

				$http.get("/user/" + UserService.getCurrent().id).then(function (data) {
					$scope.user = data.data;
				})

			}

			init();


		});
