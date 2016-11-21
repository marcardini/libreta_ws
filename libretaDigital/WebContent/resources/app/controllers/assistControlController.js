app.controller('assistControlCtrl', ['$scope', '$filter', '$http', function ($scope, $filter, $http) {
	
	$scope.date = new Date();
	$scope.students = [];
	$scope.studentsAbsences = [];
	angular.copy(students, $scope.students);
	angular.copy(studentsAbsences, $scope.studentsAbsences);
	
	console.log($scope.studentsAbsences);
	console.log(groups);
	
	/* LISTAS */
	
	 $scope.models = [
	      {listName: "A", items: $scope.students, dragging: false},
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
			 var aux = {idStudent:"", late:false};
			 if(item.late){
				 aux.idStudent = item.oid;
				 aux.late = item.late;
				 absences.push(aux);
			 } 		 
		 });
		 angular.forEach($scope.models[1].items, function(item){ 
			 var aux = {idStudent:"", late:false};
			 aux.idStudent = item.oid;			 	
			 absences.push(aux);			 	 
		 });
//		 console.log(absences)
		 $http({
			  method: 'POST',
			  url: 'assistControl/saveAbsences',
			  data: absences
			}).success(function successCallback(response) {			    
			    $scope.getAbsencesStudents();			   
			  }, function errorCallback(response) {
				  console.log(response);
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
			  });
	 };
	 
	 $scope.getAbsencesStudents = function(){
		 $http.get('assistControl/studentsAbsences').success(function (data, status, headers, config) {			 
			    $scope.studentsAbsences = data;
			    $scope.setLabelStudentsList($scope.studentsAbsences);
			  }).error(function (data, status, header, config) {
				  console.log(status);			   
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
				tmp.late = true;
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
	        console.log($scope.total);
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
	    angular.forEach($scope.models[0].items, function(item) {  	
	    	  item.late = false;
	    	  item.label =  $filter('capitalize')(item.name) +" "+ $filter('capitalize')(item.lastName);       
	    });
	    
	    $scope.setLabelStudentsList($scope.studentsAbsences);
	    // Model to JSON for demo purpose
	    $scope.$watch('models', function(model) {
	        $scope.modelAsJson = angular.toJson(model, true);
	    }, true);
	
}]);

