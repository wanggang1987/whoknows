'use strict';

angular.module('WhoKnows').directive('wkPagination', function ($location, $window, UserService) {

	return {
		restrict: 'EA',
		templateUrl: 'app/commons/directives/pagination',
		replace: true,
		scope: {
			paginationInfo: '=',
			displayAble: '@',
			loadData: '&',
			cacheData: '='
		},
		replace: true,
		link: function (scope, elem) {
			scope.PAGE_MIDDLE_SHOW = 3;
			scope.$watch('paginationInfo', function (value) {
				calcShowPage();
			});


			scope.setPage = function (page) {
				scope.paginationInfo.currentPage = page;
				calcShowPage();
				scope.loadData({cacheData: scope.cacheData, paging: scope.paginationInfo});
			}

			var calcShowPage = function () {
				scope.showPageArray = [];
				scope.showLeftDo = false;
				scope.showRightDo = false;
				if (scope.paginationInfo.totalPage <= 2) {// 1 ... 6 7 8 9 
					//do nothing
				} else if (scope.paginationInfo.totalPage <= 5) {
					for (var i = 2; i < scope.paginationInfo.totalPage; i++) {
						scope.showPageArray.push(i);
					}
				} else {
					if (scope.paginationInfo.currentPage <= scope.PAGE_MIDDLE_SHOW) {
						for (var i = 2; i < 2 + scope.PAGE_MIDDLE_SHOW; i++) {
							scope.showPageArray.push(i);
						}
						scope.showRightDo = true;
					} else if (scope.paginationInfo.currentPage > scope.paginationInfo.totalPage - scope.PAGE_MIDDLE_SHOW) {
						for (var i = scope.paginationInfo.totalPage - scope.PAGE_MIDDLE_SHOW; i < scope.paginationInfo.totalPage; i++) {
							scope.showPageArray.push(i);
						}
						scope.showLeftDo = true;
					} else {
						for (var i = scope.paginationInfo.currentPage - 1; i <= scope.paginationInfo.currentPage + 1; i++) {
							scope.showPageArray.push(i);
						}
						scope.showLeftDo = true;
						scope.showRightDo = true;
					}
				}
			}

			calcShowPage();
		}
	};
});