
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
			
			var getTopicLists = function(page , keyWord){
				if(keyWord == undefined || keyWord == null){
					keyWord = "";
				}
				$http.get("hot/vip/" + page +"?keyWord=" + keyWord).then(function(data){
					scope.vips = data.data;
				});
			}
			
			
			getTopicLists(1);
			
			scope.searchHotTopic = function(){
				
			}
			
			scope.prePage = function(){
				scope.currentPage = scope.currentPage <= 1 ? 1 : scope.currentPage -1;
				getTopicLists(scope.currentPage);
			}
			
			scope.nextPage = function(){
				scope.currentPage = scope.currentPage + 1;
				getTopicLists(scope.currentPage);
			}
		}
	};

});