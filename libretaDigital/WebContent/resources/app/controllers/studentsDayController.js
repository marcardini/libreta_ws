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
	
	console.log(professors);
	
	$scope.students = [];	
	angular.copy(students, $scope.students);
	
	$scope.student = {calendar: []};
	$scope.student.qualyAvg = 0;
	$scope.absence = {};
	$scope.qualy = {};
//	console.log($scope.eventsRegistrationTypes );
//	 angular.forEach($scope.students, function(item){ 
//		item.isSelected = false;			 	 
//	 });
	 console.log($scope.students);
	 
	$scope.studentSelect = function(row) {		
		    if(!row.isSelected){
		    	$scope.student = {};
		    	$scope.student.qualyPer = 0;
		    	$scope.student.absencesPer = 0;
		    	$scope.student.qualyAvg = 0;
		    	$scope.calificateButton = true;
//		    	console.log($scope.student);
		    }else{
		    	$scope.student = row;		
				$scope.setPercents($scope.student);
				$scope.calificateButton = false;
			    console.log($scope.student);
		    }
	}
	
	$scope.setPercents = function (student){
		student.absencesTotal = $scope.gatAbsencesCount(student.calendar);
		student = $scope.getAbsencesAndQualifications(student);
		student.absencesPer = (student.absencesTotal * 100)/15;
		student.qualyPer = ($scope.getAverage(student.qualifications) * 100)/12;
		student.qualyAvg = ($scope.getAverage(student.qualifications));
	}
	
	$scope.absenceSelect = function(row){		
		if(!row.isSelected){
			$scope.absence = {};
			$scope.editButtons = true;
			console.log($scope.absence);
		}else{
			$scope.editButtons = false;
			$scope.absence = row;			
			console.log($scope.absence);
		}
	}
	
	$scope.qualySelect = function(row){
		if(!row.isSelected){
			$scope.qualy = null;
			//$scope.calificateButton = true;
			$scope.editCalfButton = false;
			
		}else{
			$scope.qualy = row;
			//$scope.calificateButton = false;
			$scope.editCalfButton = true;
			console.log($scope.qualy);
		}
	}
	
	$scope.gatAbsencesCount = function (calendar){
		var count = 0;
		for (var int = 0; int < calendar.length; int++) {
			if(calendar[int].eventRegistrationType === "FALTA"){				
				count++;
			}else if(calendar[int].eventRegistrationType === "MEDIA_FALTA"){
				count = count + 0.5;
			}else if(calendar[int].eventRegistrationType === "JUSTIFICADA"){
				count = count + 0.5;
			}
		}
		return count;
	}
	
	$scope.getAbsencesAndQualifications = function(student){	
		student.absences = [];
		student.qualifications = [];
		for (var int = 0; int < student.calendar.length; int++) {
			if(student.calendar[int].eventRegistrationType === "FALTA" || student.calendar[int].eventRegistrationType === "JUSTIFICADA" 
							|| student.calendar[int].eventRegistrationType === "MEDIA_FALTA" || student.calendar[int].eventRegistrationType === "MEDIA FALTA"){
				if(student.calendar[int].eventRegistrationType === "MEDIA_FALTA"){
					//student.calendar[int].eventRegistrationType = "MEDIA FALTA";
				}
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

	 }, true);
	 
	 $scope.delete = function (item){
		 var items= [];
		 items.push(item.oid);
		 $http({
			  method: 'POST',
			  url: 'main/deleteStudentDay',
			  data: items
			}).success(function successCallback(response) {
				blockUI.stop();
				ngNotify.set('Eliminado corectamente', 'success');
				$scope.student.calendar.splice($scope.student.calendar.indexOf(item), 1);
				$scope.getAbsencesAndQualifications($scope.student);			
			    $scope.gatAbsencesCount($scope.student.calendar);			   
			  }, function errorCallback(response) {				  
				  console.log(response);
				  ngNotify.set('ERROR - Datos no guardados', 'error');
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });			
	 };
	 
	 
	 //Modal	 	 
	 $scope.calificate = function () {		    
		    var modalInstance = $uibModal.open({
		      animation: true,
		      ariaLabelledBy: 'modal-title',
		      ariaDescribedBy: 'modal-body',
		      bindToController: true,
		      templateUrl: 'editQualificationModal.html',
		      controller: 'ModalInstanceQualifyCtrl',
		      controllerAs:'$scope',
		      backdrop: 'static',
		      keyboard: true,
		      size: 'lg',		      
		      windowClass: 'center-modal',
		      resolve: {
		    	  qualy: function () {
		    		 return $scope.qualy;
		    	 },
		    	 student:function(){
		    		 return $scope.student;
		    	 }
		      }
		    });

		    modalInstance.result.then(function (response) {
		      $scope.editCalfButton = false;		      		      
		      console.log(response);
		      $scope.getAbsencesAndQualifications($scope.student);		      
		      blockUI.stop();		      
		      if(response){		    	  
		    	  ngNotify.set('Guardado corectamente', 'success');			    	  
		      }else{
		    	  ngNotify.set('ERROR - Datos no guardados', 'error');
		      }		      
		    }, function () {
//		      console.log('Modal dismissed at: ' + new Date());		      
		    });		    
		  };
		  
			 //Modal	 	 
		 $scope.editAbsence = function () {		    
				    var modalInstance = $uibModal.open({
				      animation: true,
				      ariaLabelledBy: 'modal-title',
				      ariaDescribedBy: 'modal-body',
				      bindToController: true,
				      templateUrl: 'editAbsenceModal.html',
				      controller: 'ModalInstanceAbsenceCtrl',
				      controllerAs:'$scope',
				      backdrop: 'static',
				      keyboard: true,
				      size: 'lg',		      
				      windowClass: 'center-modal',
				      resolve: {
				    	  absence: function () {
				    		 return $scope.absence;
				    	 },
				    	 student:function(){
				    		 return $scope.student;
				    	 }
				      }
				    });

				    modalInstance.result.then(function (response) {
//				      $scope.editCalfButton = false;		      		      
				      console.log(response);
				      $scope.getAbsencesAndQualifications($scope.student);		      
				      blockUI.stop();		      
				      if(response){		    	  
				    	  ngNotify.set('Guardado corectamente', 'success');			    	  
				      }else{
				    	  ngNotify.set('ERROR - Datos no guardados', 'error');
				      }		      
				    }, function () {
//				      console.log('Modal dismissed at: ' + new Date());		      
				    });		    
		  };

		  //Modal	 	 
			 $scope.calGroup = function () {		    
				    var modalInstance = $uibModal.open({
				      animation: true,
				      ariaLabelledBy: 'modal-title',
				      ariaDescribedBy: 'modal-body',
				      bindToController: true,
				      templateUrl: 'editGroupQualificationModal.html',
				      controller: 'ModalInstanceGroupQualifyCtrl',
				      controllerAs:'$scope',
				      backdrop: 'static',
				      keyboard: true,
				      size: 'lg',		      
				      windowClass: 'center-modal',
				      resolve: {
				    	  qualy: function () {
				    		 return $scope.qualy;
				    	 },
				    	 students:function(){
				    		 return $scope.students;
				    	 }
				      }
				    });

				    modalInstance.result.then(function (response) {
				      $scope.editCalfButton = false;		      		      
				      console.log(response);
				      $scope.getAbsencesAndQualifications($scope.student);		      
				      blockUI.stop();		      
				      if(response){		    	  
				    	  ngNotify.set('Guardado corectamente', 'success');			    	  
				      }else{
				    	  ngNotify.set('ERROR - Datos no guardados', 'error');
				      }		      
				    }, function () {
//				      console.log('Modal dismissed at: ' + new Date());		      
				    });		    
				  };
		

}]);


app.controller('ModalInstanceQualifyCtrl', function ($uibModalInstance, qualy, student, $scope, $http) {
	
	$scope.title = "Calificar";
	$scope.qualy = {};
	$scope.qualy.oid = null;
	$scope.qualy.value = 1;
	$scope.qualy.date = new Date();			
	$scope.events = [];
	$scope.qualy.eventRegistrationType = "ORAL";
	angular.forEach(eventsRegistrationTypes, function(event){
		if( event != "FALTA" && event != "MEDIA_FALTA" && event != "JUSTIFICADA" ){
			$scope.events.push(event);
		}
	});
		
	if(qualy != null && !angular.isUndefined(qualy.value)){		
		$scope.qualy = qualy;
		$scope.old = angular.copy(qualy);
		$scope.title = "Modificar Calificacion";
//		console.log(qualy.eventRegistrationType);
	}else{		
//		console.log(student);
		$scope.qualy.studentId = student.oid;		
	}
	

	$scope.slider = {
			value: $scope.qualy.value,			   		   
		    options: {	    	
		        showSelectionBar: true,
		        showTicks: true,
		        floor: 1,
		        ceil: 12,
		        minValue: 1,
		        maxValue: 12,
		        getSelectionBarColor: function(value) {
		            if (value <= 5)
		                return 'red';
		            if (value <= 6)
		                return 'orange';
		            if (value <= 8)
		                return 'yellow';
		            return '#2AE02A';
		        }
		    }
		};
	   
	$scope.ok = function () {
		$scope.data = [];
		$scope.qualy.value = $scope.slider.value;
		$scope.data.push($scope.qualy);		
		$http({
			  method: 'POST',
			  url: 'main/saveStudentDay',
			  data: $scope.data
			}).success(function successCallback(response) {					
				if($scope.qualy.oid === null){	
					student.calendar.push($scope.qualy);
					$uibModalInstance.close(true);
				}else{					
					$uibModalInstance.close(true);
				}			
								
			  }, function errorCallback(response) {				  
				  console.log(response);
				  qualy.value = $scope.old.value;
				  qualy.comment = $scope.old.comment;
				  qualy.eventRegistrationType = $scope.old.eventRegistrationType;
				  $uibModalInstance.close(false);
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });	   	   	
	};

	$scope.cancel = function () {
		if(angular.isObject($scope.old)){
			qualy.value = $scope.old.value;
			qualy.comment = $scope.old.comment;
			qualy.eventRegistrationType = $scope.old.eventRegistrationType;
		}		
		$uibModalInstance.dismiss('cancel');	  
	};
	
	 setInterval(function() {		       
		  $scope.$broadcast('reCalcViewDimensions');
	    }, 100);
		
	   
});

app.controller('ModalInstanceAbsenceCtrl', function ($uibModalInstance, absence, student, $scope, $http) {
	
	$scope.title = "Justificar";
	$scope.absence = {};	
	$scope.events = [];	
	angular.forEach(eventsRegistrationTypes, function(event){
		if(event == "FALTA" || event === "MEDIA_FALTA" || event === "JUSTIFICADA"){
			$scope.events.push(event);
		}
	});
	console.log(eventsRegistrationTypes);
	
	if(angular.isObject($scope.absence)){
		$scope.absence = absence;
		$scope.old = angular.copy(absence);
		$scope.title = "Modificar Falta";
//		console.log(qualy.eventRegistrationType);
	}else{		
		$scope.absence.studentId = student.oid;
	}
	   
	$scope.ok = function () {
		$scope.data = [];		
		$scope.data.push($scope.absence);		
		$http({
			  method: 'POST',
			  url: 'main/saveStudentDay',
			  data: $scope.data
			}).success(function successCallback(response) {					
				if($scope.absence.oid === null){	
					student.calendar.push($scope.qualy);
					$uibModalInstance.close(true);
				}else{					
					$uibModalInstance.close(true);
				}			
								
			  }, function errorCallback(response) {				  
				  console.log(response);				 
				  absence.comment = $scope.old.comment;
				  absence.eventRegistrationType = $scope.old.eventRegistrationType;
				  $uibModalInstance.close(false);
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });	   	   	
	};
		   
});


app.controller('ModalInstanceGroupQualifyCtrl', function ($uibModalInstance,  students, $scope, $http) {
	
	$scope.title = "Calificar Grupo";
	$scope.qualy = {};
	$scope.qualy.oid = null;
	$scope.qualy.value = 1;
	$scope.qualy.date = new Date();			
	$scope.events = [];
	$scope.qualy.eventRegistrationType = "ORAL";
	angular.forEach(eventsRegistrationTypes, function(event){
		if( event != "FALTA" && event != "MEDIA_FALTA" && event != "JUSTIFICADA" ){
			$scope.events.push(event);
		}
	});
		
	$scope.slider = {
			value: $scope.qualy.value,			   		   
		    options: {	    	
		        showSelectionBar: true,
		        showTicks: true,
		        floor: 1,
		        ceil: 12,
		        minValue: 1,
		        maxValue: 12,
		        getSelectionBarColor: function(value) {
		            if (value <= 5)
		                return 'red';
		            if (value <= 6)
		                return 'orange';
		            if (value <= 8)
		                return 'yellow';
		            return '#2AE02A';
		        }
		    }
		};
	   
	$scope.ok = function () {
		$scope.data = [];
		$scope.qualy.value = $scope.slider.value;
		angular.forEach(students, function(item){ 
			var cds = angular.copy($scope.qualy);
			cds.studentId = item.oid;
			$scope.data.push(cds);	
		 });
		console.log($scope.data);
		$http({
			  method: 'POST',
			  url: 'main/saveStudentDay',
			  data: $scope.data
			}).success(function successCallback(response) {					
				if($scope.qualy.oid === null){	
//					student.calendar.push($scope.qualy);
					angular.forEach(students, function(item){ 
						var cds = angular.copy($scope.qualy);
						cds.studentId = item.oid;
						item.calendar.push(cds);	
					 });
					$uibModalInstance.close(true);
				}else{					
					$uibModalInstance.close(true);
				}			
								
			  }, function errorCallback(response) {				  
				  console.log(response);				  
				  $uibModalInstance.close(false);
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });	   	   	
	};

	$scope.cancel = function () {
		if(angular.isObject($scope.old)){
			qualy.value = $scope.old.value;
			qualy.comment = $scope.old.comment;
			qualy.eventRegistrationType = $scope.old.eventRegistrationType;
		}		
		$uibModalInstance.dismiss('cancel');	  
	};
	
	 setInterval(function() {		       
		  $scope.$broadcast('reCalcViewDimensions');
	    }, 100);
		
	   
});
