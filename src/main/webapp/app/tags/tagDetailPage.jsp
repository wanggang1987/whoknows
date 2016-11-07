<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-page"> 
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
        		<div class="row">
        			<img alt="" ng-src="{{defaultTagImg}}" style="width:75px; float:left"/>
        			<h4>有机催化</h4>
        			<h5 class="font-gray-color">创建时间： 2016-10-1 &nbsp;&nbsp;|&nbsp;&nbsp; 关注人数： 100人</h5>
        			<br />
        			
        			<hr class="wk-orign-hr"/>
        		</div>
       	 	<topic-list></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>