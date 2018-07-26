var clickNFe = angular.module("clickNFe", []);



clickNFe.controller("mainController", ['$scope', '$http', '$timeout', '$location', function($scope,$http,$timeout,$location){

	// >>>>>>>>>>>>> GLOBAL VARIABLE <<<<<<<<<<<<<<<<<<<<<
	
	$scope.report = { 
			nApproved : 0,
			nDisabled : 0,
			nCancelled : 0,
			nRejected : 0
	}
	
	
	$scope.reportList = { 
			nApproved : [],
			nDisabled : [],
			nCancelled : [],
			nRejected : []
	
	}
	
	
	$scope.alerts = { 
			vApproved : 0,
			vDisabled : 0,
			vCancelled : 0,
			vRejected : 0
	}
 
	

	
	// >>>>>>>>>>>>> GRAFICOS <<<<<<<<<<<<<<<<<<<<<
	
	// GRAFICO 1
	$scope.chart01 = function(data) { 
	

	for (var c = 14; c >= 0; c--) {
		var day = moment().subtract(c, 'days').format('YYYY-MM-DD').toString()
		
		var approved = data.filter( function( elem, i,  array ) {
		  if (elem.dhEmi.substring(0,10) == day && elem.status == 'APPROVED') {
			  	return elem ;
			}
		} );
		
		var cancelled = data.filter( function( elem, i,  array ) {
			if (elem.dhEmi.substring(0,10)== day && elem.status == 'CANCELLED') {
				return elem;
			}
		} );
		
		var rejected = data.filter( function( elem, i,  array ) {
			if (elem.dhEmi.substring(0,10)== day && elem.status == 'REJECTED') {
				return elem;
			}
		} );

		var disabled = data.filter( function( elem, i,  array ) {
			if (elem.dhEmi.substring(0,10)== day && elem.status == 'DISABLED') {
				return elem;
			}
		} );
		
		$scope.reportList.nApproved.push(approved.length)
		$scope.reportList.nCancelled.push(cancelled.length)
		$scope.reportList.nRejected.push(rejected.length)
		$scope.reportList.nDisabled.push(disabled.length)
	}
	
		
	    $('#chart01').highcharts({
			credits: { enabled: false},
	        title: {
	            text: 'Visão Geral',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Últimos 15 dias',
	            x: -20
	        },
	        xAxis: {categories: [
					            	moment().subtract(14, 'days').format('DD/MM'),
						        	moment().subtract(13, 'days').format('DD/MM'),
						        	moment().subtract(12, 'days').format('DD/MM'),
						    		moment().subtract(11, 'days').format('DD/MM'),
									moment().subtract(10, 'days').format('DD/MM'),
									moment().subtract(9, 'days').format('DD/MM'),
									moment().subtract(8, 'days').format('DD/MM'),
									moment().subtract(7, 'days').format('DD/MM'),
									moment().subtract(6, 'days').format('DD/MM'),
		        					moment().subtract(5, 'days').format('DD/MM'),
	        						moment().subtract(4, 'days').format('DD/MM'),
	        						moment().subtract(3, 'days').format('DD/MM'),
	        						moment().subtract(2, 'days').format('DD/MM'),
	        						moment().subtract(1, 'days').format('DD/MM'),
	        						moment().subtract(0, 'days').format('DD/MM')
	        						]},
	        yAxis: {
	            title: {text: 'Quantidade Notas Fiscais'},
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: { valueSuffix: ''},
	      
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: 'Aprovada',
	            data: $scope.reportList.nApproved,
	            color:"#33ff33"
	        }, {
	            name: 'Cancelada',
	            data: $scope.reportList.nCancelled,
	            color:"#FF0000"
	        }, {
	            name: 'Inutilizada',
	            data: $scope.reportList.nDisabled,
	            color:"#00008B" 
	        },{
	            name: 'Rejeitada',
	            data: $scope.reportList.nRejected,
	            color:"#CDCD00"  
	        }]
	    });	
	}
	
	
	
	// GRAFICO 2
	$scope.chart02 = function(data) {
		
		
		// Agrupando informações do status 
		angular.forEach(data, function(i){
			 if(i.status == 'APPROVED'){
			    	$scope.report.nApproved += 1    
			    }else if(i.status == 'CANCELLED'){
			    	$scope.report.nCancelled += 1    
			    }else if(i.status == 'REJECTED'){
			    	$scope.report.nRejected += 1    
			    }else if(i.status == 'DISABLED'){
			    	$scope.report.nDisabled += 1    
			    }  
		})

		
		
		
	    $('#chart02').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        title: { text: 'Visão Geral' },
	        subtitle: { text: 'Últimos 30 Dias'  },
	        labels: { enabled: false },
	        credits: { enabled: false },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: false
	                },
	                showInLegend: true
	            }
	        },
	        series: [{
	                name: 'NFe',
	                colorByPoint: true,
	                labels: { enabled: false },
	                data: [{
	                        name: 'Aprovada',
	                        y: $scope.report.nApproved,
	                        color:"#33ff33"
	                    },{
	                        name: 'Cancelada',
	                        y: $scope.report.nCancelled,
	                        color:"#FF0000"
	                    },{
	                        name: 'Inutilizada',
	                        y: $scope.report.nDisabled,
	                        color:"#00008B" 
	                    },{
	                        name: 'Rejeitada',
	                        y: $scope.report.nRejected,
	                        color:"#CDCD00"  
	                    }]
	            }]
	    });
	}


	
	
	// >>>>>>>>>>>>> ALERT <<<<<<<<<<<<<<<<<<<<<
	$scope.alert = function(data) { 
		var dayData = []
		
		angular.forEach(data, function(i){
			 if(i.dhEmi.substring(0,10) ==  moment().format('YYYY-MM-DD').toString()){
				  dayData.push(i)
			    }
		})

		
		

		angular.forEach(dayData, function(i){
			 if(i.status == 'APPROVED'){
				 	$scope.alerts.vApproved += i.vNF  
			    }else if(i.status == 'CANCELLED'){
			    	$scope.alerts.vCancelled += i.vNF   
			    }else if(i.status == 'REJECTED'){
			    	$scope.alerts.vRejected += i.vNF
			    }else if(i.status == 'DISABLED'){
			    	$scope.alerts.vDisabled += i.vNF
			    }  
		})

		
	}
	
	
	
	
	
	// >>>>>>>>>>>>> LOAD <<<<<<<<<<<<<<<<<<<<<
	
	$scope.params ={
			emit: angular.element('#emit').val(),
			dhEmi:  moment().subtract(30, 'days').hour(00).minute(00).second(00).format() 
																		+ ',' + 
																		moment().hour(23).minute(59).second(59).format()
											
	}
	
	
	
	$scope.load =  function() {
		$('.cortina').show()
		$http.get('https://' + $location.host() + ":" + $location.port() + '/dashboard/report', {params: $scope.params})
		.success(function(data){
			$scope.alert(data) 
			$scope.chart02(data) 
		    $scope.chart01(data)
			$('.cortina').hide()
		})
	}



	
	//Load Default
	$scope.load()

	
	
	
	
}]);




