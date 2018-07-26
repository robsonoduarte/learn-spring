var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http', '$location', function($scope,$http,$location){

	$scope.modal = {message : "", btn:"Novo", action : "/cadastros/cliente/novo"}


	$scope.dest = {}
	$scope.dest.id = angular.element('#id').val()
	$scope.dest.cnpj = angular.element('#cnpj').val()
	$scope.dest.razaoSocial = angular.element('#razaoSocial').val()
	$scope.dest.cpf = angular.element('#cpf').val()
	$scope.dest.nome = angular.element('#nome').val()
	$scope.dest.email = angular.element('#email').val()
	$scope.dest.ie = angular.element('#ie').val()
	$scope.dest.im = angular.element('#im').val()
	$scope.dest.nomeFantasia = angular.element('#nomeFantasia').val()

	$scope.dest.address = {}
	$scope.dest.address.logradouro = angular.element('#logradouro').val()
	$scope.dest.address.numero = angular.element('#numero').val()
	$scope.dest.address.complemento = angular.element('#complemento').val()
	$scope.dest.address.bairro = angular.element('#bairro').val()
	$scope.dest.address.cep = angular.element('#cep').val()
	$scope.dest.address.municipio = angular.element('#municipio').val()
	$scope.dest.address.uf = angular.element('#uf').val()
	$scope.dest.address.codIbge = angular.element('#codIbge').val()
	$scope.dest.address.fone = angular.element('#fone').val()



	$scope.search = function() {

		var valid = $("#document" + $scope.type).parsley().validate()

		if (valid) {
			showLoad()
			$http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/cliente/search/'+ $scope.type.toLowerCase() + '/' + $scope.document)
			.success(function(data){
				$scope.dest = data
				$scope.showForm = $scope.type
				hideLoad()
				disableFields()
			})
		}
	}




	$scope.save = function() {

		var valid = $("#form" + $scope.type).parsley().validate()

		if (valid) {
			setDocument()
			$http.post('https://' + $location.host() + ":" + $location.port() + '/cadastros/cliente/save/'+ $scope.type.toLowerCase() , $scope.dest)
		    	.then(function success(result){
		    		$scope.modal.message = result.data.msg;
		    		createModalForm({
	 					  id : 'modal-cadastro',
					      title: 'Cadastro Cliente'
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
				? $scope.dest.cpf = $scope.document
				: $scope.dest.cnpj = $scope.document
		}

	
	
	
	// DISABLE FIELDS ONLY IT IS EMPTY
	function disableFields() {
		$scope.disableCodIbge = $scope.dest.address.codIbge != null 
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
