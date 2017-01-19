app.controller('editLeasingSchemaController', ['$state','$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', '$stateParams','LeasingCommon', 
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster,$stateParams,LeasingCommon) {
	
	$scope.title = "增加融资租赁方案";
/*	$scope.largeAreaTypes = [{"id":"1", "name":"华东区"},{"id":"2", "name":"华南区"},{"id":"3", "name":"华北区"}];
	$scope.businessTypes =[{"id":"1", "name":"直租"},{"id":"2", "name":"售后回租"}];
	$scope.itemTypes = [{"id":"1", "name":"车辆"},{"id":"2", "name":"票据"}];
	$scope.leaseCycles = [{"id":"1", "name":"月"}];
	$scope.paymentMethods = [{"id":"1", "name":"期初还款"},{"id":"0", "name":"期末还款"}];
	$scope.interestSettings = [{"id":"1", "name":"固定利率"},{"id":"2", "name":"浮动利率"}];
	$scope.loanRules = [{"id":"1", "name":"先抵押后放款"},{"id":"2", "name":"先放款后抵押"}];
	$scope.depositRules = [{"id":"1", "name":"期末退回"},{"id":"2", "name":"期末冲抵"}];
	$scope.endingRules = [{"id":"1", "name":"留购"},{"id":"2", "name":"续租"},{"id":"3", "name":"退回"}];
	$scope.sponsorRequireds = [{"id":1, "name":"是"},{"id":0, "name":"否"}];
	$scope.calculateRules = [{"id":"1", "name":"等额本金"},{"id":"2", "name":"等额本息"}];*/
/*	$scope.largeAreaTypes = [{"id":"1", "name":"华东区"},{"id":"2", "name":"华南区"},{"id":"3", "name":"华北区"}];*/
   	LeasingCommon.getDictByGroupId(11).then(function (result) {
   		$scope.largeAreaTypes = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.businessTypes =[{"id":"1", "name":"直租"},{"id":"2", "name":"售后回租"}];
   	LeasingCommon.getDictByGroupId(12).then(function (result) {
   		$scope.businessTypes = result;
   		
   	}, function (err) {
   		
   	});
   	
	//$scope.itemTypes = [{"id":"1", "name":"车辆"},{"id":"2", "name":"票据"}];
   	LeasingCommon.getDictByGroupId(13).then(function (result) {
   		$scope.itemTypes = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.leaseCycles = [{"id":"1", "name":"月"}];
 	LeasingCommon.getDictByGroupId(14).then(function (result) {
   		$scope.leaseCycles = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.paymentMethods = [{"id":"1", "name":"期初还款"},{"id":"0", "name":"期末还款"}];
 	LeasingCommon.getDictByGroupId(15).then(function (result) {
   		$scope.paymentMethods = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.interestSettings = [{"id":"1", "name":"固定利率"},{"id":"2", "name":"浮动利率"}];
 	LeasingCommon.getDictByGroupId(19).then(function (result) {
   		$scope.interestSettings = result;
   		
   	}, function (err) {
   		
   	});
//	$scope.loanRules = [{"id":"1", "name":"先抵押后放款"},{"id":"2", "name":"先放款后抵押"}];
 	LeasingCommon.getDictByGroupId(23).then(function (result) {
   		$scope.loanRules = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.depositRules = [{"id":"1", "name":"期末退回"},{"id":"2", "name":"期末冲抵"}];
 	LeasingCommon.getDictByGroupId(20).then(function (result) {
   		$scope.depositRules = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.endingRules = [{"id":"1", "name":"留购"},{"id":"2", "name":"续租"},{"id":"3", "name":"退回"}];
 	LeasingCommon.getDictByGroupId(24).then(function (result) {
   		$scope.endingRules = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.sponsorRequireds = [{"id":1, "name":"是"},{"id":0, "name":"否"}];
 	LeasingCommon.getDictByGroupIdInt(21).then(function (result) {
   		$scope.sponsorRequireds = result;
   		
   	}, function (err) {
   		
   	});
	//$scope.calculateRules = [{"id":"1", "name":"等额本金"},{"id":"2", "name":"等额本息"}];
 	LeasingCommon.getDictByGroupId(22).then(function (result) {
   		$scope.calculateRules = result;
   		
   	}, function (err) {
   		
   	});
	//获取方案ID
	var id = $stateParams.id;
	$scope.leasing = {};
	$scope.sellers = [];
	$scope.tableFeeData = [];
	
	//定义全局页面的当前类型是否是编辑页面
	var mainEdidMode = false;
	//页面验证
	$scope.submitTried = false;
	
	//控制Radio代码Begin
	var InputTypeChange = function(val){
		if(val == 1){
			$scope.leasing.depositMin = null;
			$scope.leasing.depositMax = null;
		}else{
			$scope.leasing.depositRatioMin = null;
			$scope.leasing.depositRatioMax = null;
		}
	}
	var InputTypeChange2 = function(val){
		if(val == 1){
			$scope.leasing.firstPayMin = null;
			$scope.leasing.firstPayMax = null;
		}else{
			$scope.leasing.firstPayRatioMin = null;
			$scope.leasing.firstPayRatioMax = null;
		}
	}
	var InputTypeChange3 = function(val){
		if(val == 1){
			$scope.leasing.serviceFeeMin = null;
			$scope.leasing.serviceFeeMax = null;
		}else{
			$scope.leasing.serviceFeeRatioMin = null;
			$scope.leasing.serviceFeeRatioMax = null;
		}
	}
	var InputTypeChange4 = function(val){
		if(val == 1){
			$scope.leasing.floatInterestRatioMin = null;
			$scope.leasing.floatInterestRatioMax = null;
		}else{
			$scope.leasing.interestMin = null;
			$scope.leasing.interestMax = null;
		}
	}
	//控制Radio代码End
	
	//根据ID获取融资租赁方案信息
	var findById = function(id) {
		mainEdidMode = true;
		Restangular.one('master/leasingschema', id).get().then(function(result) {
			$scope.leasing = result;
			$scope.tableFeeData = findFeeByLeasingId(id);
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	//根据LeasingSchemaID查询Fee
	var findFeeByLeasingId = function(id) {
		mainEdidMode = true;
		Restangular.one('master/leasingschemaFee', id).get().then(function(result) {
			$scope.tableFeeData = result;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	
	//获取所有出卖方信息
	var findAllSellers = function() {
		Restangular.one('master/sellers').get().then(function(result) {
			$scope.sellers = _.filter(result, {'status': 0});
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	//根据传入的ID值改变title
	if (id > 0) { 
		$scope.title = "修改";
		findById(id);
		findFeeByLeasingId(id);
		findAllSellers();
	} else {
		$scope.title = "新建";
		findAllSellers();
		$scope.leasing.depositInputM="1";    //保证金
		$scope.leasing.fpInputM="1";         //首期租金
		$scope.leasing.serviceFeeInputM="1"; //手续费计算方式
		$scope.leasing.interestSetting="1";  //利率配置
	}
	//更新数据
	var update = function() {
		$scope.leasing.put().then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '修改融资租赁方案数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
	}
	//创建一条数据
	var create = function()  {
		var allData = {};
		allData.leasingSchema = $scope.leasing;
		allData.leasingSchemaFee = $scope.tableFeeData;
		Restangular.all('master/leasingschema').post(allData).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '新建融资租赁方案数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};
  	
  	//总页面的取消
	var back = function(){
		$state.go("app.s1.leasing-list",null,null);
	};
	$scope.back = back;
	
	//保存信息
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
	
	
	
	//leasingschemaFee模块//
	//var baseLeasingSchemasFee = Restangular.one('master/leasingschemaFee');
    //新增费率
	$scope.createFee = function () {
		var dlg = dialogs.create('views/tpl/master/editLeasingSchemaFee.html','editLeasingSchemaFeeController',{},{size:'md',backdrop:'static'});
		dlg.result.then(function(retData){
			var leasingFee = retData.data;
			leasingFee.leasingSchemaId = $scope.leasing.id;
			if (mainEdidMode) {
				Restangular.all('master/leasingschemaFee').post(leasingFee).then(function(result) {
		    		if (result) {
		    			toaster.pop('success', '', '新建融资租赁方案费率数据成功！');
		    			findFeeByLeasingId(leasingFee.leasingSchemaId);
		    		}	
				}, function(errResponse) {
					console.log("Error with status code", errResponse.status);
				}); 
			} else {
				$scope.tableFeeData.push(leasingFee);
			}
		},function(){
			console.log("Cancelled");
		});
	}
	
	//编辑费率
	$scope.editFee = function (leasingFee,index) {
		var dlg = dialogs.create('views/tpl/master/editLeasingSchemaFee.html','editLeasingSchemaFeeController',{"leasingFee":leasingFee},{size:'md','backdrop':'static'});
		dlg.result.then(function(retData){
			var updLeasingFee = retData.data;
			if(mainEdidMode){
				Restangular.one('master/leasingschemaFee', updLeasingFee.id).customPUT(updLeasingFee).then(function(result) {
		    		if (result) {
						var feeList = $scope.tableFeeData;
						var index = _.findIndex(feeList, ['id', updLeasingFee.id]);
						feeList[index] = result;
		    			toaster.pop('success', '', '修改方案费率数据成功！');
		    		}	
				}, function(errResponse) {
					console.log("Error with status code", errResponse.status);
				}); 
			}else{
				var feeList = $scope.tableFeeData;
				feeList[index] = updLeasingFee;
			}
		},function(){
			console.log("Cancelled");
		});
	 };
	 
	 //删除费率
	 $scope.logicalDelFee = function (leasingFee,index) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此方案费率吗？", {'size': 'sm','backdrop':'static'});
		if(mainEdidMode){
			dlg.result.then(function(btn){
				Restangular.one('master/leasingschemaFee', leasingFee.id).remove().then(function(result) {
					var feeList = $scope.tableFeeData;
					var index = _.findIndex(feeList, ['id', leasingFee.id]);
					feeList.splice(index,1);
	    			toaster.pop('success', '', '删除融资租赁方案费率成功！');
				}, function(response) {
					console.log("Error with status code", response.status);
				});
			},function(btn){
				console.log("Cancelled");
			});
		}else{
			dlg.result.then(function(btn){
				var feeList = $scope.tableFeeData;
				feeList.splice(index,1);
			});
		}
	 };
	 $scope.InputTypeChange = InputTypeChange;
	 $scope.InputTypeChange2 = InputTypeChange2;
	 $scope.InputTypeChange3 = InputTypeChange3;
	 $scope.InputTypeChange4 = InputTypeChange4;
}]);





app.controller('editLeasingSchemaFeeController',function($scope,$modalInstance,data){
	$scope.title = "新建融资租赁方案费率";
	var editMode = false;
	$scope.paymentMethods = [{"id":"0", "name":"期末还款"},{"id":"1", "name":"期初还款"}];
	if (data.leasingFee) {
		$scope.title = "修改融资租赁方案费率";
		$scope.leasingFee = data.leasingFee;
		editMode = true;
	}
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	$scope.save = function(){
		var retData = {mode:editMode, data:$scope.leasingFee};
		$modalInstance.close(retData);
	};
});
