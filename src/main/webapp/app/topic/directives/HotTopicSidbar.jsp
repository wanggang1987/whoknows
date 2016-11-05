<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <div class="panel panel-default hot-topic-siderbar">
	  <div class="panel-heading text-center">热门标签</div>
	  <div class="panel-body">
		 <div class="input-group">
		      <input type="text" class="form-control" placeholder="搜索您感兴趣的标签" ng-model="hotTagSearchKeyWord">
		      <span class="input-group-btn">
		        <button class="btn btn-default " type="button" ng-click="searchTag()"><span class="glyphicon glyphicon-search"></span></button>
	     	  </span>
	    	 </div>
	    	 <div class="list-group">
	    	 	<div class="list-group-item row" ng-repeat="tag in tags">
	    	 		<div class="col-md-3"><img alt="" ng-src="{{tag.picture}}" ng-cloak></img></div>
	    	 		<div class="col-md-9">
	    	 			<a href="#"><h5 class="list-group-item-heading" ng-bind-html="tag.tagName | to_trusted"></h5></a>
			    		<p class="list-group-item-text">关注人数:{{tag.follow}}</p>
			    	</div>
	    	 	</div>
	    	 	<div class="list-group-item text-center see-more-topic">
		    	 	<a href="javascrpit:void(0)" ng-click="prePage()" ng-hide="currentPage == 1">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    	 	<a href="javascrpit:void(0)" ng-click="nextPage()" ng-hide="lastPage" >下一页</a>
	    	 	</div>
		  </div>
	  </div>
 </div>