app.controller('groupCtrl', ['$scope','$http','ngNotify','blockUI',	function($scope, $http, ngNotify, blockUI) {

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
			$scope.group = {};

			$scope.groups = [];
			angular.copy(groups, $scope.groups);
			$scope.professors = angular.copy(professors);
//			console.log(professors);
//			console.log($scope.professors);
//			console.log(groups);
//			console.log($scope.groups);

			$scope.reset = function(form) {
				$scope.group = {};
				if (form) {
					form.$setPristine();
					form.$setUntouched();
				}
				for (var i = 0; i < $scope.groups.length; i++) {
					$scope.groups[i].isSelected = false;
				}
			}

			$scope.types = [ "groups", "admin" ];
			$scope.groups.type = $scope.types[0];

			$scope.$watch('groups', function() {
				if ($scope.group.oid != null) {
					$scope.btnAdd = "Update";
				} else {
					$scope.btnAdd = "Add";
				}
			});

			$scope.deleteGroup = function(item) {				
				var items = [];
				items.push(item);
				$http({
					method : 'POST',
					url : 'data/deleteGroup',
					data : items
				}).success(function successCallback(response) {
					blockUI.stop();
					ngNotify.set('Eliminado corectamente', 'success');
					$scope.groups.splice($scope.groups.indexOf(item), 1);

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
					$scope.group = {};
					$scope.addButton = true;
					$scope.editButton = false;
				} else {
					$scope.group = angular.copy(row);
					$scope.addButton = false;
					$scope.editButton = true;
				}

			}

			$scope.addGroup = function() {
				console.log($scope.Group);
				var groups = [];
				groups.push($scope.group);
				$http({
					  method: 'POST',
					  url: 'data/saveGroups',
					  data: groups
					}).success(function successCallback(response) {
						blockUI.stop();
						ngNotify.set('Guardado corectamente', 'success');
					    $scope.getGroups();			   
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
			
			$scope.getGroups = function(){
				 $http.get('data/groups').success(function (data, status, headers, config) {			 
					   console.log(data);			   
					  }).error(function (data, status, header, config) {
						  console.log(status);
						  ngNotify.set('ERROR - Datos no cargados', 'error');
					  });
			 };

}]);
