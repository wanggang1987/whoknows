
'use strict';

angular.module('wkCommon').service('LocalStorageService', function ($window, $cookieStore, $cookies) {

	var localStorage = $window.localStorage;

	var isLocalStorageAvailable = function () {
		var test = 'testLocal';
		try {
			localStorage.setItem(test, test);
			localStorage.removeItem(test);
			return true;
		} catch (e) {
			return false;
		}
	};

	var useCookie = function (options) {
		return (options !== undefined && options.cookie) || !lsAvailable;
	};

	var lsAvailable = isLocalStorageAvailable();

	this.get = function (key, options) {
		var value;
		if (useCookie(options)) {
			if (options.raw) {
				value = $cookies.get(key);
			} else {
				value = $cookieStore.get(key);

			}
		} else {
			value = localStorage[key];
		}
		return value;
	};

	this.put = function (key, value, options) {
		if (useCookie(options)) {
			$cookieStore.put(key, value);
		} else {
			localStorage.setItem(key, value);
		}
	};

	this.remove = function (key) {
		$cookieStore.remove(key);
		if (lsAvailable) {
			localStorage.removeItem(key);
		}
	};
});