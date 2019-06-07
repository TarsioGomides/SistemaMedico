/*
$(function(){
    $("#nome").click(function(){
        var id = $(this).attr('id');
        alert(id);
    });
});
*/

$(document).on("click", ".td-remover", function(event) {
    event.preventDefault();
    var id = $(this).parent().attr("id");  // pega o id do select clicado
    //var val= $(this).val();       // pega o valor do option selecionado
    var dados = {id: id};

    //console.log("ID: "+id);
    $.get("http://localhost:8080/removerPacienteJSON", dados, listarPacientes);

});


/*
function lis(dados) {
    console.log(dados);
}
*/
