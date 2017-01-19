app.controller('FileUploadController', ['$state', '$scope', 'Restangular' , 'dialogs', 'toaster', 
	function($state, $scope, Restangular, dialogs, toaster) {

	$scope.fileURL = null;
	
	$scope.upload = function() {
		
		var config = {title:"用户头像上传", path:"/test/"};
		
		var dlg = dialogs.create('views/tpl/common/imageUpload.html','ImageUploadController',{config:config},{size:'md','backdrop':'static'});
		dlg.result.then(function(result){
			//console.log(result);
			if (result.status == "SUCCESS"){	
				toaster.pop('success', '', '上传用户头像成功！');
				var fileURL = "upload" + config.path + result.result;
				console.log("FileName: " + fileURL);
				$scope.fileURL = fileURL;
				//TODO call other function for DB updating to save the file URL below
			} else {
				toaster.pop('error', '', '上传用户头像失败！');
			}

		},function(){
			console.log("Cancelled");
		});
		
	}
	
}]);
