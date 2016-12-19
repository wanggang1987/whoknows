<%@page contentType="text/html" pageEncoding="UTF-8"%>

<article class="page-error">
	<div class="error">
		<h2 view-title><s:message code="error.page.title"/></h2>
		<div class="alert alert-danger">
			<s:message code="error.page.line1"/>
			</br>
			<s:message code="error.page.line2"/>
		</div>
		<button class="btn btn-orange" ng-click="go('/')"><s:message code="error.page.return"/></button>
	</div>
</article>
