<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
			<topic-list topic-lists="topicLists" hide-read-more="hideReadMore" load-more-data="loadMore()"></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>