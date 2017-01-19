// config

var rootUrl = "";
var idx = document.URL.indexOf('#');
if (idx < 0) {
	rootUrl = document.URL.replace("?","");
} else {
	rootUrl = document.URL.substring(0,idx).replace("?","");
}

var app =  
angular.module('app')
  .config(
    [        '$controllerProvider', '$compileProvider', '$filterProvider', '$provide', 
    function ($controllerProvider,   $compileProvider,   $filterProvider,   $provide ) {
        
        // lazy controller, directive and service
        app.controller = $controllerProvider.register;
        app.directive  = $compileProvider.directive;
        app.filter     = $filterProvider.register;
        app.factory    = $provide.factory;
        app.service    = $provide.service;
        app.constant   = $provide.constant;
        app.value      = $provide.value;
    }
  ])
  .config(function(RestangularProvider) {
	  
	//RestangularProvider.setBaseUrl('http://localhost:8080/bdp');
	console.log("rootURL: " + rootUrl);
	RestangularProvider.setBaseUrl(rootUrl);
	RestangularProvider.setResponseExtractor(function(response, operation) {
		if (response.status === undefined) {
			//location.reload();
			console.log("session timeout");
		} else if (response.status == "SUCCESS"){
			return response.result;
		} else if (response.status == "PDF_CREATED"){
			console.log("pdf file created.");
			return response;
		} else {
			console.log(response.status + "错误 !", response.statusText);
		}	
    });
	//RestangularProvider.setRequestSuffix('.json');
	RestangularProvider.setDefaultHeaders({'content-type': 'application/json; charset=utf-8'});
  })
  .config(function(storeProvider) {
    // Store defaults to localStorage but we can set sessionStorage or cookieStorage.
    storeProvider.setStore('sessionStorage');
  })
  // add for shiro
  .config([ 'angularShiroConfigProvider',
		function(config) {
			//config.setFilter('/**/*','authc');
			config.setLoginPath(rootUrl + 'api/authenticate');
			//config.setLoginPath("api/test");

	} ])
	.config(function ($idleProvider, $keepaliveProvider) {
        // The keepalive ping will be sent every 30 seconds.
        $keepaliveProvider.setInterval(30);
        // We will ping the following address to keep the session alive.
        // $keepaliveProvider.http(rootUrl + '/keepalive');

        // Set the idle and timeout timers in seconds.
        // User is considered idle if AFK for 120 minutes
        $idleProvider.setIdleTime(7200);
        // User will timeout at the end of 15 seconds after considered idle.
        $idleProvider.setTimeoutTime(15); 
        // The $idle service will ping the specified URL (see $keepaliveProvider.http) to keep the session alive.
        $idleProvider.keepalive(true);

    })	
  .config(['$translateProvider', function($translateProvider){
    // Register a loader for the static files
    // So, the module will search missing translation tables under the specified urls.
    // Those urls are [prefix][langKey][suffix].
    $translateProvider.useStaticFilesLoader({
      prefix: 'views/l10n/',
      suffix: '.js'
    });
    // Tell the module what language to use by default
    $translateProvider.preferredLanguage('cn');
    // Tell the module to store the language in the local storage
    $translateProvider.useLocalStorage();
    
  }]).run(function ($idle, Restangular, toaster) {
      $idle.watch();
      
      Restangular.setErrorInterceptor(function(response, deferred, responseHandler) {
	    if(response.status == -1) {
	    	toaster.pop('error', '', '服务器通讯中断，请检查网络！！！');
	        return false; // error handled
	    } else {
	    	toaster.pop('error', response.status + "错误 !", response.statusText);
	    	return false; // error handled
	    }
	
	    return true; // error not handled
	});
      
  })
//  }]);