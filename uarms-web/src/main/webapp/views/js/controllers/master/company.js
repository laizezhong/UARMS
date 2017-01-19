app.controller('CompanyController', ['$state', '$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 'LeasingCommon',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster,LeasingCommon) { 
	var baseCompanies = Restangular.one('master/companies');

    //数据字典:公司级别
	LeasingCommon.getDictByGroupId(2).then(function (result) {
		$scope.level = LeasingCommon.dict2Object(result);
	}, function (err) {
		
	});
	
	$scope.userNames = LeasingCommon.getUserNames();                   //得到用户名  
	//搜索
	var search = function() {	
		baseCompanies.get($scope.searcher).then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsCompany = new NgTableParams({count: 10,sorting: { modifyTime: "desc" }}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
    
    //创建
    var create = function () {
    	$state.go("app.s1.company-mgmt",null,null);
    }
    
    //修改
    var edit = function (id) {
    	$state.go("app.s1.company-mgmt",{"id": id},null);
    }
    
    //查看
    var view = function (id) {
    	$state.go("app.s1.company-view",{"id": id},null);
    };
       //逻辑删除
        var logicalDel = function (company) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此公司吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			var baseSellers = Restangular.one('purchase/contract');
			var newRow1 = {leaseName:company.name};
			baseSellers.get(newRow1).then(function(result) {   //判断是否有买卖合同记录
			if(result.length < 1){
			   if(company.level==1){       //判断是否是是总公司
			   var baseCompanies = Restangular.one('master/companies');	  //若是总公司,判断是否有相应的子公司记录
			   baseCompanies.get($scope.searcher).then(function(result){
					   var newRow2 = {parentId:company.id};
					   baseCompanies.get(newRow2).then(function(result) {
						   if(result.length < 1){
							   Restangular.one('master/companies', company.id).remove().then(function(result) {
									var companyList = $scope.tableParamsCompany.data;
									var index = _.findIndex(companyList, ['id', company.id]);
									companyList.splice(index,1);
					    			toaster.pop('success', '', '删除公司数据成功！');
								}, function(response) {
									console.log("Error with status code", response.status);
								});
						   }else{
								toaster.pop('warning', '', '此公司含有子公司，不能删除！');
							}
					   });
			   });
			}else{
				Restangular.one('master/companies', company.id).remove().then(function(result) {
					var companyList = $scope.tableParamsCompany.data;
					var index = _.findIndex(companyList, ['id', company.id]);
					companyList.splice(index,1);
	    			toaster.pop('success', '', '删除公司数据成功！');
				}, function(response) {
					console.log("Error with status code", response.status);
				});
				}
			}
				else{
				toaster.pop('warning', '', '此公司含有未终止的合同，不能删除！');
				}
			});
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


