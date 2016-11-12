
'use strict';

angular.module('wkCommon').filter('formateName', function () {

	return function (user) {
		console.log("---")
		console.log(user)
		if(user != null){
			var firstName = user.firstName !== undefined && user.firstName !=null ? user.firstName : '';
			var lastName = user.lastName !== undefined && user.lastName != null ? user.lastName : '';
			if(firstName != '' || lastName != ''){
				return firstName + lastName;
			}else{
				return user.email;
			}
		}else{
			return '';
		}
		return (translated ? translated : value);
	};
});