'use strict';
appControllers.controller('RegisterCtrl', ['$scope', 'ajaxService', function ($scope, ajaxService) {
    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };
    $scope.dateOptions = {
        startingDay: 1,
        showWeeks: false
    };
    $scope.cities = ["Delhi", "Yerevan", "Pune", "Madrid", "Berlin", "Moscow", "San Francisco", "New York",];
    $scope.isOtpSent = false;
    $scope.alertVisible = false;
    $scope.alertMessage = '';

    $scope.changeCity = function (city) {
        $scope.registeredUser.location = city;
    }

    $scope.registeredUser = {
        firstName: 'Anand',
        lastName: 'Pateriya',
        email: 'anand.pateriya88gmail.com',
        mobileNo: 2342334,
        company: 'Ace',
        birthDate: null,
        password: null,
        confirmPassword: null,
        gender: 'M',
        location: null,
        otp: null,
        verified: 0,
        userRating: 1
    }

    $scope.getOtp = function () {
        if ($scope.registeredUser.password !== $scope.registeredUser.confirmPassword) {
            $scope.alertVisible = true;
            $scope.alertMessage = "Passwords don't match.";
            return;
        }
        var tempDate = moment($scope.registeredUser.birthDate).format('YYYY-MM-DD');
        var postParams = {
            "userProfile": {
                "userNo": null,
                "firstName": $scope.registeredUser.firstName,
                "lastName": $scope.registeredUser.lastName,
                "email": $scope.registeredUser.email,
                "mobileNo": $scope.registeredUser.mobileNo,
                "company": $scope.registeredUser.company,
                "birthDate": tempDate,
                "password": $scope.registeredUser.password,
                "gender": $scope.registeredUser.gender,
                "location": $scope.registeredUser.location,
                "otp": null,
                "verified": 0,
                "userRating": 1
            }
        }


        ajaxService.httpPost('registerUser', postParams).then(function (resp) {
            if (resp.exitCode == 'OK') {
                console.log(resp);
                $scope.registeredUser.userNo = resp.userProfile.userNo;
                $scope.isOtpSent = true;
            }
            else {
                $scope.alertMessage = resp.txnDetails.txnErrorCode;
                $scope.alertVisible = true;
            }
        });
    }

    $scope.registerUser = function () {
        if ($scope.registeredUser.password !== $scope.registeredUser.confirmPassword) {
            $scope.alertVisible = true;
            $scope.alertMessage = "Passwords don't match.";
            return;
        }
        var tempDate = Date.parse($scope.registeredUser.birthDate);
        var postParams = {
            "userProfile": {
                "userNo": $scope.registeredUser.userNo,
                "firstName": $scope.registeredUser.firstName,
                "lastName": $scope.registeredUser.lastName,
                "email": $scope.registeredUser.email,
                "mobileNo": $scope.registeredUser.mobileNo,
                "company": $scope.registeredUser.company,
                "birthDate": tempDate,
                "password": $scope.registeredUser.password,
                "gender": $scope.registeredUser.gender,
                "location": $scope.registeredUser.location,
                "otp": parseInt($scope.registeredUser.otp),
                "verified": 0,
                "userRating": 1
            }
        }


        ajaxService.httpPost('completeRegistration', postParams).then(function (resp) {
            if (resp.exitCode == 'OK') {
                console.log(resp);
                alert("Success");
            }
            else {
                $scope.alertMessage = resp.txnDetails.txnErrorCode;
                $scope.alertVisible = true;
            }
        });
    }
}]);