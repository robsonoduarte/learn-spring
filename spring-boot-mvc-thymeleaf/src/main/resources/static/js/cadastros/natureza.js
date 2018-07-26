var clickNFe = angular.module("clickNFe", ['angularUtils.directives.dirPagination']);



clickNFe.controller("mainController", ['$scope', '$http', '$location', function($scope,$http,$location){


	$scope.load =  function() {
		showLoad()
		$http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/natureza/list')
		.success(function(data){
			$scope.natops = data
			hideLoad()
		})
	}

	$scope.load()


}]);