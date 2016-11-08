<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-page"> 
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
        		<div class="have-follow-topic">
        			<h4>已关注话题动态</h4>
        			<hr/>
        			<a href="javascript:void(0)" class="active">有机催化</a>	
        			<a href="javascript:void(0)">光氧化还原</a>	
        			<a href="javascript:void(0)">糖化学</a>	
        			<a href="javascript:void(0)">天然产物全合成</a>	
        			<a href="javascript:void(0)">波谱解析</a>	
        			<a href="javascript:void(0)">计算机辅助药物设计</a>	
        		</div>
       	 	<topic-list topic-lists="topicLists" hide-read-more="hideReadMore" load-more-data="loadMore()"></topic-list>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>