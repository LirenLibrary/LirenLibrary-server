angular.module('donation', ['donationService', 'commonFilters']).
config(function ($routeProvider) {
    $routeProvider.
    when('/donations', {controller:DonationsCtrl, templateUrl:'donations.html'}).
    when('/history', {controller:HistoryCtrl, templateUrl:'history.html'}).
    otherwise({redirectTo:'/donations'});

});


function HistoryCtrl($scope, Donations, Donation) {

     $scope.found = 'hidden';

     $scope.findHistorical = function(donationId) {

       if(!isPositiveInteger(donationId)) {
          $scope.donation = null;
          $scope.found = 'hidden';
          return;
       }

       Donations.findHistorical(donationId, function(donation){
           $scope.donation = donation;
           $scope.found = '';
       }, function(error){
           $scope.found = 'hidden';
       })
     }
}

function isPositiveInteger(str){
    var regex=new RegExp("^[1-9]+\\d*$");
    return regex.test(str);
}


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
