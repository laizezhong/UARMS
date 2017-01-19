app.controller('BankController2', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 'LeasingCommon',
	function($scope, Restangular, NgTableParams, dialogs, toaster, LeasingCommon) {
	
	$scope.bankTypes = {"1":"总行", "2":"分行", "3":"支行"};
	
	$scope.userNames = LeasingCommon.getUserNames();
	
	var baseBanks = Restangular.one('master/demobanks');
	

	
	var search = function() {	
		baseBanks.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsBank = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
    var create = function () {
		var dlg = dialogs.create('views/tpl/demo/editDemoBank2.html','EditBankController2',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(bank){
			Restangular.all('master/demobanks').post(bank).then(function(result) {
	    		if (result) {
	    			toaster.pop('success', '', '新建银行数据成功！');
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };

    var edit = function (bank) {
		var dlg = dialogs.create('views/tpl/demo/editDemoBank2.html','EditBankController2',{"bank":bank},{size:'md','backdrop':'static'});
		dlg.result.then(function(updBank){
			Restangular.one('master/demobanks', updBank.id).customPUT(updBank).then(function(result) {
	    		if (result) {
					var bankList = $scope.tableParamsBank.data;
					var index = _.findIndex(bankList, ['id', updBank.id]);
					bankList[index] = result;
	    			toaster.pop('success', '', '更新银行数据成功！');
	    			//search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    
    
    var logicalDel = function (id) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此银行吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			Restangular.one('master/demobanks', id).remove().then(function(result) {
				var bankList = $scope.tableParamsBank.data;
				var index = _.findIndex(bankList, ['id', id]);
				bankList.splice(index,1);
    			toaster.pop('success', '', '删除银行数据成功！');
    			//search();
			}, function(response) {
				console.log("Error with status code", response.status);
			});
		},function(btn){
			console.log("Cancelled");
		});
    };
//    var view = function (bank) {
//		var dlg = dialogs.create('views/tpl/master/viewBank.html','viewBankController',{"bank":bank},{size:'md','backdrop':'static'});
//		dlg.result.then(function(updBank){
//			Restangular.one('master/demobanks', updBank.id).customPUT(updBank).then(function(result) {
//	    		if (result) {
//					var bankList = $scope.tableParamsBank.data;
//					var index = _.findIndex(bankList, ['id', updBank.id]);
//					bankList[index] = result;
//	    			toaster.pop('success', '', '查看数据！');
//	    			search();
//	    		}	
//	    		
//			}, function(errResponse) {
//				console.log("Error with status code", errResponse.status);
//			}); 
//
//		},function(){
//			console.log("Cancelled");
//		});
//    };

	$scope.search = search;
	$scope.create = create;
	$scope.edit = edit;
	$scope.logicalDel = logicalDel;
//	$scope.view = view;

	search();
	
	$scope.exportExcel = function () {
		LeasingCommon.exportExcel('sampleTable');
	};
	
}]);


app.controller('EditBankController2',function($scope,$modalInstance,data){

	$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
	$scope.title = "增加银行";
	$scope.eidtMode=false;
	if (data.bank) {
		
		$scope.title = "更新银行";
		$scope.eidtMode=true;
		$scope.bank = data.bank;
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	
	$scope.save = function(){
		$modalInstance.close($scope.bank);
	};

});

//app.controller('viewBankController',function($scope,$modalInstance,data){
//	$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
//	$scope.title = "增加银行";
//	$scope.eidtMode=false;
//	if (data.bank) {
//		$scope.title = "查看银行";
//		$scope.eidt=true;
//		$scope.bank = data.bank;
//	}
//	$scope.cancel = function(){
//		$modalInstance.dismiss('Cancelled');
//	};
//	$scope.save = function(){
//		$modalInstance.close($scope.bank);
//	};
//
//});

