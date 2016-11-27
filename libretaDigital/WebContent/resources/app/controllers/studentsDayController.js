app.controller('studentsDayCtrl', ['$scope', '$filter', '$http', 'ngNotify', 'blockUI', function ($scope, $filter, $http, ngNotify, blockUI) {

	
	  ngNotify.config({
	        theme: 'pure',
	        position: 'bottom',
	        duration: 3000,
	        type: 'info',
	        sticky: false,
	        button: true,
	        html: false
	    });
	  
	blockUI.autoInjectBodyBlock = false;
	blockUI.message = 'Cargando...';

	$scope.date = new Date();

	console.log(students);
	
	 $scope.student = {
			 name:"Datos del Estudiante"
	 };
	$scope.students = [];	
	angular.copy(students, $scope.students);
	
	$scope.rowSelect = function(row) {
		$scope.student = angular.copy(row);
	      console.log($scope.student);
	}
	
	

} ]);
