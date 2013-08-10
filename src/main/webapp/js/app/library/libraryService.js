angular.module('libraryService', []).
    factory('Libraries',function ($http) {
        var Libraries = {
            getAllLibraries:function (callback) {
                $http.get('/lirenlibrary/api/libraries/all', {
//                $http.get('/lirenlibrary/api/donations/status/new', {
                    headers:{
                        'Accept':'application/vnd.liren-libraries+json',
                        'Version':'v1'
                    }}).success(function (response) {
                        callback(response);
                    }).error(function(error) {
                        alert(error.message);
                    })
            },

//            findHistorical:function (donationId, callback, errorCallback) {
//                $http.get('/lirenlibrary/api/donations/historical/' + donationId, {
//                    headers:{
//                        'Accept':'application/vnd.liren-donation+json',
//                        'Version':'v1'
//                    }}).success(function (response) {
//                        callback(response);
//                    }).error(function(error) {
//                        alert(error.message);
//                        errorCallback(error);
//                    })
//            }

        };
        return Libraries;
    }).factory('Library', function($http){
        var Library = {
            saveOrUpdate: function(library, successHandler, failHandler){
                $http.post
            }
        };
        return Library;
    });
