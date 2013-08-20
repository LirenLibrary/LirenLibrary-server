angular.module('libraryService', []).
    factory('Libraries',function ($http) {
        var Libraries = {
            getAllLibraries:function (callback) {
                $http.get('/lirenlibrary/api/libraries/', {
                    headers:{
                        'Accept':'application/vnd.liren-libraries+json',
                        'Version':'v1'
                    }}).success(function (response) {
                        callback(response);
                    }).error(function(error) {
                        alert(error.message);
                    })
            }
        };
        return Libraries;
    }).factory('Library', function($http){
        var Library = {
            saveOrUpdate: function(library, successHandler, failHandler){
                $http.post('/lirenlibrary/api/libraries/new', library, {
                    headers:{
                        'Content-Type':'application/vnd.liren-library+json',
                        'Version':'v1'
                    }}).success(function(response){
                        successHandler(response);
                    }).error(function(error){
                        failHandler(error.message);
                    });
            },
            remove: function(id, successHandler, failHandler){
                $http.delete('/lirenlibrary/api/libraries/' + id, {
                    headers:{
                        'Content-Type':'application/vnd.liren-library+json',
                        'Version':'v1'
                    }
                }).success(function(response){
                    successHandler(response);
                }).error(function(error){
                    failHandler(error.message);
                });
            }

        };
        return Library;
    });
