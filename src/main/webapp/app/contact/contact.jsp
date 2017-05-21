<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="contact-us-page">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8 text-left">
			<p ng-bind-html="content | to_trusted"></p>
		</div>
		<div class="col-lg-2"></div>
	</div>

</div>
