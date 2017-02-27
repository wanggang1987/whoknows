
'use strict';

angular.module('wkCommon').filter('formateName', function () {

	return function (user) {
		if (user != null) {
			var fullName = user.name;
			var firstName = user.firstName !== undefined && user.firstName != null ? user.firstName : '';
			var lastName = user.lastName !== undefined && user.lastName != null ? user.lastName : '';
			if (fullName !== undefined && fullName != null) {
				return fullName;
			} else if (firstName != '' || lastName != '') {
				return lastName + firstName;
			} else {
				return user.email;
			}
		} else {
			return '';
		}
	};
});