app.controller('RoleController', ['$q', '$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
	function($q, $scope, Restangular, NgTableParams, dialogs, toaster) {
	
	$scope.roleName = "";
	var roles = null;
	var auths = null
	var roleauths = null;
	
	var search = function () {
	
		var promises = [];
		promises.push(Restangular.one('system/roles').get({roleName:$scope.roleName}));
		promises.push(Restangular.one('system/roleauths').get({roleId:""}));
		if (auths == null && roleauths == null) {
			promises.push(Restangular.one('system/auths').get({authGroup:"", authName:""}));
			//promises.push(Restangular.one('system/roleauths').get({roleId:""}));
		}
		$q.all(promises).then(function(ret){
			roles = ret[0];
			roleauths = ret[1];
			if (ret.length == 3) {
				auths = ret[2];
			} 
			if (roles.length < 1) {
				toaster.pop('warning', '', '没有找到符合条件的数据！');
			} else {

				_(roleauths).forEach(function(roleauth) {
					roleauth.permission = _.find(auths, {id: roleauth.permissionId});
				});
				
				_(roles).forEach(function(role) {
					role.permissions = _.map(_.filter(roleauths, {roleId: role.id}),"permission");
				});				
			}
			$scope.tableParamsRoleList = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: roles});
		}, function (err) {
			
		});

	
	};
	
    $scope.addRole = function () {

		var dlg = dialogs.create('views/tpl/system/editRole.html','editRoleController',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(role){
			Restangular.all('system/roles').post(role).then(function(res) {
				//$scope.tableParamsRoleList.data.push(res);
				search();
				toaster.pop('success', '', '已经成功追加新角色：' + role.rolename);
			}, function(response) {
				console.log("Error with status code", response.status);
			});

		},function(){

		});
    };

	$scope.editRole = function (role) {
		var dlg = dialogs.create('views/tpl/system/editRole.html','editRoleController',{"role":role},{size:'md','backdrop':'static'});
		dlg.result.then(function(updRole){
			
			var roleList = $scope.tableParamsRoleList.data;
			var perms = _.find(roleList, ['id', updRole.id]).permissions;
						
			delete updRole['permissions'];
			
			Restangular.one('system/roles',updRole.id).customPUT(updRole).then(function(res) {
				var index = _.findIndex(roleList, ['id', res.id]);
				res.permissions = perms;
				$scope.tableParamsRoleList.data[index] = res;
				toaster.pop('success', '', '该角色已经被成功更新！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
			
		},function(){

		});
    };
	
	$scope.configRole = function (id) {
		
		var roleList = $scope.tableParamsRoleList.data;
		var index = _.findIndex(roleList, ['id', id]);	
		var role = roleList[index];
		
		var dlg = dialogs.create('views/tpl/system/authPopup.html','RoleConfigController',{"role":role},{size:'lg','backdrop':'static'});
		dlg.result.then(function(permissions){
			// delete $$hashKey property
			_(permissions).forEach(function (perm){
				delete perm['$$hashKey'];
			});
			Restangular.one('system/roleauths',role.id).customPUT(permissions).then(function(res) {
				role.permissions = res;
				toaster.pop('success', '', '成功配置了该角色的权限！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
			
		},function(){

		});
    };
    
    search();
    
    $scope.search = search;
    
}]);


app.controller('editRoleController',function($scope,$modalInstance,data,toaster,Restangular,$q){

	$scope.submitTried = false;
	
	$scope.title = "新增角色";
	$scope.editMode=false;
	var originalName = "";
	
	if (data.role) {	
		$scope.title = "修改角色";
		$scope.editMode=true;
		$scope.role = data.role;
		originalName = data.role.rolename;
	} else {
		$scope.role = {};
	}
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	};
	
	$scope.save = function(isNotValid){
		//$scope.user.account_pwd = $scope.password;
		$scope.submitTried = true;
		if (isNotValid) {
			toaster.pop('warning', '', '页面输入项有非法数据存在，请按提示修改后再提交！');
		} else {
		
			var promises = [];
			
			if (!$scope.editMode) {
				promises.push(Restangular.one('system/roles/existcode').get({roleCode:$scope.role.rolecode}));
				promises.push(Restangular.one('system/roles/existname').get({roleName:$scope.role.rolename}));
			} else {
				if (originalName != $scope.role.rolename) {
					promises.push(Restangular.one('system/roles/existname').get({roleName:$scope.role.rolename}));
				}
			}
			
			var hasDuplicate = false;
			var codes = [];
			var names = [];
			$q.all(promises).then(function(ret){
				if (ret.length == 2) {
					codes = ret[0];  //code checked
					names = ret[1];  //name checked
				} else if (ret.length == 1) {
					names = ret[0];  //only name is checked
				}
				if (codes.length > 0) {
					toaster.pop('error', '数据重复', '该角色代码已存在！');
					hasDuplicate = true;
				}
				if (names.length > 0) {
					toaster.pop('error', '数据重复', '该角色名称已存在！');
					hasDuplicate = true;
				}
				if (!hasDuplicate) {
					$modalInstance.close($scope.role);
				}
			}, function (err) {
				
			});
			
		}	
	};
});

app.controller('RoleConfigController', ['$scope', '$modalInstance', 'data', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
                              	function($scope, $modalInstance, data, Restangular, NgTableParams, dialogs, toaster ) {

	var baseAuths = Restangular.one('system/auths');

	$scope.authGroup = "";
	$scope.authName = "";
	$scope.role = data.role;
	$scope.modified = false;
   
    $scope.checkboxes = {
      checked: false,
      items: {}
    };
	
	var simpleList = [];
	
	var search = function () {
	
		baseAuths.get({authGroup:$scope.authGroup, authName:$scope.authName}).then(function(auths) {
			//$scope.userList = _.castArray(users);
    		if (auths.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
    		simpleList = auths;
			$scope.tableParamsAuthList = new NgTableParams({count: 5}, { counts: [5, 10, 15], dataset: auths});
			
			$scope.checkboxes = {
		      checked: false,
		      items: {}
		    };
			
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	
	};
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	};
	
	$scope.save = function(){
//		 var msg = "您确认要对此角色做如上权限配置吗？";
//		if ($scope.role.permissions.length == 0) {
//			var msg = "没有选择任何的权限，您确认要清空该角色的权限配置吗？";
//			var dlg = dialogs.confirm("确认",msg, {'size': 'sm','backdrop':'static'});
//			dlg.result.then(function(btn){
//				$modalInstance.close($scope.role.permissions);
//			});
//		} else {
//			$modalInstance.close($scope.role.permissions);
//		}
		if (!$scope.modified) {
			toaster.pop('info', '', '' + '您没有做任何修改，请添加或移除权限后再提交或则点击取消按钮关闭本界面。');
		} else {
			$modalInstance.close($scope.role.permissions);
		}
	};

	$scope.remove = function(idx){
		$scope.modified = true;
		$scope.role.permissions.splice(idx,1);
	};
	
	$scope.append = function(){
		var ids = _.map($scope.role.permissions,"id");
		_.forEach($scope.checkboxes.items, function(value,key) {
			if (value == true) {
				//console.log(key);
				var idKey = Number(key);
				var item = _.find(simpleList, {id:Number(idKey)})
				if (_.includes(ids, idKey)) {
					toaster.pop('info', '', '权限 ' + item.name+ ' 已经配置过了！');
				} else {
					//delete item['$$hashKey'];
					if ($scope.role.permissions == undefined) {$scope.role.permissions = [];}
					$scope.role.permissions.push(item);
					$scope.modified = true;
				}
			}
		});
		
		$scope.checkboxes = {
	      checked: false,
	      items: {}
	    };
		
	};

    // watch for check all checkbox
    $scope.$watch(function() {
      return $scope.checkboxes.checked;
    }, function(value) {
      angular.forEach(simpleList, function(item) {
        $scope.checkboxes.items[item.id] = value;
      });
    });
    
    // watch for data checkboxes
    $scope.$watch(function() {
      return $scope.checkboxes.items;
    }, function(values) {
      var checked = 0, unchecked = 0,
          total = simpleList.length;
      angular.forEach(simpleList, function(item) {
        checked   +=  ($scope.checkboxes.items[item.id]) || 0;
        unchecked += (!$scope.checkboxes.items[item.id]) || 0;
      });
      if ((unchecked == 0) || (checked == 0)) {
    	  if (total == 0) {
    		  $scope.checkboxes.checked = false;
    	  } else {
    		  $scope.checkboxes.checked = (checked == total);
    	  }
      }
      // grayed checkbox
      angular.element(document.getElementsByClassName("select-all")[0]).prop("indeterminate", (checked != 0 && unchecked != 0));
    }, true);
    
    search();  
    $scope.search = search;
    
}]);
