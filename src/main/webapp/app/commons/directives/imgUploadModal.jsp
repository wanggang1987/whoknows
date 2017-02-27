<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal fade"   id ="topicImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form name="myForm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title" id="myModalLabel">插入图片</h4>
				</div>
				<div class="modal-body">
					<h5>请选择要插入的图片(最大500KB),请不要上传与回答问题无关的图片. </h5>
					<input type="file" ngf-select ng-model="picFile" name="file"    
						   accept="image/*" ngf-max-size="500KB" required ngf-model-invalid="errorFile">
					<span class="wk-warn-color" ng-show="myForm.file.$error.maxSize">文件大小超过500k.请选择合适大小的图片上传</span>
					<span class="text-center" ng-show="uploadingImg">图片上传中...</span>
					<span class="wk-warn-color" ng-show="uploadImgError">图片上传失败!请重试.</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default font-gray-color" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-default wk-blue-color"  ng-disabled="!myForm.$valid" ng-click="uploadImg(picFile)">上传</button>
				</div>
			</div>
		</form>
	</div>
</div>