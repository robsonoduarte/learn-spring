var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http','$location', function($scope,$http,$location){


	$scope.material = {}
	$scope.material.id = angular.element('#id').val()
	$scope.material.origem = angular.element('#origem').val()
	$scope.material.ind = angular.element('#ind').val()
	$scope.material.codigo = angular.element('#codigo').val()
	$scope.material.valor = angular.element('#valor').val()
	$scope.material.cest = angular.element('#cest').val()
	$scope.material.ncm = angular.element('#ncm').val()
	$scope.material.unidade = angular.element('#unidade').val()
	$scope.material.descricao = angular.element('#descricao').val()
	$scope.material.cean = angular.element('#cean').val()
	$scope.material.ceanTrib = angular.element('#ceanTrib').val()



	$scope.save = function() {
		var valid = $("#material-form").parsley().validate();

		if(valid){
			//unmasked value
			$scope.material.valor = $('.maskMoney').maskMoney('unmasked')[0]

			//clear json
			$scope.material = angular.forEach($scope.material, function(key,value){
			    if(key==""||key==null || key <= 0.0){
			        delete $scope.material[value];
			    }
			})

			$http.post('https://' + $location.host() + ":" + $location.port() + '/cadastros/material/save/', $scope.material)
	    	.then(function success(result){
				createModalForm({
 					  id : 'modal-cadastro',
				      title: 'Cadastro Material'
				 });

	    	});
		}
	}



	$scope.modal = function() {
		angular.element('#codigo').val() == ""
			? $scope.modal = {message : "Cadastro realizado com sucesso!", btn:"Novo", action : "/cadastros/material/novo"}
			: $scope.modal = {message : "Cadastro atualizado com sucesso!", btn:"Voltar", action : "/cadastros/material"}
	}


	$scope.modal()

}]);






$(function(){
	$('.maskMoney').maskMoney({showSymbol: false, decimal: ",", thousands: "."});
	// apply mask if the valor is gt 0
	if($("#valor").val() != ""){
		$('.maskMoney').maskMoney('mask')
	}
});

