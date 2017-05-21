<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="admin-import-user-page">
	<div class="row">
		<div class="col-lg-12 text-left">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading text-center"><strong>导入用户</strong></div>
				<div class="" ng-show="step === 'STEP-1'">
					<h5 class="text-center">将需要导入的用户email粘贴到下面的文本框内。email格式: 每个email按照回车分割</h5>
					<div class="form-group">
						<label for="raw-passwd">初始密码*:</label>
						<input type="text" class="form-control" id="raw-passwd" placeholder="初始密码"  ng-model ="rawPassword">
					</div>
					<div class="form-group">
						<label for="raw-email-txt">邮箱列表*(<small>每个email按照回车分割, 系统只会解析有效的邮箱地址</small>):</label>
						<textarea ng-model="rawText" class="form-control" id="raw-email-txt" placeholder="邮箱列表" rows="15"></textarea>
					</div>

					<div class="text-right">
						<button class="btn btn-default" ng-disabled="!rawText || rawText.length  == 0 || !rawPassword || rawPassword.length == 0" ng-click="parseRawText()"><span class="glyphicon glyphicon-arrow-right"></span>下一步</button>
					</div>
				</div>
				<!-- Table -->
				<table class="table" ng-show="step === 'STEP-2'">
					<thead>
						<tr>
							<th>邮箱</th>
							<th>密码</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in users">
							<td><input type="text" ng-model="user.email"> </td>
							<td><input type="text" ng-model="user.passwd"> </td>
							<td><button class="btn btn-default" ng-click="deleteUser(user)"><span class="glyphicon glyphicon-trash"></span>删除用户</button></td>
						</tr>
						<tr>
							<td colspan="3">
								<button class="btn btn-default" ng-click="turnToStep1()"><span class="glyphicon glyphicon-arrow-left"></span>上一步</button>
								<button class="btn btn-default" ng-click="addUser()"><span class="glyphicon glyphicon-plus"></span>添加用户</button>
								<button class="btn btn-default" ng-disabled ="users.length == 0" ng-click="submitUser()"><span class="glyphicon glyphicon-floppy-saved"></span>提交</button>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="alert alert-danger" ng-show="error" ng-click="closeAlert()">
					<strong>提交失败请重试。</strong>
				</div>
				<div class="alert alert-success" ng-show="success" ng-click="closeAlert()">
					<strong>提交成功。</strong>
				</div>
			</div>
		</div>
	</div>
	
</div>
