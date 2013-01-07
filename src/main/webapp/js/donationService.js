angular.module('donationService', []).
    factory('Donations',function ($http) {
        var Donations = {
            get:function (callback) {
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
            }
        }

        return Donation;
    });
