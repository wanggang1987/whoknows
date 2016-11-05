<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<article class="page-home">

<div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-6">
		    <div class="input-group">
		      <input type="text" class="form-control" ng-model="searchContent">
		      <span class="input-group-btn">
		        <button class="btn btn-default"  ng-disabled="searchContent == undefined || searchContent == null || searchContent.length <= 0 " ng-click= "search()" type="button" ><span class="glyphicon glyphicon-search"></span></button>
		      </span>
		    </div><!-- /input-group -->
		    <div class="home-page-link">
		    		<div>
		    			<a href="#/">传播</a>&nbsp;|&nbsp;
			    		<a href="#/">连接</a>&nbsp;|&nbsp;
			    		<a href="#/">分享</a>
		    		</div>
		    		
		    </div>
        </div>
 </div>
</article>
