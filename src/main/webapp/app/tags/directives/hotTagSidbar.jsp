<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-default hot-topic-siderbar">
	<div class="panel-heading text-center">热门标签</div>
	<div class="panel-body">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="搜索您感兴趣的标签" ng-model="hotTagSearchKeyWord">
			<span class="input-group-btn">
				<button class="btn btn-default " type="button" ng-click="searchTag()"><span class="glyphicon glyphicon-search"></span></button>
			</span>
		</div>
		<div class="list-group">
			<div class="list-group-item row" ng-repeat="tag in tags">
				<div class="col-md-3"><img alt="" ng-src="{{tag.picture|| defaultPeopleIMg}}" ng-cloak></img></div>
				<div class="col-md-9">
					<a ng-href="#/tagDetail/{{tag.tagID}}" ><p class="list-group-item-heading" ng-bind-html="tag.tagName | to_trusted"></p></a>
					<p class="list-group-item-text"><a href="javascript:void(0);" ng-click="fllowTag(tag)" class="font-gray-color"><span class="glyphicon glyphicon-heart"></span><span ng-hide="tag.currentFollowed">关注</span><span ng-show="tag.currentFollowed">取消关注</span></a>({{tag.followCount}})</p>

				</div>
			</div>
			<div class="list-group-item text-center see-more-topic">
				<a href="javascrpit:void(0)" ng-click="prePage()" ng-hide="currentPage == 1">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascrpit:void(0)" ng-click="nextPage()" ng-hide="lastPage" >下一页</a>
			</div>
		</div>
	</div>
</div>