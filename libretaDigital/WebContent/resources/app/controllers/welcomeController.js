app.controller('welcomeCtrl', [ '$scope', '$http', 'ngNotify', 'blockUI', function ($scope, $filter, $http, ngNotify, blockUI) {

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

	$scope.date = new Date();

} ]);
