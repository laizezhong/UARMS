app.controller('editCompanyController', ['$state','$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', '$stateParams', 'LeasingCommon',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster,$stateParams,LeasingCommon) {
	$scope.submitTried = false;
	
	//数据字典:三证合一
	LeasingCommon.getDictByGroupIdInt(3).then(function (result) {
		$scope.businessCodeTypes = result;
	}, function (err) {
	});
	$scope.dateOptions = {
      startingDay: 1,
      class: 'datepicker'
	};
    $scope.format = "yyyy-MM-dd";
	$scope.openDatepicker = function($event, i) {
		$event.preventDefault();
		$event.stopPropagation();
		if(i==0)
			$scope.expireDate = true;
		else if(i==1)
			$scope.foundDate = true;
	}
	
	//得到公司级别的转换事件,并动态的清空所属公司的输入框的内容
	var companyLevelChange = function(){
		$scope.company.parentId = null;
	}
	 
	//得到三证合一的转换事件,并动态清除三证合一有关的输入框的内容
	var businessCodeTypeChange = function(){
		$scope.company.creditCode = null;
		$scope.company.license = null;
		$scope.company.orgCode = null;
		$scope.company.taxNum = null;
	}
	
	var id = $stateParams.id;
	$scope.company = null;
	$scope.parentIds = null;
	
	//根据ID查询数据
	var findById = function(id) {
		Restangular.one('master/companies', id).get().then(function(result) {
			$scope.company = result;
			$scope.company.level = result.level;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	
	//查询所有公司数据
	var findAllCompanies = function() {
		Restangular.one('master/companies').get().then(function(result) {
			$scope.parentIds =_.filter(result,{level:1});
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}
	
	$scope.editMode = false;
	if (id > 0) { 
		$scope.title = "修改";
		$scope.eidtMode=true;
		findById(id);
	} else {
		$scope.title = "新建";
		$scope.company = {};
		$scope.company.level = 1;	 //新建时默认选中自然人
	}
	
	//修改公司数据
	var update = function() {
		$scope.company.put().then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '修改公司数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
	}
	
	//新建公司数据
	var create = function()  {
		Restangular.all('master/companies').post($scope.company).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '新建公司数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};

  	//返回主菜单
  	var back = function(){
  		$state.go("app.s1.company-list",null,null);
	};
	
	//保持数据
	$scope.save = function(){
		if (id > 0) { 
			update();
		} else {
			create();
		}
	};
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
	
	findAllCompanies();
	$scope.companyLevelChange = companyLevelChange;
	$scope.back = back; 
	$scope.businessCodeTypeChange = businessCodeTypeChange;
	
}]);
