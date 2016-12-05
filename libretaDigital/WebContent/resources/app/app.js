//var app = angular.module("app", []);
var app = angular.module('app', ['ui.bootstrap', 'smart-table', 'dndLists', 'percentCircle-directive', 'ngAnimate', 'ngTouch', 'mwl.calendar', 'ngNotify', 'blockUI' ]);

app.filter('capitalize', function() {
	return function(input, scope) {
		if (input != null) {
			input = input.toLowerCase().split(' ');

			for (var i = 0; i < input.length; i++) {
				// You do not need to check if i is larger than input length, as
				// your for does that for you
				// Assign it back to the array
				input[i] = input[i].charAt(0).toUpperCase()
						+ input[i].substring(1);
			}
			// Directly return the joined string
			return input.join(' ');
		} else {
			return "";
		}
	}
});

app.directive('validNumber', function() {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if (!ngModelCtrl) {
        return;
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
          var val = '';
        }

        var clean = val.replace(/[^-0-9\.]/g, '');
        var negativeCheck = clean.split('-');
        var decimalCheck = clean.split('.');
        if (!angular.isUndefined(negativeCheck[1])) {
          negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
          clean = negativeCheck[0] + '-' + negativeCheck[1];
          if (negativeCheck[0].length > 0) {
            clean = negativeCheck[0];
          }

        }

        if (!angular.isUndefined(decimalCheck[1])) {
          decimalCheck[1] = decimalCheck[1].slice(0, 2);
          clean = decimalCheck[0] + '.' + decimalCheck[1];
        }

        if (val !== clean) {
          ngModelCtrl.$setViewValue(clean);
          ngModelCtrl.$render();
        }
        return clean;
      });

      element.bind('keypress', function(event) {
        if (event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
});

app.directive('dropzone', function($parse) {
    return {
        restrict: 'C',
        link: function(scope, element, attrs) {
        	
        	element.val("value=" + $parse(attrs.selectedType)(scope));
        	
            var config = {
                url: 'fileUpload/upload?type=',
                maxFilesize: 100,
                paramName: "uploadFile",
                maxThumbnailFilesize: 10,
                parallelUploads: 1,
                autoProcessQueue: false
            };
                                    
            var eventHandlers = {
                'addedfile': function(file) {                	
                    scope.file = file;                     
                    if (this.files[1]!=null) {
                        this.removeFile(this.files[0]);
                    }
                    scope.$apply(function() {
                        scope.fileAdded = true;
                    });
                },

                'success': function (file, response) {
                }

            };

            dropzone = new Dropzone(element[0], config);
            
                    
            angular.forEach(eventHandlers, function(handler, event) {
                dropzone.on(event, handler);                
            });
            
            

            scope.processDropzone = function() {            	
            	dropzone.options.url = "fileUpload/upload?type=" + scope.selectedType;            	
                dropzone.processQueue();
            };

            scope.resetDropzone = function() {            
                dropzone.removeAllFiles();
            }
        }
    }
});

