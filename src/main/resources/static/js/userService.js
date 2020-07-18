'use strict';

App.factory('userService', function($http, $q) {
    return {
       
        postUserData : function(user){
            var config = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }
            return $http.post('validate', user, config)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse) {
                        alert(errResponse.status + ':' + errResponse.statusText);
                        return $q.reject(errResponse);
                    });
        },
        
        getUserDetails : function() {
        	return $http.get('details')
            .then(
                function(response){
                    return response.data;
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
        
        updateRecords : function(detail){
        	return $http.post('update',detail)
        	.then(function(response){
        		return response;
        	});
        },
        
        deleteRecord : function(detail){
        	return $http.post('delete',detail)
        	.then(function(response){
        		return response;
        	});
        }

    };
});