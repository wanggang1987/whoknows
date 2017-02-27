<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="reset-password-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">重置密码</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">

					<div class="form-group">
						<label for="inputPassword3" required class="col-sm-3 control-label" >密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control  login-passwd-input" ng-model="resetPasswdInfo.oldPasswd" id="inputPassword3" placeholder="Password">
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" required class="col-sm-3 control-label" >密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control  login-passwd-input" ng-model="resetPasswdInfo.newPasswd" id="inputPassword3" placeholder="Password">
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" required class="col-sm-3 control-label">重复密码</label>
						<div class="col-sm-9">
							<input type="password" class="form-control  login-passwd-input" ng-model="resetPasswdInfo.repeatNewPasswd" id="inputPassword3" placeholder="Password">
							<p class="warn-color-font" ng-show="resetPasswdInfo.repeatNewPasswd != resetPasswdInfo.newPasswd" >*两次密码不一致</p>
						</div>
					</div>
				</form>
			</div>
			<div class="row text-center">
				<p class="warn-color-font" ng-hide="resetPasswdSuccess" >*修改密码失败</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn reset-passwd-btn" ng-click="resetPasswd()">重置密码</button>
				<button type="button" class="btn btn-default cancel-btn" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->