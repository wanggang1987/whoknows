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
        		<div class="row margin-top-40">
        			<div class="col-lg-2"><img class="wk-img-65" ng-src="{{currentVipDetail.picture || defaultPeopleImg}}"></div>
        			<div class="col-lg-8">
        				<h3>{{ currentVipDetail | formateName }}</h3>
        				<h4>{{ currentVipDetail.title }}</h4>
        				<dl class="dl-horizontal">
					  <dt>邮箱</dt><dd>{{ currentVipDetail.email || '暂无'}}</dd>
					  <dt>电话</dt><dd>{{ currentVipDetail.phone || '暂无'}}</dd>
					</dl>
        			</div>
        			<div class="col-lg-2">
        				<ul class="wk-default-ul">
        					<li class="font-gray-color"><a href="javascript:void(0);" ng-click="fllowVip(currentVip)" class="font-gray-color"><span class="glyphicon glyphicon-heart wk-orange-color"></span><span ng-hide="currentVip.currentFollowed">关注</span><span ng-show="currentVip.currentFollowed">取消关注</span></a>({{currentVip.followCount}})</li>
        				</ul>
        			</div>
        		</div>
        		<div class="row">
        			<h5>个人简历</h5>
        			<p>{{currentVipDetail.profile || '暂无'}}</p>
        		</div>
        		<div class="topic-list">
				<div class="row" ng-repeat="reply in topic.replys"> 
					<div class="col-xs-20 col-sm-12"> 
						<div class="topic-body" >
							<h3>标题</h3>
							<div class="topic-body-content">
								<p ng-bind-html="reply.reply.content | to_trusted"></p>
							</div>
						</div>
						<div class="topic-footer">
							<ul class="topic-footer-ul">
								<li><a href="javascript:void(0);" ng-click="likeReply(reply)"><span class="glyphicon glyphicon-thumbs-up"></span><span ng-hide="reply.currentLiked">点赞</span><span ng-show="reply.currentLiked">取消点赞</span></a>({{reply.likeCount}})</li>
								<li><a href="javascript:void(0);" ng-click="expandCommentLists(reply)"><span class="glyphicon glyphicon-comment"></span>评论</a>({{reply.commentCount}})</li>
							</ul>
							<div class="topic-comment-lists" >
								<div class="popover bottom topic-comment-list-{{reply.reply.id}}" role="tooltip"  style="display:none">
								      <div class="arrow"></div>
								      <div class="popover-content">
								      		<div class="text-center" ng-hide="reply.loadCommentsSuccess">评论加载中...</div>
								      		<div class="topic-comment-list row" ng-repeat="comment in reply.commentLists.comments" ng-show="reply.loadCommentsSuccess">
								      			<div class="topic-comment-body">
													<div class="topic-comment-body-author">
														<img alt="" ng-src="{{comment.userDtail.picture || defaultPeopleImg}}" ></img>
														<span >{{comment.userDtail.email}}</span>
													</div>
													<div class="topic-comment-body-content">
														{{comment.comment.content}}
													</div>
													<div class="topic-comment-body-attach-info">
														{{ comment.comment.create_time | date : "y-dd-MM HH:mm:ss" }}
													</div>
												</div>
								      		</div>
								      		<wk-pagination ng-if="reply.loadCommentsSuccess && reply.commentLists.comments.length > 0" pagination-info="reply.commentLists.paging" cache-data="reply" load-data="loadComments(cacheData, paging)"></wk-pagination>
								      		<sec:authorize access="isAuthenticated()">
								      		<div class="">
								      			<textarea class="form-control" maxlength="20000" rows="3" id="commentContent" required ng-model="request.commentContent" ></textarea>
									        		<p>还可以输入个{{20000 - title.length}}字</p>
									        		<div class="text-right">
										        		<button type="submit" class="btn btn-default  " ng-click="calcelComment()">取消</button>
										        		<button type="submit" class="btn btn-default  " ng-click="createComment(reply)" ng-disabled="request.commentContent == undefined || request.commentContent == null || request.commentContent == '' || request.commentContent.length == 0">评论</button>
								      			</div>
								      		</div>
								      		</sec:authorize>
								      </div>
								 </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
        		<div class="row">
        			<button class="btn btn-default btn-lg wk-add-more-btn"  ng-click="loadMore()" ng-hide="hideReadMore">更多</button>
        		</div>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
          <hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
		  <hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
      </div><!--/row-->

 </div>