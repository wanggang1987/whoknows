<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container self-home-page">

	<div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
			<wk-user-profile ng-if="visitUser" user="visitUser" enable-edit-user="false"></wk-user-profile>
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" role="navigation">
			<hot-tag-siderbar topic="ttttopic"></hot-tag-siderbar>
			<hot-superstar-siderbar star="str"></hot-superstar-siderbar>
        </div><!--/.sidebar-offcanvas-->
	</div><!--/row-->

</div>
<wk-crop-img-modal callback-function="refreshImg(imgId)"></wk-crop-img-modal>