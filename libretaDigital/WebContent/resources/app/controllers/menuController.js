app.controller('menuCtrl', [ '$scope', '$http', function($scope, $http) {
	console.log(logguedUserName);
//	$scope.logguedUserName = logguedUserName;
	$scope.logguedUserName = '';
	$scope.logguedUserName = angular.copy(logguedUserName);
//	console.log($scope.logguedUserName);
	switch (codMenu) {
	case "H0":
		$scope.home = "active";		
		break;
	case "D1":
		$scope.datos = "active";
		$scope.datos1 = "active";
		break;
	case "D2":
		$scope.datos = "active";
		$scope.datos2 = "active";
		break;
	case "G1":
		$scope.group = "active";
		$scope.group1 = "active";
		break;
	case "G2":
		$scope.group = "active";
		$scope.group2 = "active";
		break;
	case "G3":
		$scope.group = "active";
		$scope.group3 = "active";
		break;
	case "B1":
		$scope.bulletins = "active";		
		break;	
	default:
		$scope.home = "active";
	}
	
} ]);