$(function(){
	$(".numeric").numeric();

	$('.counter').keyup(function () {
	    countCharacters($(this));
	});

	$('.block-enter').keypress(function(event) {
	    if (event.keyCode == 13) {
	        event.preventDefault();
	    }
	});

	
	
});



function countCharacters(elemento) {

    var defualt = 5000;

    if (typeof elemento.attr('maxlength') === 'undefined') {
        elemento.attr('maxlength', defualt);
    }

    var max = elemento.attr('maxlength');
    var size = elemento.val().length;
    var remaining = parseFloat(max) - size;
    var msg = remaining + " Caracteres Restantes";

    elemento.parent().find(".count").html(msg);
}




function showLoad(){
	$('.cortina').show()
}

function hideLoad() {
	$('.cortina').hide()
}


/*function loading(selector, callback){
	selector.html(" <p class='icon-carregando'>&nbsp;</p> ");
    if(typeof callback == "function"){
        callback(seletor);
    }
}*/




function validateCNPJ(cnpj) {

	cnpj = cnpj.replace(/[^\d]+/g,'');

	if(cnpj == '') return false;

	if (cnpj.length != 14)
		return false;

	// Elimina CNPJs invalidos conhecidos
	if (cnpj == "00000000000000" ||
		cnpj == "11111111111111" ||
		cnpj == "22222222222222" ||
		cnpj == "33333333333333" ||
		cnpj == "44444444444444" ||
		cnpj == "55555555555555" ||
		cnpj == "66666666666666" ||
		cnpj == "77777777777777" ||
		cnpj == "88888888888888" ||
		cnpj == "99999999999999")
		return false;

	// Valida DVs
	tamanho = cnpj.length - 2
	numeros = cnpj.substring(0,tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
	  soma += numeros.charAt(tamanho - i) * pos--;
	  if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(0))
		return false;

	tamanho = tamanho + 1;
	numeros = cnpj.substring(0,tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (i = tamanho; i >= 1; i--) {
	  soma += numeros.charAt(tamanho - i) * pos--;
	  if (pos < 2)
			pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
	if (resultado != digitos.charAt(1))
		  return false;

	return true;

}



function validateCPF(cpf) {
	cpf = cpf.replace(/[^\d]+/g,'');
	if(cpf == '') return false;
	// Elimina CPFs invalidos conhecidos
	if (cpf.length != 11 ||
		cpf == "00000000000" ||
		cpf == "11111111111" ||
		cpf == "22222222222" ||
		cpf == "33333333333" ||
		cpf == "44444444444" ||
		cpf == "55555555555" ||
		cpf == "66666666666" ||
		cpf == "77777777777" ||
		cpf == "88888888888" ||
		cpf == "99999999999")
			return false;
	// Valida 1o digito
	add = 0;
	for (i=0; i < 9; i ++)
		add += parseInt(cpf.charAt(i)) * (10 - i);
		rev = 11 - (add % 11);
		if (rev == 10 || rev == 11)
			rev = 0;
		if (rev != parseInt(cpf.charAt(9)))
			return false;
	// Valida 2o digito
	add = 0;
	for (i = 0; i < 10; i ++)
		add += parseInt(cpf.charAt(i)) * (11 - i);
	rev = 11 - (add % 11);
	if (rev == 10 || rev == 11)
		rev = 0;
	if (rev != parseInt(cpf.charAt(10)))
		return false;
	return true;
}


$(document).ready(function(){
	$('.cpfcnpj').each(function(){
		if($(this).text().length == 14){
		 $(this).mask('99.999.999/9999-99');
		}else{
		 $(this).mask("999.999.999-99");
		}           
	})
});
