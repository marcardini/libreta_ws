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
	
	$scope.today = function() {
	    $scope.date = new Date();
	};
	$scope.today();
	
	$scope.options = {
//			    customClass: getDayClass,
			    minDate: new Date("2017-01-01"),
			    maxDate: new Date("2017-12-31"),
			    showWeeks: true
			  };

	blockUI.autoInjectBodyBlock = false;
	blockUI.message = 'Cargando...';

	//$scope.date = new Date("yyyy-mm-dd");
	$scope.notebook = { desarrolloDelCurso:[]};
	$scope.notebook = angular.copy(notebook[0]);
//	console.log($scope.notebook);
	
	$scope.save = function() {
//		console.log($scope.notebook);		
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
	
		
	$scope.setDay = function (date){
		console.log($scope.notebook);
		$scope.day = $scope.selectDay(date);
		console.log($scope.day);
	}
	

	$scope.addDay = function(){	
//		console.log($scope.day);
		var existe = false;
		for (var int = 0; int < $scope.notebook.desarrolloDelCurso.length; int++) {
			var aux = $scope.notebook.desarrolloDelCurso[int];	
			var auxDate = new Date(""+ aux.date +"");
			var dateObj = new Date(""+$scope.day.date+"");
			if(dateObj.getYear() == auxDate.getYear() && dateObj.getMonth() == auxDate.getMonth() && dateObj.getDate() == auxDate.getDate()){
				existe = true;
				aux.comment = $scope.day.comment;
			}			
		}
		if(!existe){
			$scope.notebook.desarrolloDelCurso.push($scope.day);	
		}
		$scope.isNew = false;						
		console.log($scope.notebook.desarrolloDelCurso);
		
	};
	
	$scope.selectDay = function (date){			
//		console.log($scope.notebook);
		var day = {"date":new Date(""+date+""), "comment":"", "notebookId":$scope.notebook.oid};	
		for (var int = 0; int < $scope.notebook.desarrolloDelCurso.length; int++) {
			var aux = $scope.notebook.desarrolloDelCurso[int];	
			var auxDate = new Date(""+ aux.date +"");			
			if(date.getYear() == auxDate.getYear() && date.getMonth() == auxDate.getMonth() && date.getDate() == auxDate.getDate()){
				day = aux;
			}			
		}
		$scope.isNew = (day.comment == "");
//		console.log(day);
		return day;		
	}
	
//	console.log($scope.notebook);
	$scope.setDay($scope.date);
	
	

} ]);
