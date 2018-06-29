var clickNFe = angular.module("clickNFe", ['ui.utils.masks']);





clickNFe.controller("mainController", ['$scope', '$http', '$timeout','$window', '$location', function($scope,$http,$timeout,$window,$location){

   $scope.request = {
		operation: 'NFE',
		dets : [],
		NFref: [],
		transp : {
			vols:[]
		},
	   pag : {
		   detPag:[]
		}
	}


    // WIZARD

   	var sfw;
   	$timeout(function(){ // NOTA 1
   		sfw = $("#wizard").stepFormWizard({
   		 linkNav: 'prev',
   		 height: 'auto',
   		 nextBtn: $('<a class="next-btn sf-right sf-btn" href="#">Próximo</a>'),
   		 prevBtn: $('<a class="prev-btn sf-left sf-btn" href="#">Anterior</a>'),
   		 finishBtn: $('<a class="finish-btn sf-right sf-btn" href="#">Emitir</a>'),
   		 onNext: function(step) {
   			 if(step == 2 && $scope.natOp != undefined){
   				return validNatOp()
   			 }

   			 if(step == 4){ // step 4 = prods
   				 return validProds()
   			 }


             return validStep(step);
           },
         onFinish: function(step, wizard) {
        	var valid = validStep(step)

        	
        	if($scope.request.versao == "3.10")  $scope.request.indPag = 0;
        	if($scope.request.versao == "4.00") {
        		$scope.detPagTemp = {
        					vPag :"1.00",
        					tPag : "01"
        		}
        		
        		$scope.request.pag.detPag.push($scope.detPagTemp)
        	}
        	
        	
        	if(valid){
            	sfw.addSpinner('finish');
           		$http.post('https://' + $location.host() + ":" + $location.port() + '/+nfe/send/', $scope.request)
           		.success(function(data,status) {
           			$window.location.href = '/home'
           		})
        	}

            return false;
           }
   		});

   		$('#wizard-container').css({visibility: 'visible'}).fadeIn(300);

	  },0)








	// FUNCTIONS TO PROD

	$scope.changeProd = function () {
		$scope.prod.desc =  $("#select-prods option:selected").html();
	}


	$scope.newProd = function (){
		$("#prod-form").parsley().reset()
		$scope.prod = {}
		createModalForm({
			id : 'modal-prod',
			title: '<i class="glyphicon glyphicon-plus"></i> Novo Produto'
		});
	}

	$scope.addProd = function(){
		var valid = $("#prod-form").parsley().validate()
		if(valid){
			$scope.request.dets.push($scope.prod)
			showProds()
			reset()
			refresh()
			$scope.prod = {}
		}
	}

	$scope.saveProd = function(){
		var valid = $("#prod-form").parsley().validate()
		if(valid){
			$("#modal-prod").modal('hide')
		}
	}


	$scope.removeProd = function(prod){
		index = $scope.request.dets.indexOf(prod);
		$scope.request.dets.splice(index,1)

		refresh()

		if($scope.request.dets.length == 0){
			hideProds()
		}
	};

	$scope.editProd = function(prod){
		$scope.prod = prod
		$("#prod-form").parsley().reset()
		createModalForm({
	        id : 'modal-prod',
	        title: '<i class="glyphicon glyphicon-pencil"></i> Editar Produto'
	    });
	}

    function reset() {
		$("#prod-form").parsley().reset()
	}

	function showProds() {
		$('#table-prods').css({visibility: 'visible'}).fadeIn(300);
		$("#prod-error-msg").hide()
	}

	function hideProds() {
		$('#table-prods').css({visibility: 'hidden'}).fadeIn(300);
	}

	function validProds(){
		if(hasProds())
			return true
		 $("#prod-error-msg").show()
		 return false
	}

	function hasProds() {
		var has = false
		$('#table-prods').each(function() {
			has = $('tr', $(this).find('tbody')).length > 0
		})
		return has
	}



	// FUNCTIONS TO NAT OP AND NFREF


	$scope.changeNatOp = function() {
		showLoad()
		var id = $("#natOp :selected").val()
		$http.get('https://' + $location.host() + ":" + $location.port() + '/+nfe/natop/' + id)
        .success(function(data) {
        	$scope.natOp = data
        	$scope.request.NFref = []
        	hideLoad()
        	hideNFref()
        	refresh()
        })
	}

	$scope.newNFref = function (){
		$("#nfref-form").parsley().reset()
		createModalForm({
			id : 'modal-nfref',
			title: '<i class="glyphicon glyphicon-plus"></i> Nova NFe Referenciada'
		});
	}

	$scope.addNFref = function(){
		var valid = $("#nfref-form").parsley().validate()
		var noHas  = $scope.request.NFref.indexOf($scope.NFref) == -1
		if(valid && noHas){
			$scope.request.NFref.push($scope.NFref)
			$scope.NFref = ''
			showNFref()
			refresh()
		}
	}





	$scope.removeNFref = function(NFref){
		index = $scope.request.NFref.indexOf(NFref);
		$scope.request.NFref.splice(index,1)

		refresh()

		if($scope.request.NFref.length == 0){
			hideNFref()
		}
	};


	function validNatOp(){
		if($scope.natOp.finNFe != 4) return true
		if($scope.natOp.finNFe == 4 && $scope.request.NFref.length > 0)return true

		$("#natop-error-msg").show()
		return false
	}

	function showNFref() {
		$('#table-nfref').css({visibility: 'visible'}).fadeIn(300);
		$("#natop-error-msg").hide()
	}

	function hideNFref(){
		$('#table-nfref').css({visibility: 'hidden'}).fadeIn(300);
		$("#natop-error-msg").hide()
	}




	// FUNCTION TO VOLUME

	$scope.newVol = function (){
		$("#vol-form").parsley().reset()
		$scope.vol = {}
		createModalForm({
			id : 'modal-vol',
			title: '<i class="glyphicon glyphicon-plus"></i> Novo volume'
		});
	}



	$scope.addVol = function(){
		var valid = $("#vol-form").parsley().validate()
		if(valid){
			$scope.request.transp.vols.push($scope.vol)
			$("#vol-form").parsley().reset()
			$scope.vol = {}
			showVols()
			refresh()
		}
	}

	$scope.saveVol = function(){
		var valid = $("#vol-form").parsley().validate()
		if(valid){
			$("#modal-vol").modal('hide')
		}
	}

	$scope.removeVol = function(vol){
		index = $scope.request.transp.vols.indexOf(vol);
		$scope.request.transp.vols.splice(index,1)

		refresh()

		if($scope.request.transp.vols.length == 0){
			hideVols()
		}
	}


	$scope.editVol = function(vol){
		$scope.vol = vol
		$("#vol-form").parsley().reset()
		createModalForm({
	        id : 'modal-vol',
	        title: '<i class="glyphicon glyphicon-pencil"></i> Editar Volume'
	    });
	}


	function showVols() {
		$('#table-vols').css({visibility: 'visible'}).fadeIn(300);
	}

	function hideVols() {
		$('#table-vols').css({visibility: 'hidden'}).fadeIn(300);
	}












   // COMMONS FUNCTIONS

	function refresh() {
		$("#wizard").stepFormWizard().refresh();
	}

	function validStep(step){
        var valid = $("#wizard").parsley().validate('block' + step);
        sfw.refresh();
        return valid
	}


}]);








/*
 * NOTA 1
 *
 * Por algum motivo on qual não sei nesse momento ( de correria ), executar o plugin do wizard diretamente no controller
 *
 * está ocorrendo um erro no Angular ( Error: k is undefined ), e adicionanto a chamada dentro do $timeout esse erro não ocorre.
 *
 * P.S: Procurei algo no google e nada de solução
 *
 * Robson Duarte 07.03.2017
 *
 */

