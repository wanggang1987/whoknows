
'use strict';

angular.module('wkTopic').directive('hotTopicSiderbar', function ($location, $log, $http) {

	return {
		restrict: 'AE',
		templateUrl: 'app/topic/directives/HotTopicSidbar.html',
		replace: true,
		scope: {
		},
		link: function (scope) {
			scope.currentPage = 1;
			
			var getTopicLists = function(page, keyWord){
				if(keyWord == undefined || keyWord == null){
					keyWord = "";
				}
				
				$http.get("hot/topic/" + page +"?keyWord=" + keyWord).then(function(data){
					scope.topics = data.data;
				});
			}
			
			getTopicLists(1);
			
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