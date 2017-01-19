app.controller('DictionaryController', ['$state','$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 'LeasingCommon',
	function($state,$scope, Restangular, NgTableParams, dialogs, toaster,LeasingCommon) {
	
	$scope.userNames = LeasingCommon.getUserNames();    
	                                 	
	var baseSysElementGroup = Restangular.one('master/syselementgroup');
	
	var search = function() {
		baseSysElementGroup.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsSysElementGroup = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
    var create1=function(){
    	$state.go("app.s1.dictionary-create",null,null);
    }
    var create = function () {
    	$state.go("app.s1.dictionary-mgmt",null,null);
    }
    
    var edit = function (id) {
    	$state.go("app.s1.dictionary-mgmt",{"id": id},null);
    }
	$scope.show=false;
    var view = function (id) {
    	$scope.show=true;
    	$state.go("app.s1.dictionary-view",{"id": id},null);
    };
    
    var logicalDel = function (syselementgroup) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此数据字典吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			Restangular.one('master/syselementgroup', syselementgroup.id).remove().then(function(result) {
				var syselementgroupList = $scope.tableParamsSysElementGroup.data;
				var index = _.findIndex(syselementgroupList, ['id', syselementgroup.id]);
				syselementgroupList.splice(index,1);
    			toaster.pop('success', '', '删除数据字典成功！');
    			//search();
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
	$scope.view=view;
	search();
}]);
//app.controller('editCodeRuleController',function($scope,$modalInstance,data){
//	$scope.types = [{"id":"1", "name":"融资租赁合同"},{"id":"2", "name":"产品卖买合同"}];
//	//$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
//	$scope.resetType = {"1":"是", "2":"否"};
//	$scope.title = "增加编码";
//	$scope.eidtMode=false;
//	if (data.codeRule) {
//		$scope.title = "更新编码";
//		$scope.eidtMode=true;
//		$scope.codeRule = data.codeRule;
//	}
//	$scope.cancel = function(){
//		$modalInstance.dismiss('Cancelled');
//	};
//	$scope.save = function(){
//		$modalInstance.close($scope.codeRule);
//	};
//
//});
//app.controller('viewCodeRuleController',function($scope,$modalInstance,data){
//	$scope.types = [{"id":"1", "name":"融资租赁合同"},{"id":"2", "name":"产品卖买合同"}];
//	$scope.resetType = {"1":"是", "2":"否"};
//	$scope.show=false;
//	$scope.title = "增加编码";
//	if (data.codeRule) {
//		$scope.title = "查看编码";
//		$scope.show=true;
//		$scope.codeRule = data.codeRule;
//	}
//	$scope.cancel = function(){
//		$modalInstance.dismiss('Cancelled');
//	};
//	$scope.save = function(){
//		$modalInstance.close($scope.codeRule);
//	};
//});