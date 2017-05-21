<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul  class="wk-pagination text-center">
	<li ng-class="{disabled:paginationInfo.currentPage === 1}">
		<a ng-if="paginationInfo.currentPage !== 1" ng-click="setPage(paginationInfo.currentPage - 1)" href="javascript:void(0)">上页</a>
		<span ng-if="paginationInfo.currentPage == 1">上页</span>
	</li>
	<li ng-class="{disabled: paginationInfo.currentPage === 1}">
		<a ng-if="paginationInfo.currentPage !== 1" ng-click="setPage(1)" href="javascript:void(0)">1</a>
		<span ng-if="paginationInfo.currentPage == 1">1</span>
	</li>
	<li ng-if ="showLeftDo">...</li>

	<li ng-repeat="page in showPageArray" ng-class="{disabled:paginationInfo.currentPage === page}" >
		<a ng-if="paginationInfo.currentPage !== page" ng-click="setPage(page)" href="javascript:void(0)">{{page}}</a>
		<span ng-if="paginationInfo.currentPage == page">{{page}}</span>
	</li>

	<li ng-if ="showRightDo">...</li>                
	<li ng-if ="paginationInfo.totalPage > 1" ng-class="{disabled:paginationInfo.currentPage === paginationInfo.totalPage}">
		<a ng-if="paginationInfo.currentPage !== paginationInfo.totalPage" ng-click="setPage(paginationInfo.totalPage)" href="javascript:void(0)">{{paginationInfo.totalPage}}</a>
		<span ng-if="paginationInfo.currentPage == paginationInfo.totalPage">{{paginationInfo.totalPage}}</span>
	</li> 
	<li ng-class="{disabled:paginationInfo.currentPage === paginationInfo.totalPage}">
		<a ng-if="paginationInfo.currentPage !== paginationInfo.totalPage" ng-click="setPage(paginationInfo.currentPage + 1)" href="javascript:void(0)">下页</a>
		<span ng-if="paginationInfo.currentPage == paginationInfo.totalPage">下页</span>
	</li>
</ul>