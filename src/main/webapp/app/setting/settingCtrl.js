'use strict';

angular.module('wkCommon').controller('SettingCtrl',
		function ($scope, $rootScope, $location, UserService, $http, TINYMCE, LocalStorageService) {
			if (!UserService.isSignedIn()) {
				LocalStorageService.put('LastPage', $location.path());
				$location.path("/login");
				return;
			}
			var init = function () {
				$http.get("/user/" + UserService.getCurrent().id).then(function (data) {
					$scope.setUser = data.data;
				})

				$scope.tinymceOptions1 = {
					resize: true,
					menubar: false,
					statusbar: false,
					height: 250,
					content_style: ".mce-content-body {font-size:13px;}",
					width: '100%',
					language_url: TINYMCE.LANG_URL,
					plugins: ["link", "code", "textcolor"],
					toolbar: "undo redo | bold italic subscript superscript strikethrough underline | bullist numlist | forecolor backcolor | link mybutton | removeformat",
					setup: function (editor) {
						editor.addButton('mybutton', {
							type: 'button',
							title: 'Insert image',
							icon: 'image',
							id: 'mybutton'
						});
						editor.on('init', function (e) {
							$("#mybutton").on("click", function () {
								$scope.$broadcast('event:upload:topic:img');
							});
						});
					}
				}
			}

			$scope.uploadImgSuccess = function (imgId) {
				if ($scope.setUser.profile == undefined) {
					$scope.setUser.profile = '';
				}
				tinyMCE.activeEditor.execCommand('mceInsertContent', false, '<img style="max-width:100%" src="/img/' + imgId + '" />');
			}

			$scope.resetPasswd = function () {
				$scope.$broadcast('event:resetPassword');
			}

			//---------------base info
			$scope.showSaveBaseInfoSuccess = false;
			$scope.showSaveBaseInfoError = false;

			$scope.saveBaseInfo = function () {
				$http.post("/user", $scope.setUser).success(function () {
					$scope.showSaveBaseInfoSuccess = true;
					$scope.showSaveBaseInfoError = false;
				}).error(function () {
					$scope.showSaveBaseInfoSuccess = false;
					$scope.showSaveBaseInfoError = true;
				});

			}
			$scope.closeBaseInfoSuccessPanel = function () {
				$scope.showSaveBaseInfoSuccess = false;
			}

			$scope.closeBaseInfoWarnPanel = function () {
				$scope.showSaveBaseInfoError = false;
			}

			//---------------pass info
			$scope.showSavePassInfoSuccess = false;
			$scope.showSavePassInfoError = false;
			$scope.savePassInfo = function () {
				$http.post("/user", $scope.setUser).success(function () {
					$scope.showSavePassInfoSuccess = true;
					$scope.showSavePassInfoError = false;
				}).error(function () {
					$scope.showSavePassInfoSuccess = false;
					$scope.showSavePassInfoError = true;
				});
			}
			$scope.closePassInfoSuccessPanel = function () {
				$scope.showSavePassInfoSuccess = false;
			}

			$scope.closePassInfoWarnPanel = function () {
				$scope.showSavePassInfoError = false;
			}

			//------------------profile info
			$scope.saveProfileInfo = function () {
				$http.post("/user", $scope.setUser).success(function () {
					$scope.showSaveProfileInfoSuccess = true;
					$scope.showSaveProfileInfoError = false;
				}).error(function () {
					$scope.showSaveProfileInfoSuccess = false;
					$scope.showSaveProfileInfoError = true;
				});
			}
			$scope.closeProfileInfoSuccessPanel = function () {
				$scope.showSaveProfileInfoSuccess = false;
			}
			$scope.closeProfileInfoWarnPanel = function () {
				$scope.showSaveProfileInfoError = false;
			}
			init();

		});
