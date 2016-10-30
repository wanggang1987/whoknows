
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
			scope.lastPage = false;
			
			var getTagLists = function(page, keyWord){
				var tagListUrl = "hot/tag/" + page;
				if(keyWord != undefined && keyWord != null && $.trim(keyWord) != ''){
					tagListUrl = tagListUrl + "?keyWord=" + keyWord;
				}
				
				$http.get(tagListUrl).then(function(data){
					_.each(data.data, function(tag){
						if(tag.picture == undefined || tag.picture == null) {
							tag.picture = "../../images/tag-no-img-default.png";
						}
					})
					scope.tags = data.data;
					scope.lastPage = scope.tags.length < 5;
				});
			}
			
			getTagLists(1);
			
			scope.searchTag = function(){
				scope.currentPage = 1;
				getTagLists(1, scope.hotTagSearchKeyWord);
				
			}
			
			scope.prePage = function(){
				scope.currentPage = scope.currentPage <= 1 ? 1 : scope.currentPage -1;
				getTagLists(scope.currentPage, scope.hotTagSearchKeyWord);
			}
			
			scope.nextPage = function(){
				scope.currentPage = scope.currentPage + 1;
				getTagLists(scope.currentPage, scope.hotTagSearchKeyWord);
			}
			
			
		}
	};

});