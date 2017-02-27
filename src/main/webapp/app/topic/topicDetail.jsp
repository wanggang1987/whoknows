<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container topic-detail-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
			<h2 class="text-center"><p ng-bind-html="topic.topic.title | to_trusted"></p> </h2>
			<h5 class="font-gray-color">{{topic.topic.create_time| date : "y-MM-dd HH:mm:ss"}}&nbsp;&nbsp;{{topic.author| formateName}}, {{topic.author.companyName}},{{topic.author.title}}</h5>
			<h5 class="font-gray-color"> <a class="font-gray-color" href="javascript:void(0);" ng-click="fllowTopic(topic)"><span class="glyphicon glyphicon-heart"></span><span ng-hide="topic.currentFollowed">关注问题</span><span ng-show="topic.currentFollowed">取消关注</span></a>({{topic.followCount}})</h5>
			<p ng-bind-html="topic.topic.content | to_trusted"></p>
			<hr />

			<div class="topic-list">
				<div class="row" ng-repeat="reply in topic.replys"> 
					<div class="col-xs-20 col-sm-12"> 
						<div class="topic-body" >
							<div class="topic-body-author">
								<img ng-src="{{reply.author.picture|| defaultPeopleImg}}" class="ng-cloak"></img>
								<wk-name-span user="reply.author"></wk-name-span>
							</div>
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
			<sec:authorize access="isAuthenticated()">
				<div class="row margin-top-70">
					<textarea ui-tinymce="tinymceOptions1" required ng-model="content"   class="tea"/></textarea>
				</div>
				<div class="form-group text-right">
					<button type="submit" class="btn btn-default default-wk-blue-btn" ng-disabled=" content == undefined || content == null && content.length == 0" ng-click="replyQuestion()">回答</button>
				</div>
			</sec:authorize>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>

<wk-img-upload-modal callback-function="uploadImgSuccess(imgId)"></wk-img-upload-modal>


