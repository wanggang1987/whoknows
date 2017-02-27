<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="topic-list">
	<div class=" margin-top-70 alert alert-warning alert-dismissable" ng-if="topicLists == undefined || topicLists == null || topicLists.length == 0">
		暂时没有相应的话题，您可以点击创建话题按钮创建话题。
	</div>
	<div class="row" ng-repeat="topic in topicLists"> 
		<div class="col-xs-20 col-sm-12"> 
			<div class="topic-header"><a href="#/topicDetail?id={{topic.topicDetail.topic.id}}"><p class="topic-list-title" ng-bind-html="topic.topicDetail.topic.title | to_trusted"></p> </a></div>
			<div class="topic-body" ng-show="topic.replyDetail.reply != null">
				<div class="topic-body-author">
					<img ng-src="{{topic.replyDetail.author.picture|| defaultPeopleImg}}" class="ng-cloak"></img>
					<wk-name-span ng-if="topic.replyDetail.author" user="topic.replyDetail.author"></wk-name-span>
				</div>
				<div class="topic-body-content">
					<p ng-show="topic.replyDetail.shortContent.fullAble"><span ng-bind-html="topic.replyDetail.shortContent.text + '...' | to_trusted"></span><a href="javascript:void(0)" ng-show="topic.replyDetail.shortContent.fullAble" ng-click="toggelExpandReply(topic.replyDetail)">显示全部</a></p>
					<p ng-hide="topic.replyDetail.shortContent.fullAble" ng-bind-html="topic.replyDetail.reply.content | to_trusted"></p>
				</div>
			</div>
			<div class="topic-footer">
				<ul class="topic-footer-ul">
					<li><a href="javascript:void(0);" ng-click="fllowTopic(topic.topicDetail)"><span class="glyphicon glyphicon-heart"></span><span ng-hide="topic.topicDetail.currentFollowed">关注问题</span><span ng-show="topic.topicDetail.currentFollowed">取消关注</span></a>({{topic.topicDetail.followCount}})</li>
					<li ng-show="topic.replyDetail.reply != null"><a href="javascript:void(0);" ng-click="expandCommentLists(topic.replyDetail)"><span class="glyphicon glyphicon-comment"></span>评论</a>({{topic.replyDetail.commentCount}})</li>
					<li ng-show="topic.replyDetail.reply != null"><a href="javascript:void(0);" ng-click="likeReply(topic.replyDetail)"><span class="glyphicon glyphicon-thumbs-up"></span><span ng-hide="topic.replyDetail.currentLiked">点赞</span><span ng-show="topic.replyDetail.currentLiked">取消点赞</span></a>({{topic.replyDetail.likeCount}})</li>
					<li ng-show="topic.replyDetail.reply != null && !topic.replyDetail.shortContent.fullAble"><a href="javascript:void(0);" ng-click="toggelExpandReply(topic.replyDetail)"><span class="glyphicon glyphicon-arrow-up"></span>收起</a></li>
				</ul>
				<div class="topic-comment-lists" >
					<div class="popover bottom topic-comment-list-{{topic.replyDetail.reply.id}}" role="tooltip"  style="display:none">
						<div class="arrow"></div>
						<div class="popover-content">
							<div class="text-center" ng-hide="topic.replyDetail.loadCommentsSuccess">评论加载中...</div>
							<div class="topic-comment-list row" ng-repeat="comment in topic.replyDetail.commentLists.comments" ng-show="topic.replyDetail.loadCommentsSuccess">
								<div class="topic-comment-body">
									<div class="topic-comment-body-author">
										<img alt="" ng-src="{{comment.userDtail.picture|| defaultPeopleImg}}" ></img>
										<span >{{comment.userDtail.email}}</span>
									</div>
									<div class="topic-comment-body-content">
										{{comment.comment.content}}
									</div>
									<div class="topic-comment-body-attach-info">
										{{ comment.comment.create_time | date : "y-MM-dd HH:mm:ss" }}
									</div>
								</div>
							</div>
							<wk-pagination ng-if="topic.replyDetail.loadCommentsSuccess && topic.replyDetail.commentLists.comments.length > 0" pagination-info="topic.replyDetail.commentLists.paging" cache-data="topic.replyDetail" load-data="loadComments(cacheData, paging)"></wk-pagination>
							<sec:authorize access="isAuthenticated()">
								<div class="">
									<textarea class="form-control" maxlength="20000" rows="3" id="commentContent" required ng-model="request.commentContent" ></textarea>
									<p>还可以输入个{{20000 - title.length}}字</p>
									<div class="text-right">
										<button type="submit" class="btn btn-default  " ng-click="calcelComment()">取消</button>
										<button type="submit" class="btn btn-default  " ng-click="createComment(topic.replyDetail)" ng-disabled="request.commentContent == undefined || request.commentContent == null || request.commentContent == '' || request.commentContent.length == 0">评论</button>
									</div>
								</div>
							</sec:authorize>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<button class="btn btn-default btn-lg wk-add-more-btn"  ng-click="loadMore()" ng-hide="hideReadMore || topicLists == undefined || topicLists == null || topicLists.length < defaultPerPage">更多</button>
</div>