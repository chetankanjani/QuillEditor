var app=angular.module('myApp',[]);

/**********************************************Quill Editor Configuration*********************************************/
var toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
  ['blockquote', 'code-block'],

  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript        
  [{ 'direction': 'rtl' }],                         // text direction

  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
  [{ 'font': [] }],
  [{ 'align': [] }],
  ['image','video'],

  ['clean']                                         // remove formatting button
];

var editor = new Quill('.editor-custom',{
 	modules:{
 		toolbar:{
 			container:toolbarOptions
 		}
 	},
 	theme:'snow',
 	placeholder:'default text'
 });

/*******************************************************************************************************************/

function quillGetHTML(inputDelta) {
    var tempCont = document.createElement("div");
    (new Quill(tempCont)).setContents(inputDelta);
    return tempCont.getElementsByClassName("ql-editor")[0].innerHTML;
}


app.controller('editorController',['$scope','$http','$window','$q',function($scope,$http,$window,$q){

	// for loading the editor with the last post 
	$http.get('/getLastPost').success(function(data){
		editor.clipboard.dangerouslyPasteHTML(data[0].editorContent);
	});

	var getImageUrl=function(imageData){
		return $http.post("/getImageUrl").success(function(url){                                 //pass the image data in this post request
			
		});
	}	

	var convertDelta=function(delta){
		var requests=[];
		angular.forEach(delta.ops,function(value,index){
			if(value.insert){
				if(value.insert.image){
					var deferred = $q.defer();
					requests.push(deferred.promise);
					getImageUrl(value.insert.image)
					.then(function(url){
						deferred.resolve();
						value.insert.image=url.data;
					})
				}
			}
		})
		$q.all(requests).then(function(){
			$scope.data1.editorContent=quillGetHTML(delta);
	   		$http.post('newPost',$scope.data1).success(function(data){
				$window.editor.setContents([]);
		   	});
		})
	}

	$scope.submit=function(){
       	$scope.data1={};
		$scope.data1.editorContent=$window.editor.container.firstChild.innerHTML;
		var delta=$window.editor.getContents();
		convertDelta(delta);
	}
}]);

app.controller('viewController',['$http','$scope','$sce',function($http,$scope,$sce){
	$http.get('/getPosts').success(function(data){
		$scope.posts=[];
		angular.forEach(data,function(value,index){
			$scope.posts.push($sce.trustAsHtml(value.editorContent));
        })
	});
}]);