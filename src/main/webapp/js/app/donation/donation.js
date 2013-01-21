angular.module('donation', ['donationService', 'commonFilters']).
config(function ($routeProvider) {
    $routeProvider.
    when('/donations', {controller:DonationsCtrl, templateUrl:'donations.html'}).
    otherwise({redirectTo:'/donations'});

});

function DonationsCtrl($location, $scope, Donations, Donation) {

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

        $scope.updatePostSpecification = function(donation) {
          Donation.updatePostSpecification(donation.donation_id, donation.post_specification, function(response){
          });
        }

        $scope.confirmDonation = function(donation) {
          Donation.confirm(donation.donation_id, function(response){
             $location.path("#/donations");
          });
        }
  });
}
