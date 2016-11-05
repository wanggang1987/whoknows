<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="WhoKnows">
<head>
<!-- 检测浏览器 -->
<script type="text/javascript" src="/app/commons/browserdcheck.js"></script>
<title>LabNetwork knows</title>
<link href="/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!--  -->
<link href="/styles/main.css" rel="stylesheet" media="screen" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="who knows" />
<meta name="keywords" content="who knows,whoknows,whoKnows,WhoKnows,who,knows" />
</head>
<body ng-controller="wkCommon.appCtrl">
	<wk-side-bar></wk-side-bar>
	<nav class="navbar navbar-default" role="navigation" >
        <div class="container-fluid">
          <div class="navbar-header"  >
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#/" >
            		<span style="font-size:12px">Labnetwork</span><br> <span style="font-size:20px">Knows</span>
            	</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right wk-nav" >
            		<li ng-class="{ active: isActive('/') }"><a href="#/">首页</a></li>
				<li ng-class="{ active: isActive('/topic') }"><a href="#/topic" >话题</a></li>
				<li ng-class="{ active: isActive('/superStar') }"><a href="#/superStar"  >大咖</a></li>
				<li ng-class="{ active: isActive('/message') }"><a href="#/message"  >消息</a></li>
				<sec:authorize access="isFullyAuthenticated()">
					<li class="fill-maring-left" ng-show="loginIn">
						<div class="form-group index-create-question-btn">
							<button type="submit" class="btn btn-default " ng-click="createQuestion()">提问</button>
						</div>
					</li>
				</sec:authorize>
              	<li class="fill-maring-left" ng-hide="loginIn" ng-class="{ active: isActive('/login') }"><a href="#/login" >注册/登录</a></li>
              	<sec:authorize access="isFullyAuthenticated()">
	              	<li class="fill-maring-left dropdown" ng-show="loginIn" ng-class="{ active: isActive('/selfPage') }">
		              	<a href="#/selfPage" data-toggle="dropdown" >
		              		<img alt="" class="img-20-size" ng-src="{{user.picture}}" ng-cloak></img> {{user.email}}
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
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

	<div class="header-fill"></div>

	<div class="app-content">
		<div ng-view autoScroll="true"></div>
	</div>
	<footer>
      <div class="container">
        <p class="text-muted">LabNetwork knows. © Company 2016</p>
      </div>
    </footer>

	<script type="text/javascript" src="/bower_components/jquery/dist/jquery.min.js"></script>
	<script type="text/javascript" src="/bower_components/jquery.placeholder/jquery.placeholder.min.js"></script>
	<script type="text/javascript" src="/bower_components/angular/angular.min.js"></script>
	<script type="text/javascript" src="/bower_components/angular-cookies/angular-cookies.js"></script>
	<script type="text/javascript" src="/bower_components/lodash/lodash.min.js"></script>
	<script type="text/javascript" src="/bower_components/angular-filter/dist/angular-filter.min.js"></script>
	<script type="text/javascript" src="/bower_components/angular-resource/angular-resource.js"></script>
	<script type="text/javascript" src="/bower_components/angular-route/angular-route.js"></script>
	<script type="text/javascript" src="/bower_components/angular-sanitize/angular-sanitize.js"></script>
	<script type="text/javascript" src="/bower_components/bootstrap/js/dropdown.js"></script>
	<script type="text/javascript" src="/bower_components/bootstrap/js/modal.js"></script>
	<script type="text/javascript" src="/bower_components/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="/bower_components/angular-ui-tinymce/dist/tinymce.min.js"></script>
	<!-- self app js  -->
	<script type="text/javascript" src="/app/app.js"></script>
	<script type="text/javascript" src="/app/commons/commonModule.js"></script>
	<script type="text/javascript" src="/app/commons/filters/trustAsHtmlFilter.js"></script>
	<script type="text/javascript" src="/app/setting/settingModule.js"></script>
	<script type="text/javascript" src="/app/searchResult/searchResultModule.js"></script>
	<script type="text/javascript" src="/app/searchResult/searchResultCtrl.js"></script>
	<script type="text/javascript" src="/app/commons/appCtrl.js"></script>
	<script type="text/javascript" src="/app/commons/services/localStorageService.js"></script>
	<script type="text/javascript" src="/app/commons/factories/permissionCheckInterceptor.js"></script>
	<script type="text/javascript" src="/app/homeCtrl.js"></script>
	<script type="text/javascript" src="/app/users/selfHomeCtrl.js"></script>
	<script type="text/javascript" src="/app/topic/topicModule.js"></script>
	<script type="text/javascript" src="/app/topic/wkTopicCtrl.js"></script>
	<script type="text/javascript" src="/app/topic/directives/hotTopicSiderbar.js"></script>
	<script type="text/javascript" src="/app/topic/directives/topicList.js"></script>
	<script type="text/javascript" src="/app/superstar/superstarModule.js"></script>
	<script type="text/javascript" src="/app/superstar/wkSuperstarCtrl.js"></script>
	<script type="text/javascript" src="/app/superstar/directives/leftSidebarHotSuperstar.js"></script>
	<script type="text/javascript" src="/app/commons/directives/side-bar.js"></script>
	<script type="text/javascript" src="/app/login/loginModule.js"></script>
	<script type="text/javascript" src="/app/login/loginCtrl.js"></script>
	<script type="text/javascript" src="/app/login/directives/forgetPasswdDirective.js"></script>
	<script type="text/javascript" src="/app/registration/registModule.js"></script>
	<script type="text/javascript" src="/app/registration/registCtrl.js"></script>
	<script type="text/javascript" src="/app/setting/settingCtrl.js"></script>
	<script type="text/javascript" src="/app/commons/services/userService.js"></script>
	<script type="text/javascript" src="/app/setting/directives/resetPasswdDirective.js"></script>
	<script type="text/javascript" src="/app/topic/createTopicCtrl.js"></script>
	<script type="text/javascript" src="/app/topic/topicDetailCtrl.js"></script>
</body>
</html>
