'use strict';

angular.module('wkCommon').directive('wkCropImgModal', function(Upload){
	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/wkCropImgModal',
		replace: true,
		scope: {
			callbackFunction : '&'
		},
		replace: true,
		link: function (scope, elem) {
			scope.cropper = {
				"sourceImage" : null,
				"croppedImage" : null
			};
	        scope.bounds = {
	        		"left" : 0,
	        		"right" : 0,
	        		"top" : 0,
	        		"bottom" : 0,
	        };
	        scope.uploadingImg = false;
			scope.uploadImgError = false;
			
			scope.$on('event:crop:img', function (event, data) {
				elem.modal({backdrop: 'static'});
			});
			
			scope.uploadImg = function(img, fileName){
				  Upload.upload({
				      url: '/user/img',
				      data: {
			                img: Upload.dataUrltoBlob(img, fileName)
			            }
				  }).then(function (response) {
					 	scope.callbackFunction({imgId: response.data})
					 	elem.modal('hide');
					 	scope.uploadingImg = true;
					 	scope.uploadImgError = false;
				    }, function (response) {
					    	scope.uploadingImg = false;
				    		scope.uploadImgError = true;
				    });
			}
			
		}
	};
})