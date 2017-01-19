app.controller('TestController', ['$window', '$state', '$scope', 'Restangular' , 'dialogs', 'toaster', 'LeasingCommon',
	function($window, $state, $scope, Restangular, dialogs, toaster, LeasingCommon) {
	
	$scope.testJob = function() {
		Restangular.one('test/job').get().then(function(result) {
			console.log("output:" + result);
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});  

		
	};
	
	$scope.test = function() {
/*		Restangular.one('test/number').get($scope.input).then(function(result) {
			console.log("output:" + result);
			$scope.output = result;
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		});  */ 

		alert($window.innerHeight);
		
	};

	$scope.chkValid = function(form){

		alert(form.$invalid);
	}
	
	$scope.chgMoneyRange = function(){

		$scope.maxMoney = $scope.maxMoney * 2;
		$scope.minMoney = $scope.minMoney * 2;	

		// to invoke ng-validate for money input
		$scope.money = $scope.money + 0.000001;
	}
	
	$scope.chgMoney = function(){
		$scope.ratio = $scope.money / 1000;
	}

	$scope.chgRatio = function(){
		$scope.money = $scope.ratio * 1000;
	}
	
	$scope.chgMoney2 = function(){
		$scope.ratio2 = $scope.money2 / 1000;
	}

	$scope.chgRatio2 = function(){
		$scope.money2 = $scope.ratio2 * 1000;
	}
	
	$scope.inRange = function(currency, min, max) {
		ret = false;
		if (currency != null) {
			strVal = currency.toString();
			console.log(strVal);
			val = strVal.replace(/['¥'|',']/g,'');
			if(val >= min && val <= max){
				ret = true;
			}
		} else {
			return true;
		}
		return ret;
	}
	
	$scope.userNames = LeasingCommon.getUserNames();
	
//	LeasingCommon.getUserNames().then(function (result) {
//		$scope.userNames = result;
//	}, function (err) {
//		
//	});
	
	LeasingCommon.getDictByGroupId(13).then(function (result) {
		$scope.dic1 = result;
		$scope.dic1Ob = LeasingCommon.dict2Object(result);
	}, function (err) {
		
	});
	
	LeasingCommon.getDictByGroupIdInt(13).then(function (result) {
		$scope.dic1Int = result;
	}, function (err) {
		
	});
	
//	$scope.dic1Ob = LeasingCommon.dict2Object($scope.dic1);
	
// --------------------- PDF Output Sample Start -----------------------------
     var fontSetting = {
			simblack: {
			  normal: 'msyh.ttf',
			  bold: 'msyh.ttf',
			  italics: 'msyh.ttf',
			  bolditalics: 'msyh.ttf'
			}
		};  
	  
	  var docDefinition = {
	    content: [
	      {
	        text: '水果列表：',
			style: 'header',
	      },
	      {
	        style: 'demoTable',
	        table: {
	          widths: ['*', '*', '*'],
	          body: [
	            [{text: '水果', style: 'header'}, {text: '数量', style: 'header'},
	              {text: '卡路里', style: 'header'}
	            ],
	            ['Apple', '100 grams', '52'],
	            ['Bananas', '100 grams', '89'],
	            ['Guava', '100 grams', '68'],
	            ['Lemon', '100 grams', '29'],
	            ['Mangos', '100 grams', '60'],
	            ['Orange', '100 grams', '47'],
	            ['Strawberries', '100 grams', '33']
	          ]
	        }
	      }
	    ],
	    styles: {
	      header: {
	        bold: true,
	        color: '#000',
			//font:'simblack',
	        fontSize: 11
	      },
	      demoTable: {
	        color: '#666',
			//font:'simblack',
	        fontSize: 10
	      }
	    }
	  };

	  $scope.openPdf = function() {
		//pdfMake.fonts = fontSetting;
	    pdfMake.createPdf(docDefinition).open();
	  };

	  $scope.downloadPdf = function() {
		//pdfMake.fonts = fontSetting;
	    pdfMake.createPdf(docDefinition).download();
	  };
// --------------------- PDF Output Sample End -----------------------------	
	
}]);
