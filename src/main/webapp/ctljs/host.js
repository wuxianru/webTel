function HostCtl($scope,$http){
	
	
	$scope.connect=function(){
		$http.post('http://localhost:8080/webtel/login',$scope.host).
			success(function (respdate){
				$scope.resp=respdate;
			});
	}
	
	$scope.refresh=function(){
		$http.get('http://localhost:8080/webtel/refresh').
		success(function (respdate){
			$scope.output+=respdate;
		});
	}
	
	$scope.execute=function(){
		if($scope.command.line){
			$http.post('http://localhost:8080/webtel/execute',$scope.command).
			success(function (respdate){
			});	
		}
		$scope.refresh();
		$scope.command.line='';
	}
	
	$scope.getOutput=function(){
		
	}
}