app.controller('CodeRuleController', ['$stateParams','$scope', 'Restangular' ,'ngTableParams', 'dialogs',  'LeasingCommon', 'toaster', 
                                   	function($stateParams,$scope, Restangular, NgTableParams, dialogs, LeasingCommon, toaster) {
	$scope.codeRule={};
	$scope.userNames = LeasingCommon.getUserNames(); 
	/*$scope.resetType = {"1":"是", "2":"否"};
	$scope.types = {"1":"承租人编号", "2":"融资租赁合同编号","3":"买卖合同编号", "4":"资信编号","5":"资金编号", "6":"分解单编号编号"};*/
	LeasingCommon.getDictByGroupId(9).then(function (result) {
		
		$scope.types = LeasingCommon.dict2Object(result);
	}, function (err) {
		
	});
	LeasingCommon.getDictByGroupId(10).then(function (result) {

		$scope.resetType = LeasingCommon.dict2Object(result);
	}, function (err) {
		
	});
	//当Id为1时;为承租人编号
	//当Id为2时;为融资租赁合同编号
	//当Id为3时;为买卖合同编号
	//当Id为4时；资信编号
	//当Id为5时;为资金编号
	//当Id为6时为分解单编号编号
	
	//$scope.bankTypes = {"1":"总行", "2":"分行", "3":"支行"};
//	$scope.codeRule.lesscode="融资租赁合同编号";
//	$scope.codeRule.code="流水号";

	var baseCodeRules = Restangular.one('master/codeRules');
	
	var search = function() {
		baseCodeRules.get().then(function(result) {
    		if (result.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsCodeRule = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: result});
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});   	
    };
//    var search = function() {	
//		baseCodeRules.get($scope.searcher).then(function(result) {
//    		if (result.length < 1) {
//    			toaster.pop('warning', '', '没有找到符合条件的数据！');
//    		}
//			$scope.tableParamsCodeRule = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: result});
//		}, function(errResponse) {
//			console.log("Error with status code", errResponse.status);
//		});   	
//    };

    var create = function () {
		var dlg = dialogs.create('views/tpl/master/editcodeRule.html','editCodeRuleController',{},{size:'lg','backdrop':'static'});
		dlg.result.then(function(codeRule){
			delete codeRule.lesscode;
			delete codeRule.code;
			delete codeRule.item;
			delete codeRule.Province;
			delete codeRule.year;
			delete codeRule.Month;
			delete codeRule.itemcode;
			delete codeRule.Distributor;
			delete codeRule.Customer;
			delete codeRule.Service;
//			delete updBankAccount.bankName;
			Restangular.all('master/codeRules').post(codeRule).then(function(result) {
	    		if (result) {
	    			toaster.pop('success', '', '新建编码数据成功！');
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    }
    var edit = function (codeRule) {
		var dlg = dialogs.create('views/tpl/master/editcodeRule.html','editCodeRuleController',{"codeRule":codeRule},{size:'lg','backdrop':'static'});
		dlg.result.then(function(updCodeRule){
			delete updCodeRule.lesscode;
			delete updCodeRule.code;
			delete updCodeRule.item;
			delete updCodeRule.Province;
			delete updCodeRule.year;
			delete updCodeRule.Month;
			delete updCodeRule.itemcode;
			delete updCodeRule.Distributor;
			delete updCodeRule.Customer;
			delete updCodeRule.Service;
			Restangular.one('master/codeRules', updCodeRule.id).customPUT(updCodeRule).then(function(result) {
	    		if (result) {
					var codeRuleList = $scope.tableParamsCodeRule.data;
					var index = _.findIndex(codeRuleList, ['id', updCodeRule.id]);
					codeRuleList[index] = result;
	    			toaster.pop('success', '', '修改编码数据成功！');
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 

		},function(){
			console.log("Cancelled");
		});
    };
    var view = function (codeRule) {
		var dlg = dialogs.create('views/tpl/master/viewCodeRule.html','viewCodeRuleController',{"codeRule":codeRule},{size:'md'});
		dlg.result.then(function(updCodeRule){
			Restangular.one('master/codeRules', updCodeRule.id).customPUT(updCodeRule).then(function(result) {
	    		if (result) {
					var codeRuleList = $scope.tableParamsCodeRule.data;
					var index = _.findIndex(codeRuleList, ['id', updCodeRule.id]);
					codeRuleList[index] = result;
	    			toaster.pop('success', '', '查看数据！',{'backdrop':'static'});
	    			search();
	    		}	
			}, function(errResponse) {
				console.log("Error with status code", errResponse.status);
			}); 
			LeasingCommon.getDictByGroupId(10).then(function (result) {

				$scope.resetType = LeasingCommon.dict2Object(result);
			}, function (err) {
				
			});
		},function(){
			console.log("Cancelled");
		});
    };
    
    
    var logicalDel = function (codeRule) {
		var dlg = dialogs.confirm("删除确认","您确认要删除此方案吗？", {'size': 'sm'});
		dlg.result.then(function(btn){
			Restangular.one('master/codeRules', codeRule.id).remove().then(function(result) {
				var codeRuleList = $scope.tableParamsCodeRule.data;
				var index = _.findIndex(codeRuleList, ['id', codeRule.id]);
				codeRuleList.splice(index,1);
    			toaster.pop('success', '', '删除数据成功！');
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
app.controller('editCodeRuleController',function($scope,$modalInstance,data,LeasingCommon){
	
	$scope.codeRule={};
LeasingCommon.getDictByGroupId(9).then(function (result) {
	$scope.types=result;
		
	}, function (err) {
		
	});
//	$scope.types = [{"id":"1", "name":"承租人合同"},{"id":"2", "name":"融资租赁合同"},{"id":"3", "name":"买卖合同编号"},{"id":"4", "name":"资信编号"},{"id":"5", "name":"资金编号"},{"id":"6", "name":"分解单编号编号"}];
	//$scope.bankTypes = [{"id":1, "name":"总行"},{"id":2, "name":"分行"},{"id":3, "name":"支行"}];
LeasingCommon.getDictByGroupId(10).then(function (result) {

	$scope.resetType = LeasingCommon.dict2Object(result);
}, function (err) {
	
});
	$scope.title = "新建";
	$scope.eidtMode=false;
	if (data.codeRule) {
		$scope.title = "修改";
		$scope.eidtMode=true;
		$scope.codeRule = data.codeRule;
	}
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	$scope.save = function(){
		$modalInstance.close($scope.codeRule);
	};
//	$scope.tableFeeData = [];
//	 var aa="";
	
	var push=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		};
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		aa=	$scope.codeRule.expression;
//		var newRow = {$scope.codeRule:"{融资租赁编号}"}
		 $scope.codeRule.lesscode="{融资租赁编号}";
		 
  	    	if (aa.indexOf("{融资租赁编号}")<0){//.indexOf("融资租赁编号"
       $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.lesscode;
       $scope.codeRule.lesscode=null;;
		}
		else{
			var strArr = aa.split('{融资租赁编号}');
			$scope.codeRule.expression= strArr.join('');
			$scope.codeRule.lesscode=null;
		
		}
	};	
	var push1=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		bb=$scope.codeRule.expression;
		 $scope.codeRule.code="{流水号}";
//    var newRow = {code:"{流水号}"}
		if (	bb.indexOf("{流水号}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.code;
				$scope.codeRule.code=null;
		}
		else{
			var strArr = bb.split('{流水号}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.code=null;
		}
	};	
	var push2=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		cc=$scope.codeRule.expression;
		 $scope.codeRule.item="{项目类型}";
//    var newRow = {code:"{流水号}"}
		if (	cc.indexOf("{项目类型}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.item;
				$scope.codeRule.item=null;
		}
		else{
			var strArr = cc.split('{项目类型}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.item=null;
		}
	};	
	var push3=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		dd=$scope.codeRule.expression;
		 $scope.codeRule.Province="{省份简称}";
//    var newRow = {code:"{流水号}"}
		if (	dd.indexOf("{省份简称}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.Province;
				$scope.codeRule.Province=null;
		}
		else{
			var strArr = dd.split('{省份简称}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.Province=null;
		}
	};	
	var push4=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		ee=$scope.codeRule.expression;
		 $scope.codeRule.year="{年份}";
//    var newRow = {code:"{流水号}"}
		if (	ee.indexOf("{年份}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.year;
				$scope.codeRule.year=null;
		}
		else{
			var strArr = ee.split('{年份}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.year=null;
		}
	};	
	var push5=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		jj=$scope.codeRule.expression;
		 $scope.codeRule.Month="{月份}";
//    var newRow = {code:"{流水号}"}
		if (jj.indexOf("{月份}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.Month;
				$scope.codeRule.Month=null;
		}
		else{
			var strArr = jj.split('{月份}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.Month=null;
		}
	};	
	var push6=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		ff=$scope.codeRule.expression;
		 $scope.codeRule.itemcode="{项目编号}";
//    var newRow = {code:"{流水号}"}
		if (	ff.indexOf("{项目编号}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.itemcode;
				$scope.codeRule.itemcode=null;
		}
		else{
			var strArr = ff.split('{项目编号}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.itemcode=null;
		}
	};	
	var push7=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		gg=$scope.codeRule.expression;
		 $scope.codeRule.Distributor="{经销商代码}";
//    var newRow = {code:"{流水号}"}
		if (	gg.indexOf("{经销商代码}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.Distributor;
				$scope.codeRule.Distributor=null;
		}
		else{
			var strArr = gg.split('{经销商代码}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.Distributor=null;
		}
	};
	var push8=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		hh=$scope.codeRule.expression;
		 $scope.codeRule.Customer="{客户类型}";
//    var newRow = {code:"{流水号}"}
		if (	hh.indexOf("{客户类型}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.Customer;
				$scope.codeRule.Customer=null;
		}
		else{
			var strArr = hh.split('{客户类型}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.Customer=null;
		}
	};
	var push9=function(){
		if($scope.codeRule==null){
		$scope.codeRule={};
		$scope.codeRule.expression="";
		}
		if($scope.codeRule.expression==null){
			$scope.codeRule.expression="";
		}
		ii=$scope.codeRule.expression;
		 $scope.codeRule.Service="{业务类型}";
//    var newRow = {code:"{流水号}"}
		if (	ii.indexOf("{业务类型}")<0){//.indexOf("融资租赁编号"
			 $scope.codeRule.expression= $scope.codeRule.expression+$scope.codeRule.Service;
				$scope.codeRule.Service=null;
		}
		else{
			var strArr = ii.split('{业务类型}');
			$scope.codeRule.expression= strArr.join('') ;
			$scope.codeRule.Service=null;
		}
	};
	$scope.push = push;
	$scope.push1 = push1;
	$scope.push2 = push2;
	$scope.push3 = push3;
	$scope.push4 = push4;
	$scope.push5= push5;
	$scope.push6 = push6;
	$scope.push7 = push7;
	$scope.push8 = push8;
	$scope.push9 = push9;

});
app.controller('viewCodeRuleController',function($scope,$modalInstance,data,LeasingCommon){
//	$scope.types = [{"id":"1", "name":"承租人合同"},{"id":"2", "name":"融资租赁合同"},{"id":"3", "name":"买卖合同编号"},{"id":"4", "name":"资信编号"},{"id":"5", "name":"资金编号"},{"id":"6", "name":"分解单编号编号"}];
	LeasingCommon.getDictByGroupId(9).then(function (result) {
		
		$scope.typess = result;
	}, function (err) {
		
	});
	LeasingCommon.getDictByGroupId(10).then(function (result) {

		$scope.resetType = LeasingCommon.dict2Object(result);
	}, function (err) {
		
	});
	$scope.show=false;
	$scope.title = "新建";

	
	if (data.codeRule) {
		$scope.title = "查看";
		$scope.show=true;
		$scope.codeRule = data.codeRule;
	}
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	$scope.save = function(){
		$modalInstance.close($scope.codeRule);
	};
});