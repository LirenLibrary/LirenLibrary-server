angular.module('donation', ['donationService']).
    config(function ($routeProvider) {
        $routeProvider.
            when('/', {controller:DonationsCtrl, templateUrl:'books.html'}).
            when('/donation/:donationId', {controller: BooksCtrl, templateUrl:'books.html'}).
            otherwise({redirectTo:'/'});

    });

function DonationsCtrl($scope, Donations) {
    Donations.get(function(donations){
        $scope.donations = donations;
        $scope.books = donations[0].books;
    });
}

function BooksCtrl($scope, $routeParams, Donation){
    Donation.get($routeParams.donationId, function(donation){
        $scope.books = donation.books;
    });
}