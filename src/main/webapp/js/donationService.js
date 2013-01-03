angular.module('donationService', []).
    service('mockData', function () {

        var donations = [
            {id:0, date:'2012.1.1', num:2, books:[
                {ISBN:'123', name:'foo1'},
                {ISBN:'456', name:'bar1'}
            ]},
            {id:1, date:'2012.1.2', num:2, books:[
                {ISBN:'223', name:'foo2'},
                {ISBN:'556', name:'bar2'}
            ]},
            {id:2, date:'2012.1.3', num:2, books:[
                {ISBN:'323', name:'foo3'},
                {ISBN:'656', name:'bar3'}
            ]}
        ];

        return {
            getDonations : function () {
                return donations;
            },

            getBooksById : function(donationId){
                return donations[donationId].books;
            }
        }
    })
;
