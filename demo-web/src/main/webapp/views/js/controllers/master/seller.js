app.controller('SellerController', ['$state', '$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 'LeasingCommon',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster, LeasingCommon) {

	//$scope.sellerTypes = {"1":"有限责任公司", "2":"股份制公司", "3":"集团公司","4":"一人制公司"};
	
	//数据字典 出卖方类型
	LeasingCommon.getDictByGroupId(5).then(function (result) {
		$scope.sellerType = result;
		$scope.sellerTypes = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//数据字典 合作状态
	LeasingCommon.getDictByGroupId(8).then(function (result) {
		$scope.statu = result;
		$scope.status = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//$scope.status = {"0":"合作中", "1":"已终止"};
	
	$scope.nowDate = new Date().getTime();
	
	$scope.searcher = {};
	
	var search = function() {	
		
		//$scope.sellerType = [{"id":"1", "name":"有限责任公司"},{"id":"2", "name":"股份制公司"},{"id":"3", "name":"集团公司"},{"id":"4", "name":"一人制公司"}];	
		//$scope.statu = [{"id":"0", "name":"合作中"},{"id":"1", "name":"已终止"}];	
		
		Restangular.one('master/sellers').get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsSeller = new NgTableParams({count: 10,sorting: { modifyTime: "desc" }}, { counts: [10, 15, 20], dataset: result});
			
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
    
    var create = function () {
    	$state.go("app.s1.seller-mgmt",null,null);
    }
    
    var edit = function (id) {
    	$state.go("app.s1.seller-mgmt",{id:id},null);
    };
    
    var view = function (id) {
    	$state.go("app.s1.seller-view",{id:id},null);
    };
    
    var logicalDel = function (id) {
		var dlg = dialogs.confirm("终止确认","您确认要终止与此出卖方的合作吗？", {'size': 'sm'});
		dlg.result.then(function(btn){
			//-----
			/*var newSearcher = {sellerId:id};
			Restangular.one('master/leasingschema').get(newSearcher).then(function(result) {
				if (result.length < 1) {*/
			//-----
					Restangular.one('master/sellers', id).remove().then(function(result) {
						var sellerList = $scope.tableParamsSeller.data;
						var index = _.findIndex(sellerList, ['id', id]);
						//bankList[index] = result;
						sellerList[index] = result;
		    			toaster.pop('success', '', '终止成功,并已删除相关租赁方案！');
					}, function(response) {
						console.log("Error with status code", response.status);
						toaster.pop('error', '', '审核处理过程中出现数据库或网络异常！');
					});
			//----
				/*}else{
			    			toaster.pop('warning', '', '此出卖方有关联的融资租赁方案，不能删除！');
			    	}
			});*/
			//----
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


