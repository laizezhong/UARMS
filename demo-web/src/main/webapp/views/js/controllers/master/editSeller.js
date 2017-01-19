app.controller('editSellerController', ['$state','$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', '$stateParams', 'LeasingCommon',
	function($state, $scope, Restangular, NgTableParams, dialogs, toaster,$stateParams, LeasingCommon) {
	
	var id = $stateParams.id;
	$scope.submitTried = false;
	$scope.seller = {};
	$scope.sellerBank = null;
	//定义全局页面的当前类型是否是编辑页面
	var mainEdidMode = false;
	$scope.tableData = [];
	
	//数据字典 出卖方类型
	LeasingCommon.getDictByGroupIdInt(5).then(function (result) {
		$scope.sellerTypes = result;
		$scope.sellerType = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//数据字典 合作状态
	LeasingCommon.getDictByGroupId(8).then(function (result) {
		$scope.status = result;
		$scope.statu = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//数据字典 企业性质
	LeasingCommon.getDictByGroupId(6).then(function (result) {
		$scope.sellerNatures = result;
		$scope.sellerNature = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//数据字典 三证合一
	LeasingCommon.getDictByGroupIdInt(3).then(function (result) {
		$scope.businessCodeTypes = result;
		$scope.businessCodeType = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	
	//数据字典 是否回购
	LeasingCommon.getDictByGroupIdInt(7).then(function (result) {
		$scope.buybackTypes = result;
		$scope.buybackType = LeasingCommon.dict2Object(result);
	}, function (err) {
	});
	

	//获取出卖方信息
	var findById = function(id) {
		mainEdidMode = true;
		Restangular.one('master/sellers',id).get().then(function(result) {
			$scope.seller = result;
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	}

	$scope.dateOptions = {
		// formatYear: 'yy',
		startingDay : 1,
		class : 'datepicker'
	};
	$scope.format = "yyyy-MM-dd";

	$scope.openDatepicker = function($event, i) {
		$event.preventDefault();
		$event.stopPropagation();
		if (i == 0)
			$scope.foundDate = true;
		else if (i == 1)
			$scope.expireDate = true;
		else if (i == 2)
			$scope.partnershipExpire = true;
	}

	var update = function() {
			Restangular.one('master/sellers', $scope.seller.id).customPUT($scope.seller.plain()).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '修改出卖方数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
	}
	
	var view = function() {
		Restangular.one('master/sellers', $scope.seller.id).customPUT($scope.seller.plain()).then(function(result) {
    		if (result) {
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
	}
	
	var create = function()  {
		var allData = {};
		allData.seller = $scope.seller;
		allData.sellerBank = $scope.tableData;
		Restangular.all('master/sellers').post(allData).then(function(result) {
    		if (result) {
    			toaster.pop('success', '', '新建出卖方数据成功！');
    			back();
    		}	
		}, function(errResponse) {
			console.log("Error with status code", errResponse.status);
		}); 
  	};
  	
  //得到三证合一的转换事件,并动态清除三证合一有关的输入框的内容
	var businessCodeTypeChange = function(){
		var bct =  $scope.seller.businessCodeType
		if(bct==0){
			$scope.seller.creditCode = null;
		}
		if(bct==1){
			$scope.seller.license = null;
			$scope.seller.orgCode = null;
			$scope.seller.taxNum = null;
		}
	}

  	var back = function(){
		$state.go("app.s1.seller-list",null,null);
	};
	
	/**********银行**********/
	var baseSellerBanks = Restangular.one('master/sellerBanks');
	var searchBank = function(id) {
		mainEdidMode = true;
		Restangular.one('master/sellerBanks', id).getList().then(function(result) {		
    	    $scope.tableData = result;
    	}, function(errResponse) {
    		console.log("Error with status code", errResponse.status);
    	});   	
    };
    
    //新增银行
    var createBank = function (id) {
		var dlg = dialogs.create('views/tpl/master/editSellerBank.html','editSellerBankController',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(sellerBank){
			sellerBank.sellerId = id;
			if (mainEdidMode) {
				Restangular.all('master/sellerBanks').post(sellerBank).then(function(result) {
		    		if (result) {
		    			toaster.pop('success', '', '新建银行数据成功！');
		    			searchBank(id);
		    		}	
				}, function(errResponse) {
					console.log("Error with status code", errResponse.status);
				}); 
			} else {
				$scope.tableData.push(sellerBank);
			}
		},function(){
			console.log("Cancelled");
		});
    };
	//编辑银行
	var editBank = function (sellerBank,index) {
		var dlg = dialogs.create('views/tpl/master/editSellerBank.html','editSellerBankController',{"sellerBank":sellerBank},{size:'md','backdrop':'static'});
		dlg.result.then(function(updSellerBank){
			if(mainEdidMode){
				Restangular.one('master/sellerBanks', updSellerBank.id).customPUT(updSellerBank).then(function(result) {
		    		if (result) {
						var bankList = $scope.tableData;
						var index = _.findIndex(bankList, ['id', updSellerBank.id]);
						bankList[index] = result;
		    			toaster.pop('success', '', '修改银行数据成功！');
		    		}	
				}, function(errResponse) {
					console.log("Error with status code", errResponse.status);
				}); 
			}else{
				var bankList = $scope.tableData;
				bankList[index] = updSellerBank;
			}
		},function(){
			console.log("Cancelled");
		});
	 };
	 
	 //删除银行
	 var logicalDelBank = function (sellerBank,index) {
			var dlg = dialogs.confirm("删除确认","您确认要删除此银行吗？", {'size': 'sm','backdrop':'static'});
			if(mainEdidMode){
				dlg.result.then(function(btn){
					//-----
					var newSearcher = {sellerBankId:sellerBank.id};
					Restangular.one('purchase/contract').get(newSearcher).then(function(result) {
						if (result.length < 1) {
					//-----
							Restangular.one('master/sellerBanks', sellerBank.id).remove().then(function(result) {
								var bankList = $scope.tableData;
								var index = _.findIndex(bankList, ['id', sellerBank.id]);
								bankList.splice(index,1);
				    			toaster.pop('success', '', '删除银行数据成功！');
				    			//search();
							}, function(response) {
								console.log("Error with status code", response.status);
							});
					//----
						}else{
					    			toaster.pop('warning', '', '此出卖方银行帐号有关联的买卖买合同，不能删除！');
					    	}
					});
					//----
				},function(btn){
					console.log("Cancelled");
				});
			}else{
				dlg.result.then(function(btn){
					var bankList = $scope.tableData;
					bankList.splice(index,1);
				});
			}
	    };
	    
	  //附件上传
	    $scope.seller.licenseCopyUrl = null;
		 $scope.seller.legalIdFrontUrl = null;
		 $scope.seller.legalIdBackUrl = null;
		 $scope.seller.agreementUrl = null;
		 $scope.seller.otherAttachmentUrl = null;
			
		$scope.uploadLicenseCopyUrl = function() {
			var config = {title:"营业执照复印件", path:"/seller/"};
			var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
			dlg.result.then(function(result){
				//console.log(result);
				if (result.status == "SUCCESS"){	
					toaster.pop('success', '', '上传营业执照复印件成功！');
					var licenseCopyUrl = "upload" + config.path + result.result;
					$scope.seller.licenseCopyUrl = licenseCopyUrl;
					//TODO call other function for DB updating to save the file URL below
				} else {
					toaster.pop('error', '', '上传营业执照复印件失败！');
				}
			},function(){
				console.log("Cancelled");
			});
		}
		
		$scope.uploadLegalIdFrontUrl = function() {
			var config = {title:"法人代表身份证正面复印件", path:"/seller/"};
			var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
			dlg.result.then(function(result){
				//console.log(result);
				if (result.status == "SUCCESS"){	
					toaster.pop('success', '', '上传法人代表身份证正面复印件成功！');
					var legalIdFrontUrl = "upload" + config.path + result.result;
					console.log("FileName: " + legalIdFrontUrl);
					$scope.seller.legalIdFrontUrl = legalIdFrontUrl;
					//TODO call other function for DB updating to save the file URL below
				} else {
					toaster.pop('error', '', '上传法人代表身份证正面复印件失败！');
				}
			},function(){
				console.log("Cancelled");
			});
		}
		
		$scope.uploadLegalIdBackUrl = function() {
			var config = {title:"法人代表身份证反面复印件", path:"/seller/"};
			var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
			dlg.result.then(function(result){
				//console.log(result);
				if (result.status == "SUCCESS"){	
					toaster.pop('success', '', '上传法人代表身份证反面复印件成功！');
					var legalIdBackUrl = "upload" + config.path + result.result;
					console.log("FileName: " + legalIdBackUrl);
					$scope.seller.legalIdBackUrl = legalIdBackUrl;
					//TODO call other function for DB updating to save the file URL below
				} else {
					toaster.pop('error', '', '上传法人代表身份证反面复印件失败！');
				}
			},function(){
				console.log("Cancelled");
			});
		}
		
		$scope.uploadAgreementUrl = function() {
			var config = {title:"合作协议复印件", path:"/seller/"};
			var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
			dlg.result.then(function(result){
				//console.log(result);
				if (result.status == "SUCCESS"){	
					toaster.pop('success', '', '上传合作协议复印件成功！');
					var agreementUrl = "upload" + config.path + result.result;
					console.log("FileName: " + agreementUrl);
					$scope.seller.agreementUrl = agreementUrl;
					//TODO call other function for DB updating to save the file URL below
				} else {
					toaster.pop('error', '', '上传合作协议复印件失败！');
				}
			},function(){
				console.log("Cancelled");
			});
		}
		
		$scope.uploadOtherAttachmentUrl = function() {
			var config = {title:"其他附件", path:"/seller/"};
			var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
			dlg.result.then(function(result){
				//console.log(result);
				if (result.status == "SUCCESS"){	
					toaster.pop('success', '', '上传其他附件成功！');
					var otherAttachmentUrl = "upload" + config.path + result.result;
					console.log("FileName: " + otherAttachmentUrl);
					$scope.seller.otherAttachmentUrl = otherAttachmentUrl;
					//TODO call other function for DB updating to save the file URL below
				} else {
					toaster.pop('error', '', '上传其他附件失败！');
				}
			},function(){
				console.log("Cancelled");
			});
		}
		
		//显示图片
		var showImg = function (imgUrl) {
			var dlg = dialogs.create('views/tpl/creditinfo/showLegalCreditImg.html','ShowImageController',{"imgUrl":imgUrl},{size:'lg'});
			dlg.result.then(function(imgUrl){
				$scope.imgUrl = imgUrl;
			},function(){
				console.log("Cancelled");
			});
		 };
	  	$scope.showImg = showImg;
	    
	 $scope.editMode = false;
	 $scope.editBankMode = true;
	if (id > 0) {
		$scope.title = "修改";
		findById(id);
		searchBank(id);
		$scope.editMode = true;
	} else {
		$scope.title = "新建";
		$scope.editBankMode = false;
	}
	
	
	/*$scope.view = view;*/
	
	$scope.back = back;
	$scope.searchBank = searchBank;
	$scope.createBank = createBank;
	$scope.editBank = editBank;
	$scope.logicalDelBank = logicalDelBank;
	$scope.businessCodeTypeChange = businessCodeTypeChange;
	
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
		
}]);

app.controller('editSellerBankController', function($scope, $modalInstance,
		data) {

	$scope.title = "增加银行";

	if (data.sellerBank) {
		$scope.title = "更新银行";
		$scope.sellerBank = data.sellerBank;
	}

	$scope.cancel = function() {
		$modalInstance.dismiss('Cancelled');
	};

	$scope.saveBank = function() {
		$modalInstance.close($scope.sellerBank);
	};

});

app.controller('ShowImageController',function($scope,$modalInstance,data){
	
	$scope.imgUrl = data.imgUrl;
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};

});

