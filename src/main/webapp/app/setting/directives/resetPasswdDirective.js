angular.module('wkSetting').directive('wkResetPassword',
		function ($log, $http, $timeout, LocalStorageService) {

			return {
				templateUrl: 'app/setting/directives/resetPasswd',
				scope: {
					activateResetPassword: '='
				},
				link: function (scope) {
					scope.resetPasswdSuccess = true;

					scope.$on('event:resetPassword', function () {
						$('#reset-password-modal').modal({keyboard: false, backdrop: 'static'});
					});

					scope.resetPasswd = function () {
						scope.resetPasswdInfo.email = LocalStorageService.get("userName");

						$http.post("/user/password/reset", scope.resetPasswdInfo).success(function () {
							console.log("Reset passwd success.");
							scope.resetPasswdSuccess = true;
							$('#reset-password-modal').modal('toggle');
						}).error(function () {
							console.log("Reset passwd error.");
							scope.resetPasswdSuccess = false;
						});
					}

				}
			}
		});