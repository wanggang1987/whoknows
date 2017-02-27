<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="panel panel-default hot-topic-siderbar">
	<div class="panel-heading text-center">热门大咖</div>
	<div class="panel-body">
		<div class="input-group">
			<input type="text" class="form-control" ng-model="keyWordVip"  placeholder="搜索您感兴趣的大咖">
			<span class="input-group-btn">
				<button class="btn btn-default " type="button" ng-click="searchHotVip()"><span class="glyphicon glyphicon-search"></span></button>
			</span>
		</div>
		<div class="list-group">
			<div class="list-group-item row" ng-repeat="vip in vips">
				<div class="col-md-3"><img alt="" ng-src="{{vip.picture|| defaultPeopleImg }}" ng-cloak></img></div>
				<div class="col-md-9">
					<a ng-href="#/vipDetail/{{vip.userID}}"><p class="list-group-item-heading">{{vip.name}}</p></a>
					<a href="javascript:void(0);" ng-click="fllowVip(vip)" class="font-gray-color"><span class="glyphicon glyphicon-heart"></span><span ng-hide="vip.currentFollowed">关注</span><span ng-show="vip.currentFollowed">取消关注</span></a>({{vip.followCount}})
				</div>
			</div>
			<div class="list-group-item text-center see-more-topic">
				<a href="javascrpit:void(0)" ng-click="prePage()" ng-hide="currentPage == 1">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascrpit:void(0)" ng-click="nextPage()" ng-hide="lastPage" >下一页</a>
			</div>
		</div>
	</div>
</div>