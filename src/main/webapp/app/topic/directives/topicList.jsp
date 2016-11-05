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
				<ul>
					<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-heart"></span>关注问题</a></li>
					<li><a href="javascript:void(0);" ng-click="expandCommentLists(topic)"><span class="glyphicon glyphicon-comment"></span>评论</a></li>
					<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-thumbs-up"></span> 点赞</a></li>
					<li><a href="javascript:void(0);" ng-if="!result.topic.commentListsExpandAble" ng-click="collapseTopicContent(result.topic)"><span class="glyphicon glyphicon-eject" ></span> 收起</a></li>
				</ul>
				<div class="topic-comment-lists" >
					<div class="popover bottom topic-comment-list-{{topic.id}}" role="tooltip"  >
					      <div class="arrow"></div>
					      <div class="popover-content">
					      		<div class="topic-comment-list row" ng-repeat="comment in topic.commentLists">
					      			<div class="topic-comment-body">
										<div class="topic-comment-body-author">
											<img alt="" ng-src="{{comment.commentAuthor.img}}" ></img>
											<span >{{comment.commentAuthor.baseInfo}}</span>
										</div>
										<div class="topic-comment-body-content">
											{{comment.commentContent}}
										</div>
									</div>
					      		</div>
					      		 <ul uib-pagination total-items="bigTotalItems" ng-model="bigCurrentPage" max-size="maxSize" class="pagination-sm" boundary-link-numbers="true"></ul>
					      </div>
					 </div>
				</div>
				<div id="topic-pag-div"></div>
			</div>
		</div>
	</div>
</div>