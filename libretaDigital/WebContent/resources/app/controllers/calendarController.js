app.controller('calendarCtrl', ['$scope','moment', 'calendarConfig', 'blockUI', function ($scope, moment, calendarConfig, blockUI) {
	
	blockUI.autoInjectBodyBlock = false;
	blockUI.message = 'Cargando...';
	
	 var vm = this;
	 	
	    vm.events = [];
	    vm.calendarView = 'year';
	    vm.viewDate = moment().startOf('month').toDate();	    
	   
	    calendarConfig.i18nStrings.weekNumber = 'Semana {week}';
	    
	    vm.viewChangeClicked = function(nextView) {
	      if (nextView === 'day') {
	        return false;
	      }
	    };
	
	
	
}]);