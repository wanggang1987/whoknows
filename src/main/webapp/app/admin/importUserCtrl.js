'use strict';

angular.module('wkAdmin').controller('wkAdmin.importUser',
	function ($scope, $http) {
		var emailRegex = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		var SETP_CONF = {
			PARSTE_RAW_TEXT : 'STEP-1',
			SUBMIT_USRE: 'STEP-2'
		}
		$scope.step = SETP_CONF.PARSTE_RAW_TEXT;

		$scope.users = [];
		$scope.error = false;
		$scope.success =false;

		$scope.parseRawText = function(){
			console.log($scope.rawText);
			var emails = $scope.rawText.split('\n');
			emails = _.uniq(emails);
			for(var index in emails ){
				var email = emails[index];
				if(!emailRegex.test(email)){
					continue;
				}
				var existsEmail = _.find($scope.users, {email:email});
				if(existsEmail){
					existsEmail.passwd = $scope.rawPassword;
				}else{
					$scope.users.push({
						email:email,
						passwd: $scope.rawPassword
					})
				}
			}

			$scope.step = SETP_CONF.SUBMIT_USRE;
		};

		$scope.turnToStep1 = function(){
			$scope.step = SETP_CONF.PARSTE_RAW_TEXT;
		};

		$scope.addUser = function(){
			$scope.users.push({email: '', passwd: $scope.rawPassword});
		};
		$scope.deleteUser = function(user){
			_.remove($scope.users, function(u){
				return u.email === user.email;
			})
		};

		$scope.submitUser = function(){
			console.log(JSON.stringify($scope.users));
			$http.put('/user/import', $scope.users).success(function(data){
				$scope.error = false;
				$scope.success = true;
				$scope.users = [];

			}).error(function(data){
				$scope.error = true;
				$scope.success = false;
			});
		};

		$scope.closeAlert = function(){
			$scope.error = false;
			$scope.success = false;
		}
	});
