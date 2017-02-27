<%@page contentType="text/html" pageEncoding="UTF-8"%>
<article class="page-home">

	<div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-6">
			<div class="input-group page-home">
				<input type="text" class="form-control" ng-model="searchContent">
				<span class="input-group-btn">
					<button class="btn btn-default"  ng-disabled="searchContent == undefined || searchContent == null || searchContent.length <= 0" ng-click= "search()" type="button" ><span class="glyphicon glyphicon-search"></span></button>
				</span>
			</div><!-- /input-group -->
        </div>
	</div>
</article>
