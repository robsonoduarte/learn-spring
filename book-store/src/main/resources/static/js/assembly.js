var myApp = angular.module("myApp", []);



myApp.config(function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
});



myApp.controller("mainController", ['$scope', '$http', '$window', '$location', function($scope,$http,$window,$location){


	$scope.request = {
		indPag: 0,
		modFrete: '9', // default sem frete
		dets : [],
		cobr : {fat: {}, dups: []},
		vols : []
	}





	$scope.send = function() {

		$scope.request = angular.forEach($scope.request, function(key,value){
		    if(value==""||value==null || value <= 0.0){
		        delete $scope.request[value];
		    }
		})


		$http.post('https://' + $location.host() + ":" + $location.port() + '/emit/', $scope.request)
		.success(function(data,status) {
			$window.location.href = '/home'
		})
	}



	// item
	$scope.add = function() {

		var det = {
			id: $scope.det.id,
			qCom: $scope.det.qCom,
			vUnCom: $scope.det.vUnCom,
			vFrete: $scope.det.vFrete,
			vSeg: $scope.det.vSeg,
			vDesc: $scope.det.vDesc,
			vOutro: $scope.det.vOutro,
			xPed: $scope.det.xPed,
			nItemPed: $scope.det.nItemPed
		}

		$scope.request.dets.push(det)
		$scope.det = {}
	}

	$scope.remove = function(index){
		$scope.request.dets.splice(index, 1);
	};





	// faturamento - duplicatas
	$scope.addDup = function() {
		var dup = {
				nDup: $scope.dup.nDup,
				dVenc: $scope.dup.dVenc,
				vDup: $scope.dup.vDup
			}

		$scope.dup = {}

	}

	$scope.removeDup = function(index){
		$scope.request.cobr.dups.splice(index, 1);
	};





	// transporte  - Volumes
	$scope.vol = {
			lacres: []
	}

	$scope.addVol = function() {
		var vol = {
				qVol: $scope.vol.qVol,
				pesoL: $scope.vol.pesoL,
				pesoB: $scope.vol.pesoB,
				esp: $scope.vol.esp,
				marca: $scope.vol.marca,
				nVol: $scope.vol.nVol,
				lacres: $scope.vol.lacres
			}

		$scope.request.vols.push(vol)

		$scope.vol = {lacres:[]}
		$scope.lacre = ''

	}


	// transporte  - Volumes - Lacres
	$scope.addLac = function() {
		$scope.vol.lacres.push($scope.lacre)
	}

	$scope.removeVol = function(index){
		$scope.request.vols.splice(index, 1);
	};


	$scope.removeLac = function(index){
		$scope.vol.lacres.splice(index, 1);
	};






}]);
























$(document).ready(function () {

    $(".natOp").select2({
		 placeholder: "Selecione a Natureza de Operação"
	});

	$(".emit").select2({
		 placeholder: "Selecione o Emitente"
	});

	$(".dest").select2({
		placeholder: "Selecione o Destinatário"
	});

	$(".entrega").select2({
		placeholder: "Informar somente se diferente do endereço Destinatário"
	});

	$(".material").select2({
		placeholder: "Selecione o Produto"
	});

	$(".transporta").select2({
		placeholder: "Selecione a Trasnportadora"
	});


});


