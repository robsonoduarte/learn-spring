

function _(el) {
    return document.getElementById(el);
}


function uploadFile(nome_seletor) {

    showModal();

    var file = _(nome_seletor).files[0];
    var formdata = new FormData();
    formdata.append(nome_seletor, file);
    var ajax = new XMLHttpRequest();
    ajax.upload.addEventListener("progress", progressHandler, false);
    ajax.addEventListener("load", completeHandler, false);
    ajax.addEventListener("error", errorHandler, false);
    ajax.addEventListener("abort", abortHandler, false);
    ajax.responseType = 'json';
    ajax.open("POST", "api/importar-exemplo-resposta.json");
    ajax.send(formdata);

    document.getElementById("importar").value = "";

}


function showModal() {
    var html = "";

    html += '<div class="upload-status">';

    html += '              <h3 class="status"></h3>';
    html += '              <h4 class="total"></h4>';
    html += '               <div id="bar" class="progress">';
    html += '                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" width=0></div>';
    html += '                </div>';
    html += '                <div class="exibicao-dados"></div>';
    html += '          </div>';

    createModal({
        title: 'Progresso',
        html: html,
        footer: ''
    });
}

function progressHandler(event) {

    var percent = (event.loaded / event.total) * 100;

    if (percent === 100) {
        percent = 90;
    }

//    $(".upload-status .total").html("Uploaded " + event.loaded + " bytes of " + event.total);
    $(".upload-status .progress-bar").css('width', percent + "%");
    $(".upload-status .status").html(Math.round(percent) + "% carregando... espere por favor");



}
function completeHandler(event) {

    var percent = (event.loaded / event.total) * 100;

    console.log(event.target.response);
    console.log(event);
    setTimeout(function () {

//        $(".upload-status .total").html(event.target.response.message);
        $(".upload-status .progress-bar").css('width', percent + "%");
        $(".upload-status .status").html("Carregamento Finalizado, deseja confirmar esta ação?");
        $(".upload-status .exibicao-dados").html(event.target.response.html);
        $(".modal-footer").html('<button type = "button" class = "btn btn-danger btn-close-modal" >Não</button><button type = "button" class = "btn btn-success btn-close-modal" >Sim</button>');

    }, 500);
}

function errorHandler(event) {
    $("#upload-status .total").html("Upload Failed");

}

function abortHandler(event) {
    $("#upload-status .total").html("Upload Aborted");
}

// Listenners de Exibicao

var campoTipoImportacao = $("#tipo-importacao");
var clickDownload = false;

function showPasso2() {
    if (campoTipoImportacao.val() != '') {
        $(".passo-2").show();
    }else{
        $(".passo-2").hide();
    }
}

function showPasso3() {

    console.log("Click", clickDownload);
    if (campoTipoImportacao.val() !== '' && clickDownload === true ) {
        $(".passo-3").show();
    }else{
        $(".passo-3").hide();
    }

}

function init() {
    $(".passo-2").hide();
    $(".passo-3").hide();
}


$(document).ready(function () {
    init();

    $("body").on("change", '#importar', function () {
        uploadFile('importar');
    });

    $("body").on("change", '#tipo-importacao', function () {
        clickDownload = false;
        showPasso2();
        showPasso3();
    });

    $("body").on("click", '.download-arquivo', function () {
        clickDownload = true;
        showPasso2();
        showPasso3();
    });



});
