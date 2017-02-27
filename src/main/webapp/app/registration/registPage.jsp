<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container login-page"> 
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="panel login-panel regist-panel">
			<div class="panel-heading text-center">注册</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" name="registForm">
					<div class="alert alert-danger alert-dismissible" role="alert" ng-show="registError">
						<button type="button" class="close" data-dismiss="alert" ng-click="closeRegistErrorWarn()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						<strong>注册失败!</strong> 
					</div>
					<div class="alert alert-success alert-dismissible" role="alert" ng-show="registSuccess">
						<strong>注册成功!我们已经向您的邮箱：{{registInfo.email}} 发送了一封激活邮件.请注意查收。</strong> 
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">邮箱地址</label>
						<div class="col-sm-9">
							<input type="email" required class="form-control login-email-input"  ng-model="registInfo.email" placeholder="Email">
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" required class="col-sm-3 control-label" >密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control  login-passwd-input" ng-model="registInfo.passwd" id="inputPassword3" placeholder="Password">
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" required class="col-sm-3 control-label">重复密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control  login-passwd-input" ng-model="registInfo.passwordAgain" id="inputPassword3" placeholder="Password">
							<p class="warn-color-font" ng-show="registInfo.passwordAgain != registInfo.passwd" >*两次密码不一致</p>
						</div>
					</div>

					<!--  <div class="form-group">
					  <label for="inputPassword3"  class="col-sm-3 control-label">由朋友或同事推荐？</label>
					  <div class="col-sm-8 regise-friends-checkbox">
						<div class="checkbox " >
						  <input type="checkbox"   ng-model= "recommendByFriends" ng-model="registInfo.recommendByFriends"> 
						</div>
					  </div>
					  <div class="col-sm-1"></div>
					</div>
					
					<div class="form-group" ng-show = "recommendByFriends" >
					  <label for="inputEmail3" class="col-sm-3 control-label">推荐人Email</label>
					  <div class="col-sm-9">
						<input type="email"  class="form-control login-email-input" ng-model="registInfo.recommendEmail" placeholder="Email">
					  </div>
					</div>
					<div class="form-group" ng-show = "recommendByFriends" >
					  <label for="inputEmail3" class="col-sm-3 control-label">推荐人姓名</label>
					  <div class="col-sm-9">
						<input type=text  class="form-control login-email-input"  ng-model="registInfo.recommendName" placeholder="姓名">
					  </div>
					</div> -->
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label">您是从什么渠道了解到我们的？</label>
						<div class="col-sm-7">
							<select class="form-control get-us-by">
								<option selected="true" class="" selected="selected">--请选择--</option>
								<option label="Facebook" value="string:registration.heardAboutUs.facebook">Facebook</option>
								<option label="LinkedIn" value="string:registration.heardAboutUs.linkedIn">LinkedIn</option>
								<option label="Weibo" value="string:registration.heardAboutUs.weibo">Weibo</option>
								<option label="展会" value="string:registration.heardAboutUs.tradeshow">展会</option>
								<option label="推特" value="string:registration.heardAboutUs.twitter">推特</option>
								<option label="搜索引擎" value="string:registration.heardAboutUs.searchEngine">搜索引擎</option>
								<option label="朋友/同事" value="string:registration.heardAboutUs.friend">朋友/同事</option>
								<option label="简报" value="string:registration.heardAboutUs.newsletter">简报</option>
								<option label="邮件" value="string:registration.heardAboutUs.directEmail">邮件</option>
								<option label="其它" value="string:registration.heardAboutUs.other">其它</option>
							</select>
						</div>
						<div class="col-sm-2"></div>
					</div>

					<div class="form-group panel-footer">
						<div class="col-sm-offset-4 col-sm-10">
							<button type="submit" class="btn btn-default login-btn" ng-disabled="registForm.$invalid || registInfo.passwd != registInfo.passwordAgain || registSuccess || processing" ng-click="regist()">提交</button>
							<button type="submit" class="btn btn-default cancel-btn">取消</button>
						</div>
					</div>
				</form>
			</div><!--end-panel-body -->
		</div>        		
	</div> 
</div>