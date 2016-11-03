app.controller('menuCtrl', [ '$scope', '$http', function($scope, $http) {

	switch (codMenu) {
	case "H0":
		$scope.home = "active";		
		break;
	case "D1":
		$scope.datos = "active";
		$scope.datos1 = "active";
		break;
	default:
		$scope.home = "active";
	}
	
	

} ]);
