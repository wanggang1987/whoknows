<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="forgot-password-modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">忘记密码</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-3 control-label text-center">邮箱地址</label>
					<div class="col-sm-9">
						<input type="email" required class="form-control forget-passwd-email-input"  placeholder="Email">
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn reset-passwd-btn">重置密码</button>
				<button type="button" class="btn btn-default cancel-btn" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->