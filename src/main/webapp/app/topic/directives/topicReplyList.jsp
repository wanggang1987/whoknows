<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div ng-repeat = "replyDetail in replyDetailList">
	<div class="topic-body" ng-if="replyDetail != null && replyDetail.reply != null">
		<div class="topic-body-author"> 
			<img ng-src="{{replyDetail.author.picture|| defaultPeopleImg}}" class="ng-cloak"></img>
			<wk-name-span user="replyDetail.author"></wk-name-span>
		</div>
		<div class="topic-body-content">
			<p ng-bind-html="replyDetail.reply.content | to_trusted"></p>
		</div>
	</div>
	<div class="topic-footer">
		<ul class="topic-footer-ul">
			<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-heart"></span>关注问题({{topicDetail.followCount}})</a></li>
			<li ng-show="replyDetail != null && replyDetail.reply != null"><a href="javascript:void(0);" ng-click="expandCommentLists(replyDetail)"><span class="glyphicon glyphicon-comment"></span>评论</a></li>
			<li ng-show="replyDetail != null && replyDetail.reply != null"><a href="javascript:void(0);"><span class="glyphicon glyphicon-thumbs-up"></span> 点赞</a></li>
		</ul>
		<div class="topic-comment-lists" ng-if="replyDetail != null && replyDetail.reply != null">
			<div class="popover bottom topic-comment-list-{{replyDetail.reply.id}}" role="tooltip"  style="display:none">
				<div class="arrow"></div>
				<div class="popover-content">
					<div class="text-center" ng-hide="replyDetail.loadCommentsSuccess">评论加载中...</div>
					<div class="topic-comment-list row" ng-repeat="comment in replyDetail.commentLists.comments" ng-show="replyDetail.loadCommentsSuccess">
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
					<wk-pagination ng-if="replyDetail.loadCommentsSuccess && replyDetail.commentLists.comments.length > 0" pagination-info="replyDetail.commentLists.paging" cache-data="replyDetail" load-data="loadComments(cacheData, paging)"></wk-pagination>
					<sec:authorize access="isAuthenticated()">
						<div class="">
							<textarea class="form-control" maxlength="20000" rows="3" id="commentContent" required ng-model="request.commentContent" ></textarea>
							<p>还可以输入个{{20000 - title.length}}字</p>
							<div class="text-right">
								<button type="submit" class="btn btn-default  " ng-click="calcelComment()">取消</button>
								<button type="submit" class="btn btn-default  " ng-click="createComment(replyDetail)">评论</button>
							</div>
						</div>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</div>