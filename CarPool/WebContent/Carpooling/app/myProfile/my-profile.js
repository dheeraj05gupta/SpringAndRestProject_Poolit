'use strict';
appControllers.controller('MyProfileCtrl', ['$scope', 'ajaxService', function ($scope, ajaxService) {

    $scope.radioModel = 'UpdateProfile';

    $scope.editProfile = false;
    $scope.editPassword = false;

    $scope.doEditProfile = function (isPassword) {
        if (isPassword) {
            $scope.editPassword = !$scope.editPassword;
        }
        else {
            $scope.editProfile = !$scope.editProfile;
        }
    };

    $scope.doSaveProfile = function (isPassword) {
        if (isPassword) {
            $scope.editPassword = !$scope.editPassword;
        }
        else {
            $scope.editProfile = !$scope.editProfile;
        }
    };

    $scope.user = {
        firstName: 'FirstName',
        lastName: 'LastName',
        email: 'Email',
        phone: 'Phone',
        password: '',
        newPassword: '',
        confirmPassword: ''
    }

    var postParams = {
        "userProfile": {
            "firstName": "Anand",
            "userNo": 12,
            "lastName": "Pateriya",
            "email": "anand.pateriya88@gmail.com",
            "mobileNo": 2342334,
            "company": "Ace",
            "birthDate": "2015-03-05",
            "password": "password",
            "gender": "M",
            "location": "Pune",
            "otp": 2244,
            "verified": 0,
            "userRating": 1
        }
    }


    ajaxService.httpPost('adsPosted', postParams).then(function (resp) {
        if (resp.exitCode == 'OK') {
            console.log(resp);
            $scope.ads = resp.cpAddList;
        }
    });

    /*$scope.ads = [
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'},
     {fromLocal: 'Ad post', toLocal:  'Ad post2', dateTime: 1449858600000, vehicleNo: 'hr2695', seatsAvailable: '8'}
     ];*/

}]);