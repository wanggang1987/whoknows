<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="topic-list">
	<div class="row" ng-repeat="result in topicLists"> 
		<div class="col-xs-20 col-sm-12"> 
			<div class="topic-header"><a href="#/topicDetail?id={{result.topic.id}}">{{result.topic.title}}</a></div>
			<div class="topic-body">
				<div class="topic-body-author">
					<img alt="" ng-src="{{result.topicUser.picture}}" ng-cloak></img>
					<span >{{result.topicUser.email}}</span>
				</div>
				<div class="topic-body-content">
					<p ng-bind-html="result.topic.displayContent | to_trusted"></p><a href="javascript:void(0);" ng-click="expandTopicContent(result.topic)" ng-if="result.topic.commentListsExpandAble">显示全部</a>
				</div>
			</div>
			<div class="topic-footer">
				<ul class="topic-footer-ul">
					<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-heart"></span>关注问题</a></li>
					<li><a href="javascript:void(0);" ng-click="expandCommentLists(result.topic)"><span class="glyphicon glyphicon-comment"></span>评论</a></li>
					<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-thumbs-up"></span> 点赞</a></li>
					<li><a href="javascript:void(0);" ng-if="!result.topic.commentListsExpandAble" ng-click="collapseTopicContent(result.topic)"><span class="glyphicon glyphicon-eject" ></span> 收起</a></li>
				</ul>
				<div class="topic-comment-lists" >
					<div class="popover bottom topic-comment-list-{{result.topic.id}}" role="tooltip"  style="display:none">
					      <div class="arrow"></div>
					      <div class="popover-content">
					      		<div class="text-center" ng-hide="result.topic.loadCommentsSuccess">评论加载中...</div>
					      		<div class="topic-comment-list row" ng-repeat="comment in result.topic.commentLists.data" ng-show="result.topic.loadCommentsSuccess">
					      			<div class="topic-comment-body">
										<div class="topic-comment-body-author">
											<img alt="" ng-src="{{comment.author.img}}" ></img>
											<span >{{comment.author.firstName}}</span>
										</div>
										<div class="topic-comment-body-content">
											{{comment.content}}
										</div>
										<div class="topic-comment-body-attach-info">
											{{ comment.create_time | date : "y-dd-MM HH:mm:ss" }}
										</div>
									</div>
					      		</div>
					      		<wk-pagination ng-if="result.topic.loadCommentsSuccess" pagination-info="result.topic.commentLists.paging"  load-data="loadComments(result.topic)"></wk-pagination>
					      		<div class="">
					      			<textarea class="form-control" maxlength="1024" rows="3" required ng-model="title" ></textarea>
						        		<p>还可以输入个{{20000 - title.length}}字</p>
						        		<div class="text-right">
							        		<button type="submit" class="btn btn-default  " ng-click="createQuestion()">取消</button>
							        		<button type="submit" class="btn btn-default  " ng-click="createQuestion()">评论</button>
					      			</div>
					      		</div>
					      </div>
					 </div>
				</div>
			</div>
		</div>
	</div>
</div>