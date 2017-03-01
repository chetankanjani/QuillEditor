var app=angular.module('myApp',[]);

app.controller('viewController',['$http','$scope','$sce',function($http,$scope,$sce){
	$http.get('/getPosts').success(function(data){
		$scope.posts=[];
		angular.forEach(data,function(value,index){
			$scope.posts.push($sce.trustAsHtml(value.editorContent));
        })
	});
}]);