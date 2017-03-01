var editor = new Quill('.editor-custom',{
 	modules:{
 		toolbar:[
 			['bold', 'italic', 'underline'],
 			['video','image','code']
 		]
 	}
 	,
 	theme:'snow',
 	placeholder:'default text'
 });


//var delta = editor.getContents();
//console.log(delta);

var editorApp=angular.module('new',[]);

editorApp.controller('newcontroller',['$scope','$window',function($scope,$window){
	console.log($window.editor.getContents());
}]);

