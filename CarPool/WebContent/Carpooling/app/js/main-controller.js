'use strict';
appControllers.controller('MainCtrl', ['$scope', '$modal', '$location', 'AuthService', 'ModalService',
    function ($scope, $modal, $location, AuthService, ModalService) {

        $scope.isAuth = AuthService;
        $scope.logout = function () {
            AuthService.logout();
            $location.path("/search");
        }

        $scope.openLogin = function () {
            ModalService.openLogin();
        }

        $scope.openFeedback = function () {
            ModalService.openFeedback();
        }

        $scope.goHome = function(){
            $location.path("/")
            $scope.autocomplete = new google.maps.places.Autocomplete(
                    (document.getElementById('autocomplete')),
                     { types: ['geocode'] });
                 google.maps.event.addListener($scope.autocomplete, 'place_changed', function() {
   			  if($scope.autocomplete && $scope.autocomplete.getPlace() && $scope.autocomplete.getPlace().place_id){
   			   //--var "place" should be send to backend.
   			   var place = $scope.autocomplete.getPlace().place_id;
   			  }
                 });
        }
    }])
    .controller('LoginInstanceCtrl', ['$scope', '$modalInstance', 'ajaxService', function ($scope, $modalInstance, ajaxService) {
        $scope.account = {username: 'anand.pateriya88@gmail.com', password: 'pateriya88'}
        $scope.alertVisible = false;
        $scope.alertMessage = '';

        $scope.login = function () {
            /*$modalInstance.close(true);
            return;*/

            var postParams = {
                "userProfile": {
                    "email": $scope.account.username,
                    "password": $scope.account.password
                }
            }

            ajaxService.httpPost('loginService', postParams).then(function (resp) {
                if (resp.exitCode == 'OK' && resp.userProfile != null) {
                    console.log("Login succeed");
                    $modalInstance.close(true);
                }
                else {
                    $scope.alertMessage = resp.txnDetails.txnStaus;
                    $scope.alertVisible = true;
                }
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.register = function () {
            $modalInstance.close('register');

        };

        $scope.forgotPass = function () {
            $modalInstance.close('forgotPassword')
        };
    }])
    .controller('ContactDetailCtrl', ['$scope', '$modalInstance', 'contact', function ($scope, $modalInstance, contact) {

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }])
    .controller('FeedbackCtrl', ['$scope', '$modalInstance', 'contact', function ($scope, $modalInstance, contact) {

        $scope.cities = ["Delhi", "Yerevan", "Paris", "Madrid", "Berlin", "Moscow", "San Francisco", "New York",];
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);