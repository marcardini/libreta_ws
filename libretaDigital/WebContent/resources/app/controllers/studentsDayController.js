app.controller('studentsDayCtrl', ['$scope', '$filter', '$http', 'ngNotify', 'blockUI', '$uibModal', function ($scope, $filter, $http, ngNotify, blockUI, $uibModal) {

	
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
	$scope.editButtons = true;
	$scope.calificateButton = true;
	$scope.editCalfButton = false;
	
	$scope.students = [];	
	angular.copy(students, $scope.students);
	$scope.student = {};
	$scope.absence = {};
	$scope.qualy = null;
	
//	 angular.forEach($scope.students, function(item){ 
//		item.isSelected = false;			 	 
//	 });
	 console.log($scope.students);
	 
	$scope.studentSelect = function(row) {		
		    if(!row.isSelected){
		    	$scope.student = {};
		    	$scope.student.qualyPer = 0;
		    	$scope.student.absencesPer = 0;
//		    	console.log($scope.student);
		    }else{
		    	$scope.student = angular.copy(row);		
				$scope.setPercents($scope.student);
//			    console.log($scope.student);
		    }
	}
	
	$scope.setPercents = function (student){
		student.absencesTotal = $scope.gatAbsencesCount(student.calendar);
		student = $scope.getAbsencesAndQualifications(student);
		student.absencesPer = (student.absencesTotal * 100)/15;
		student.qualyPer = ($scope.getAverage(student.qualifications) * 100)/12;		
	}
	
	$scope.absenceSelect = function(row){		
		if(!row.isSelected){
			$scope.absence = {};
			$scope.editButtons = true;
			console.log($scope.absence);
		}else{
			$scope.editButtons = false;
			$scope.absence = angular.copy(row);			
			console.log($scope.absence);
		}
	}
	
	$scope.qualySelect = function(row){
		if(!row.isSelected){
			$scope.qualy = null;
			$scope.calificateButton = true;
			$scope.editCalfButton = false;
			
		}else{
			$scope.qualy = angular.copy(row);
			$scope.calificateButton = false;
			$scope.editCalfButton = true;
			console.log($scope.qualy);
		}
	}
	
	$scope.gatAbsencesCount = function (calendar){
		var count = 0;
		for (var int = 0; int < calendar.length; int++) {
			if(calendar[int].eventRegistrationType === "INASSISTANCE"){				
				count++;
			}else if(calendar[int].eventRegistrationType === "HALF_ASSISTANCE"){
				count = count + 0.5;
			}else if(calendar[int].eventRegistrationType === "JUSTIFIED"){
				count = count + 0.5;
			}
		}
		return count;
	}
	
	$scope.getAbsencesAndQualifications = function(student){	
		student.absences = [];
		student.qualifications = [];
		for (var int = 0; int < student.calendar.length; int++) {
			if(student.calendar[int].eventRegistrationType === "INASSISTANCE"){
				student.calendar[int].label = "FALTA";
				student.absences.push(student.calendar[int]);
			}else if( student.calendar[int].eventRegistrationType === "HALF_ASSISTANCE"){
				student.calendar[int].label = "MEDIA FALTA";
				student.absences.push(student.calendar[int]);	
			}else if( student.calendar[int].eventRegistrationType === "JUSTIFIED"){
				student.calendar[int].label = "JUSTIFICADA";
				student.absences.push(student.calendar[int]);
			}else{
				student.qualifications.push(student.calendar[int]);
			}
		}
		return student;
	}
	
	$scope.getAverage = function(qualifications){
		var avg = 0;
		var count = 0;
		if(qualifications.length > 0){
			angular.forEach(qualifications, function(qualy){
				avg = avg + qualy.value;
				count++;
			});
			return avg/count;
		}else{
			return 0;
		}		
		
	}
	
	 $scope.qualyColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#337AB7', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#D9EDF7' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			};
	 
	 $scope.absencesColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#D9534F', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#F2DEDE' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			};
	
	 $scope.$watch('student', function() {
//		 if($scope.student == null){
//			 $scope.editButtons = true;	
//		 }
		 
	         	        
	 }, true);
	 
	 
	 //Modal
	 	 
	 $scope.calificate = function () {		    
		    var modalInstance = $uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      bindToController: true,
		      templateUrl: 'myModalContent.html',
		      controller: 'ModalInstanceCtrl',
		      controllerAs:'$scope',
		      keyboard: true,
		      size: 'lg',		      
		      windowClass: 'center-modal',
		      resolve: {
		    	  qualy: function () {
		          return $scope.qualy;
		        }
		      }
		    });

//		    modalInstance.result.then(function (selectedItem) {
//		      console.log(selectedItem);
//		    }, function () {
//		      $log.info('Modal dismissed at: ' + new Date());
//		    });
		  };


		

}]);



app.controller('ModalInstanceCtrl', function ($uibModalInstance, qualy, $scope) {
	
	$scope.title = "Calificar";
	$scope.qualy = qualy;			
	console.log($scope.qualy)
	
	if(angular.isObject($scope.qualy)){
		$scope.title = "Modificar Calificacion";
	}
	   
	$scope.ok = function () {		
	   $uibModalInstance.close();	  
	};

	$scope.cancel = function () {		
	   $uibModalInstance.dismiss('cancel');	  
	};
	   
});
