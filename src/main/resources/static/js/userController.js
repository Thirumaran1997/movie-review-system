'use strict';
App.controller('userController', ['$scope', '$rootScope', 'userService','$http',
		function($scope, $rootScope, userService, $http) {

			$scope.user = {};
			$scope.user.email = "";
			$scope.user.password = "";
			$scope.showUserDetails = false;
			$scope.showLogin = true;
			$scope.userDetails = [];
        	$scope.detail ={};
        	$scope.isInsertMode = true;
        	
			$scope.login = function() {
				userService.postUserData($scope.user).then(
						function(d) {
							$scope.showLogin = false;
							$scope.showUserDetails = true;
							$scope.inputUserDetails = false;
							$scope.getUserDetails();
						});
			};
			
			$scope.initialize = function(){
				$scope.detail['firstname'] ="";
	        	$scope.detail['lastname'] ="";
	        	$scope.detail['age'] ="";
	        	$scope.detail['scheme'] ="";
	        	$scope.detail['paydue'] ="";
	        	$scope.detail['paypermonth'] ="";
	        	$scope.detail['comments'] ="";
			}
			
			$scope.getUserDetails = function() {
				userService.getUserDetails().then(
						function(d) {
							$scope.userDetails = d;
							$scope.inputUserDetails = false;
						});
			}
			$scope.updateCustomerDetails = function(){
				$scope.initialize();
				$scope.showUserDetails = false;
				$scope.inputUserDetails = true;
			}
			
			$scope.insertRecords = function(){
				$scope.isInsertMode = true;
				$scope.isUpdateMode = false;
				userService.insertRecords($scope.detail).then(
						function(response){
							if(response['data']['status']==='pass'){
								alert('Success');
								$scope.initialize();
							}else
								alert('An error occurred!');
						});
			}
			
			$scope.onCancel = function(){
				$scope.showUserDetails = true;
				$scope.inputUserDetails = false;
				$scope.getUserDetails();
			}
			
			$scope.editRecord = function(){
				$scope.showUserDetails = false;
				$scope.inputUserDetails = true;
				$scope.isInsertMode = false;
				$scope.isUpdateMode = true;
				var row  = event.currentTarget.closest('tr');
				$scope.detail['id'] = row.cells[0].textContent;
				$scope.detail['firstname'] = row.cells[1].textContent;
	        	$scope.detail['lastname'] =row.cells[2].textContent;
	        	$scope.detail['age'] =parseInt(row.cells[3].textContent);
	        	$scope.detail['scheme'] =row.cells[4].textContent;
	        	$scope.detail['paydue'] =row.cells[5].textContent;
	        	$scope.detail['paypermonth'] =row.cells[6].textContent;
	        	$scope.detail['comments'] =row.cells[7].textContent;
			}
			
			$scope.updateRecords = function(){
				userService.updateRecords($scope.detail).then(
						function(response){
							if(response['data']['status']==='pass'){
								alert('Success');
								$scope.showUserDetails = true;
								$scope.inputUserDetails = false;
								$scope.getUserDetails();
							}else
								alert('An error occurred!');
						});
			}
			
			$scope.deleteRecord = function(){
				var row  = event.currentTarget.closest('tr');
				$scope.detail['id'] = row.cells[0].textContent;
				userService.deleteRecord($scope.detail).then(
						function(response){
							if(response['data']['status']==='pass'){
								alert('Success');
								$scope.showUserDetails = true;
								$scope.inputUserDetails = false;
								$scope.getUserDetails();
							}else
								alert('An error occurred!');
						});
			}
			
		} ]);