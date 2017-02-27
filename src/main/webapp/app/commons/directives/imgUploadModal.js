'use strict';

angular.module('wkCommon').directive('wkImgUploadModal', function (Upload) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/imgUploadModal',
		replace: true,
		scope: {
			callbackFunction: '&'
		},
		replace: true,
		link: function (scope, elem) {
			scope.uploadingImg = false;
			scope.uploadImgError = false;
			scope.$on('event:upload:topic:img', function (event, data) {
				elem.modal({backdrop: 'static'});
			});

			scope.uploadImg = function (file) {
				scope.uploadingImg = true;
				file.upload = Upload.upload({
					url: '/img',
					data: {img: file},
				});
				file.upload.then(function (response) {
					scope.callbackFunction({imgId: response.data})
					elem.modal('hide');
					scope.uploadingImg = false;
					scope.uploadImgError = false;
					scope.picFile = null;
				}, function (response) {
					scope.uploadingImg = false;
					scope.uploadImgError = true;
				});
			}
		}
	};
});