angular.module('wkLogin').directive('wkForgotPassword',
		function ($log, $http, $timeout) {

			return {
				templateUrl: 'app/login/directives/forgetPasswd',
				scope: {
					activateResetPassword: '='
				},
				link: function (scope) {
					scope.$on('event:forgotPassword', function () {
						$('#forgot-password-modal').modal({keyboard: false, backdrop: 'static'});
					});
				}
			}
		});