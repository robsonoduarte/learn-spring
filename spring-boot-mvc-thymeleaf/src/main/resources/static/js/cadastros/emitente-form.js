var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http','$location', function($scope,$http,$location){

	$scope.modal = {message : "", btn:"Novo", action : "/cadastros/emitente/novo"}

	$scope.emit = {}
	$scope.emit.id = angular.element('#id').val()
	$scope.emit.cnpj = angular.element('#cnpj').val()
	$scope.emit.razaoSocial = angular.element('#razaoSocial').val()
	$scope.emit.email = angular.element('#email').val()
	$scope.emit.ie = angular.element('#ie').val()
	$scope.emit.iest = angular.element('#iest').val()
	$scope.emit.im = angular.element('#im').val()
	$scope.emit.nomeFantasia = angular.element('#nomeFantasia').val()
	$scope.emit.crt = angular.element('#crt').val()
	$scope.emit.cnae = angular.element('#cnae').val()

	$scope.emit.address = {}
	$scope.emit.address.logradouro = angular.element('#logradouro').val()
	$scope.emit.address.numero = angular.element('#numero').val()
	$scope.emit.address.complemento = angular.element('#complemento').val()
	$scope.emit.address.bairro = angular.element('#bairro').val()
	$scope.emit.address.cep = angular.element('#cep').val()
	$scope.emit.address.municipio = angular.element('#municipio').val()
	$scope.emit.address.uf = angular.element('#uf').val()
	$scope.emit.address.codIbge = angular.element('#codIbge').val()
	$scope.emit.address.fone = angular.element('#fone').val()





	// DISABLE FIELDS ONLY IT IS EMPTY
	function disableFields() {
		$scope.disableIE = $scope.emit.ie != null
		$scope.disableCodIbge = $scope.emit.address.codIbge != null
	}






	$scope.search = function() {
    	$('#formEmit').parsley().reset()

		var valid = $("#cnpj").parsley().validate()

		if (valid) {
			showLoad()
			$http.get('https://' + $location.host() + ":" + $location.port() + '/cadastros/emitente/search/' + $scope.cnpj)
			.success(function(data){
				$scope.emit = data
				hideLoad()
				disableFields()
			})
		}
	}




	$scope.save = function() {
		var valid = $("#formEmit").parsley().validate()

		if (valid) {
			$scope.emit.cnpj = $scope.cnpj
			$http.post('https://' + $location.host() + ":" + $location.port() + '/cadastros/emitente/save/', $scope.emit)
		    	.then(function success(result){
		    		$scope.modal.message = result.data.msg;
		    		createModalForm({
	 					  id : 'modal-cadastro',
					      title: 'Cadastro Emitente'
					 });
	    	});
		}
	}
	
	
	

	
	
	



}]);


window.Parsley.addValidator('cnpj', {
	  validateString: function(value) {
	    return validateCNPJ(value);
	  },
	  messages: {
	    en: 'Informe um CNPJ Válido',
	  }
});
