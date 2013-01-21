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
                    }).error(function () {
                        console.log("Can not get donations from donation service")
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
                    }).error(function() {
                        console.log("cann't get the donation from donation service")
                    })
            },

            approveBook: function (donationId, bookIsbn, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/approve/book/"+ bookIsbn,{},{
                    headers:{
                        Version:'v1'
                    }
                }).success(function (response) {
                        callback(response);
                }).error(function() {
                   console.log("cann't approve book with isbn:" + bookIsbn);
                });
            },

            rejectBook: function (donationId, bookIsbn, callback) {
                $http.put("/lirenlibrary/api/donations/" + donationId + "/reject/book/"+ bookIsbn,{},{
                    headers:{
                        Version:'v1'
                    }
                }).success(function (response) {
                        callback(response);
                }).error(function() {
                   console.log("cann't reject book with isbn:" + bookIsbn);
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
                }).error(function() {
                   console.log("cann't update donation post specification. " + donation.donation_id);
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
                }).error(function() {
                   console.log("cann't confirm donation. " + donation.donation_id);
                });
            }
        }

        return Donation;
    });
