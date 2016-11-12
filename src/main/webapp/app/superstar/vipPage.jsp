<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container self-home-page">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
        		<div class="row have-follow-topic">
        			<h4>已关注大咖</h4>
        			<hr/>
        			<div class="alert alert-danger alert-dismissable" ng-show="noVipWarn">
					<button type="button" class="close" ng-click="closeNoVipWarn()" data-dismiss="alert" aria-hidden="true">×</button>
					您没有关注的大咖，您可以通过热门大咖选择感兴趣的大咖。
				</div>
        			<a href="javascript:void(0)" ng-click="loadVipDetail(vip)" ng-repeat="vip in vips" ng-class="{active : currentVip.userID == vip.userID}">{{vip.name}}</a>	
        		</div>
        		<div class="row">
        			<div class="col-lg-2"></div>
        			<div class="col-lg-2"></div>
        		</div>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
		  <hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>