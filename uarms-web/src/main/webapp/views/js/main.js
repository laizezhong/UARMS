'use strict';

/* Controllers */

angular.module('app')
  .controller('AppCtrl', ['$idle','$state','$scope', '$translate', '$localStorage', '$window', 'subject', 'usernamePasswordToken','dialogs','toaster','Restangular',
    function(             $idle, $state, $scope,   $translate,   $localStorage,   $window , subject, usernamePasswordToken, dialogs, toaster,Restangular) {
      
	  $scope.$on('$userIdle', function () {
          //alert('You appear to be idle');
		  toaster.pop('warning', '警告', 'Session将在15秒后过期！！');
      });
	  
	  $scope.$on('$userTimeout', function () {
		  location.href="logout";
/*			var dlg=dialogs.notify("通知","Session已经过期,请重新登录！", {'size': 'sm','backdrop':'static'});
			dlg.result.then(function(btn){
				location.href="logout";
			});*/
      });
	  
	  // add 'ie' classes to html
      var isIE = !!navigator.userAgent.match(/MSIE/i);
      isIE && angular.element($window.document.body).addClass('ie');
      isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');

      // config
      $scope.app = {
        name: 'admin',
        version: '0.0.1',
        // for chart colors
        color: {
          primary: '#7266ba',
          info:    '#23b7e5',
          success: '#27c24c',
          warning: '#fad733',
          danger:  '#f05050',
          light:   '#e8eff0',
          dark:    '#3a3f51',
          black:   '#1c2b36'
        },
        settings: {
          themeID: 1,
          navbarHeaderColor: 'bg-black',
          navbarCollapseColor: 'bg-white-only',
          asideColor: 'bg-black',
          headerFixed: true,
          asideFixed: false,
          asideFolded: false,
          asideDock: false,
          container: false
        }
      }

      // save settings to local storage
      if ( angular.isDefined($localStorage.settings) ) {
        $scope.app.settings = $localStorage.settings;
      } else {
        $localStorage.settings = $scope.app.settings;
      }
      $scope.$watch('app.settings', function(){
        if( $scope.app.settings.asideDock  &&  $scope.app.settings.asideFixed ){
          // aside dock and fixed must set the header fixed.
          $scope.app.settings.headerFixed = true;
        }
        // save to local storage
        $localStorage.settings = $scope.app.settings;
      }, true);

      /*
      // angular translate
      $scope.lang = { isopen: false };
//      $scope.langs = {zh_CN:'简体中文', en:'English', de_DE:'German', it_IT:'Italian'};
      $scope.langs = {zh_CN:'简体中文'};
    //  $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "English";
      $scope.selectLang = $scope.langs['zh_CN'];
      $scope.setLang = function(langKey, $event) {
        // set the current lang
        $scope.selectLang = $scope.langs[langKey];
        // You can change the language during runtime
        $translate.use(langKey);
        $scope.lang.isopen = !$scope.lang.isopen;
      };
*/
      
      function isSmartDevice( $window )
      {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
      }
      
      
      // Add on 0819 for AngularShiro Start
		$scope.errauthc = false;
		
		$scope.editUser = function(){
			//alert($scope.loginUser.realname);
			var dlg = dialogs.create('views/tpl/system/editUser.html','editUserController',{"user":$scope.loginUser,"pwdMode":true },{size:'md','backdrop':'static'});
			dlg.result.then(function(updUser){
				Restangular.one('system/users',updUser.id).customPUT(updUser).then(function(res) {
					//toaster.pop('success', '', '该用户信息已经被更新,请重新登录！');
					var dlg=dialogs.notify("通知","该用户信息已经被更新,请重新登录！", {'size': 'sm','backdrop':'static'});
					dlg.result.then(function(btn){
						location.href="logout";
					});
				}, function(response) {
					console.log("Error with status code", response.status);
				});
				
			},function(){

			});
			
		};

		var token = usernamePasswordToken;
		token.username="admin"; //username and password for testing
		token.password="admin"; 

		var getAuth = function() {
			subject.login(token).then(function(ret) {
				//console.log("Auth:" + ret);
				$scope.loginUser = ret.user;
				if (ret.stillInitPwd == true) {
					toaster.pop('warning', '初始密码尚未更改', '为确保帐户安全，请点击用户名更改您的密码！');
				}
			}, function(data) {
				//$scope.errauthc = true;
			});
		};
		
		getAuth();
	// Add on 0819 for AngularShiro End
		
  }]);

angular.module('app')
   .controller('editUserController',function($scope,$modalInstance,data,toaster,Restangular){

	$scope.submitTried = false;
	
	$scope.title = "新增用户";
	$scope.pwdTitle ="初始密码";
	$scope.editMode=false;
	$scope.pwdMode = data.pwdMode;
	
	$scope.companies = "";
	
	if (data.user) {	
		$scope.title = "修改用户";
		$scope.pwdTitle ="当前密码";
		$scope.editMode=true;
		$scope.user = data.user;
		$scope.user.password = "";
	} else {
		$scope.user = {};
		$scope.user.password = "123456";
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
			
			$scope.user.password
			if ($scope.editMode == false) {
				Restangular.one('system/users/exist').get({userName:$scope.user.username}).then(function(ret) {
					
					if (ret.length > 0) {
						toaster.pop('error', '数据重复', '该用户登录名已存在！');
					} else {
						$modalInstance.close($scope.user);
					}
				}, function(response) {
					console.log("Error with status code", response.status);
				});
			} else {
				$scope.user.password = $scope.password;
				$modalInstance.close($scope.user);
			}
		}	
	};
	
});