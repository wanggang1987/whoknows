<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container self-home-page">

	<div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
			<div class="row alert alert-danger alert-dismissable" ng-show="noVipWarn">
				您没有关注的大咖，您可以通过热门大咖选择感兴趣的大咖。
			</div>
			<div class="row have-follow-topic" ng-hide="noVipWarn">
				<h4>已关注大咖</h4>
				<hr/>
				<a href="javascript:void(0)" ng-click="loadVipDetail(vip)" ng-repeat="vip in vips" ng-class="{active : currentVip.userID == vip.userID}">{{vip.name}}</a>	
			</div>
			<wk-vip-detail-page ng-if ="currentVipDetail" load-more-data="loadMore()" papers ="paperLists" current-vip-detail="currentVipDetail" hide-read-more="hideReadMore"></wk-vip-detail-page>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>