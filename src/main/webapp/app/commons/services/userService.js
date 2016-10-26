
'use strict';

angular.module('wkCommon').service('UserService', function ($http, $q, $rootScope, $injector, $log, $interval) {
	var self = this;
	var initSuccess = false;
	var signedIn = false;
	var watch;
	var user;
	var deferredResource = $q.defer();

	this.initialize = function (reInit) {
		var reInit = reInit || false;
		if (reInit) {
			initSuccess = false;
			deferredResource = $q.defer();
		}

		if (!initSuccess) {
			initSuccess = true;
			$http.get('/user/current').success(function (data) {
				if (!data) {
					deferredResource.reject();
					return undefined;
				}
				user = data;
				signedIn = true;

				self.verify();
				watch = $interval(self.verify, 40000); 

				deferredResource.resolve(user);
				console.log("login success" + data)
			}).error(function () {
				signedIn = false;
				user = {};
				deferredResource.reject();
				console.log("login error")
			});
		}
		return deferredResource.promise;
	};

	this.verify = function () {
		return $http.get('/user/current').then(function (resp, code) {
			if (resp.status !== 200) {
				cancelWatchAndBroadcast();
			}
		}, function (resp, code) {
			cancelWatchAndBroadcast();
		});
	};
	
	var cancelWatchAndBroadcast = function () {
		$interval.cancel(watch);
	};
	
	this.getCurrent = function () {
		return user;
	};

	this.isSignedIn = function () {
		return signedIn;
	};

});
