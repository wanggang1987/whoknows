<%@page contentType="text/html" pageEncoding="UTF-8" %>

 <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
  	<form name="myForm">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="myModalLabel">上传头像</h4>
	      </div>
	      <div class="modal-body">
	      	   <h5>请选择本地照片(大小不超过500k)，上传编辑自己的头像. </h5>
	      	   <div>
    					<input type="file" img-cropper-fileread image="cropper.sourceImage" 
    						ngf-select ng-model="picFile" name="file"    
			             accept="image/*" ngf-max-size="500KB" 
			             required ngf-model-invalid="errorFile"/>
    					<div>{{$scope.cropper.sourceImage}}
				      <canvas width="500" height="300" id="canvas" image-cropper image="cropper.sourceImage" cropped-image="cropper.croppedImage" crop-width="150" crop-height="150" min-width="50" min-height="50" keep-aspect="true" crop-area-bounds="bounds"></canvas>
				    </div>
				    <div class="text-center" ng-show="cropper.croppedImage!=null"><img ng-src="{{cropper.croppedImage}}" /></div>
				     <span class="wk-warn-color" ng-show="myForm.file.$error.maxSize">文件大小超过500k.请选择合适大小的图片上传</span>
					 <span class="text-center" ng-show="uploadingImg">图片上传中...</span>
					 <span class="wk-warn-color" ng-show="uploadImgError">图片上传失败!请重试.</span>
  				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default font-gray-color" data-dismiss="modal">取消</button>
	     	<button type="button" class="btn btn-default wk-blue-color"  ng-disabled="myForm.$invalid || cropper.croppedImage == null"  ng-click="uploadImg(cropper.croppedImage, picFile.name)">上传</button>
	      </div>
	   </div>
    </form>
  </div>
</div>