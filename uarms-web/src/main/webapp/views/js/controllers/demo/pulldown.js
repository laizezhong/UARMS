app.controller('PullDownController', ['$timeout', '$window', '$state', '$scope', 'Restangular' , 'dialogs', 'toaster', 'LeasingCommon',
	function($timeout, $window, $state, $scope, Restangular, dialogs, toaster, LeasingCommon) {
	
	$scope.showAll = false;
	
    $timeout(function () {
    	initChkBox();
		_(testDataGroup).forEach( function(items, group) {
			$scope.paymentItemGroup[group].rows = document.getElementsByName(group+'-tr').length;			
		});
    });
	
	$scope.exportExcel = function () {
		LeasingCommon.exportExcel('G-A-tb');
	};

	$scope.exportCsv = function () {
		LeasingCommon.exportCsv('G-A-tb');
	};
	
	$scope.chkboxs = {};
	_($scope.paymentItemGroup).forEach( function (items,group) {
		$scope.chkboxs[group] = [];
	});
	
	//分组初始化checkbox
	var initChkBox = function(){
		_($scope.paymentItemGroup).forEach( function (items,group) {
			var chkList = angular.element(document.querySelectorAll("#"+group));
			for (i=chkList.length-1; i >= 0; i--) {
				if (chkList[i].checked == true) {
					if (i < chkList.length-1) chkList[i+1].disabled = false;
					return;
				} else {
					if (i==0) {
						chkList[i].disabled = false;
					} else {
						chkList[i].disabled = true;
					}
				}
			}		
		});
	}
	
	$scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
		//initChkBox();
/*		_(testDataGroup).forEach( function(items, group) {
			$scope.paymentItemGroup[group].rows = document.getElementsByName(group+'-tr').length;			
		});*/
	});
	
	var testData = [
            {group:'G-A', row:'A1',deduct1:"",deduct2:"",deduct3:"",name1:"nameA1-1",name2:"nameA1-2",name3:"nameA1-3",plan1:20,plan2:30,plan3:10,remain1:0,remain2:0,remain3:0},
            {group:'G-A', row:'A2',deduct1:"",deduct2:"",deduct3:"",name1:"nameA2-1",name2:"nameA2-2",name3:"nameA2-3",plan1:10,plan2:30,plan3:10,remain1:0,remain2:0,remain3:10},
            {group:'G-A', row:'A3',deduct1:"",deduct2:"",deduct3:"",name1:"nameA3-1",name2:"nameA3-2",name3:"nameA3-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
            {group:'G-A', row:'A4',deduct1:"",deduct2:"",deduct3:"",name1:"nameA4-1",name2:"nameA4-2",name3:"nameA4-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
            {group:'G-B', row:'B1',deduct1:"",deduct2:"",deduct3:"",name1:"nameB1-1",name2:"nameB1-2",name3:"nameB1-3",plan1:20,plan2:30,plan3:10,remain1:0,remain2:22,remain3:10},
            {group:'G-B', row:'B2',deduct1:"",deduct2:"",deduct3:"",name1:"nameB2-1",name2:"nameB2-2",name3:"nameB2-3",plan1:10,plan2:30,plan3:10,remain1:10,remain2:30,remain3:10},
            {group:'G-B', row:'B3',deduct1:"",deduct2:"",deduct3:"",name1:"nameB3-1",name2:"nameB3-2",name3:"nameB3-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
            {group:'G-B', row:'B4',deduct1:"",deduct2:"",deduct3:"",name1:"nameB4-1",name2:"nameB4-2",name3:"nameB4-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
            {group:'G-C', row:'C1',deduct1:"",deduct2:"",deduct3:"",name1:"nameC1-1",name2:"nameC1-2",name3:"nameC1-3",plan1:20,plan2:30,plan3:10,remain1:20,remain2:20,remain3:20},
            {group:'G-C', row:'C2',deduct1:"",deduct2:"",deduct3:"",name1:"nameC2-1",name2:"namec2-2",name3:"nameC2-3",plan1:10,plan2:30,plan3:10,remain1:10,remain2:30,remain3:10},
            {group:'G-C', row:'C3',deduct1:"",deduct2:"",deduct3:"",name1:"nameC3-1",name2:"namec3-2",name3:"nameC3-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
            {group:'G-C', row:'C4',deduct1:"",deduct2:"",deduct3:"",name1:"nameC4-1",name2:"nameC4-2",name3:"namec4-3",plan1:20,plan2:20,plan3:20,remain1:20,remain2:20,remain3:20},
          ];
	
	$scope.testDataGroupAll = _.groupBy(angular.copy(testData), "group");
	
	var testDataGroup = _.groupBy( _.filter(testData, function (item) {return item.remain3+item.remain2+item.remain1 > 0}), "group");
	
	$scope.paymentItemGroup = {};
	_(testDataGroup).forEach( function(items, group) {
		$scope.paymentItemGroup[group] = _.take(testDataGroup[group], testDataGroup[group].length > 2 ? 2 : testDataGroup[group].length);
	});


	//定义待拆分总金额
	$scope.totalRemain = 120;

	$scope.chkChanged = function(group, row, idx) {
	
		var item = _.find($scope.paymentItemGroup[group],{row:row});
		
		switch(idx){
		case 1:
			if (item.deduct1 > 0) {
				item.remain1 += item.deduct1;
				$scope.totalRemain += item.deduct1;
				item.deduct1 = 0;
			} else {
				item.deduct1 = Math.min(item.remain1,$scope.totalRemain);
				item.remain1 -= item.deduct1;
				$scope.totalRemain -= item.deduct1;
			}
			break;
		case 2:
			if (item.deduct2 > 0) {
				item.remain2 += item.deduct2;
				$scope.totalRemain += item.deduct2;
				item.deduct2 = 0;
			} else {
				item.deduct2 = Math.min(item.remain2,$scope.totalRemain);
				item.remain2 -= item.deduct2;
				$scope.totalRemain -= item.deduct2;
			}
			break;
		case 3:
			if (item.deduct3 > 0) {
				item.remain3 += item.deduct3;
				$scope.totalRemain += item.deduct3;
				item.deduct3 = 0;
			} else {
				item.deduct3 = Math.min(item.remain3,$scope.totalRemain);
				item.remain3 -= item.deduct3;
				$scope.totalRemain -= item.deduct3;
				// 若是最后一条自动追加数据
				addPeriod(group);		
			}
			break;
		default:
			
		} 
		enableChkBoxs(group);
	}
	
	$scope.submitTable = function() {
		var updateList = [];
		_($scope.chkboxs).forEach(function (items, group) {
			var ids= _.uniq(_.reject($scope.chkboxs[group],function(n){return n==undefined}));
			
			_($scope.paymentItemGroup[group]).forEach(function (item) {
				if ( _.includes(ids,item.row.toString())) {
					delete item.$$hashKey;
					updateList.push(item);
				}
			});
		});

		console.log(updateList);

	};
	
	$scope.downloadPdf = function() {
		
		var htmlContent = $('#pdfArea').html();
		var title = "PDF功能测试";
		//var form = document.form;	
		LeasingCommon.downladPdf(title,htmlContent);

	};
	

	var addPeriod = function(group) {
		if ($scope.chkboxs[group][$scope.paymentItemGroup[group].length*3-1] != undefined) {
			if ($scope.paymentItemGroup[group].length < testDataGroup[group].length) {
				$scope.paymentItemGroup[group].push(testDataGroup[group][$scope.paymentItemGroup[group].length]);
			}
		}
	};
	

	
	var enableChkBoxs = function(group) {		
		var chkList = angular.element(document.querySelectorAll("#"+group));

		for (i=0; i< chkList.length; i++) {
			if (chkList[i].checked == false){
				if(i > 0) chkList[i-1].disabled = false;
				if (i < chkList.length-1) chkList[i+1].disabled = true;
				break;
			}
		}
		for (i=chkList.length-1; i >= 0; i--) {
			if (chkList[i].checked == true) {
				if (i < chkList.length-1 && $scope.totalRemain > 0) chkList[i+1].disabled = false;
				if (i > 0) chkList[i-1].disabled = true;
				return;
			}
		}
		
	}
	
}]);
