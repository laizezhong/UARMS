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
	
    search();
    
    $scope.search = search;
    
}]);