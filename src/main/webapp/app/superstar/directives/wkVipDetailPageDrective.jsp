<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
	<div class="row margin-top-40" ng-hide="noVipWarn">
   			<div class="col-lg-2"><img class="wk-img-65" ng-src="{{currentVipDetail.userDetail.picture || defaultPeopleImg}}"></div>
   			<div class="col-lg-8">
   				<h3 style="font-family:宋体; font-size:21px;font-weight:bold">{{ currentVipDetail.userDetail | formateName }}</h3>
   				<h4 style="font-family:宋体; font-size:18px;">{{ currentVipDetail.userDetail.title }}</h4>
   				<dl class="dl-horizontal">
				  <dt style="font-family:Times New Roman; font-size:18px;">邮箱</dt><dd style="font-family:Times New Roman; font-size:18px;">{{ currentVipDetail.userDetail.email || '暂无'}}</dd>
				  <dt style="font-family:Times New Roman; font-size:18px;">电话</dt><dd style="font-family:Times New Roman; font-size:18px;">{{ currentVipDetail.userDetail.phone || '暂无'}}</dd>
				</dl>
   			</div>
   			<div class="col-lg-2">
   				<ul class="wk-default-ul">
   					<li class="font-gray-color"><a href="javascript:void(0);" ng-click="fllowVip(currentVipDetail)" class="font-gray-color"><span class="glyphicon glyphicon-heart wk-orange-color"></span><span ng-hide="currentVipDetail.currentFollowed">关注</span><span ng-show="currentVipDetail.currentFollowed">取消关注</span></a>({{currentVipDetail.followCount}})</li>
   				</ul>
   			</div>
   		</div>
    		<div class="row" ng-hide="noVipWarn">
    			<h5 style="font-family:Times New Roman; font-size:18px;font-weight:bold">个人简历</h5>
    			<p class="" style="font-family:Times New Roman; font-size:18px;" ng-bind-html="(currentVipDetail.userDetail.profile || '暂无') | to_trusted"></p>
    			<hr/>
    		</div>
    		
    		<div class="topic-list" ng-hide="noVipWarn">
			<div class="row" ng-repeat="paper in papers"> 
				<div class="col-xs-20 col-sm-12"> 
					<div class="topic-body" >
						<h5 class="wk-blue-color"><a ng-href="#/vipPaperDetail/{{paper.paper.id}}" ><p style="font-family:Times New Roman; font-size:18px;font-weight:bold" ng-bind-html="paper.paper.title | to_trusted"></p></a></h5>
						<div class="topic-body-content">
							<p ng-show="paper.shortContent.fullAble">
								<span style="font-family:Times New Roman; font-size:18px; " ng-bind-html="paper.shortContent.text + '...' | to_trusted"></span>
								<a href="javascript:void(0)" ng-show="paper.shortContent.fullAble" ng-click="toggelExpandReply(paper)">显示全部</a>
							<p>
							<p style="font-family:Times New Roman; font-size:18px; " ng-hide="paper.shortContent.fullAble" ng-bind-html="paper.paper.content | to_trusted"></p>
						</div>
				</div>
				<div class="topic-footer">
					<ul class="topic-footer-ul">
						<li><a href="javascript:void(0);" ng-click="likePaper(paper)"><span class="glyphicon glyphicon-thumbs-up"></span><span ng-hide="paper.currentLiked">点赞</span><span ng-show="paper.currentLiked">取消点赞</span></a>({{paper.likeCount}})</li>
						<li ng-hide="paper.shortContent.fullAble"><a href="javascript:void(0);" ng-click="toggelExpandReply(paper)"><span class="glyphicon glyphicon-arrow-up"></span>收起</a></li>
					</ul>
				</div>
			</div>
	     </div>


		<div class="row">
		 <button class="btn btn-default btn-lg wk-add-more-btn"  ng-click="loadMore()" ng-hide="hideReadMore">更多</button>
		</div>
</div>