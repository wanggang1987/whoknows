<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container login-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
        <div class="col-sm-6 login-panel-parent" >
			<div class="panel login-panel">
				<div class="panel-heading text-center">老客户</div>
				<div class="panel-body">
					<h5>如果您已创建过账号，请在此登录：</h5>
					<div class="alert alert-danger alert-dismissible" role="alert" ng-show="loggingError">
						<button type="button" class="close" data-dismiss="alert" ng-click="closeWarn()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<strong>登录失败! 用户名或密码错误</strong> 
					</div>
					<form class="form-horizontal" role="form" name="loginForm">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label">邮箱地址</label>
							<div class="col-sm-9">
								<input type="email" required class="form-control login-email-input"  ng-model="loginInfo.username" placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<div class="checkbox">
									<label>
										<input type="checkbox"> 保存用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" required  class="col-sm-3 control-label">密码</label>
							<div class="col-sm-9">
								<input type="password" class="form-control  login-passwd-input" id="inputPassword3" ng-model="loginInfo.password"  placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<span class="form-instructions hint">
									<!-- <a href="javascript:void(0)" ng-click="forgotPasswordRequest()"><span >忘记密码</span>?</a> 
									<br>-->
									<span data-prop-message="" title="login.help">如果您还需要其他帮助，<a href="#/contact">请联系我们的客服</a>。</span>
								</span>
							</div>
						</div>
						<div class="form-group panel-footer">
							<div class="col-sm-offset-4 col-sm-10">
								<button type="submit" ng-click="login()" ng-disabled="loginForm.$invalid" class="btn btn-default login-btn">登录</button>
							</div>
						</div>
					</form>
				</div><!--end-panel-body -->
			</div><!-- end-panel -->        		
        </div><!--/.col-xs-12.col-sm-9-->
		<div class="col-sm-1"></div>
        <div class="col-sm-5 regist-panel-parent" >
			<div class="panel login-panel">
				<div class="panel-heading text-center">新客户</div>
				<div class="panel-body">
					<h5>请点击相应按钮进行注册。</h5>	 
					<div class="form-group">
						<div class="col-sm-offset-4 col-sm-10">
							<button type="submit" class="btn btn-default regist-btn" ng-click="regist()">注册</button>
						</div>
					</div>
					<p><span data-prop-message="" title="login.newCustRegComp">如果您还需要其他帮助，请<a href="#/contact">联系我们的客服</a>。</span></p>
				</div>

			</div>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->
	<div wk-forgot-password></div>
</div>