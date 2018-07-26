var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http', '$location', function($scope,$http,$location){

	$scope.modal = {message : "", btn:"Novo", action : "/cadastros/transportadora/novo"}


	$scope.transp = {}
	$scope.transp.id = angular.element('#id').val()
	$scope.transp.cnpj = angular.element('#cnpj').val()
	$scope.transp.razaoSocial = angular.element('#razaoSocial').val()
	$scope.transp.cpf = angular.element('#cpf').val()
	$scope.transp.nome = angular.element('#nome').val()
	$scope.transp.ie = angular.element('#ie').val()


	$scope.transp.address = {}
	$scope.transp.address.logradouro = angular.element('#logradouro').val()
	$scope.transp.address.municipio = angular.element('#municipio').val()
	$scope.transp.address.uf = angular.element('#uf').val()




	$scope.search = function() {

		var valid = $("#document" + $scope.type).parsley().validate()

		if (valid) {
			showLoad()
			$http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/transportadora/search/'+ $scope.type.toLowerCase() + '/' + $scope.document)
			.success(function(data){
				$scope.transp = data
				$scope.showForm = $scope.type
				hideLoad()
			})
		}
	}




	$scope.save = function() {

		var valid = $("#form" + $scope.type).parsley().validate()

		if (valid) {
			setDocument()
			$http.post('https://' + $location.host() + ":" + $location.port() + '/cadastros/transportadora/save/'+ $scope.type.toLowerCase() , $scope.transp)
		    	.then(function success(result){
		    		$scope.modal.message = result.data.msg;
		    		createModalForm({
	 					  id : 'modal-cadastro',
					      title: 'Cadastro Transportadora'
					 });
	    	});
		}
	}




	$scope.reset = function() {
		$scope.showForm = ''
	    $scope.document = ''
	    $('form').each(function(){
	    	$(this).parsley().reset()
	    })
	}




	$scope.view = function() {
		$scope.showForm = angular.element('#fisica').val() != ''
				? angular.element('#fisica').val()
			    : angular.element('#juridica').val()
	}

	$scope.view()



	function setDocument() {
		  $scope.type == 'F'
				? $scope.transp.cpf = $scope.document
				: $scope.transp.cnpj = $scope.document
		}


}]);



window.Parsley.addValidator('cpf', {
	  validateString: function(value) {
	    return validateCPF(value);
	  },
	  messages: {
	    en: 'Informe um CPF Válido',
	  }
});


window.Parsley.addValidator('cnpj', {
	  validateString: function(value) {
	    return validateCNPJ(value);
	  },
	  messages: {
	    en: 'Informe um CNPJ Válido',
	  }
});
