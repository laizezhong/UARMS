app.controller('InterestController', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'LeasingCommon','toaster', 
	function($scope, Restangular, NgTableParams, dialogs, LeasingCommon,toaster) {
	
	$scope.userNames = LeasingCommon.getUserNames();
	
	var baseInterests = Restangular.one('master/interests');
	
	var search = function() {	
		baseInterests.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsInterest = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };

    var create = function () {
		var dlg = dialogs.create('views/tpl/master/editInterest.html','editInterestController',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(interest){
			Restangular.all('master/interests').post(interest).then(function(result) {
	    		if (result) {
	    			toaster.pop('success', '', '新建利率数据成功！');
	    			search();
	    		}else{
	    			toaster.pop('warning', '', 'can not input sameTime record！');
	    		}
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
		},function(){
			console.log("Cancelled");
		});
    };

    var edit = function (interest) {
		var dlg = dialogs.create('views/tpl/master/editInterest.html','editInterestController',{"interest":interest},{size:'md','backdrop':'static'});
		dlg.result.then(function(updInterest){
			Restangular.one('master/interests', updInterest.id).customPUT(updInterest).then(function(result) {
	    		if (result) {
					var interestList = $scope.tableParamsInterest.data;
					var index = _.findIndex(interestList, ['id', updInterest.id]);
					interestList[index] = result;
	    			toaster.pop('success', '', '修改利率数据成功！');
	    			//search();
	    		}
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
		},function(){
			console.log("Cancelled");
		});
    };
    
    var view = function (interest) {
		var dlg = dialogs.create('views/tpl/master/viewInterest.html','editInterestController',{"interest":interest},{size:'md'});
		dlg.result.then(function(updInterest){
			Restangular.one('master/interests', updInterest.id).customPUT(updInterest).then(function(result) {
	    		if (result) {
					var interestList = $scope.tableParamsInterest.data;
					var index = _.findIndex(interestList, ['id', updInterest.id]);
					interestList[index] = result;
	    			toaster.pop('success', '', '修改利率数据成功！');
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
		},function(){
			console.log("Cancelled");
		});
    };
    
    var logicalDel = function (interest) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此利率吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			Restangular.one('master/interests', interest.id).remove().then(function(result) {
				var interestList = $scope.tableParamsInterest.data;
				var index = _.findIndex(interestList, ['id', interest.id]);
				interestList.splice(index,1);
    			toaster.pop('success', '', '删除利率成功！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
		},function(btn){
			console.log("Cancelled");
		});
    };
    
	$scope.search = search;
	$scope.create = create;
	$scope.edit = edit;
	$scope.view = view;
	$scope.logicalDel = logicalDel;
	
	search();
	
}]);

app.controller('editInterestController',function($scope,$modalInstance,data){
	
	$scope.dateOptions = {
		      //formatYear: 'yy',
		      startingDay: 1,
		      class: 'datepicker'
		    };
		    $scope.format = "yyyy-MM-dd";
			
			$scope.openDatepicker = function($event, i) {
				$event.preventDefault();
				$event.stopPropagation();
				if(i==0)
					$scope.interestDate = true;
				else if(i==1)
					$scope.endopened = true;
				else if(i==2)
					$scope.endopened = true;
			},
	$scope.eidtMode=false;
	$scope.title = "新建";
	if (data.interest) {
		$scope.title = "修改";
		$scope.eidtMode=true;
		$scope.interest = data.interest;
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	
	$scope.save = function(){
		$modalInstance.close($scope.interest);
	};

});
