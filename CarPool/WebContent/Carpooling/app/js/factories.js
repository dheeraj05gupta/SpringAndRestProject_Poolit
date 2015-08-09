'use strict';

appServices.factory('AuthService', ['$window', function ($window) {
    var authStatus;
    if ($window.sessionStorage.getItem('authenticated') == null) {
        //$window.sessionStorage.setItem('authenticated', false);
        authStatus = false;
    }
    else {
        authStatus = $window.sessionStorage.getItem('authenticated');
    }

    return {
        isAuthenticated: authStatus,
        login: function () {
            this.isAuthenticated = true;
            $window.sessionStorage.setItem('authenticated', '1');
        },
        logout: function () {
            this.isAuthenticated = false;
            $window.sessionStorage.setItem('authenticated', '');
        }
    };
}])
    .factory('ModalService', ['$modal', '$location', 'AuthService', function ($modal, $location, AuthService) {
        //var isAuth = AuthService;
        return {
            openLogin: function () {
                var modalInstance = $modal.open({
                    templateUrl: 'myLogin.html',
                    controller: 'LoginInstanceCtrl',
                    backdrop: true,
                    windowClass: 'cp-modal-window'
                });

                modalInstance.result.then(function (res) {
                    if (res === 'register') {
                        $location.path("/register");
                        return;
                    }
                    if (res === 'forgotPassword') {
                        $location.path("/forgotPassword");
                        return;
                    }
                    if (res === true) {
                        AuthService.login();
                        return;
                    }
                }, function () {
                    ////
                });
            },
            openDetails: function (items) {
                var modalInstance = $modal.open({
                    templateUrl: 'contactDetail.html',
                    controller: 'ContactDetailCtrl',
                    backdrop: true,
                    windowClass: 'cp-modal-window',
                    resolve: {
                        contact: function () {
                            return items;
                        }
                    }
                });

                /* modalInstance.result.then(function (res) {
                 ////
                 }, function () {
                 ////
                 });*/
            },
            openFeedback: function (items) {
                var modalInstance = $modal.open({
                    templateUrl: 'feedback.html',
                    controller: 'FeedbackCtrl',
                    backdrop: true,
                    windowClass: 'cp-modal-window',
                    resolve: {
                        contact: function () {
                            return items;
                        }
                    }
                });

                /* modalInstance.result.then(function (res) {
                 ////
                 }, function () {
                 ////
                 });*/
            }
        };
    }]);