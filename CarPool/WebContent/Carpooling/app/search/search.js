'use strict';

appControllers.controller('SearchCtrl', ['$scope', 'ModalService',
    function ($scope, ModalService) {

        $scope.postType = 'local';
        var slides = $scope.slides = [];
        slides.push({image: '/Carpooling/app/image/image_1.jpg'});
        slides.push({image: '/Carpooling/app/image/image_2.jpg'});
        slides.push({image: '/Carpooling/app/image/image_3.jpg'});
        $scope.radioModel = 'SearchLocal';

        $scope.postCarpoolTab = function (e) {
            if(!$scope.$parent.isAuth.isAuthenticated){
                e.preventDefault();
                e.stopImmediatePropagation();
                ModalService.openLogin();
                return false;
            }

        }

    }])
