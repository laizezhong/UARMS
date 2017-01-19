app.controller('BankController', ['$stateParams','$scope', 'Restangular' ,'ngTableParams', 'dialogs',  'LeasingCommon', 'toaster', 
	                                     	function($stateParams,$scope, Restangular, NgTableParams, dialogs, LeasingCommon, toaster) {
	$scope.userNames = LeasingCommon.getUserNames();                         	

	LeasingCommon.getDictByGroupIdInt(4).then(function (result) {

		$scope.bankTypess = LeasingCommon.dict2Object(result);
		
	}, function (err) {
		
	});

	var search = function() {	
		Restangular.one('master/banks').get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsBank = new NgTableParams({count: 10,sorting: { modifyTime: "desc" }}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
    var create = function () {
		var dlg = dialogs.create('views/tpl/master/editBank.html','editBankController',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(bank){
			Restangular.all('master/banks').post(bank).then(function(result) {
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
//    var createdit = function (bankAccount) {
//		var dlg = dialogs.create('views/tpl/master/editBankAccount.html','editBankAccountController',{"bankAccount":bankAccount},{size:'lg',backtrop:'static'});
//		dlg.result.then(function(updBankAccount){
//			Restangular.one('master/bankAccounts', updBankAccount.id).customPUT(updBankAccount).then(function(result) {
//	    		if (result) {
//					var bankAccountList = $scope.tableParamsBankAccount.data;
//					var index = _.findIndex(bankAccountList, ['id', updBankAccount.id]);
//					bankAccountList[index] = result;
//	    			toaster.pop('success', '', '更新银行账户数据成功！');
//	    			search();
//	    		}	
//			}, function(errResponse) {
//				console.log("Error with status code", errResponse.status);
//			}); 
//		},function(){
//			console.log("Cancelled");
//		});
//    };
    var edit = function (bank) {
		var dlg = dialogs.create('views/tpl/master/editBank.html','editBankController',{"bank":bank},{size:'md','backdrop':'static'});
		dlg.result.then(function(updBank){
			Restangular.one('master/banks', updBank.id).customPUT(updBank).then(function(result) {
	    		if (result) {
					var bankList = $scope.tableParamsBank.data;
					var index = _.findIndex(bankList, ['id', updBank.id]);
					bankList[index] = result;
	    			toaster.pop('success', '', '修改银行数据成功！');
	    			//search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    var createbankaccount= function (bankAccount) {
		var dlg = dialogs.create('views/tpl/master/createBankAccount.html','createBankAccountController',{"bankAccount":bankAccount},{size:'md','backdrop':'static'});
		dlg.result.then(function(updBankAccount){
			updBankAccount.bankId = bankAccount.id;
			delete updBankAccount.bankCode;
			delete updBankAccount.bankName;
			Restangular.all('master/bankAccounts').post(updBankAccount).then(function(result) {
	    		if (result) {
	    			
//					var bankList = $scope.tableParamsBank.data;
//					var index = _.findIndex(bankList, ['id', updBank.id]);
//					bankList[index] = result;
	    			toaster.pop('success', '', '新建银行账户成功！');
//	    			//search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    var logicalDel = function (bank) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此银行吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
	var baseBankAccounts = Restangular.one('master/bankAccounts');
	
	var newRow = {bankname:bank.bankName};

		baseBankAccounts.get(newRow).then(function(result) {
		if (result.length < 1) {
			Restangular.one('master/banks', bank.id).remove().then(function(result) {
				var bankList = $scope.tableParamsBank.data;
				var index = _.findIndex(bankList, ['id', bank.id]);
				bankList.splice(index,1);
    			toaster.pop('success', '', '删除银行数据成功！');
    			//search();
			}, function(response) {
				console.log("Error with status code", response.status);
			});
}else{
    			toaster.pop('warning', '', '此银行有关联的的银行账号，不能删除！');
    		}
		
			});
		},function(btn){
			console.log("Cancelled");
		});
    };
    var view = function (bank) {
		var dlg = dialogs.create('views/tpl/master/viewBank.html','viewBankController',{"bank":bank},{size:'md','backdrop':'static'});
		dlg.result.then(function(updBank){
			Restangular.one('master/banks', updBank.id).customPUT(updBank).then(function(result) {
	    		if (result) {
					var bankList = $scope.tableParamsBank.data;
					var index = _.findIndex(bankList, ['id', updBank.id]);
					bankList[index] = result;
	    			toaster.pop('success', '', '查看数据！');
	    			search();
	    		}	
	    		
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };

	$scope.search = search;
	$scope.create = create;
	$scope.edit = edit;
	$scope.logicalDel = logicalDel;
	$scope.view = view;
	$scope.createbankaccount = createbankaccount;
	

	search();
}]);


app.controller('editBankController',function($scope,$modalInstance,data,LeasingCommon){
	$scope.bank={};
//	$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];

	LeasingCommon.getDictByGroupIdInt(4).then(function (result) {

		$scope.bankTypes = result;
	}, function (err) {
		
	});
	$scope.title = "新建";
	$scope.eidtMode=false;
	if (data.bank) {
		
		$scope.title = "修改";
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
app.controller('viewBankController',function($scope,$modalInstance,data,LeasingCommon){

	
	LeasingCommon.getDictByGroupIdInt(4).then(function (result) {
		$scope.bankTypesss = result;
	}, function (err) {
		
	})
	$scope.title = "增加银行";
	$scope.eidtMode=false;
	if (data.bank) {
		$scope.title = "查看";
		$scope.eidt=true;
		$scope.bank = data.bank;
	}
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	$scope.save = function(){
		$modalInstance.close($scope.bank);
	};

});


app.controller('createBankAccountController',function($scope,Restangular,$modalInstance,data,LeasingCommon){


//	$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
	LeasingCommon.getDictByGroupId(25).then(function (result) {

		$scope.accountTypes = result;
	}, function (err) {
		
	});
	//$scope.accountTypes = [{"id":"1", "name":"基本账号"},{"id":"2", "name":"一般账号"},{"id":"3", "name":"临时账号"},{"id":"4", "name":"专用账号"}];
//	$scope.accountType = {"1":"基本账号", "2":"一般账号", "3":"临时账号","4":"专用账号"};
//	$scope.company =  [{"id":"1", "name":"仁聚"},{"id":"2", "name":"普华"},{"id":"3", "name":"汉德"},{"id":"4", "name":"用友"}];
	$scope.title = "";
	$scope.company = null;
	$scope.show=false;
	if (data.bankAccount) {
		$scope.title = "增加账号";
		$scope.show=true;
		$scope.bankAccount = {};
		$scope.bankAccount.bankCode = data.bankAccount.bankCode;
		$scope.bankAccount.bankName = data.bankAccount.bankName;
	}
	var findAllCompanies = function() {
		Restangular.one('master/companies').get().then(function(result) {
			$scope.company = result;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	$scope.save = function(){
		$modalInstance.close($scope.bankAccount);
	};
	findAllCompanies();
});

