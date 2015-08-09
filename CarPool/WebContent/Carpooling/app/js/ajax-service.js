'use strict';
appServices.factory("ajaxService", ['$http', '$q', function ($http, $q) {
    var backEndUrl = 'http://52.11.245.66:8080/rest/v1/'
    return {
        loginUser: function (url, params) {
            var deffered = $q.defer();
            $http.post(url, params)
                .success(deffered.resolve)
                .error(deffered.reject);
            return deffered.promise;
        },
        logoutUser: function (url, params) {
            var deffered = $q.defer();
            $http.post(url, params)
                .success(deffered.resolve)
                .error(deffered.reject);
            return deffered.promise;
        },
        httpPost: function (url, params) {
            var deffered = $q.defer();
            $http.post(backEndUrl + url, params)
                .success(deffered.resolve)
                .error(deffered.reject);
            return deffered.promise;
        },
        httpGet: function (url) {
            var deffered = $q.defer();
            $http.get(backEndUrl + url)
                .success(deffered.resolve)
                .error(deffered.reject);
            return deffered.promise;
        }
    }
}]);