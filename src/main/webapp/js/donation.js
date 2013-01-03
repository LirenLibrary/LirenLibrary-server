angular.module('donation', ['donationService']).
    config(function ($routeProvider) {
        $routeProvider.
            when('/', {controller:DonationsCtrl, templateUrl:'books.html'}).
            when('/donation/:donationId', {controller: BooksCtrl, templateUrl:'books.html'}).
            otherwise({redirectTo:'/'});

    });

function DonationsCtrl($scope, mockData) {
    $scope.donations = mockData.getDonations();
    $scope.books = $scope.donations[0].books;
}

function BooksCtrl($scope, $routeParams, mockData){
    $scope.books = mockData.getBooksById($routeParams.donationId)
}