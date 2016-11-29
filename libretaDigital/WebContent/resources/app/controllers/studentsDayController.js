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
		$scope.student.absencesTotal = $scope.absencesCount($scope.student.calendar);
		$scope.student = $scope.getAbsencesAndQualifications($scope.student);
	    console.log($scope.student);
	}
	
	$scope.absencesCount = function (calendar){
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
	
	

} ]);
