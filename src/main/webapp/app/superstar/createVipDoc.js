'use strict';

angular.module('wkSuperstar').controller('CreateVipDocCtrl',
		function ($scope, $rootScope, $location, UserService, $http, LocalStorageService, ROLE_TYPE, TINYMCE) {

			var init = function () {
				if (!UserService.isSignedIn() || !UserService.hasPermission(ROLE_TYPE.SITE_VIP)) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/");
					return;
				}
				$scope.tinymceOptions1 = {
					resize: true,
					menubar: false,
					statusbar: false,
					height: 350,
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
				if ($scope.req == undefined || $scope.req.content == undefined) {
					$scope.req = {};
					$scope.req.content = '';
				}
				tinyMCE.activeEditor.execCommand('mceInsertContent', false, '<img style="max-width:100%" src="/img/' + imgId + '" />');
			}

			$scope.createVipDoc = function () {
				if (!UserService.isSignedIn() || !UserService.hasPermission(ROLE_TYPE.SITE_VIP)) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/");
					return;
				}

				$scope.req.user_id = UserService.getCurrent().id;
				$http.put("/paper", $scope.req).success(function () {
					console.log("create topic success");
					$location.path("/");
				}).error(function () {
					console.log("crete topic error")
				});
			}


			init();

		});
