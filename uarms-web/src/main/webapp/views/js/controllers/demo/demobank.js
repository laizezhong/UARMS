app.controller('BankController', ['$state', '$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 'store',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster, store) {
	
	$scope.bankTypes = {"1":"总行", "2":"分行", "3":"支行"};
	
	var baseBanks = Restangular.one('master/demobanks');
	
	var search = function() {	
		baseBanks.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsBank = new NgTableParams({count: 10, sorting: { bankCode: "asc" }}, { counts: [10, 15, 20], dataset: result});
			//var parameters = $scope.tableParamsBank.parameters();
			
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };

    var create = function () {
    	$state.go("app.demo.demo1-mgmt",null,null);
    }
    
    //var edit = function (id) {
    var edit = function (bank) {
    	//$state.go("app.s1.bank-mgmt",{data:angular.toJson(bank)},null);
    	//$state.go("app.demo.demo1-mgmt",{id:id},null);
    	store.set("currentBank",bank);
    	$state.go("app.demo.demo1-mgmt",{id:bank.id},null);
    };
    
    
    var logicalDel = function (id) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此银行吗？", {'size': 'sm'});
		dlg.result.then(function(btn){
			Restangular.one('master/demobanks', id).remove().then(function(result) {
				var bankList = $scope.tableParamsBank.data;
				var index = _.findIndex(bankList, ['id', id]);
				//bankList[index] = result;
				bankList.splice(index,1);
    			toaster.pop('success', '', '删除银行数据成功！');
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
	$scope.logicalDel = logicalDel;
	
	search();
	
}]);

