<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-detail-page"> 
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
        		<h2 class="text-center"><p ng-bind-html="topic.topic.title | to_trusted"></p> </h2>
        		<h5 class="font-gray-color">{{topic.topic.create_time | date : "y-dd-MM HH:mm:ss"}}&nbsp;&nbsp;{{topic.author.email}}, {{topic.author.companyName}},{{topic.author.title}}</h5>
        		<p ng-bind-html="topic.topic.content | to_trusted"></p>
        		<hr />
        		<div class="topic-reply-list" ng-repeat="reply in topic.replys">
        			<div class="row">
        				<div class="col-lg-10">
        					<span><strong>{{reply.author.email}}</strong> {{reply.author.companyName}}</span>
        				</div>
        				<div class="col-lg-2">
        					<img alt="" ng-src="{{reply.author.picture || defaultPeopleImg}}" class="wk-img-25">
        				</div>
        			</div>
        			<div class="row">
        				<p ng-bind-html="reply.reply.content | to_trusted"></p>
        			</div>	
        			<div class="row">
        				<span>发布于: {{reply.reply.create_time | date : "y-dd-MM HH:mm:ss"}}</span>
        				&nbsp;&nbsp;&nbsp;&nbsp;
        				<a href="javascript:void(0);" ng-click="expandCommentLists(result.topic)"><span class="glyphicon glyphicon-comment"></span>评论</a>
        				&nbsp;&nbsp;&nbsp;&nbsp;
        				<a href="javascript:void(0);"><span class="glyphicon glyphicon-thumbs-up"></span> 点赞</a>
        			</div>
        			<hr />
        		</div>
        		<div class="row">
        			<button class="btn btn-default btn-lg wk-add-more-btn"  ng-click="loadMore()" ng-hide="hideReadMore">更多</button>
        		</div>
        		<div class="row">
        			<textarea ui-tinymce="tinymceOptions1" required ng-model="content"   class="tea"/></textarea>
        		</div>
        		<div class="form-group text-right">
				<button type="submit" class="btn btn-default default-wk-blue-btn" ng-disabled=" content == undefined || content == null && content.length == 0" ng-click="replyQuestion()">回答</button>
			</div>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>
