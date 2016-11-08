
'use strict';

angular.module('wkSuperstar').directive('hotSuperstarSiderbar', function ($location, $log, $http, DEFAULT_IMG) {

	return {
		restrict: 'AE',
		templateUrl: 'app/superstar/directives/leftSidebarHotSuperstar',
		replace: true,
		scope: {
			star: '='
		},
		link: function (scope) {
			scope.currentPage = 1;
			scope.lastPage = false;
			scope.defaultPeopleImg = DEFAULT_IMG.PEOPLE_NO_IMG;
			var getVipLists = function(page , keyWord){
				var vipListUrl = "/hot/vip/" + page;
				if(keyWord != undefined && keyWord != null && $.trim(keyWord) != ''){
					vipListUrl = vipListUrl + "?keyWord=" + keyWord;
				}
				
				$http.get(vipListUrl).then(function(data){
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