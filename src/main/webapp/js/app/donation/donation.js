angular.module('donation', ['donationService', 'libraryService', 'commonFilters']).
config(function ($routeProvider) {
    $routeProvider.
    when('/donations', {controller:DonationsCtrl, templateUrl:'donations.html'}).
    when('/history', {controller:HistoryCtrl, templateUrl:'history.html'}).
    when('/manage', {controller:ManageCtrl, templateUrl:'manage.html'}).
    when('/address', {controller:AddressCtrl, templateUrl:'address.html'}).
    otherwise({redirectTo:'/donations'});
});


function HistoryCtrl($scope, Donations, Donation) {

     $scope.findHistorical = function(donationId) {

       if(!isPositiveInteger(donationId)) {
          $scope.donation = null;
          return;
       }

       Donations.findHistorical(donationId, function(donation){
          $scope.donation = donation;
       }, function(error){
       })
     };


     $scope.isFound = function(donation) {
        if(donation) return true;
        return false;
     };

     $scope.receiveDonation = function(donation) {
         Donation.receive(donation.donation_id, function(response){
           $scope.findHistorical(donation.donation_id);
           alert("通知已发出");
         });
     };

     $scope.isAllowNotify = function(donation) {
        if (!donation) return false;
        if (donation.donation_status == 'REJECTED') return false;
        if (donation.donation_status == 'NOTIFIED') return false;
        return true;
     }
}

function isPositiveInteger(str){
    var regex=new RegExp("^[1-9]+\\d*$");
    return regex.test(str);
}


function DonationsCtrl($location, $scope, Donations, Donation) {

    $scope.hasDonations = function(donations) {
       if(donations && donations.length != 0) return true;
       return false;
    }

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
             alert("捐赠 "+donation.donation_id+" 审核完成");
             $location.path("#/donations");
          });
        }
  });
}

function ManageCtrl($location, $scope) {

}

function AddressCtrl($location, $scope, Libraries, Library) {

    $scope.init = function(){
        Libraries.getAllLibraries(function(response){
            var libs = [];
            for (var lib in response){
                response[lib].shown=true;
                response[lib].saveOrEdit="编辑";
                libs.push(response[lib]);
            }
            $scope.libraries = libs;
            console.log($scope.libraries);
        });
    };

    $scope.editOrSave = function(library){
        console.log(library);
        var data = {};
        data.id = library.id;
        data.name = library.name;
        data.contacter = library.contacter;
        data.address = library.address;
        data.postcode = library.postcode;
        data.telephone = library.telephone;
        if (library.shown == false){
            Library.saveOrUpdate(data, function(){
                console.log("save sucessful");
            }), function(){
                console.log("failed");
            };
        }
        library.shown = !library.shown;
        library.saveOrEdit = library.shown ? "编辑" : "保存";
    }

    $scope.remove = function(id){
        var deleteLibrary = confirm('确定删除？');
        console.log(deleteLibrary);
        if (deleteLibrary){

        }
    }

    $scope.add = function(){
        console.log("addition");
        $scope.libraries.push({
            address: "",
            contacter: "",
            id: "",
            name: "",
            postcode: "",
            telephone: "",
            shown:false,
            saveOrEdit:"保存"
        });
    }
}
