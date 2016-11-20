<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container self-home-page">
 	<button uib-popover-template="app/superstar/directives/leftSidebarHotSuperstar.jsp" popover-title="{{dynamicPopover.title}}" type="button" class="btn btn-default">Popover With Template</button>

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
        		<div class="row have-follow-topic">
        			{{vip.name}}
        		</div>
        		<wk-vip-detail-page ng-if ="currentVipDetail" load-more-data="loadMore()" papers ="paperLists" current-vip-detail="currentVipDetail" hide-read-more="hideReadMore"></wk-vip-detail-page>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
		  <hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>