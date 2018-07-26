/*$('#exampleModal').modal('show');


function createModal(data) {

    $('#modal-sistema.modal').modal('hide');



    var title = "";
    var html = "";
    var footer;

    if(typeof data.title != 'undefined'){
        title = data.title;
    }
    if(typeof data.html != 'undefined'){
        html = data.html;
    }

    if(typeof data.footer != 'undefined'){
        footer = data.footer;
    }


    $(".modal-title").html(title);
    $(".modal-footer").html(footer);
    $("#modal-sistema .modal-body").html(html);


    $('#modal-sistema.modal').modal('show');

}
*/

function createModalForm(data) {

    $('.modal').modal('hide');

    var title = "";
    var html = "";
    var id = 'modal';

    var footer;

    if(typeof data.title != 'undefined'){
        title = data.title;
    }
    if(typeof data.html != 'undefined'){
        html = data.html;
    }

    if(typeof data.footer != 'undefined'){
        footer = data.footer;
    }

    if(typeof data.id != 'undefined'){
        id = data.id;
    }

    $(".modal-title").html(title);
    $(".modal-footer").html(footer);

    $('#'+ id).modal('show');

}

// Exemplo

/*
createModal({
    title : 'Atenção',
    html : 'doiajdaioda',
    footer:  '<button type = "button" class = "btn btn-primary" >Confirmar</button>'
})*/;


/*$("body").on("click",".btn-close-modal",function(){
        objeto = $(this).parent().parent().parent().parent();
        objeto.modal('hide');
});
*/

