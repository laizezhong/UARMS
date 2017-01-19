app.controller('ImageUploadController',function($scope,$modalInstance,data,FileUploader,toaster){

	var rootUrl = "";
	var idx = document.URL.indexOf('#');
	if (idx < 0) {
		rootUrl = document.URL.replace("?","");
	} else {
		rootUrl = document.URL.substring(0,idx).replace("?","");
	}
	
	var config = data.config;
	$scope.title = config.title;
	
    var uploader = $scope.uploader = new FileUploader({
       url: rootUrl + "uploadFile?path=" + config.path,
       headers:{}
    });

    // FILTERS
    uploader.filters.push({
        name: 'imageFilter',
        fn: function(item /*{File|FileLikeObject}*/, options) {
            var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    });
		
	$scope.cancel = function(){
		$modalInstance.dismiss('Cancelled');
	};
	
	$scope.save = function(){
		var file = uploader.queue[0];
		file.upload();
	};

	$scope.remove = function(item){
		item.remove();
		$("#fileSelect").val("");
		console.log(form);
	};
	
    uploader.onSuccessItem = function(fileItem, response, status, headers) {
        //console.info('onSuccessItem', fileItem, response, status, headers);
    	if (response.result == "EMPTY_FILE") {
    		toaster.pop('warning', '', '所选择文件为空文件！');
    	} else if (response.result == "FILE_EXISTED") {
    		toaster.pop('warning', '', '此文件已经存在，请确认并选择其它文件上传！');
    	} else {
    		$modalInstance.close(response);
    	}
    };
    
    uploader.onErrorItem = function(fileItem, response, status, headers) {
        //console.info('onErrorItem', fileItem, response, status, headers);
        $modalInstance.close(response);
    };
	
});

