angular.module('donation', ['donationService', 'commonFilters']).
    config(function ($routeProvider) {
        $routeProvider.
            when('/', {controller:DonationsCtrl, templateUrl:'books.html'}).
            when('/donations/:donationId', {controller: BooksCtrl, templateUrl:'books.html'}).
            when('/donations/:donationId/:action/book/:isbn', {controller: BookCtrl, templateUrl:'books.html'}).
            otherwise({redirectTo:'/'});

    });

function DonationsCtrl($scope, Donations) {
    Donations.get(function(donations){
        $scope.donations = donations;
        $scope.donation = donations[0];
        $scope.books = donations[0].books;
    });
}

function BooksCtrl($location, $scope, $routeParams, Donation){
    Donation.get($routeParams.donationId, function(donation){
        $scope.donation = donation;
        $scope.books = donation.books;
    });

}

function BookCtrl($location, $scope, $routeParams, Donation){

    if($routeParams.isbn && $routeParams.action == 'approve'){
        Donation.approveBook($routeParams.donationId,$routeParams.isbn, function(response){
            $location.path("/donations/"+$routeParams.donationId);
        });
    }

    if($routeParams.isbn && $routeParams.action == 'reject'){
            Donation.rejectBook($routeParams.donationId,$routeParams.isbn, function(response){
            $location.path("/donations/"+$routeParams.donationId);
        });
    }
}