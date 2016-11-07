<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="nav navbar-nav navbar-right wk-nav" >
            		<li ng-class="{ active: isActive('/') }"><a href="#/">首页</a></li>
				<li ng-class="{ active: isActive('/topic') }"><a href="#/topic" >话题</a></li>
				<li ng-class="{ active: isActive('/superStar') }"><a href="#/superStar"  >大咖</a></li>
				<li ng-class="{ active: isActive('/message') }"><a href="#/message"  >消息</a></li>
				<sec:authorize access="isAuthenticated()">
					<li class="fill-maring-left" ng-show="loginIn">
						<div class="form-group index-create-question-btn">
							<button type="submit" class="btn btn-default " ng-click="createQuestion()">提问</button>
						</div>
					</li>
				</sec:authorize>
              	<li class="fill-maring-left" ng-hide="loginIn" ng-class="{ active: isActive('/login') }"><a href="#/login" >注册/登录</a></li>
              	<sec:authorize access="isAuthenticated()">
	              	<li class="fill-maring-left dropdown" ng-show="loginIn" ng-class="{ active: isActive('/selfPage') }">
		              	<a href="#/selfPage" data-toggle="dropdown" >
		              		<img alt="" class="img-20-size" ng-src="{{user.picture || defaultPeopleImg}}"></img> <span class="ng-cloak">{{user.email}}</span>
		              		<span class="glyphicon glyphicon-chevron-down"></span>
		              	</a>
		              	<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						  	<li><a href="#/selfPage" ><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;我的主页</a></li>
						  	<li><a href="#/setting" ><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;设置</a></li>
						  	<li><a href="/logout" ><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;退出</a></li>
						</ul>
	              	</li>
            		</sec:authorize>
            </ul>