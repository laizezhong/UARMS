'use strict';

angular
   .module('app')

   .directive('onFinishRenderFilters', function ($timeout) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attr) {
	            if (scope.$last === true) {
	                $timeout(function() {
	                    scope.$emit('ngRepeatFinished');
	                });
	            }
	        }
	    };
	})
   
	.directive( 'elemReady', function( $parse ) {
		return {
	        restrict: 'A',
	        link: function( $scope, elem, attrs ) {
	            elem.ready(function(){

	                if(!$scope.$$phase) {
	                    $scope.$apply(function(){
	                        var func = $parse(attrs.elemReady);
	                        func($scope);
	                    })
	                }
	                else {
	                    var func = $parse(attrs.elemReady);
	                    func($scope);
	                }

	            })
	        }
	    }
	})
	
	.directive('modelRender', function () {
        return {
            require: 'ngModel',
            link: function (scope, iElm, iAttrs, ngModelCtrl) {
                iElm.on('mouseenter', function () {
                    //尝试注释
                    iElm.val(1);
                  
                    //尝试注释
                    ngModelCtrl.$setViewValue(11);
                    
                    //尝试注释
                    ngModelCtrl.$render();
                    
                    ngModelCtrl.$setViewValue(111);
                    
                    console.log(ngModelCtrl);
                }) 
            }
        }
    })
	
   .directive('dictSelect2', ['LeasingCommon', function(LeasingCommon) {
        return {
            restrict: 'EA',
            replace: true,
            transclude: true,
            //template: '<ui-select theme="bootstrap" ng-model="ss"><ui-select-match >{{$select.selected.name}}</ui-select-match><ui-select-choices  repeat="item.id as item in dicts | filter: $select.search"><div ng-bind-html="item.name | highlight: $select.search"></div></ui-select-choices></ui-select>',
            template: ' <div class="input-group"> ' +	 
						//'  <ui-select ng-model="dict.sel" theme="bootstrap"> ' +
            			'  <ui-select ng-transclude theme="bootstrap"> ' +
				        '  <ui-select-match placeholder="">{{$select.selected.name}}</ui-select-match> ' +
				        '   <ui-select-choices  repeat="item.id as item in items | filter: $select.search"> ' +
				        '    <div ng-bind-html="item.name | highlight: $select.search"></div> ' +
				        '   </ui-select-choices> ' +
				       	'	</ui-select> ' +
					  	' <span class="input-group-btn"> ' +
				        '     <button ng-click="dict.sel=null;" class="btn btn-default"> ' +
				        '       <span class="glyphicon glyphicon-trash"></span> ' +
				        '     </button> ' +
				      	' </span> ' +
					 	' </div> ',
            
            scope: {dictId:'='},
/*
            controller: function($scope, $element, $attrs, $transclude ) { 
            	LeasingCommon.getDictByGroupId($attrs.dictId).then(function (result) {
            		$scope.items = result;
            	}, function (err) {
            		
            	});
            },
            link: function(scope, element, attributes) {
               
            }*/
        };
    }])

    .directive('dictSelect', ['LeasingCommon', function(LeasingCommon) {
        return {
            restrict: 'EA',
            template: ' <select class="form-control" ng-transclude ' +
			          ' 	ng-options="opt.id as opt.name for opt in items" > ' +
					  '	<option value="">-- 请选择 --</option> ' +
					  '	</select>',
            
            scope: {},
            replace: true,
            transclude: true,
            controller: function($scope, $element, $attrs, $transclude ) { 
            	LeasingCommon.getDictByGroupId($attrs.dictSelect).then(function (result) {
            		//result.unshift({id:"",name:""});
            		$scope.items = result;
            	}, function (err) {
            		
            	});
            },
            link: function(scope, element, attributes) {
               
            }
        };
    }])   
    
     .directive('myA', ['$interval', function($interval) {
        return {
            restrict: 'A',
            scope: {},
            template: '<span>{{time|date:"yyyy-MM-dd HH:mm:ss"}}</span>',
            link: function(scope, element, attrs) {
          	
            	 $interval(function(){
            		 attrs.$set('showValue', new Date().getTime());
            	 },2000);
            	 
             	attrs.$observe('showValue', function (newVal){
            		scope.time=newVal;
            	});
            	 
            }
        };
    }])   
    
    .directive('myB', [ function() {
        return {
            restrict: 'AE',
            template: '<span>{{time|date:"yyyy-MM-dd HH:mm:ss"}}</span>',
            scope: {},

            link: function(scope, element, attrs) {
            	attrs.$observe('showValue', function (newVal){
            		scope.time=newVal;
            	});
            }
        };
    }])
    
    //;

