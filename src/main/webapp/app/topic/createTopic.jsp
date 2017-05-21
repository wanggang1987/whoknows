<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container create-topic-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
			<hr />
			<h4>请选择标签<span class="small">&nbsp;&nbsp;&nbsp;(最多支持选择5个标签)</span></h4>
			<form class="attireCodeToggleBlock" action="" style="width: 100%">
				<select class="multipleSelect" multiple name="language" >
					<option ng-repeat="tag in tags" value="{{tag.value}}" >{{tag.text}}</option>
				</select>
			</form>
			<hr/>

			<h4>标题</h4>
			<textarea class="form-control" maxlength="1024" rows="3" required ng-model="title" ></textarea>
			<p>还可以输入个{{1024 - title.length}}字</p>
			<hr/>
			<h4>内容</h4>
			<textarea ui-tinymce="tinymceOptions1" required ng-model="content"   class="tea"/></textarea>
			<div class="form-group text-right">
				<div class="alert alert-danger alert-dismissible fade in wk-warn-panel" role="alert" ng-show="tagEmptyWarn">
					<button type="button" class="close" data-dismiss="alert" ng-click="closeWarnPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					<strong>输入的标签为空</strong> 请选择合适的标签.
				</div>
				<button type="submit" class="btn btn-default default-wk-blue-btn" ng-disabled="title == undefined || title == null || title.length == 0 || content == undefined || content == null && content.length == 0" ng-click="createQuestion()">提问</button>
			</div>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

	<wk-img-upload-modal callback-function="uploadImgSuccess(imgId)"></wk-img-upload-modal>

