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
        	
			$scope.login = function() {
				$scope.loading = true;
				movieService.validateUser($scope.user).then(
						function(data) {
							if(data.result=='pass'){
								$scope.movieDetails = data.movieList;
								if(data.role=='admin'){
									$scope.showAdminDetails = true;
									$scope.showUserDetails = false;
								}else{
									$scope.showAdminDetails = false;
									$scope.showUserDetails = true;
								}
							}else{
								console.log('Username or password is wrong!')
							}
							$scope.loading = false;
							$scope.showLogin = false;
							$scope.showDetails = true;
						});
			};
			
			$scope.postData = function() {
				$scope.loading = true;
				movieService.postData($scope.city).then(
						function(data) {
							$scope.loading = false;
							$scope.showLogin = false;
							$scope.filterData($scope.city);
						});
			};
			
		} ]);