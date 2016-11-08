<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="topic-list">
	<div class="row" ng-repeat="topic in topicLists"> 
		<div class="col-xs-20 col-sm-12"> 
			<div class="topic-header"><a href="#/topicDetail?id={{topic.topicDetail.topic.id}}">{{topic.topicDetail.topic.title}}</a></div>
			<div class="topic-body" ng-show="topic.replyDetail.reply != null">
				<div class="topic-body-author">
					<wk-name-span user="topic.topicDetail.author"></wk-name-span>
				</div>
				<div class="topic-body-content">
					<p ng-bind-html="topic.replyDetail.reply.content | to_trusted"></p>
				</div>
			</div>
			<div class="topic-footer">
				<ul class="topic-footer-ul">
					<li><a href="javascript:void(0);" ng-click="fllowTopic(topic.topicDetail)"><span class="glyphicon glyphicon-heart"></span><span ng-hide="topic.topicDetail.cancelFllow">关注问题</span><span ng-show="topic.topicDetail.cancelFllow">取消关注</span></a>({{topic.topicDetail.followCount}})</li>
					<li ng-show="topic.replyDetail.reply != null"><a href="javascript:void(0);" ng-click="expandCommentLists(topic.replyDetail)"><span class="glyphicon glyphicon-comment"></span>评论</a></li>
					<li ng-show="topic.replyDetail.reply != null"><a href="javascript:void(0);" ng-click="likeReply(topic.replyDetail)"><span class="glyphicon glyphicon-thumbs-up"></span><span ng-hide="topic.replyDetail.cancelLike">点赞</span><span ng-show="topic.replyDetail.cancelLike">取消点赞</span></a>({{topic.replyDetail.likeCount}})</li>
				</ul>
				<div class="topic-comment-lists" >
					<div class="popover bottom topic-comment-list-{{topic.replyDetail.reply.id}}" role="tooltip"  style="display:none">
					      <div class="arrow"></div>
					      <div class="popover-content">
					      		<div class="text-center" ng-hide="topic.replyDetail.loadCommentsSuccess">评论加载中...</div>
					      		<div class="topic-comment-list row" ng-repeat="comment in topic.replyDetail.commentLists.comments" ng-show="topic.replyDetail.loadCommentsSuccess">
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
	<button class="btn btn-default btn-lg wk-add-more-btn"  ng-click="loadMore()" ng-hide="hideReadMore">更多</button>
</div>