angular.module('donationService', []).
    factory('Donations',function ($http) {
        var Donations = {
            get:function (callback) {
                $http.get('/lirenlibrary/api/donations', {
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
                $http.get("/lirenlibrary/api/donation/" + donationId, {
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

//angular.module('donationService', []).
//    service('mockData', function () {
//
//        var donations = [
//            {id:0, date:'2012.1.1', num:2, books:[
//                {ISBN:'123', name:'foo1'},
//                {ISBN:'456', name:'bar1'}
//            ]},
//            {id:1, date:'2012.1.2', num:2, books:[
//                {ISBN:'223', name:'foo2'},
//                {ISBN:'556', name:'bar2'}
//            ]},
//            {id:2, date:'2012.1.3', num:2, books:[
//                {ISBN:'323', name:'foo3'},
//                {ISBN:'656', name:'bar3'}
//            ]}
//        ];
//
//        return {
//            getDonations : function () {
//                return donations;
//            },
//
//            getBooksById : function(donationId){
//                return donations[donationId].books;
//            }
//        }
//    })
//;
