app.controller('ChartsController', ['$window', '$state', '$scope', 'Restangular' , 'dialogs', 'toaster', 'LeasingCommon',
	function($window, $state, $scope, Restangular, dialogs, toaster, LeasingCommon) {
	
	$scope.legend = ["Berlin2", "London",'New York','Tokyo'];  
	$scope.item = ['Jan', 'Feb', 'Mar', 'Apr', 'Mar', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];  
	$scope.data = [  
		[1, 1, 3, 7, 13, 16, 18, 16, 15, 9, 4, 9], //Berlin  
		[0, 1, 4, 7, 12, 15, 16, 15, 15, 10, 6, 5], //London  
		[4, 4, 5, 10, 16, 22, 25, 24, 20, 14, 9, 3],    //New York  
		[7, 6, 8, 14, 17, 22, 25, 27, 24, 17, 14, 17]   //Tokyo  
	]; 
	
	$scope.mapData = [  
		{name: '北京',value: Math.round(Math.random()*1000)},
		{name: '天津',value: Math.round(Math.random()*1000)},
		{name: '上海',value: Math.round(Math.random()*1000)},
		{name: '重庆',value: Math.round(Math.random()*1000)},
		{name: '河北',value: Math.round(Math.random()*1000)},
		{name: '河南',value: Math.round(Math.random()*1000)},
		{name: '云南',value: Math.round(Math.random()*1000)},
		{name: '辽宁',value: Math.round(Math.random()*1000)},
		{name: '黑龙江',value: Math.round(Math.random()*1000)},
		{name: '湖南',value: Math.round(Math.random()*1000)},
		{name: '安徽',value: Math.round(Math.random()*1000)},
		{name: '山东',value: Math.round(Math.random()*1000)},
		{name: '新疆',value: Math.round(Math.random()*1000)},
		{name: '江苏',value: Math.round(Math.random()*1000)},
		{name: '浙江',value: Math.round(Math.random()*1000)},
		{name: '江西',value: Math.round(Math.random()*1000)},
		{name: '湖北',value: Math.round(Math.random()*1000)},
		{name: '广西',value: Math.round(Math.random()*1000)},
		{name: '甘肃',value: Math.round(Math.random()*1000)},
		{name: '山西',value: Math.round(Math.random()*1000)},
		{name: '内蒙古',value: Math.round(Math.random()*1000)},
		{name: '陕西',value: Math.round(Math.random()*1000)},
		{name: '吉林',value: Math.round(Math.random()*1000)},
		{name: '福建',value: Math.round(Math.random()*1000)},
		{name: '贵州',value: Math.round(Math.random()*1000)},
		{name: '广东',value: Math.round(Math.random()*1000)},
		{name: '青海',value: Math.round(Math.random()*1000)},
		{name: '西藏',value: Math.round(Math.random()*1000)},
		{name: '四川',value: Math.round(Math.random()*1000)},
		{name: '宁夏',value: Math.round(Math.random()*1000)},
		{name: '海南',value: Math.round(Math.random()*1000)},
		{name: '台湾',value: Math.round(Math.random()*1000)},
		{name: '香港',value: Math.round(Math.random()*1000)},
		{name: '澳门',value: Math.round(Math.random()*1000)}
	];  
	
}]);
