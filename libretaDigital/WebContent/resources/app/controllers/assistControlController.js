app.controller('assistControlCtrl', ['$scope', function ($scope) {
	
	
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
	 }
	 
	 $scope.title = function (name){
		 if(name === "A"){			 
			 return "Presentes";
		 }else{
			 return "Faltas";
		 }
	 }
	 
	 $scope.cantidad = function(name){		
		 if(name === "A"){			 
			 return $scope.presentes;
		 }else{
			 return $scope.ausentes;
		 }
	 }
	 
	 $scope.presentesColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#337AB7', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#D9EDF7' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			}
	 
	 $scope.ausentesColor ={
//			    center    : 'lightGreen', // the color in the center of the circle. Default: #F5FBFC
			    highlight : '#D9534F', // the highlighted section of the circle, representing the percentage number. Default: #2BCBED
			    remaining : '#F2DEDE' // the color of the circle before highlighting occurs, representing the amount left until the percent equals 100. Default: #C8E0E8
			}
	 
	 	$scope.selAll = function (items){
		 angular.forEach(items, function(item) { item.selected = true; });
//	      list.items = list.items.slice(0, index)
//	                  .concat(items)
//	                  .concat(list.items.slice(index));
//	      return true;
	 	}
	 
	 	$scope.deSelAll = function (items){
			 angular.forEach(items, function(item) { item.selected = false; });
		 	}
	 	
		$scope.invertirSel = function (items){
			 angular.forEach(items, function(item) { item.selected = !item.selected; });
		 	}
	 
	    $scope.$watch('models', function() {
	    	$scope.presentes = $scope.models[0].items.length;
	        $scope.ausentes = $scope.models[1].items.length;
	        $scope.total = 16;
	        $scope.presentesPer = ($scope.presentes * 100)/$scope.total;
	        $scope.ausentesPer = ($scope.ausentes * 100)/$scope.total;
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
	         img.src = 'framework/vendor/ic_content_copy_black_24dp_2x.png';
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
	    }

	    /**
	     * Last but not least, we have to remove the previously dragged items in the
	     * dnd-moved callback.
	     */
	    $scope.onMoved = function(list) {
	      list.items = list.items.filter(function(item) { return !item.selected; });
	    };

	    // Generate the initial model
	    angular.forEach($scope.models, function(list) {
	      for (var i = 1; i <= 8; ++i) {
	          list.items.push({label: "Item " + list.listName + i});
	      }
	    });

	    // Model to JSON for demo purpose
	    $scope.$watch('models', function(model) {
	        $scope.modelAsJson = angular.toJson(model, true);
	    }, true);
	
}]);

