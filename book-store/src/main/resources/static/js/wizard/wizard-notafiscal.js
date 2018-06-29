

var global_formulario_errors;
var global_formulario_status;

function validar_wizard(index) {

    console.log("Valindo:", index);
    global_formulario_errors = [];
    global_formulario_status = [];


    // Valida a Partir da Tab 4
    if (index >= 4) {

        validar_status(1);
        validar_status(2);
        validar_status(3);



        if (global_formulario_errors.length > 0) {

            var msgError = "";

            global_formulario_errors.map(function (error) {
                msgError += error + "<br/>";
            });
            createModal({
                title: 'Atenção',
                html: msgError,
                footer: '<button type = "button" class = "btn btn-default btn-close-modal" >Ok</button>'
            });

            return false;

        }
    }


    return true;

}



function validar_status(index) {

    // Valida os campos obrigatórios
    validar_obrigatorios(index);

    // Busca o status

    item = getStatus(index);

    console.log(item);

    if (item.status === false) {
        global_formulario_errors.push(item.mensagem);
    }


}

function validar_obrigatorios(index) {

    var msg = [];
    var seletor = "#tab" + index + " .required";
    console.log("Seletor:" + seletor);
    $(seletor).each(function () {

        var valor = $(this).val();

        if (valor.trim() == '' || valor == 'undefined' || valor == null) {
            msg.push("O campo <b>" + $(this).parent().find("label").html() + "</b> não pode ser vazio");
        }
    });

    if (msg.length > 0) {

        var msgFinal = "";
        msg.map(function (texto) {
            msgFinal += "<br/>" + texto;
        });

        setStatus(index, {
            mensagem: msgFinal,
            status: false
        });
    } else {

        setStatus(index, {
            mensagem: "Ok",
            status: true
        });
    }

    console.log(msg);
}


function setStatus(index, item) {

    var lgt = global_formulario_status.length;
    var posicao = null;

    for (var i = 0; i < lgt; i++) {

        var atual = global_formulario_status[i];

        if (item.id === atual.id) {

            posicao = i;
            break;
        }
    }


    if (posicao !== null) {

        global_formulario_status[posicao] = {
            status: item.status
            , mensagem: item.mensagem
            , id: index
        };

    } else {
        global_formulario_status.push({
            status: item.status
            , mensagem: item.mensagem
            , id: index
        });
    }

    console.log(global_formulario_status);


}

function getStatus(index) {

    var itemRetorno = null;

    global_formulario_status.map(function (item) {

        console.log(item);
        if (item.id === index) {
            itemRetorno = item;
        }

    });

    return itemRetorno;

}




$(document).ready(function () {

	$('#rootwizard').bootstrapWizard(
    	{/*onNext: function (tab, navigation, index) {
            // Exemplo de ação ao exibir a tab
            return validar_wizard(index);
        },*/
        onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;
            var $percent = ($current / $total) * 100;
            $('#rootwizard .progress-bar').css({width: $percent + '%'});

    		if($current >= $total) {
    			$('#rootwizard').find('.next').hide();
    			$('#rootwizard').find('.last').show().removeClass('disabled');
    		} else {
    			$('#rootwizard').find('.next').show();
    			$('#rootwizard').find('.last').hide();
    		}

    		/* // Exemplo de ação ao exibir a tab
            return validar_wizard(index);*/
      }});

});