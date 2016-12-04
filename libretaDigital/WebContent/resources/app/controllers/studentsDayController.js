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
	
	$scope.student = {  };
	$scope.students = [];	
	angular.copy(students, $scope.students);
	
	$scope.rowSelect = function(row) {
		$scope.student = angular.copy(row);
		$scope.student.absencesTotal = $scope.gatAbsencesCount($scope.student.calendar);
		$scope.student = $scope.getAbsencesAndQualifications($scope.student);
		$scope.student.absencesPer = ($scope.student.absencesTotal * 100)/15;
	    $scope.student.qualyPer = ($scope.getAverage($scope.student.qualifications) * 100)/12;
	    console.log($scope.student);
	}
	
	$scope.gatAbsencesCount = function (calendar){
		var count = 0;
		for (var int = 0; int < calendar.length; int++) {
			if(calendar[int].eventRegistrationType === "INASSISTANCE"){
				count++;
			}else if(calendar[int].eventRegistrationType === "HALF_ASSISTANCE"){
				count = count + 0.5;
			}
		}
		return count;
	}
	
	$scope.getAbsencesAndQualifications = function(student){	
		student.absences = [];
		student.qualifications = [];
		for (var int = 0; int < student.calendar.length; int++) {
			if(student.calendar[int].eventRegistrationType === "INASSISTANCE" || student.calendar[int].eventRegistrationType === "HALF_ASSISTANCE"){
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
		angular.forEach(qualifications, function(qualy){
			avg = avg + qualy.value;
			count++;
		});
		return avg/count;
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
	    	
	        //$scope.qualifications.length;      	        
	       ;	       
	         	        
	    }, true);
	
	

} ]);
