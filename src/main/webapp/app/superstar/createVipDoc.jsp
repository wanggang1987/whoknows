<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container create-topic-page"> 
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
        		<h4>标题</h4>
        		<textarea class="form-control" maxlength="1024" rows="3" required ng-model="title" ></textarea>
        		<p>还可以输入个{{1024 - title.length}}字</p>
       		<hr/>
       		<h4>内容</h4>
       		<textarea ui-tinymce="tinymceOptions1" required ng-model="content"   class="tea"/></textarea>
        		<div class="form-group text-right">
				<button type="submit" class="btn btn-default default-wk-blue-btn" ng-disabled="title == undefined || title == null ||  title.length == 0 || content == undefined || content == null && content.length == 0" ng-click="createQuestion()">提问</button>
			</div>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>
