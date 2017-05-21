'use strict';

angular.module('wkTopic').controller('CreateTopicCtrl',
		function ($scope, $rootScope, $location, UserService, $http, LocalStorageService, TINYMCE) {
			$scope.tagEmptyWarn = false;

			$scope.closeWarnPanel = function () {
				$scope.tagEmptyWarn = false;
			}

			$scope.uploadImgSuccess = function (imgId) {
				if ($scope.content == undefined) {
					$scope.content = '';
				}
				tinyMCE.activeEditor.execCommand('mceInsertContent', false, '<img style="max-width:100%" src="/img/' + imgId + '" />');
			}
			var init = function () {
				if (!UserService.isSignedIn()) {
					LocalStorageService.put('LastPage', $location.path());
					$location.path("/login");
				}
				$http.get("/tag/list").then(function (data) {
					$scope.tags = data.data;
					$('.multipleSelect').fastselect({"maxItems": 5, "placeholder": "请选择标签"});
				})


				$scope.tinymceOptions1 = {
					resize: true,
					menubar: false,
					statusbar: false,
					plugins: ["link", "code", "textcolor", "paste"],
					content_style: ".mce-content-body {font-size:13px;}",
					width: '100%',
					paste_as_text: true,
					height: 350,
					language_url: TINYMCE.LANG_URL,
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

			$scope.createQuestion = function () {
				var tags = $('.multipleSelect').val();
				if (tags == undefined || tags == null || tags.length == 0) {
					$scope.tagEmptyWarn = true;
					return;
				}
				var reqTags = [];
				_.each(tags, function (tag) {
					reqTags.push({"value": tag});
				})
				var req = {"topic": {
						tagId: $('.multipleSelect').val(),
						user_id: UserService.getCurrent().id,
						title: $scope.title,
						content: $scope.content
					},
					"tags": reqTags
				};

				$http.put("/topic", req).success(function () {
					console.log("create topic success");
					$location.path("/");
				}).error(function () {
					console.log("crete topic error")
				});
			}

			init();
		});
