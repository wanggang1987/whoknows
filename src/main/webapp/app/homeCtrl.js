'use strict';

angular.module('WhoKnows').controller('HomeCtrl',
		function ($scope, $rootScope, $http, $location, LocalStorageService) {
			$scope.search = function () {
				LocalStorageService.put("homeSearchKeyWord", $scope.searchContent, true);
				$location.path('/searchResult');

			}

		});
