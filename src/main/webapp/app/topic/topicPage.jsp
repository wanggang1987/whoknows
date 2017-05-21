<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
			<div class="row have-follow-topic">
				<h4>已关注话题动态</h4>
				<hr/>
				<div class="alert alert-danger alert-dismissable" ng-show="noTagWarn">
					您没有关注的标签，您可以通过热门标签选择感兴趣的标签。
				</div>
				<a href="javascript:void(0)" ng-click="loadTopicByTag(tag)" ng-repeat="tag in tags" ng-class="{active : currentTag.id == tag.id}">{{tag.name}}</a>	
			</div>
			<topic-list topic-lists="topicLists" hide-read-more="hideReadMore" load-more-data="loadMore()"></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>