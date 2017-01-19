	app.controller('editdictionary', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster','$stateParams','$state','LeasingCommon',
	function($scope, Restangular, NgTableParams, dialogs, toaster,$stateParams,$state,LeasingCommon) {
	           	
                   	
     $scope.submitTried = false;
		//获取方案
    
	var id = $stateParams.id;

	$scope.syselementgroup = {};
	$scope.tableData = [];
	var mainEdidMode = false;
	
	//定义全局页面的当前类型是否是编辑页面
	  
	//根据eleGroupId查询syselementgroup列表
	var findById = function(id) {
		 var mainEdidMode=true;
		Restangular.one('master/syselementgroup',id).get().then(function(result) {
			$scope.syselementgroup = result;
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };

	//根据eleGroupId查询syselement列表
    var findByGroupId = function(id) {
	var	mainEdidMode = true;
		Restangular.one('master/syselement',id).get().then(function(result) {
			$scope.tableData = result;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
  //新增一条空记录

	$scope.createEle = function () {
		var newRow = {eleCode:"",eleName:"",eleKey:"",orderNum:""};
		var test = $scope.tableData;
		if(test){
			$scope.tableData.push(newRow);
		}else{
			$scope.tableData.push(newRow);
		}
	}
	//创建一条数据
	
    var create = function()  {
		var allData = {};
		allData.syselement = $scope.tableData;
		allData.syselementgroup = $scope.syselementgroup;
		Restangular.all('master/syselementgroup').post(allData).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '新建数据字典成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};
  //更新数据
////	$scope.leasingCreditCorp.leasingRequestId = leasingRequestId;
// 	$scope.syselement.eleGroupId = eleGroupId;
	var update = function() {

//		$scope.syselement.eleGroupId = eleGroupId;
		var allData = {};
		allData.syselement = $scope.tableData;
//		for(var i=0;i<allData.syselement.length;i++){
		var lccId = allData.syselement.length;
		

		if( null != lccId ){
			//去除多余的server传来的属性
			allData.syselement = $scope.tableData.plain();
		}

		allData.syselementgroup = $scope.syselementgroup;
		
		var lcccId = allData.syselementgroup.id;
		if( null != lcccId ){
			//去除多余的server传来的属性
			allData.syselementgroup = $scope.syselementgroup.plain();
		}
		Restangular.all('master/syselement').post(allData).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '修改数据字典成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};

    $scope.FHDel = function(syselement){
	var dlg = dialogs.confirm("废置确认","您确认要废置此记录吗？", {'size': 'sm','backdrop':'static'});
	dlg.result.then(function(gg){

		if(!mainEdidMode){
			Restangular.one('master/syselement', syselement.id).customPUT(syselement).then(function(result) {
	    	
					var feeList = $scope.tableData;
					var index = _.findIndex(feeList, ['id', syselement.id]);
					feeList[index] = result;
					toaster.pop('success', '', '废置记录成功！');
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
		}
	},function(){
		console.log("Cancelled");
	});

 };
 $scope.HuiDel = function(syselement){
		var dlg = dialogs.confirm("恢复确认","您确认要恢复此记录吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(gg){

			if(!mainEdidMode){
				Restangular.one('master/syselement', syselement.id).customPUT(syselement).then(function(result) {
		    	
						var feeList = $scope.tableData;
						var index = _.findIndex(feeList, ['id', syselement.id]);
						feeList[index] = result;
						toaster.pop('success', '', '恢复记录成功！');
					
				}, function(errResponse) {
					console.log("Error with status code", errResponse.status);
				}); 
			}
		},function(){
			console.log("Cancelled");
		});

	 };
    $scope.logicalDel = function (syselement,index) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此记录吗？", {'size': 'sm','backdrop':'static'});
		if(syselement.id !=null){
			dlg.result.then(function(btn){
				Restangular.one('master/syselement',syselement.id).remove().then(function(result) {
					var feeList = $scope.tableData;
					var index = _.findIndex(feeList, ['id', syselement.id]);
					feeList.splice(index,1);
	    			toaster.pop('success', '', '删除记录成功！');
				}, function(response) {
					console.log("Error with status code", response.status);
				});
			},function(btn){
				console.log("Cancelled");
			});
		}
		else{
			dlg.result.then(function(btn){
			var shList = $scope.tableData;
			var ss = shList.splice(index,1);
			var sss = ss;
			});

		}
	 };


	if (id > 0) { 
		$scope.title = "修改";
		
		findById(id);
		findByGroupId(id);
	} else {
		$scope.title = "新建";
	}



	//总页面的取消
	var back = function(){
		console.log("back");
		$state.go("app.s1.dictionary-list",null,null);
	};
	$scope.back = back;
	
	//保存信息
	$scope.save = function(isNotValid){
		 $scope.submitTried = true;
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


	}]);
	app.controller('editBankController',function($scope,$modalInstance,data){

		
		
		$scope.eidtMode=false;
		if (syselement.StatusFlag=2) {
			
			
			$scope.eidtMode=true;
			$scope.tableData= data.tableData;
		}
	});