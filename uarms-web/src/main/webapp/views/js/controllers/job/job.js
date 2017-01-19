app.controller('JobController', ['$state', '$scope', 'toaster','Restangular' , 'dialogs', 'store', '$filter','LeasingCommon',
	function($state, $scope,toaster, Restangular, dialogs, store, $filter, LeasingCommon) {

    var setDate = function () {
    	var dlg = dialogs.create('views/tpl/job/setDate.html','SetDateController',{},{size:'sm'});
        dlg.result.then(function(date){
    		if(null != date){
    			$scope.date = $filter('date')(date, 'yyyy-MM-dd');
    		}
    		Restangular.one('job/overdue',$scope.date).get().then(function(result) {
    			if( result == 1 ){
    				toaster.pop('success', '', '跑批成功！');
    			}
    			if( result == 2 ){
    				toaster.pop('error', '', '跑批失败, 已撤回 ！');
    			}
    		}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
        }); 
    };
    
	$scope.setDate = setDate;
	
}]);

app.controller('SetDateController',function($scope,$modalInstance,data){
	
	$scope.date = null;
	
	$scope.dateOptions = {
			startingDay : 1,
			class : 'datepicker'
	};
	$scope.format = "yyyy-MM-dd";
	$scope.openDatepicker = function($event, i) {
		$event.preventDefault();
		$event.stopPropagation();
		if (i == 0){
			$scope.tdate = true;
		};
	};
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	
	$scope.save = function(){
		$modalInstance.close($scope.date);
	};
});

