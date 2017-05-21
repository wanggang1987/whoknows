
'use strict';

angular.module('wkAdmin', ['ngRoute']).config(function($routeProvider, $logProvider){
	$routeProvider.when('/admin/import/user', {
		templateUrl: 'app/admin/importUser',
		controller: 'wkAdmin.importUser',
		data: {
			standalonePage: true
		}
	});
});

