var clickNFe = angular.module("clickNFe",  ['angularUtils.directives.dirPagination']);


clickNFe.controller("mainController",  ['$scope', '$http','$location', function($scope,$http,$location){

	$scope.load =  function() {
		showLoad()
		$http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/emitente/list')
		.success(function(data){
			$scope.emits = data
			hideLoad()
		})
	}

	$scope.upload = function(name) {
		$scope.modal = {btn:"Salvar", action : "/cadastros/upload/" + name}
		createModalForm({
			  id : 'modal-upload',
		      title: 'Cadastro Logo'
		 });
}
	
	

	
	$scope.load()
	


}]);


clickNFe.directive('onErrorSrc', function() {
    return {
        link: function(scope, element, attrs) {
          element.bind('error', function() {
            if (attrs.src != attrs.onErrorSrc) {
              attrs.$set('src', attrs.onErrorSrc);
            }
          });
        }
    }
});


$('.btn-update').on('click', function() {
	  $('.arquivo').trigger('click');
	});

	$('.arquivo').on('change', function() {
	  var fileName = $(this)[0].files[0].name;
	  $('#file').val(fileName);
	});