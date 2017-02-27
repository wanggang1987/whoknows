
'use strict';

angular.module('wkCommon').factory('permissionCheckInterceptor', function ($q, $rootScope, $location, $injector, LocalStorageService) {
	return {
		'responseError': function (response) {
			var status = response.status;
			if (status === 401 || status === 403) {
				console.log('Access was denied to the resource.');

				var userService = $injector.get('UserService');
				if (!userService.isSignedIn()) {
					if ($location.path() !== '/login') {
						LocalStorageService.put('LastPage', $location.path());
					}
					$location.path('/login');
				} else {
					$location.path('/');
				}
			}
			return $q.reject(response);

		}
	};
});