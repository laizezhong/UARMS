app.controller('LeasingSchemaController', ['$state','$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster',  'LeasingCommon',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster,LeasingCommon) {
	 	$scope.searcher= {};
	       	LeasingCommon.getDictByGroupId(12).then(function (result) {
	       		$scope.businessTypes = result;
	       		$scope.businessType = LeasingCommon.dict2Object(result);
	       	}, function (err) {
	       		
	       	});
	       	
	
	var baseLeasingSchemas = Restangular.one('master/leasingschema');
	//重置
    var reset = function(){
    	$scope.searcher={};
    }
    //搜索
	var search = function() {
		baseLeasingSchemas.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsLeasing = new NgTableParams({count: 10,sorting: { modifyTime: "desc" }}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };

    var create = function () {
    	$state.go("app.s1.leasing-mgmt",null,null);
    }
    
    var edit = function (id) {
    	$state.go("app.s1.leasing-mgmt",{id : id},null);
    };
    
    var view = function (id) {
    	$state.go("app.s1.leasing-view",{id : id},null);
    };
    
    var logicalDel = function (leasing) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此融资租赁方案吗？", {'size': 'sm'});
		dlg.result.then(function(btn){
			Restangular.one('master/leasingschema', leasing.id).remove().then(function(result) {
				var leasingList = $scope.tableParamsLeasing.data;
				var index = _.findIndex(leasingList, ['id', leasing.id]);
				leasingList[index] = result;
    			toaster.pop('success', '', '删除融资租赁方案数据成功！');
    			search();
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
    $scope.reset = reset;
	$scope.logicalDel = logicalDel;
	search();
}]);
 
