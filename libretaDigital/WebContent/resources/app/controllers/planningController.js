app.controller('planningCtrl', [ '$scope', '$http', 'ngNotify', 'blockUI', function ($scope, $http, ngNotify, blockUI) {

	ngNotify.config({
		theme : 'pure',
		position : 'bottom',
		duration : 3000,
		type : 'info',
		sticky : false,
		button : true,
		html : false
	});
	
	
	$scope.addButton = true;

	blockUI.autoInjectBodyBlock = false;
	blockUI.message = 'Cargando...';

	$scope.date = new Date();
	console.log(notebook);
	$scope.notebook = angular.copy(notebook);	
	
	$scope.selectedDay = {date: new Date(), text:"" }
	
	$scope.save = function() {
		console.log($scope.notebook);		
		$http({
			  method: 'POST',
			  url: 'planning/saveNotebook',
			  data: $scope.notebook
			}).success(function successCallback(response) {
				blockUI.stop();
				console.log(response);
				ngNotify.set('Guardado corectamente', 'success');			    		   
			  }, function errorCallback(response) {				  
				  console.log(response);
				  ngNotify.set('ERROR - Datos no guardados', 'error');
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  }).error(function (response) {
				  console.log(response);
				  ngNotify.set('ERROR - Datos no cargados', 'error');
			  });
	}
	
	
	

} ]);
