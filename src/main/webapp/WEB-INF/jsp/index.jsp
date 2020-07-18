<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" 
crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.7.9/angular.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" 
integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

<body ng-app="app" ng-controller="movieController" style="background-image: url('css/login-background.png'); background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: 100% 100%;">
	<div class="container" ng-show="showLogin">
		<div class="row" style="margin-top:250px; margin-left:250px;">
			<div class="col-md-offset-6 col-md-6" ng-show="showLogin" >
				<form class="form-horizontal" ng-submit="login()">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="email">UserName:</label>
							<div class="controls">
								<input id="loginEmail" required="required" name="email" ng-model="user.username"
									class="form-control" type="text" placeholder="Enter your User Name"
									class="input-large" height="32px">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="password">Password:</label>
							<div class="controls">
								<input id="loginPassword" required="required" ng-model="user.password"
									name="password" class="form-control" type="password"
									placeholder="**************" class="input-medium" height="32px">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="signin"></label>
							<div class="controls">
								<input type="submit" id="submit" class="btn btn-success" 
									value="Sign In" />
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div class="col-md-offset-3 col-md-3" ng-show="showUserDetails"></div>
	<div ng-show="showUserDetails" ng-include="'css/userDetails.html'"></div>
	<div ng-show="showAdminDetails" ng-include="'css/userDetails.html'"></div>
	<div class="row">
	<div class="col" style="margin-top:16px; margin:left:400px;" ng-show="isInsertMode" ng-include="'css/input.html'"></div>
	</div>
	<script>var App = angular.module('app',[]);</script>
	<script src="js/movieService.js"></script>
	<script src="js/movieController.js"></script>
</body>
</html>