app.controller('BankAccountController', ['$stateParams','$scope', 'Restangular' ,'ngTableParams', 'dialogs',  'LeasingCommon', 'toaster', 
	function($stateParams,$scope, Restangular, NgTableParams, dialogs, LeasingCommon, toaster) {
	
	$scope.userNames = LeasingCommon.getUserNames();
	
	$scope.accountTypes = {"1":"基本账号", "2":"一般账号", "3":"临时账号","4":"专用账号"};
	
	//获取方案ID
	var id = $stateParams.id;
	$scope.company = null;
	$scope.companyNames = null;
	
	var baseBankAccounts = Restangular.one('master/bankAccounts');
	
	/****银行账户首页查询账户信息****/
	var search = function() {	
		$scope.accountType = [{"id":"1", "name":"基本账号"},{"id":"2", "name":"一般账号"},{"id":"3", "name":"临时账号"},{"id":"4", "name":"专用账号"}];
		baseBankAccounts.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsBankAccount = new NgTableParams({count: 10,sorting: { modifyTime: "desc" }}, { counts: [10, 15, 20], dataset: result});
			searchcompany();
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});
    };
    
    /****银行账户首页查询公司信息****/
    var searchcompany = function() {
		baseBankAccounts.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    		//	toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});
    };
    /****新建银行账户****/
    var create = function () {
		var dlg = dialogs.create('views/tpl/master/editBankAccount.html','editBankAccountController',{},{'size': 'sm','backdrop':'static'});
		dlg.result.then(function(bankAccount){
			Restangular.all('master/bankAccounts').post(bankAccount).then(function(result) {
	    		if (result) {
	    			toaster.pop('success', '', '新建银行账户数据成功！');
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    /****更新银行账户****/
    var edit = function (bankAccount) {
		var dlg = dialogs.create('views/tpl/master/editBankAccount.html','editBankAccountController',{"bankAccount":bankAccount},{'size': 'md','backdrop':'static'});
		dlg.result.then(function(updBankAccount){
			Restangular.one('master/bankAccounts', updBankAccount.id).customPUT(updBankAccount).then(function(result) {
	    		if (result) {
	    			search();
					var bankAccountList = $scope.tableParamsBankAccount.data;
					var index = _.findIndex(bankAccountList, ['id', updBankAccount.id]);
					bankAccountList[index] = result;
	    			toaster.pop('success', '', '修改银行账户数据成功！');
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    /****查看银行账户****/
    var view = function (bankAccount) {
		var dlg = dialogs.create('views/tpl/master/viewBankAccount.html','editBankAccountController',{"bankAccount":bankAccount},{size:'md'});
		dlg.result.then(function(updBankAccount){
			Restangular.one('master/bankAccounts', updBankAccount.id).customPUT(updBankAccount).then(function(result) {
	    		if (result) {
					var bankAccountList = $scope.tableParamsBankAccount.data;
					var index = _.findIndex(bankAccountList, ['id', updBankAccount.id]);
					bankAccountList[index] = result;
	    			toaster.pop('success', '', '修改银行账户数据成功！');
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
		},function(){
			console.log("Cancelled");
		});
    };
    /****删除银行账户****/
    var logicalDel = function (bankAccount) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此银行账户吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			var basePurchaseContracts = Restangular.one('purchase/contract');
			var bankAccountId = {bankAccountId:bankAccount.id};
			basePurchaseContracts.get(bankAccountId).then(function(result) {
			if (result.length < 1) {
			    Restangular.one('master/bankAccounts', bankAccount.id).remove().then(function(result) {
				var bankAccountList = $scope.tableParamsBankAccount.data;
				var index = _.findIndex(bankAccountList, ['id', bankAccount.id]);
				bankAccountList.splice(index,1);
    			toaster.pop('success', '', '删除银行账户成功！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
				}else{
	    			toaster.pop('warning', '', '此银行账户含有未终止的合同，不能删除！');
	    		}
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

app.controller('editBankAccountController',function($scope,Restangular ,$modalInstance,data){
	$scope.accountTypes = [{"id":"1", "name":"基本账号"},{"id":"2", "name":"一般账号"},{"id":"3", "name":"临时账号"},{"id":"4", "name":"专用账号"}];
	$scope.accountType = {"1":"基本账号", "2":"一般账号", "3":"临时账号","4":"专用账号"};
	
	$scope.title = "增加银行账户";
	$scope.companies = null;
	if (data.bankAccount) {
		$scope.title = "银行账户";
		$scope.bankAccount = data.bankAccount;
	}
	/****修改页面上下拉列表框查询公司名称****/
	var findAllCompanies = function() {
		Restangular.one('master/companies').get().then(function(result) {
			$scope.companies = result;
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
