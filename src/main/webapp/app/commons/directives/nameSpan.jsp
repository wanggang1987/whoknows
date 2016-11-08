<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="wk-name-span-directive">
	<img ng-src="{{user.picture || defaultPeopleImg}}" class="ng-cloak"></img>
	<span >{{user.email}}</span>
</div>