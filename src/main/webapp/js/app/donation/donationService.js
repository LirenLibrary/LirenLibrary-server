angular.module('donationService', []).
    factory('Donations',function ($http) {
        var Donations = {
            getNewDonations:function (callback) {
                $http.get('/lirenlibrary/api/donations/status/new', {
                    headers:{
                        'Accept':'application/vnd.liren-donations+json',
                        'Version':'v1'
                    }}).success(function (response) {
                        callback(response);
                    }).error(function(error) {
                        alert(error.message);
                    })
            },

            findHistorical:function (donationId, callback, errorCallback) {
                $http.get('/lirenlibrary/api/donations/historical/' + donationId, {
                    headers:{
                        'Accept':'application/vnd.liren-donation+json',
                        'Version':'v1'
                    }}).success(function (response) {
                        callback(response);
                    }).error(function(error) {
                        alert(error.message);
                        errorCallback(error);
                    })
            }

        };
        return Donations;

    }).factory('Donation', function ($http) {

        var Donation = {
            get:function (donationId, callback) {
                $http.get("/lirenlibrary/api/donations/" + donationId, {
                    headers:{
                        Accept:'application/vnd.liren-donation+json',
                        Version:'v1'
                    }
                }).success(function (response) {
                        callback(response);
                    }).error(function(error) {
                        alert(error.message);
                    });
            },

            approveBook: function (donationId, bookIsbn, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/approve/book/"+ bookIsbn,{},{
                    headers:{
                        Version:'v1'
                    }
                }).success(function (response) {
                        callback(response);
                }).error(function(error) {
                        alert(error.message);
                });
            },

            rejectBook: function (donationId, bookIsbn, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/reject/book/"+ bookIsbn,{},{
                    headers:{
                        Version:'v1'
                    }
                }).success(function (response) {
                    callback(response);
                }).error(function(error) {
                    alert(error.message);
                });
            },

            updatePostSpecification: function (donationId, specification, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/update/post/specification",
                    {
                        specification: specification
                    },
                    {
                        headers:{
                            'Content-Type':'application/vnd.liren-donation-update-post-specification-request+json',
                            'Version':'v1'
                        }
                }).success(function (response) {
                   callback(response);
                }).error(function(error) {
                   alert(error.message);
                });
            },

            confirm: function (donationId, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/confirm", {},
                    {
                        headers:{
                            Version:'v1'
                        }
                }).success(function (response) {
                   callback(response);
                }).error(function(error) {
                   alert(error.message);
                });
            },

            receive: function (donationId, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/receive", {},
                    {
                        headers:{
                            Version:'v1'
                        }
                }).success(function (response) {
                   callback(response);
                }).error(function(error) {
                   alert(error.message);
                });
            }
        };

        return Donation;
    });
