app.controller('fileUploadCtrl', ['$scope', function ($scope) {

       
        $scope.filename = '';

        $scope.uploadFile = function() {
            $scope.processDropzone();
        };

        $scope.reset = function() {
            $scope.resetDropzone();
        };
    }

]);
