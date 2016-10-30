
'use strict';

angular.module('wkSuperstar').directive('hotSuperstarSiderbar', function ($location, $log, $http) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/leftSidebarHotSuperstar.html',
		replace: true,
		scope: {
			star: '='
		},
		link: function (scope) {
			scope.currentPage = 1;
			scope.lastPage = false;
			var getVipLists = function(page , keyWord){
				var vipListUrl = "hot/vip/" + page;
				if(keyWord != undefined && keyWord != null && $.trim(keyWord) != ''){
					vipListUrl = vipListUrl + "?keyWord=" + keyWord;
				}
				
				$http.get(vipListUrl).then(function(data){
					_.each(data.data, function(tag){
						if(tag.picture == undefined || tag.picture == null) {
							tag.picture = "../../images/people-no-img-default.png";
						}
					})
					scope.vips = data.data;
					scope.lastPage = scope.vips.length < 5;
				});
			}
			
			
			getVipLists(1, scope.keyWordVip);
			
			scope.searchHotVip = function(){
				scope.currentPage = 1;
				getVipLists(1, scope.keyWordVip);
			}
			
			scope.prePage = function(){
				scope.currentPage = scope.currentPage <= 1 ? 1 : scope.currentPage -1;
				getVipLists(scope.currentPage, scope.keyWordVip);
			}
			
			scope.nextPage = function(){
				scope.currentPage = scope.currentPage + 1;
				getVipLists(scope.currentPage, scope.keyWordVip);
			}
		}
	};

});