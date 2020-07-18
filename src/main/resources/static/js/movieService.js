'use strict';

App.factory('movieService', function($http, $q) {
    return {
       
    	validateUser : function(record){
            var config = {
                headers : {
                    'Content-Type': 'application/json',
                    'username':record.username,
                    'password':record.password
                }
            }
            return $http.get('validateUser',config)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse) {
                        alert(errResponse.status + ':' + errResponse.statusText);
                        return $q.reject(errResponse);
                    });
        },
        
        postData : function(record){
            var config = {
                headers : {
                    'Content-Type': 'application/json',
                    'cityname':record.name
                }
            }
            return $http.post('postData',config,record)
                .then(
                    function(response){
                        return response.data.result;
                    },
                    function(errResponse) {
                        alert(errResponse.status + ':' + errResponse.statusText);
                        return $q.reject(errResponse);
                    });
        },
        
        insertRecords : function(detail){
        	return $http.post('insert',detail)
        	.then(function(response){
        		return response;
        	});
        },
        
        getMovieDetails : function(){
            var config = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }
            return $http.get('getMovieDetails',config)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse) {
                        alert(errResponse.status + ':' + errResponse.statusText);
                        return $q.reject(errResponse);
                    });
        }
        
    };
});