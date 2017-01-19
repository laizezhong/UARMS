app.controller('EditBankController',function($state, $scope, $stateParams, Restangular, toaster, store ){

	
	$scope.submitTried = false;
	
	var id = $stateParams.id;
	$scope.bank = null;
	
	var currentBank = store.get("currentBank");
	console.log(currentBank);
	
	$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
	//$scope.bankTypes = {1:"总行", 2:"分行", 3:"支行"};

	var findById = function(id) {
		Restangular.one('master/demobanks', id).get().then(function(result) {
			$scope.bank = result;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	
	if (id > 0) { 
		$scope.title = "修改银行";
		findById(id);
		//$scope.bank = currentBank;
	} else {
		$scope.title = "新建银行";
	}
	
	var update = function() {
		$scope.bank.put().then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '更新银行数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
	}
	
	var create = function()  {
		Restangular.all('master/demobanks').post($scope.bank).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '新建银行数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};
	
	var back = function(){
		store.remove("currentBank");
		$state.go("app.demo.demo1-list",null,null);
	};
	
	$scope.back = back;
	
	$scope.save = function(isNotValid){
		$scope.submitTried = true;
		if (isNotValid) {
			toaster.pop('warning', '', '页面输入项有非法数据存在，请按提示修改后再提交！');
		} else {
		
			if (id > 0) { 
				update();
			} else {
				create();
			}
		}
	};
	
	
});
