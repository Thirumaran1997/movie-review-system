'use strict';
App.controller('movieController', ['$scope', '$rootScope', 'movieService','$http',
		function($scope, $rootScope, movieService, $http) {

			$scope.user = {};
			$scope.user.username = "";
			$scope.user.password = "";
			$scope.showLogin = true;
			$scope.showUserDetails = false;
			$scope.showAdminDetails = false;
			$scope.movieDetails = [];
			$scope.detail = {};
			$scope.isInsertMode = false;
			$scope.user.role="";
			$scope.idShown=false;
        	
			$scope.login = function() {
				$scope.loading = true;
				movieService.validateUser($scope.user).then(
						function(data) {
							if(data.result=='pass'){
								$scope.movieDetails = data.movieList;
								if(data.role=='admin'){
									$scope.showAdminDetails = true;
									$scope.showUserDetails = false;
									$scope.isEditable = true;
									$scope.user.role="admin";
								}else{
									$scope.showAdminDetails = false;
									$scope.showUserDetails = true;
									$scope.isEditable = false;
									$scope.user.role="user";
								}
							}else{
								console.log('Username or password is wrong!')
							}
							$scope.loading = false;
							$scope.showLogin = false;
							$scope.showDetails = true;
						});
			};
			
			$scope.initialize = function(){
				$scope.detail['name'] ="";
				$scope.detail['id'] ="";
	        	$scope.detail['year'] ="";
	        	$scope.detail['genre'] ="";
	        	$scope.detail['time'] ="";
	        	$scope.detail['rating'] ="";
	        	$scope.detail['description'] ="";
			}
			
			$scope.openInsertPage = function() {
				$scope.isInsertMode = true;
				$scope.showAdminDetails = false;
				$scope.showUserDetails = false;
			};
			
			$scope.onCancel = function() {
				$scope.getMovieDetails();
				$scope.isInsertMode = false;
				if($scope.user.role=="admin"){
					$scope.showAdminDetails = true;
				}else
					$scope.showUserDetails = true;
			};
			
			$scope.insertRecords = function(){
				$scope.isInsertMode = true;
				$scope.isUpdateMode = false;
				movieService.insertRecords($scope.detail).then(
						function(response){
							if(response['data']['status']==='pass'){
								alert('Success');
								$scope.initialize();
							}else
								alert('An error occurred!');
						});
			}
			
			$scope.getMovieDetails = function(){
				$scope.isInsertMode = true;
				$scope.isUpdateMode = false;
				movieService.getMovieDetails().then(function(response){
								$scope.movieDetails = response.movieList;
							});
			}
			
			$scope.editRecord = function(){
				$scope.isInsertMode = true;
				$scope.isUpdateMode = true;
				$scope.showAdminDetails = false;
				$scope.showUserDetails = false;
				var row  = event.currentTarget;
				var parent = row.parentElement;
				$scope.detail['id']=parent.getElementsByClassName('id')[0].textContent;
				$scope.detail['name']=parent.getElementsByClassName('movienm')[0].textContent;
				$scope.detail['year']=parent.getElementsByClassName('year')[0].textContent;
				$scope.detail['genre']=parent.getElementsByClassName('genre')[0].textContent;
				$scope.detail['time']=parent.getElementsByClassName('time')[0].textContent;
				$scope.detail['rating']=parent.getElementsByClassName('rating')[0].textContent;
				$scope.detail['description']=parent.getElementsByClassName('description')[0].textContent;
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
				var row  = event.currentTarget;
				var parent = row.parentElement;
				$scope.detail['id']=parent.getElementsByClassName('id')[0].textContent;
				userService.deleteRecord($scope.detail).then(
						function(response){
							if(response['data']['status']==='pass'){
								alert('Success');
								$scope.showUserDetails = true;
								$scope.inputUserDetails = false;
								$scope.getMovieDetails();
							}else
								alert('An error occurred!');
						});
			}
			
		} ]);