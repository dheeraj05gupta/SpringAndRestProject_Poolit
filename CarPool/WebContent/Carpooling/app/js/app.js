'use strict';

var app = angular.module('myApp', [
    'ui.bootstrap',
    'ngRoute',
    'myApp.controller',
    'myApp.directive',
    'ngGPlaces',
    'myApp.service'
]);

app.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/search', {
            templateUrl: 'search/search.html',
            controller: 'SearchCtrl'
        })
        .when('/register', {
            templateUrl: 'register/register.html',
            controller: 'RegisterCtrl'
        })
        .when('/myProfile', {
            templateUrl: 'myProfile/my-profile.html',
            controller: 'MyProfileCtrl'
        })
        .when('/forgotPassword', {
            templateUrl: 'forgotPassword/forgot-password.html',
            controller: 'ForgotPassword'
        })
        .when('/aboutUs', {
            templateUrl: 'aboutUs/about-us.html',
            controller: 'AboutUs'
        })
        .otherwise({redirectTo: '/search'});
}]);

app.run(function ($rootScope, $location, $anchorScroll, $routeParams) {
    $rootScope.$on('$routeChangeSuccess', function (newRoute, oldRoute) {
        $location.hash($routeParams.scrollTo);
        $anchorScroll();
    });
});


var appControllers = angular.module('myApp.controller', []);
var appDirectives = angular.module('myApp.directive', []);
var appServices = angular.module('myApp.service', []);