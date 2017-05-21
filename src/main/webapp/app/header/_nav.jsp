<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="nav navbar-nav navbar-right wk-nav" >
	<!-- <li ng-class="{ active: isActive('/') }"><a href="#/">首页</a></li> -->
	<li ng-class="{ active: isActive('/topic') }"><a href="#/topic" >话题</a></li>
	<li ng-class="{ active: isActive('/vipDetailPage') }"><a href="#/vipDetailPage"  >大咖</a></li>
		<sec:authorize access="isAuthenticated()">
		<li class="fill-maring-left-100" >
			<div class="form-group index-create-question-btn">
				<button type="submit" class="btn btn-default " ng-click="createQuestion()">提问</button>
			</div>
		</li>
	</sec:authorize>
	<sec:authorize access="hasAuthority('SITE_VIP')">	
		<li class="fill-maring-left-20" >
			<div class="form-group index-create-question-btn">
				<button type="submit" class="btn btn-default " ng-click="createVipDoc()">发表文章</button>
			</div>
		</li>
	</sec:authorize>
	<li class="fill-maring-left-100" ng-hide="loginIn" ng-class="{ active: isActive('/login') }"><a href="#/login" >注册/登录</a></li>

		<li class="fill-maring-left-100 dropdown" ng-show="loginIn" ng-class="{ active: isActive('/selfPage') }">
			<a href="#/selfPage" data-toggle="dropdown" class="wk-header-account-a">
				<img alt="" class="img-20-size" ng-src="{{user.picture|| defaultPeopleImg}}"></img> <span class="ng-cloak">{{user| formateName }}</span>
			</a>
			<ul class="dropdown-menu wk-header-account-dropdown" role="menu" aria-labelledby="dLabel">
				<sec:authorize access="isAuthenticated()">
					<li><a href="#/selfPage" ><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;我的主页</a></li>
					<li><a href="#/setting" ><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;设置</a></li>
				</sec:authorize>
				<sec:authorize access="hasAuthority('SITE_ADMIN')">
					<li><a href="#/admin/import/user" ><span class="glyphicon glyphicon-open"></span>&nbsp;&nbsp;导入用户</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li><a href="/logout" ><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;退出</a></li>
				</sec:authorize>
			</ul>

		</li>
</ul>