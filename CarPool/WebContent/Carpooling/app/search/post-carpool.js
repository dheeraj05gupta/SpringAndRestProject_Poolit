'use strict';

appDirectives.directive('postCarpool', function () {
    return {
        restrict: 'E',
        templateUrl: 'search/post-carpool.html',
        scope: {
            isLocal: '='
        },
        link: function (scope) {
            scope.cities = ["Delhi", "Yerevan", "Paris", "Madrid", "Berlin", "Moscow", "San Francisco", "New York"];
            scope.seats = [1, 2, 3, 4];
            scope.routes = ["Route 1", "Route 2", "Route 3", "Route 4", "Route 5", "Route 6", "Route 7", "Route 8", "Route 9"];
            scope.times = ["10:30 PM", "10:45 PM", "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM", "00:00 AM", "00:15 AM", "00:30 AM", "00:45 AM", "01:00 AM", "01:15 AM", "01:30 AM"];
            scope.postAdItem = {};
            if (!scope.isLocal) {
                scope.routes = scope.cities;
            }

            scope.selectedRoutes = [];
            scope.selRouteCount = 1;
            scope.isTxtareaFucos = false;

            scope.addRoute = function () {
                scope.selRouteCount++;
                scope.selectedRoutes.push({index: scope.selRouteCount});
            }
            scope.removeRoute = function (item) {
                scope.selRouteCount--;
                var index = scope.selectedRoutes.indexOf(item);
                if (index > -1) {
                    scope.selectedRoutes.splice(index, 1);
                }
            }


            scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();
                scope.opened = true;
            };
        }
    };
});