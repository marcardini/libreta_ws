app.controller('bulletinCtrl', [ '$scope', '$http', 'ngNotify', 'blockUI', function ($scope, $http, ngNotify, blockUI) {

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
	$scope.student = {};
	console.log(studentNotas);
	$scope.studentNotas = angular.copy(studentNotas);
	
	$scope.studentSelect = function(row) {
		if(!row.isSelected){
			$scope.student = {};
		}else{
			$scope.student = row;
		}
		
		
	};
	
	$scope.ppr = function (calendar){
		var ppr = "-";
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "PRIMER_PROMEDIO"){
				ppr = day.value;
			}
		}
		return ppr;
	}
	
	$scope.psr = function (calendar){
		var psr = "-";
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "SEGUNDO_PROMEDIO"){
				psr = day.value;
			}
		}
		return psr;
	}
	
	$scope.pf = function (calendar){
		var pf = "-";
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "PROMEDIO_FINAL"){
				pf = day.value;
			}
		}
		return pf;
	}
	
	$scope.ft = function (calendar){
		var ft = 0;
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "FALTA"){
				ft++;
			}else if(day.eventRegistrationType === "MEDIA_FALTA"){
				ft= ft + 0.5;				
			}else if(day.eventRegistrationType === "JUSTIFICADA"){
				ft = ft + 0.5;
				
			}
		}
		return Math.round(ft);
	}
	
	$scope.llt = function (calendar){
		var llt = 0;
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "MEDIA_FALTA"){
				llt++;
			}			
		}
		return llt;
	}
	
	$scope.fj = function (calendar){
		var fj = 0;
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "JUSTIFICADA"){
				fj++;
			}			
		}
		return fj;
	}
	
	$scope.pjd = function (calendar){
		var jf = " ---- ";
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "PRIMER_JUICIO_DOCENTE"){
				jf = day.comment;
			}			
		}
		return jf;
	}
	
	$scope.sjd = function (calendar){
		var jd = " ---- ";
		for (var int = 0; int < calendar.length; int++) {
			var day = calendar[int];
			if (day.eventRegistrationType === "SEGUNDO_JUICIO_DOCENTE"){
				jd = day.comment;
			}			
		}
		return jd;
	}
	
	
} ]);
