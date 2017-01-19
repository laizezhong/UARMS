app.controller('LoginCtrl', ['$scope', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
	function($scope, Restangular, NgTableParams, dialogs, toaster) {

	$scope.genders = {1:'男', 2:'女'};
	$scope.userName = "";
	
	Restangular.one('master/companies').get("").then(function(result) {
		var companies = {};
		_(result).forEach(function (company){
			companies[company.id] = company.name;
		})
		$scope.companies = companies;
	}, function(errResponse) {
		console.log("Error with status code", errResponse.status);
	});
	
	var search = function () {
		var promises = [];
		//promises.push(Restangular.one('system/userroles').get({userId:""}));
		Restangular.one('system/users').get({userName:$scope.userName}).then(function(users) {
			//$scope.userList = _.castArray(users);
    		if (users.length < 1) {
    			toaster.pop('warning', '', '没有找到符合条件的数据！');
    		}
			$scope.tableParamsUserList = new NgTableParams({count: 10}, { counts: [10, 15, 20], dataset: users});
		}, function(response) {
			console.log("Error with status code", response.status);
		});
	
	};
	
    $scope.addUser = function () {

		var dlg = dialogs.create('views/tpl/system/editUser.html','editUserController',{},{size:'md','backdrop':'static'});
		dlg.result.then(function(user){
			Restangular.all('system/users').post(user).then(function(res) {
				//$scope.tableParamsUserList.data.push(res);
				search();
				toaster.pop('success', '', '已经成功追加新用户：' + user.realname);
			}, function(response) {
				console.log("Error with status code", response.status);
			});

		},function(){

		});
    };

	$scope.editUser = function (user) {
		var dlg = dialogs.create('views/tpl/system/editUser.html','editUserController',{"user":user, "pwdMode":false},{size:'md','backdrop':'static'});
		dlg.result.then(function(updUser){
			//var newInfo = {fullname:updUser.fullname, title:updUser.title, phone:updUser.phone};
			updUser.password = null;  //只允许用户自己可以修改密码
			Restangular.one('system/users',updUser.id).customPUT(updUser).then(function(res) {
				var userList = $scope.tableParamsUserList.data;
				var index = _.findIndex(userList, ['id', res.id]);
				$scope.tableParamsUserList.data[index] = res;
				toaster.pop('success', '', '该用户信息已经被成功更新！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
			
		},function(){

		});
    };
	
    $scope.lockUser = function (userId) {
		var dlg = dialogs.confirm("锁定确认","您确认要锁定此用户吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			
			Restangular.one('system/users',userId).remove({func:"lock"}).then(function(user) {
				var userList = $scope.tableParamsUserList.data;
				var index = _.findIndex(userList, ['id', userId]);
				//$scope.tableParamsUserList.data.splice(index,1);
				$scope.tableParamsUserList.data[index].activestatus = 0;
				toaster.pop('success', '', '该用户已经被锁定！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
		},function(btn){
			console.info("selected No");
		});
    };

    $scope.unlockUser = function (userId) {
		var dlg = dialogs.confirm("解锁确认","您确认要解锁此用户吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			
			Restangular.one('system/users',userId).remove({func:"unlock"}).then(function(user) {
				var userList = $scope.tableParamsUserList.data;
				var index = _.findIndex(userList, ['id', userId]);
				//$scope.tableParamsUserList.data.splice(index,1);
				$scope.tableParamsUserList.data[index].activestatus = 1;
				toaster.pop('success', '', '该用户已经成功解锁！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
		},function(btn){
			console.info("selected No");
		});
    };
    
    $scope.resetPwd = function (userId) {
		var dlg = dialogs.confirm("密码重置确认","您确认要重置此用户的密码吗？", {'size': 'sm','backdrop':'static'});
		dlg.result.then(function(btn){
			
			Restangular.one('system/users',userId).remove({func:"reset"}).then(function(user) {
				var userList = $scope.tableParamsUserList.data;
				var index = _.findIndex(userList, ['id', userId]);
				//$scope.tableParamsUserList.data.splice(index,1);
				$scope.tableParamsUserList.data[index].activestatus = 1;
				toaster.pop('success', '', '已经成功重置该用户密码！初始密码为：123456');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
		},function(btn){
			console.info("selected No");
		});
    };
    
    
	$scope.configRoles = function (user) {
		var dlg = dialogs.create('views/tpl/system/rolePopup.html','RolesConfigController',{"user":user},{size:'lg','backdrop':'static'});
		dlg.result.then(function(roles){
			// delete $$hashKey property
			_(roles).forEach(function (role){
				delete role['$$hashKey'];
				delete role['permissions'];
			});
			Restangular.one('system/userroles',user.id).customPUT(roles).then(function(res) {
				var userList = $scope.tableParamsUserList.data;
				var index = _.findIndex(userList, ['id', user.id]);
				$scope.tableParamsUserList.data[index].roles = res;
				//search();
				toaster.pop('success', '', '成功配置了该用户的角色！');
			}, function(response) {
				console.log("Error with status code", response.status);
			});
			
		},function(){

		});
    };
    
    search();
    
    $scope.search = search;
    
}]);

app.controller('RolesConfigController', ['$q', '$scope', '$modalInstance', 'data', 'Restangular' ,'ngTableParams', 'dialogs', 'toaster', 
                                      	function($q, $scope, $modalInstance, data, Restangular, NgTableParams, dialogs, toaster ) {

	$scope.user = data.user;
	$scope.user.permissions = []; // for display only
	$scope.modified = false;
	$scope.selRole = {};
 
	var roles = null;
	var auths = null
	var roleauths = null;
	
	var getPermissions = function() {
		$scope.user.permissions = [];
		_($scope.user.roles).forEach(function(role) {
			role.permissions = _.map(_.filter(roleauths, {roleId: role.id}),"permission");
			//$scope.user.permissions.push(role.permissions);
			var ids = _.map($scope.user.permissions,"id");
			_(role.permissions).forEach(function (permission) {
				if (! _.includes(ids, permission.id)) {
					$scope.user.permissions.push(permission);
				}
			});
		});
	}
	
	var search = function () {
	
		var promises = [];
		promises.push(Restangular.one('system/roles').get({roleName:""}));
		promises.push(Restangular.one('system/auths').get({authGroup:"", authName:""}));
		promises.push(Restangular.one('system/roleauths').get({roleId:""}));
		promises.push(Restangular.one('system/userroles').get({userId:data.user.id}));

		$q.all(promises).then(function(ret){
			roles = ret[0];
			auths = ret[1];
			roleauths = ret[2];
			if (ret[3] != null && ret[3].length > 0) {
				$scope.user.roles = ret[3];
			} else {
				$scope.user.roles = [];
			}
			$scope.optRoles = roles;
			if (roles.length < 1) {
				console.log('no roles found!');
			} else {
				_(roleauths).forEach(function(roleauth) {
					roleauth.permission = _.find(auths, {id: roleauth.permissionId});
				});		
			}
			getPermissions();
			
		}, function (err) {
			
		});
	};
	
	
	$scope.cancel = function(){
		$modalInstance.dismiss('Canceled');
	};
	
	$scope.save = function(){
//		 var msg = "您确认要对此用户做如上角色配置吗？";
//		if ($scope.user.roles.length == 0) {
//			var msg = "没有选择任何的角色，您确认要清空该用户的角色配置吗？";
//			var dlg = dialogs.confirm("确认",msg, {'size': 'sm','backdrop':'static'});
//			dlg.result.then(function(btn){
//				$modalInstance.close($scope.user.roles);
//			});
//		} else {
//			$modalInstance.close($scope.user.roles);
//		}
		if (!$scope.modified) {
			toaster.pop('info', '', '' + '您没有做任何修改，请添加或移除角色后再提交或则点击取消按钮关闭本界面。');
		} else {
			$modalInstance.close($scope.user.roles);
		}
	};

	$scope.remove = function(idx){
		$scope.modified = true;
		$scope.user.roles.splice(idx,1);
		getPermissions();
	};
	
	$scope.append = function(){

		var role = _.find(roles, {id:$scope.selRole.id});
		
		var ids = _.map($scope.user.roles,"id");
		if (_.includes(ids, $scope.selRole.id)) {
			toaster.pop('info', '', '角色 ' + role.rolename+ ' 已经配置过了！');
		} else {
			$scope.user.roles.push(role);
			$scope.modified = true;
			getPermissions();
		}
	};
	
    search();  
  
}]);
