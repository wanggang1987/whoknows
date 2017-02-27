<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
			<div class="row">
				<img alt="" ng-src="{{defaultTagImg}}" style="width:75px; float:left"/>
				<h4>{{tag.name}}</h4>
				<h5 class="font-gray-color"><a href="javascript:void(0);" ng-click="fllowTag(tag)" class="font-gray-color"><span class="glyphicon glyphicon-heart"></span><span ng-hide="tag.currentFollowed">关注</span><span ng-show="tag.currentFollowed">取消关注</span></a>({{tag.tagFollowCount}})</h5>
				<br />
				<hr class="wk-orign-hr"/>
			</div>
			<topic-list topic-lists="topicLists" hide-read-more="hideReadMore" load-more-data="loadMore()"></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>