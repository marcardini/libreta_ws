//var app = angular.module("app", []);
var app = angular.module('app', ['ui.bootstrap', 'smart-table'])

.filter('capitalize', function() {
    return function(input, all) {
      var reg = (all) ? /([^\W_]+[^\s-]*) */g : /([^\W_]+[^\s-]*)/;
      return (!!input) ? input.replace(reg, function(txt) {
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
      }):'';
    };
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

app.directive('dropzone', function() {
    return {
        restrict: 'C',
        link: function(scope, element, attrs) {

            var config = {
                url: 'api/upload',
                maxFilesize: 100,
                paramName: "uploadfile",
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
                dropzone.processQueue();
            };

            scope.resetDropzone = function() {
                dropzone.removeAllFiles();
            }
        }
    }
});
