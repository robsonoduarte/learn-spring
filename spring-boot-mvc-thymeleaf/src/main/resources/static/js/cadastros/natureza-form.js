var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http','$location', function($scope,$http,$location){

	$scope.tax = 'ICMS' // Default in load
	$scope.taxrule = {}
	$scope.natop = natop()



	$scope.save = function() {
		var valid = $("#natop-form").parsley().validate()
		var has = hasRules()

		if(valid && has){
			$http.post('https://' + $location.host() + ":" + $location.port() + '/cadastros/natureza/save/', $scope.natop)
	    	.then(function success(result){
	    		createModalForm({
 					  id : 'modal-cadastro',
				      title: 'Cadastro Natureza'
				 });
	    	});
		}
		$scope.showErrorRules = !has
	}



	// return natop to new or edit
	function natop(){
		var natop = angular.element('#natop').val();

		if(natop != '')
			return JSON.parse(natop)

		return {"taxrules": [] }
	}


	// verify all tables of tax rules
	function hasRules() {
		var has = false
		$('table').each(function() {
			has = $('tr', $(this).find('tbody')).length > 1
		})
		return has
	}



	// trigger to tabs click
	$scope.setTax = function(event) {
		$scope.tax = event.target.text.trim()
	}





	// FUNCTIONS TO TAX RULES


	$scope.newRule = function () {
		$("#taxrule-form").parsley().reset()
		$scope.taxrule = {}
		createModalForm({
			id : 'modal-natureza',
			title: '<i class="glyphicon glyphicon-pencil"></i> Nova Regra'
		});
	}


	$scope.addRule = function(){
		var valid = $("#taxrule-form").parsley().validate()

		if(valid){
			$scope.taxrule.tax = $scope.tax
			$scope.natop.taxrules.push($scope.taxrule)
			$("#modal-natureza").modal('hide')
		}
	}


	$scope.saveRule = function(){
		var valid = $("#taxrule-form").parsley().validate()
		if(valid){
			$("#modal-natureza").modal('hide')
		}
	}


	$scope.removeRule = function(taxrule){
		index = $scope.natop.taxrules.indexOf(taxrule);
		$scope.natop.taxrules.splice(index,1)
	};



	$scope.editRule = function(taxrule){
		$scope.taxrule = taxrule

		if($scope.tax != 'ICMS'){
			$scope.taxrule.cfop = null // remove any values of CFOP to do not match with patter rule of parsley
		}

		$("#taxrule-form").parsley().reset()
		createModalForm({
	        id : 'modal-natureza',
	        title: '<i class="glyphicon glyphicon-pencil"></i> Editar Regra'
	    });
	}



	// MODAL
	$scope.modal = function() {
		angular.element('#natop').val() == ""
			? $scope.modal = {message : "Cadastro realizado com sucesso!", btn:"Novo", action : "/cadastros/natureza/novo"}
			: $scope.modal = {message : "Cadastro atualizado com sucesso!", btn:"Voltar", action : "/cadastros/natureza"}
	}

	$scope.modal()

}]);













$(function(){
	$('.maskMoney').maskMoney({showSymbol: false, decimal: ".", allowZero : true});

	// apply mask if the valor is different ""
	$('.masMoney').each(function () {
		if($(this).val() != ""){
			$(this).maskMoney('mask')
		}
	})

});
