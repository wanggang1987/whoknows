<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container self-home-page">

	<div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
			<h2 class="text-center"><p ng-bind-html="paper.paper.title | to_trusted"></p> </h2>
			<h5 class="font-gray-color">{{paper.paper.create_time| date : "y-MM-dd HH:mm:ss"}}&nbsp;&nbsp;{{paper.author| formateName}}, {{paper.author.companyName}},{{paper.author.title}}</h5>
			<h5 class="font-gray-color"> <a class="font-gray-color" href="javascript:void(0);" ng-click="likePaper(paper)"><span class="glyphicon glyphicon-heart"></span><span ng-hide="paper.currentLiked">关注大咖文章</span><span ng-show="paper.currentLiked">取消关注</span></a>({{paper.likeCount}})</h5>
			<hr />
			<p ng-bind-html="paper.paper.content | to_trusted"></p>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
			<hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>