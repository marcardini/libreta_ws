app.controller('fileUploadCtrl', ['$scope', function ($scope) {
	
		$scope.selectedType = "PROFESSOR";			
        $scope.filename = $scope.selectedType;  
        
//        
//        $scope.$watch('addedfile', function(selectedType) {
//        	//$scope.file.name = "";
//	        $scope.file.name = $scope.selectedType;
//	    }, true);
        console.log($scope);
        $scope.uploadFile = function() {          	
            $scope.processDropzone();
        };

        $scope.reset = function() {
            $scope.resetDropzone();
        };
        
       
        
        
    }]);
