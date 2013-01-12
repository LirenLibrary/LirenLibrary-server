angular.module('donation', ['donationService', 'commonFilters']).
config(function ($routeProvider) {
    $routeProvider.
    when('/donations', {controller:DonationsCtrl, templateUrl:'donations.html'}).
    otherwise({redirectTo:'/donations'});

});

function DonationsCtrl($scope, Donations, Donation) {

    Donations.getNewDonations(function(donations){

        $scope.donations = donations;
        $scope.donation = donations[0];

        $scope.showDonationDetail = function(donationId) {
            Donation.get(donationId, function(donation){
                $scope.donation = donation;
            });
        }


        $scope.approveBook = function(donation, book) {

            if(book.status == 'APPROVED') return false;

            Donation.approveBook(donation.donation_id, book.ISBN, function(response){
                book.status = 'APPROVED';
            });
        }

        $scope.rejectBook = function(donation, book) {

          if(book.status == 'REJECTED') return false;

          Donation.rejectBook(donation.donation_id, book.ISBN, function(response){
                book.status = 'REJECTED';
          });
      }
  });
}
