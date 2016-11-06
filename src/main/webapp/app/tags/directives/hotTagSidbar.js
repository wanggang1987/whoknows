
'use strict';

angular.module('wkTag').directive('hotTagSiderbar', function ($location, $log, $http, DEFAULT_IMG) {

	return {
		restrict: 'AE',
		templateUrl: 'app/tags/directives/hotTagSidbar',
		replace: true,
		scope: {
		},
		link: function (scope) {
			scope.currentPage = 1;
			scope.lastPage = false;
			
			var getTagLists = function(page, keyWord){
				var tagListUrl = "/hot/tag/" + page;
				if(keyWord != undefined && keyWord != null && $.trim(keyWord) != ''){
					tagListUrl = tagListUrl + "?keyWord=" + keyWord;
				}
				
				$http.get(tagListUrl).then(function(data){
					_.each(data.data, function(tag){
						if(tag.picture == undefined || tag.picture == null) {
							tag.picture = DEFAULT_IMG.TAG_NO_IMG;
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