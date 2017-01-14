app.controller('professorCtrl', ['$scope','$http','ngNotify','blockUI',	function($scope, $http, ngNotify, blockUI) {

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
			$scope.professor = {};

			$scope.professors = [];
			angular.copy(professors, $scope.professors);
			console.log(professors);
			console.log($scope.professors);

			$scope.reset = function(form) {
				$scope.professor = {};
				if (form) {
					form.$setPristine();
					form.$setUntouched();
				}
				for (var i = 0; i < $scope.professors.length; i++) {
					$scope.professors[i].isSelected = false;
				}
			}

			$scope.types = [ "professors", "admin" ];
			$scope.professors.type = $scope.types[0];

			$scope.$watch('professors', function() {
				if ($scope.professor.oid != null) {
					$scope.btnAdd = "Update";
				} else {
					$scope.btnAdd = "Add";
				}
			});

			$scope.deleteProfessor = function(item) {				
				var items = [];
				items.push(item.oid);
				$http({
					method : 'POST',
					url : 'main/deleteProfessor',
					data : items
				}).success(function successCallback(response) {
					blockUI.stop();
					ngNotify.set('Eliminado corectamente', 'success');
					$scope.professors.splice($scope.professors.indexOf(item), 1);

				}, function errorCallback(response) {
					console.log(response);
					ngNotify.set('ERROR - Datos no guardados', 'error');
					
				}).error(function(response) {
					console.log(response);
					ngNotify.set('ERROR - Datos no guardados', 'error');
				});
			}

			$scope.rowSelect = function(row) {

				if (!row.isSelected) {
					$scope.professor = {};
					$scope.addButton = true;
					$scope.editButton = false;
				} else {
					$scope.professor = angular.copy(row);
					$scope.addButton = false;
					$scope.editButton = true;
				}

			}

			$scope.addProfessor = function() {
				console.log($scope.Profesor);
				var professors = [];
				professors.push($scope.professor);
				$http({
					  method: 'POST',
					  url: 'data/saveProfessors',
					  data: professors
					}).success(function successCallback(response) {
						blockUI.stop();
						ngNotify.set('Guardado corectamente', 'success');
					    $scope.getProfessors();			   
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
			
			$scope.getProfessors = function(){		
				 $http.get('data/profesors').success(function (data, status, headers, config) {			 
					   conole.log(data);			   
					  }).error(function (data, status, header, config) {
						  console.log(status);
						  ngNotify.set('ERROR - Datos no cargados', 'error');
					  });
			 };

}]);
