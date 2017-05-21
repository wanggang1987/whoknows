<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container regist-topic-select-page"> 
	<div class="row text-center">
		<h3>你想关注哪些话题和热门大咖?</h3>
		<h5>我们将根据你关注的话题定制首页推荐内容</h5>
	</div>
	<div class="hot-topic-list">
		<div class="row">
			<div class="col-lg-8 text-left"><h4>热门话题</h4></div>
			<div class="col-lg-4 text-left"><a ng-click="randomTag()" href="javascript:void(0)">换一组</a></div>
		</div>
		<div class="row">
			<div class="col-lg-2 col-md-2" ng-repeat="tag in tags">
				<img alt="" ng-src="{{tag.picture|| defaultTagImg}}" />
				<label class="checkbox-inline" >
					<input type="checkbox"  ng-model="regist.tags[tag.tagID]" value="tag.tagID" class="ng-cloak"> {{tag.tagName}}<br/><span class="font-gray-color">(关注人数：{{tag.followCount}})</span>
				</label>
			</div>
		</div>
	</div> 
	<hr/>
	<div class="hot-superstar-list">
		<div class="row">
			<div class="col-lg-8 text-left"><h4>热门大咖</h4></div>
			<div class="col-lg-4 text-left"><a ng-click="randomVip()" href="javascript:void(0)">换一组</a></div>
		</div>

		<div class="row">
			<div class="col-lg-2 col-md-2" ng-repeat="vip in vips">
				<img alt="" ng-src="{{vip.picture|| defaultPeopleImg}}" />
				<label class="checkbox-inline" >
					<input type="checkbox"  ng-model="regist.vips[vip.userID]"  value="vip.userID" class="ng-cloak">{{vip.name}}<span class="font-gray-color"><br/>(关注人数：{{vip.followCount}})</span>
				</label>
			</div>
		</div>

	</div>
	<div class="row text-center">
		<button type="submit" class="btn btn-default default-wk-blue-btn" ng-disabled="disabledSubmit()"  ng-click="enterWk()" >完成注册</button>
	</div> 
</div>