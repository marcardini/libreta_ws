app.controller('fileUploadCtrl', ['$scope', 'ngNotify', 'blockUI', function ($scope, ngNotify, blockUI) {
				
		blockUI.autoInjectBodyBlock = false;
		blockUI.message = 'Cargando...';
	
		$scope.selectedType = "PROFESSOR";			
        $scope.filename = $scope.selectedType;  
        
//        $scope.$watch('addedfile', function(selectedType) {
//        	//$scope.file.name = "";
//	        $scope.file.name = $scope.selectedType;
//	    }, true);
        
        console.log($scope);
        $scope.uploadFile = function() {          	
            this.processDropzone();
        };

        $scope.reset = function() {
            this.resetDropzone();
        };
       
    }]);