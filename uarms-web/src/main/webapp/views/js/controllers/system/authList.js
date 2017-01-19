app.controller('AuthController', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
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
	
    search();
    
    $scope.search = search;
    $scope.addauth = addauth;
    
}]);