<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container setting-page">

	<div class="row">
		<ul id="self-home-page-tab" class="nav nav-tabs">
			<li class="active">
				<a data-target="#base-info-setting" data-toggle="tab">基本信息 </a>
			</li>
			<li><a data-target="#account-passwd-setting" data-toggle="tab">账号密码</a></li>
				<sec:authorize access="hasAuthority('SITE_VIP')">	
				<li><a data-target="#self-profile-setting" data-toggle="tab">个人简历</a></li>
				</sec:authorize>
			<!-- <li><a data-target="#message-setting" data-toggle="tab">消息设置</a></li> -->
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade in active" id="base-info-setting">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right">姓</label>
							<div class="col-sm-9">
								<input type="text" required class="form-control login-email-input"  ng-model = "setUser.lastName" placeholder="姓名" >
							</div>
						</div>
					</div><!-- end-col -->
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right">名</label>
							<div class="col-sm-9">
								<input type="text" required class="form-control login-email-input"  ng-model = "setUser.firstName" placeholder="姓名" >
							</div>
						</div>
					</div><!-- end-col -->
				</div><!-- end-row -->
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right">目前就职</label>
							<div class="col-sm-9">
								<input type="text" required class="form-control login-email-input"  ng-model = "setUser.companyName" placeholder="目前就职" >
							</div>
						</div>
					</div><!-- end-col -->
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right" >教育信息</label>
							<div class="col-sm-9">
								<input type="text" required class="form-control login-email-input"  ng-model = "setUser.education" placeholder="教育信息" >
							</div>
						</div>
					</div><!-- end-col -->
				</div><!-- end-row -->
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right">职位名称</label>
							<div class="col-sm-9">
								<input type="text" required class="form-control login-email-input"  ng-model = "setUser.title" placeholder="职位" >
							</div>
						</div>
					</div><!-- end-col -->
					<div class="col-lg-6">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right" >我的签名</label>
							<div class="col-sm-9">
								<textarea class="form-control" rows="3" required ng-model="setUser.signature"></textarea>
							</div>
						</div>
					</div>
					<!-- <div class="col-lg-6">
						<label for="inputEmail3" class="col-sm-3 control-label text-right">隐私保护</label>
						<div class="checkbox col-sm-9">
					  <label>
						<input type="checkbox" name="optionsRadios" id="optionsRadios1" value="option1" checked>
						在站内搜索到我在该平台创作的内容时，我的姓名将不会被显示。
					  </label>
					</div>
					</div>  -->
				</div><!-- end-row -->
				<div class="row">
					<div class="alert alert-success alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSaveBaseInfoSuccess">
						<button type="button" class="close" data-dismiss="alert" ng-click="closeBaseInfoSuccessPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<strong>基本信息保存成功！</strong>
					</div>
					<div class="alert alert-danger alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSaveBaseInfoError">
						<button type="button" class="close" data-dismiss="alert" ng-click="closeBaseInfoWarnPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<strong>基本信息保存失败！</strong>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-10"></div>
					<div class="col-lg-2"> <button type="submit" class="btn btn-primary text-center" ng-click="saveBaseInfo()">保存</button></div>
				</div><!-- end-row -->
			</div> 
			<div class="tab-pane fade" id="account-passwd-setting">
				<div class="row">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label text-right">绑定邮箱</label>
						<div class="col-sm-9">
							<input type="text" required disabled class="form-control login-email-input"  ng-model = "setUser.email" placeholder="email" >
						</div>
					</div>
				</div><!-- end-row -->
				<div class="row">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-3 control-label text-right">手机</label>
						<div class="col-sm-9">
							<input type="text" required class="form-control login-email-input"  ng-model = "setUser.phone" placeholder="手机" >
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right" >账号密码</label>
							<div class="col-sm-9">
								<a href="" ng-click="resetPasswd()">修改密码</a>
							</div>
						</div>
					</div><!-- end-col -->
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-3 control-label text-right">社交账号绑定</label>
							<div class="col-sm-2">
								<span class="icon wechat"></span>
								<a href="javascript:void(0)">绑定微信</a>
							</div>
							<div class="col-sm-2">
								<span class="icon weibo"></span>
								<a href="javascript:void(0)">绑定微博</a>
							</div>
							<div class="col-sm-2">
								<span class="icon qq"></span>
								<a href="javascript:void(0)">绑定QQ</a>
							</div>
							<div class="col-sm-3">
							</div>
						</div>
					</div><!-- end-col -->
				</div>
				<div class="row">
					<div class="alert alert-success alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSavePassInfoSuccess">
						<button type="button" class="close" data-dismiss="alert" ng-click="closePassInfoSuccessPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<strong>基本信息保存成功！</strong>
					</div>
					<div class="alert alert-danger alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSavePassInfoError">
						<button type="button" class="close" data-dismiss="alert" ng-click="closePassInfoWarnPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<strong>基本信息保存失败！</strong>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-10"></div>
					<div class="col-lg-2"> <button type="submit" class="btn btn-primary text-center" ng-click="savePassInfo()">保存</button></div>
				</div><!-- end-row -->
			</div>
			<sec:authorize access="hasAuthority('SITE_VIP')">	
				<div class="tab-pane fade" id="self-profile-setting">
					<div class="row">
						<div class="col-lg-1">
							我的简历:
						</div>
						<div class="col-lg-9">
							<textarea ui-tinymce="tinymceOptions1" required ng-model="setUser.profile"   class="tea"/></textarea>
						</div>
						<div class="col-lg-2">
						</div>
					</div>
					<div class="row">
						<div class="alert alert-success alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSaveProfileInfoSuccess">
							<button type="button" class="close" data-dismiss="alert" ng-click="closeProfileInfoSuccessPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
							<strong>简历信息保存成功！</strong>
						</div>
						<div class="alert alert-danger alert-dismissible fade in wk-warn-panel" role="alert" ng-show="showSaveProfileInfoError">
							<button type="button" class="close" data-dismiss="alert" ng-click="closeProfileInfoWarnPanel()"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
							<strong>简历信息保存失败！</strong>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-10"></div>
						<div class="col-lg-2"> <button type="submit" class="btn btn-primary text-center" ng-click="saveProfileInfo()">保存</button></div>
					</div><!-- end-row -->	
				</div>	
			</sec:authorize>
			<!--  <div class="tab-pane fade" id="message-setting">
				 <div class="row">
						 <div class="col-lg-3">
							  <div class="form-group">
							 <div class="radio">
								 <label>
									 <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
									 允许所有人给我发消息
								 </label>
							 </div>
						 </div>
						 </div>end-col
						 <div class="col-lg-3">
							  <div class="form-group">
							 <div class="radio">
								 <label>
									 <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
									 只允许注册用户给我发消息
								 </label>
							 </div>
						 </div>
						 </div>end-col
						 <div class="col-lg-3">
							  <div class="form-group">
							 <div class="radio">
								 <label>
									 <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
									 只允许我关注的人给我发消息
								 </label>
							 </div>
						 </div>
						 </div>end-col
						 <div class="col-lg-3"></div>
					 </div>end-row
					 <div class="row">
						 <div class="col-lg-6">
							  <div class="form-group">
							 <div class="checkbox">
								 <label>
									 <input type="checkbox" name="optionsRadios" id="optionsRadios1" value="option1" checked>
									 设置消息提醒（与邮箱绑定）
								 </label>
							 </div>
						 </div>
						 </div>end-col
						 <div class="col-lg-6"></div>
					 </div>
					 <div class="row">
						 <div class="col-lg-6">
							  <div class="form-group">
							 <div class="checkbox">
								 <label>
									 <input type="checkbox" name="optionsRadios" id="optionsRadios1" value="option1" checked>
									 接受每周精选邮件
								 </label>
							 </div>
						 </div>
						 </div>end-col
						 <div class="col-lg-6"></div>
					 </div>end-row
					 <div class="row">
						 <div class="col-lg-10"></div>
						 <div class="col-lg-2"> <button type="submit" class="btn btn-primary text-center">保存</button></div>
					 </div>end-row
					 </div>end-row -->

		</div>
	</div>
</div><!--/row-->
<wk-reset-password></wk-reset-password>

</div>
<wk-img-upload-modal callback-function="uploadImgSuccess(imgId)"></wk-img-upload-modal>
