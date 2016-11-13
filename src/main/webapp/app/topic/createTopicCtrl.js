'use strict';

angular.module('wkTopic').controller('CreateTopicCtrl',
	function ($scope, $rootScope, $location, UserService, $http, LocalStorageService) {
		console.log("wkCommon- wkTopic.TopicCtrl  load.");
		$scope.tagEmptyWarn = false;
		
		$scope.closeWarnPanel = function(){
			$scope.tagEmptyWarn = false;
		}

		$scope.uploadImgSuccess = function(imgId){
			if($scope.content == undefined){
		 		$scope.content = '';
		 	}
			$scope.content += '<img src="/img/'+ imgId +'" />';
		}
		var init = function(){
			if(!UserService.isSignedIn()){
				LocalStorageService.put('LastPage', '/creteTopic', true);
				$location.path("/login");
			}
			$http.get("/tag/list").then(function(data){
				$scope.tags = data.data;
				$('.multipleSelect').fastselect({"maxItems":5,"placeholder":"请选择标签"});
			})
			
			
			$scope.tinymceOptions1 = {
					resize: true,
					menubar: false,
					statusbar: false,
					height: 350,
					plugins: ["link", "code", "textcolor"],
					toolbar: "undo redo | formatselect styleselect fontselect fontsizeselect| bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | forecolor backcolor | link code mybutton ",
					setup: function(editor) {
						editor.addButton('mybutton', {
							type: 'button',
							title: 'Insert image',
							icon: 'image',
							id: 'mybutton'
						});
						editor.on('init', function(e) {
							$("#mybutton").on("click", function(){
								$scope.$broadcast('event:upload:topic:img');
							});
						});
					}	
				}
		}
		
		$scope.createQuestion = function(){
			var tags = $('.multipleSelect').val();
			if(tags == undefined || tags == null || tags.length == 0){
				$scope.tagEmptyWarn = true;
				return;
			}
			var reqTags = [];
			_.each(tags, function(tag){
				reqTags.push({"value" : tag});
			})
			var req ={ "topic" : {
						tagId: $('.multipleSelect').val(),
						user_id : UserService.getCurrent().id,
						title : $scope.title,	
						content: $scope.content
						}, 
						"tags" : reqTags
					};
			
			$http.put("/topic", req).success(function(){
				console.log("create topic success");
				$location.path("/");
			}).error(function(){
				console.log("crete topic error")
			});
		}
		
		init();
	});
