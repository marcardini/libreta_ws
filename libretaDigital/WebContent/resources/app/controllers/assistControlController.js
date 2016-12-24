app.controller('assistControlCtrl', ['$scope', '$filter', '$http', 'ngNotify', 'blockUI', function ($scope, $filter, $http, ngNotify, blockUI) {
	
	
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
	$scope.students = [];
	$scope.studentsAbsences = [];	
	angular.copy(studentsAbsences, $scope.studentsAbsences);
	
	//console.log($scope.studentsAbsences);
	console.log(students);
	
	/* LISTAS */
	
	 $scope.models = [
	      {listName: "A", items: [], dragging: false},
	      {listName: "B", items: [], dragging: false}
	    ];
	 
	 
	 $scope.listType = function (name){
		 if(name === "A"){			 
			 return "panel-primary";
		 }else{
			 return "panel-danger";
		 }
	 };
	 
	 $scope.invertlistType = function(name){
		 if(name === "B"){			 
			 return "primary";
		 }else{
			 return "danger";
		 }
	 };
	 
	 $scope.arrowType = function(name){
		 if(name === "B"){			 
			 return "left";
		 }else{
			 return "right";
		 }
	 };
	 
	 $scope.title = function (name){
		 if(name === "A"){			 
			 return "Presentes";
		 }else{
			 return "Faltas";
		 }
	 };
	 
	 $scope.count = function(name){		
		 if(name === "A"){			 
			 return $scope.presentes;
		 }else{
			 return $scope.ausentes;
		 }
	 };
	 
	 $scope.saveAbsences = function (){			 
		 var absences = [];		 
		 angular.forEach($scope.models[0].items, function(item){
		 var aux = {studentId:"", late:false};
			 if(item.late){
				 aux.studentId = item.oid;
				 aux.eventRegistrationType = "MEDIA_FALTA";
				 if(item.calendar[0] != null){
					 aux.oid = item.calendar[0].oid;
				 }				 
				 absences.push(aux);
			 } 		 
		 });
		 angular.forEach($scope.models[1].items, function(item){ 
			 var aux = {studentId:"", late:false};
			 aux.eventRegistrationType = "FALTA";
			 if(item.calendar[0] != null){
				 aux.oid = item.calendar[0].oid;
			 }
			 aux.studentId = item.oid;			 	
			 absences.push(aux);			 	 
		 });
		 console.log(absences)
		 $http({
			  method: 'POST',
			  url: 'main/saveStudentDay',
			  data: absences
			}).success(function successCallback(response) {
				blockUI.stop();
				ngNotify.set('Guardado corectamente', 'success');
			    $scope.getAbsencesStudents();			   
			  }, function errorCallback(response) {				  
				  console.log(response);
				  ngNotify.set('ERROR - Datos no guardados', 'error');
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  }).error(function (data, status, header, config) {
				  console.log(status);
				  ngNotify.set('ERROR - Datos no cargados', 'error');
			  });
	 };
	 
	 $scope.getAbsencesStudents = function(){		
		 $http.get('assistControl/studentsAbsences').success(function (data, status, headers, config) {			 
			    $scope.studentsAbsences = data;
			    $scope.setLabelStudentsList($scope.studentsAbsences);			   
			  }).error(function (data, status, header, config) {
				  console.log(status);
				  ngNotify.set('ERROR - Datos no cargados', 'error');
			  });
	 };
	 
	 $scope.presentsColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#337AB7', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#D9EDF7' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			};
	 
	 $scope.absencesColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#D9534F', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#F2DEDE' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			};
	 
	 	$scope.selAll = function (items){
		 angular.forEach(items, function(item) { item.selected = true; });
//	      list.items = list.items.slice(0, index)
//	                  .concat(items)
//	                  .concat(list.items.slice(index));
//	      return true;
	 	};
	 
	 	$scope.deSelAll = function (items){
			 angular.forEach(items, function(item) { item.selected = false; });
		 	};
	 	
		$scope.invertirSel = function (items){
			 angular.forEach(items, function(item) { item.selected = !item.selected; });
		 	};
		
		$scope.getSeleccionados = function(items){
			var array = [];
			angular.forEach(items, function(item) {				
				if(item.selected){
				item.selected = false;
				array.push(item);
			}
			});
			return array;			
		};
		
		$scope.exchange = function (item, name){
			var entra = 1;
			var sale = 0;
			if(name === "B"){
				entra = 0;
				sale = 1;
			}			
			$scope.models[entra].items.push(item);					
			for(var i = $scope.models[sale].items.length-1; i >= 0; i--) {
			   	if( $scope.models[sale].items[i].oid ===  item.oid) {
			   		$scope.models[sale].items.splice(i, 1);
			   	}
			}		
		};
		
		$scope.setLabelStudentsList = function(students){
			angular.forEach(students, function(item) {  		    	  
		    	  item.label =  $filter('capitalize')(item.name) +" "+ $filter('capitalize')(item.lastName);        
		    });
		}
		
		$scope.setLate = function (items, name){			 
			var array = $scope.getSeleccionados(items);			
			for(var x = 0; x < array.length; x++){
				tmp = array[x];
				tmp.late = !tmp.late;
				if(name === "B"){					
					$scope.exchange(tmp, name);
				}
			}			
		};
		
		$scope.move = function (items, name){
			var array = $scope.getSeleccionados(items);			
			for(var x = array.length-1; x >= 0; x--){
				tmp = array[x];				
				$scope.exchange(tmp, name);
			}			
		};
	 
	    $scope.$watch('models', function() {
	    	$scope.presentes = $scope.models[0].items.length;
	        $scope.ausentes = $scope.models[1].items.length;
	        $scope.total = students.length;
	        $scope.presentsPer = ($scope.presentes * 100)/$scope.total;
	        $scope.absencesPer = ($scope.ausentes * 100)/$scope.total;	        
	        angular.forEach($scope.models[1].items, function(item) { item.late = false; }); 	        
	    }, true);

	    /**
	     * dnd-dragging determines what data gets serialized and send to the receiver
	     * of the drop. While we usually just send a single object, we send the array
	     * of all selected items here.
	     */
	    $scope.getSelectedItemsIncluding = function(list, item) {
	      item.selected = true;
	      return list.items.filter(function(item) { return item.selected; });
	    };

	    /**
	     * We set the list into dragging state, meaning the items that are being
	     * dragged are hidden. We also use the HTML5 API directly to set a custom
	     * image, since otherwise only the one item that the user actually dragged
	     * would be shown as drag image.
	     */
	    $scope.onDragstart = function(list, event) {
	       list.dragging = true;
	       if (event.dataTransfer.setDragImage) {
	         var img = new Image();
	         img.src = 'resources/img/drag.png';
	         event.dataTransfer.setDragImage(img, 0, 0);
	       }
	    };

	    /**
	     * In the dnd-drop callback, we now have to handle the data array that we
	     * sent above. We handle the insertion into the list ourselves. By returning
	     * true, the dnd-list directive won't do the insertion itself.
	     */
	    $scope.onDrop = function(list, items, index) {
	      angular.forEach(items, function(item) { item.selected = false; });
	      list.items = list.items.slice(0, index)
	                  .concat(items)
	                  .concat(list.items.slice(index));
	      return true;
	    };

	    /**
	     * Last but not least, we have to remove the previously dragged items in the
	     * dnd-moved callback.
	     */
	    $scope.onMoved = function(list) {
	      list.items = list.items.filter(function(item) { return !item.selected; });
	    };

	    // Generate the initial model    
	    
	    for (var int = 0; int < students.length; int++) {	
	    	var item = students[int];	    	
	    	item.label =  $filter('capitalize')(item.name) +" "+ $filter('capitalize')(item.lastName);	    	
	    	if(item.calendar != null && item.calendar.length > 0){	    		
	    		if(item.calendar[0].eventRegistrationType === "FALTA" ){
		    		$scope.models[1].items.push(item);
		    	}else{
		    		item.late = true;
		    		$scope.models[0].items.push(item);
		    	}
	    	}else{
	    		item.late = false;
	    		item.calendar = [];
	    		$scope.models[0].items.push(item);
	    	}	    	
	    };
	   	    
	    $scope.setLabelStudentsList($scope.studentsAbsences);
	    // Model to JSON for demo purpose
	    $scope.$watch('models', function(model) {
	        $scope.modelAsJson = angular.toJson(model, true);
	    }, true);
	    
	  
	
}]);

