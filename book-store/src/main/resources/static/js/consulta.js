var clickNFe = angular.module("clickNFe", ['rzModule', 'angularUtils.directives.dirPagination']);


clickNFe.config(function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
});



clickNFe.controller("mainController", ['$scope', '$http', '$timeout', '$location', function($scope,$http,$timeout,$location){

	// >>>>>>>>>>>>> GLOBAL VARIABLE <<<<<<<<<<<<<<<<<<<<<
	$scope.nfeProcs = {}
	$scope.reloadSlider = true


	// >>>>>>>>>>> FILTERS <<<<<<<<<<<<<<<<<<<<

	// DATES TO START FILTER
	var startDate = moment().hour(00).minute(00).second(00)
	var endDate = moment().hour(23).minute(59).second(59)


	// DEFAULT PARAMETERS TO FILTER
	$scope.params ={
		emit: angular.element('#emit').val(),
		dhEmi: startDate.format() + ',' + endDate.format(),
		nNF: '0,10000000'
	}

	$scope.load =  function() {
		$('.cortina').show()
		$http.get('https://' + $location.host() + ":" + $location.port() + '/nfeprocs', {params: $scope.params})
		.success(function(data){
			$scope.nfeProcs = data
			$scope.slider()
			$('.cortina').hide()
		})
	}

	// Load default filter
	$scope.load()

	// RANGE OF DHEMI
	$('#periodo').daterangepicker({
		 startDate: startDate,
		 endDate: endDate,
		 timePicker: true,
		 timePicker24Hour: true,
		 timePickerSeconds: true,
		 dateLimit: {
			 days: 31
		 },
        locale: {
           format: 'DD/MM/YYYY HH:mm:ss',
           monthNames: ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
           daysOfWeek: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
           applyLabel: 'Aplicar',
           cancelLabel: 'Cancelar'
        }
	}, function(start, end) {
		$scope.params.nNF = '0,10000000'
		$scope.params.dhEmi = start.format() + ',' + end.format()
		$scope.load()
		$scope.reloadSlider = true
	});




	// RANGE OF NNF
	$scope.slider = function(){
		 $timeout(function () {
		 	if($scope.nfeProcs.length > 0 && $scope.reloadSlider){
		 		var min = $scope.nfeProcs[$scope.nfeProcs.length -1].nNF
				var max = $scope.nfeProcs[0].nNF
				 $scope.nNF = {
					 minValue: min,
					 maxValue: max,
					 options: {
						 step: 1,
						 floor: min,
				         ceil: max,
				         onEnd: function () {
				        	$scope.params.nNF = $scope.nNF.minValue + ',' + $scope.nNF.maxValue
				     		$scope.load()
				         }
					   }
			      }
		 		$scope.reloadSlider = false
		 	 }
	     });
	}



	// DEST

	var docs = []

	$scope.dest = function(event) {
		var input = event.target

		if(input.checked) docs.push(input.id)
		else docs.splice(docs.indexOf(input.id + ','), 1)

		$scope.params.dest = docs.toString()+ ','
		$scope.load()
	}


	

	// Status
	var status = []
	
	$scope.status = function(event) {
		var input = event.target

		if(input.checked) status.push(input.id)
		else status.splice(status.indexOf(input.id + ','), 1)

		$scope.params.status = status.toString()+ ','
		$scope.load()

	}




	// >>>>>>>>>>>>>>>> LOAD NFE BY DEMAND <<<<<<<<<<<<<<<<<<<<<
	$scope.nfes = new Map()

	$scope.loadNFe = function(id){

		var container = $("#nf-"+ id);

		if(!container.hasClass("loaded")){
			$http.get('https://' + $location.host() + ":" + $location.port() + '/nfeproc/' + id)
			.success(function(nfe){
				$scope.nfes.set(id,nfe)
				$("#"+id).prev().hide()
				$("#"+id).show()
				container.addClass("loaded")
			})
		}
	}



	 // >>>>>>>>>>>>>>> EMAIL <<<<<<<<<<<<<<<<<<<
	 $scope.msgEmail = {
		id: '',
		emails : [],
	    DetEvento : {}
	 }


	 $scope.showModal = function(id, defineOperation){
		createModalForm({
		      id : 'modal-email',
		      title: 'Enviar email'
		 });
		$scope.msgEmail.operation = 'SEND-EMAIL-' + defineOperation
		$scope.msgEmail.id = id
		$scope.msgEmail.emails = []
		$scope.email = ''
	 }

	 $scope.sendEmail = function(){
		 $http.post('https://' + $location.host() + ":" + $location.port() + '/send/email', $scope.msgEmail)
	 }

	$scope.addEmail = function() {
		$scope.msgEmail.emails.push($scope.email)
		$scope.email = ''
	}

	$scope.removeEmail = function(index){
		$scope.msgEmail.emails.splice(index, 1);
	};
	
	
	

	
	 // >>>>>>>>>>>>>>> EVENTOS <<<<<<<<<<<<<<<<<<<
	$scope.envEventos = new Map()
	$scope.envEvent = {
		operation: 'ENV-EVENT',
		det : {}
	}
	 

	//cancelamento
	 $scope.showModalCancel = function(id){
		 $("#evento-form").parsley().reset()
		 $scope.modal = {message: "Informe aqui a justificativa para cancelamento da NFe", title: "Justificativa", maxlength: "255", msgEvent: ""}
		 $(".count").html("")
		 
		 createModalForm({
			 id : 'modal-evento',
			 title: 'Cancelar NFe'
		 });
		 
    	 $scope.envEvent.cOrgao = $scope.nfes.get(id).NFe.infNFe.ide.cUF
		 $scope.envEvent.cnpj =  $scope.nfes.get(id).NFe.infNFe.emit.CNPJ
		 $scope.envEvent.chNFe = $scope.nfes.get(id).protNFe.infProt.chNFe
		 
	     $scope.envEvent.det.nProt = $scope.nfes.get(id).protNFe.infProt.nProt
	     $scope.envEvent.det.xJust = ""
	}
	 
	 
	 
	 $scope.sendCancel = function(){
		 var valid = $("#evento-form").parsley().validate()
		 if(valid){
			 $scope.envEvent.det.xJust = $scope.modal.msgEvent
			 $scope.close = "btn-close-modal"
			$http.post('https://' + $location.host() + ":" + $location.port() + '/nfeproc/cancel/', $scope.envEvent)
		 }
	 }
	 
	 
	 
	 
	 
	 //carta de correção 
	 $scope.showModalLetter = function(id){
		    $("#evento-form").parsley().reset()
			$scope.modal = {message: "Informe aqui as retificações a serem consideradas", title: "Retificação", maxlength: "1000", msgEvent: ""}
		    $(".count").html("")
		    
			createModalForm({
			      id : 'modal-evento',
			      title: 'Carta de Correção NFe'
			 });

	    	 $scope.envEvent.cOrgao = $scope.nfes.get(id).NFe.infNFe.ide.cUF
			 $scope.envEvent.cnpj =  $scope.nfes.get(id).NFe.infNFe.emit.CNPJ
			 $scope.envEvent.chNFe = $scope.nfes.get(id).protNFe.infProt.chNFe
			 
		     $scope.envEvent.det.xCorrecao = ""
		    	 
		    	 
		 }
	 
	 
	 $scope.sendLetter = function(){
		 var valid = $("#evento-form").parsley().validate()
		 if(valid){
			 $scope.envEvent.det.xCorrecao = $scope.modal.msgEvent
			 $scope.close = "btn-close-modal"
			 $http.post('https://' + $location.host() + ":" + $location.port() + '/nfeproc/letterCorrection/', $scope.envEvent)
		 }		 
	 }
	 
	 
	 //loadEnvEvento
 	$scope.loadEnvEvento = function(chNFe){
		
 		var container = $("#event-"+ chNFe);
 		
 		if(!container.hasClass("loaded")){
			$http.get('https://' + $location.host() + ":" + $location.port() + '/proceventonfes/' + chNFe)
			.success(function(data){
				$scope.envEventos.set(chNFe,data) 
				container.prev().hide()
				container.show()
				container.addClass("loaded")
			})
 		}
	}
 	
 	
 	
    // >>>>>>>>>>>>>>> INUTILIZACAO <<<<<<<<<<<<<<<<<<<
 	$scope.inutNFes = new Map()
 	$scope.disablement = {
 			operation: 'DISABLEMENT',
 	}
 	
	
 	$scope.paramsInut ={
		cnpj: '',
		numInit: '',
		serie: ''
	}
 	
	 $scope.showModalDisablement = function(id){
 		 $("#inut-form").parsley().reset()
		 createModalForm({
			 id : 'modal-inutilizacao',
			 title: 'Inutilizar NFe'
		 });
		 
 		 $scope.disablement.versao = $scope.nfes.get(id).NFe.infNFe.versao
 		 $scope.disablement.cUF = $scope.nfes.get(id).NFe.infNFe.ide.cUF
 		 $scope.disablement.CNPJ =  $scope.nfes.get(id).NFe.infNFe.emit.CNPJ
 		 $scope.disablement.serie = $scope.nfes.get(id).NFe.infNFe.ide.serie
 		 $scope.disablement.nNFIni =  $scope.nfes.get(id).NFe.infNFe.ide.nNF
 		 $scope.disablement.nNFFin = $scope.nfes.get(id).NFe.infNFe.ide.nNF

 		 
 		 $scope.disablement.xJust = ""
	    	 
		 
	}
 	
 	
	 $scope.sendDisablement = function(){
		 
		 
		 var valid = $("#inut-form").parsley().validate()
		 if(valid){
			 $scope.close = "btn-close-modal"
			 $http.post('https://' + $location.host() + ":" + $location.port() + '/nfeproc/disablement/', $scope.disablement)
		 }		 
	 }

	 
	 

		
		
	//loadInutNFe
	 $scope.loadInutNFe = function(chNFe, id){
		 var container = $("#inut-"+ chNFe);
		 $scope.paramsInut ={
				CNPJ: $scope.nfes.get(id).NFe.infNFe.emit.CNPJ,
				nNFIni:  $scope.nfes.get(id).NFe.infNFe.ide.nNF,
				serie: $scope.nfes.get(id).NFe.infNFe.ide.serie
			}
		 

		 if(!container.hasClass("loaded")){
			  $http.get('https://' + $location.host() + ":" + $location.port() + '/procInutNFe', {params: $scope.paramsInut})
			 .success(function(data){
				 $scope.inutNFes.set(id,data) 
				 container.prev().hide()
				 container.show()
				 container.addClass("loaded")
			 })
		 }
	 }
	
 	

}]);




