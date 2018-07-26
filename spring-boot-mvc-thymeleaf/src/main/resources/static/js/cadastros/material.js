var clickNFe = angular.module("clickNFe", ['angularUtils.directives.dirPagination']);



clickNFe.controller("mainController", ['$scope', '$http', '$location', function($scope,$http,$location){


	$scope.load =  function() {
		showLoad()
	     $http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/material/list')
		.success(function(data){
			$scope.materials = data
			hideLoad()
		})
	}

	$scope.load()


}]);