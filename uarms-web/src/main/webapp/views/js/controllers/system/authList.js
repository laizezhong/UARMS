﻿app.controller('AuthController', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
	function($scope, Restangular, NgTableParams, dialogs, toaster) {
	
	var baseAuths = Restangular.one('system/auths');

	$scope.authGroup = "";
	$scope.authName = "";
	
	var search = function () {
	
		baseAuths.get({authGroup:$scope.authGroup, authName:$scope.authName}).then(function(auths) {
			//$scope.userList = _.castArray(users);
    		if (auths.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsAuthList = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: auths});
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	
	};
	
	var addauth = function () {
		var dlg = dialogs.create('views/tpl/system/editAuth.html','EditAuthController',{},{size:'md'});
		dlg.result.then(function(permission){
			Restangular.all('system/auths').post(permission).then(function(result) {
	    		if (result) {
	    			toaster.pop('success', '', '新建权限成功！');
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
	
    search();
    
    $scope.search = search;
    $scope.addauth = addauth;
    
}]);


app.controller('EditAuthController',function($scope,Restangular,$modalInstance,data,LeasingCommon,toaster ){

	$scope.title = "增加权限";
	$scope.permission = {};
	$scope.show=false;
	
	if (data.permission) {
		$scope.title = "修改权限";
		$scope.show=true;
		$scope.permission = data.permission;
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	};
	
	$scope.save = function(isNotValid){
		if (isNotValid) {
			toaster.pop('warning', '', '页面输入项有非法数据存在，请按提示修改后再提交！');
		} else {
			$modalInstance.close($scope.permission);
		}
	};
});

