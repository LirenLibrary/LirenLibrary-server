angular.module('donation', ['donationService']).
    config(function ($routeProvider) {
        $routeProvider.
            when('/', {controller:DonationsCtrl, templateUrl:'books.html'}).
            when('/donations/:donationId', {controller: BooksCtrl, templateUrl:'books.html'}).
            otherwise({redirectTo:'/'});

    });

function DonationsCtrl($scope, Donations) {
    Donations.get(function(donations){
        donations.forEach(function(donation){
           donation.donation_created_date = new Date(donation.donation_time);
        });
        $scope.donations = donations;
        $scope.donation = donations[0];
        $scope.books = donations[0].books;
    });
}

function BooksCtrl($scope, $routeParams, Donation){
    Donation.get($routeParams.donationId, function(donation){
        $scope.donation = donation;
        $scope.books = donation.books;
    });
}