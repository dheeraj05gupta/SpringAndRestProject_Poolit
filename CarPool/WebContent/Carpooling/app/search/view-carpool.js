'use strict';

appDirectives.directive('viewCarpool', ['$modal', 'ModalService', function ($modal, ModalService) {
    return {
        restrict: 'E',
        templateUrl: 'search/view-carpool.html',
        scope: {
            isLocal: '=',
            isAuth: '='
        },
        link: function (scope) {
            scope.isSearched = false;
            scope.cities = ["Delhi", "Yerevan", "Paris", "Madrid", "Berlin", "Moscow", "San Francisco", "New York"];
            scope.routes = ["Route 1", "Route 2", "Route 3", "Route 4", "Route 5", "Route 6", "Route 7", "Route 8", "Route 9"];
            scope.times = ["10:30 PM", "10:45 PM", "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM", "00:00 AM", "00:15 AM", "00:30 AM", "00:45 AM", "01:00 AM", "01:15 AM", "01:30 AM"];
            scope.viewItem = {};
            if (!scope.isLocal) {
                scope.routes = scope.cities;
            }

            ///////// paging part /////////

            scope.currentPage = 1;
            scope.itemsInPage = 7;

            scope.goToPage = function (type) {
                switch (type) {
                    case 'first':
                    {
                        scope.currentPage = 1;
                        break;
                    }
                    case 'prev':
                    {
                        if (scope.currentPage > 1) {
                            scope.currentPage--;
                        }
                        break;
                    }
                    case 'next':
                    {
                        if (scope.currentPage < parseInt(scope.results.length / scope.itemsInPage)) {
                            scope.currentPage++;
                        }
                        break;
                    }
                    case 'last':
                    {
                        scope.currentPage = parseInt(scope.results.length / scope.itemsInPage);
                        break;
                    }
                    default :
                    {
                        break;
                    }
                }
                scope.filteredResults = scope.getFilteredResults();
            }

            ///////// paging part end /////////

            scope.open = function ($event) {
                $event.preventDefault();
                $event.stopPropagation();
                scope.opened = true;
            };

            scope.searchCarpool = function () {
                /*if(!scope.isAuth){
                 scope.openLogin();
                 return;
                 }*/
                scope.isSearched = true;
            }

            scope.openDetails = function () {
                if (!scope.isAuth) {
                    scope.openLogin();
                    return;
                }
                ModalService.openDetails();
            }

            scope.openLogin = function () {
                ModalService.openLogin();
            }

            scope.results = [
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                },
                {
                    Name: 'Menua',
                    Time: '10:30 PM',
                    Routes: ['r1', 'r2', 'r3'],
                    Seats: 2,
                    Rating: new Array(4), CarType: 'BMW'
                },
                {
                    Name: 'Dheeraj',
                    Time: '10:10 PM',
                    Routes: ['r1', 'r2'],
                    Seats: 8,
                    Rating: new Array(5), CarType: 'VW'
                }
            ];

            scope.getFilteredResults = function () {
                var startItem = (scope.currentPage - 1) * scope.itemsInPage;
                var endItem = scope.currentPage * scope.itemsInPage;
                var retVal = [];
                for (var i = startItem; i < endItem; i++) {
                    retVal.push(scope.results[i]);
                }
                return retVal;
            }

            scope.filteredResults = scope.getFilteredResults();
        }
    };
}]);