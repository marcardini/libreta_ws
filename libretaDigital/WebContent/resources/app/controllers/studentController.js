app.controller('studentCtrl', ['$scope','$http','ngNotify','blockUI',	function($scope, $http, ngNotify, blockUI) {

			ngNotify.config({
				theme : 'pure',
				position : 'bottom',
				duration : 3000,
				type : 'info',
				sticky : false,
				button : true,
				html : false
			});
			
	      
			blockUI.autoInjectBodyBlock = false;
			blockUI.message = 'Cargando...';

			$scope.editButton = false;
			$scope.addButton = true;

			$scope.date = new Date();
			$scope.student = {};

			$scope.students = [];
			angular.copy(students, $scope.students);
			console.log(students);
			console.log($scope.students);

			$scope.reset = function(form) {
				$scope.student = {};
				if (form) {
					form.$setPristine();
					form.$setUntouched();
				}
				for (var i = 0; i < $scope.students.length; i++) {
					$scope.students[i].isSelected = false;
				}
			}

			$scope.types = [ "students", "admin" ];
			$scope.students.type = $scope.types[0];

			$scope.$watch('students', function() {			
				if ($scope.student.oid != null) {
					$scope.btnAdd = "Update";
				} else {
					$scope.btnAdd = "Add";
				}
			});

			$scope.deleteStudent = function(item) {				
				var items = [];
				items.push(item);
				$http({
					method : 'POST',
					url : 'data/deleteStudent',
					data : items
				}).success(function successCallback(response) {
					blockUI.stop();
					ngNotify.set('Eliminado corectamente', 'success');
					$scope.students.splice($scope.students.indexOf(item), 1);

				}, function errorCallback(response) {
					console.log(response);
					ngNotify.set('ERROR - Datos no guardados', 'error');
					
				}).error(function(response) {
					console.log(response);
					ngNotify.set('ERROR - Datos no guardados', 'error');
				});
			}

			$scope.rowSelect = function(row) {
//				console.log($scope);
				console.log($scope.getFile());
				if (!row.isSelected) {
					$scope.student = {};
					$scope.addButton = true;
					$scope.editButton = false;
				} else {
					$scope.student = angular.copy(row);
					$scope.addButton = false;
					$scope.editButton = true;
				}

			}

			$scope.addStudent = function() {
				console.log($scope.Student);
				var students = [];		
				students.push($scope.student);
				$http({
					  method: 'POST',
					  url: 'data/saveStudents',
					  data: students
					}).success(function successCallback(response) {
						blockUI.stop();
						$scope.savePhoto($scope.student.email);
						ngNotify.set('Guardado corectamente', 'success');
					    $scope.getStudents();			   
					  }, function errorCallback(response) {				  
						  console.log(response);
						  ngNotify.set('ERROR - Datos no guardados', 'error');
					    // called asynchronously if an error occurs
					    // or server returns response with an error status.
					  }).error(function (data, status, header, config) {
						  console.log(status);
						  ngNotify.set('ERROR - Datos no cargados', 'error');
					  });
			}
			
			$scope.savePhoto = function(mail){
				console.log($scope.getFile());
				$http({
					  method: 'POST',
					  url: 'data/saveStudentPhoto?mail=' + mail,
					  data: $scope.getFile(),
					  headers: {'Content-Type': undefined}
					}).success(function successCallback(response) {
						blockUI.stop();
						ngNotify.set('Guardado corectamente', 'success');
					    $scope.getStudents();			   
					  }, function errorCallback(response) {				  
						  console.log(response);
						  ngNotify.set('ERROR - Datos no guardados', 'error');
					    // called asynchronously if an error occurs
					    // or server returns response with an error status.
					  }).error(function (data, status, header, config) {
						  console.log(status);
						  ngNotify.set('ERROR - Datos no cargados', 'error');
					  });
			}
			
			$scope.getStudents = function(){
				 $http.get('data/students').success(function (data, status, headers, config) {			 
					   console.log(data);			   
					  }).error(function (data, status, header, config) {
						  console.log(status);
						  ngNotify.set('ERROR - Datos no cargados', 'error');
					  });
			 };

}]);
