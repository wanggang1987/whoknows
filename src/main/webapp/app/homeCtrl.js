'use strict';

angular.module('WhoKnows').controller('HomeCtrl',
		function ($scope, $rootScope, $http, $location,  LocalStorageService) {
			$scope.search = function(){
				$location.path('/searchResult');
			}

		});
