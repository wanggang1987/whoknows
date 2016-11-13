'use strict';

angular.module('WhoKnows').directive('wkImgUploadModal', function ($location, $window, Upload) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/imgUploadModal',
		replace: true,
		scope: {
			callbackFunction : '&'
		},
		replace: true,
		link: function (scope, elem) {
			
			scope.$on('event:upload:topic:img', function (event, data) {
				$('#topicImgModal').modal({backdrop: 'static'});
			});

			scope.uploadImg = function(file){
				scope.uploadingImg = true;
				 file.upload = Upload.upload({
				      url: '/img',
				      data: {img: file},
				  });
				 file.upload.then(function (response) {
					 	scope.callbackFunction({imgId: response.data})
					 	$("#topicImgModal").modal('hide');
					 	scope.uploadingImg = false;
				    }, function (response) {
				    		scope.uploadingImg = false;
				    });
			}
		}
	};
});