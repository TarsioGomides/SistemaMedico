$(document).on("click", ".td-alterar", function(event) {
    console.log("entrou");
    event.preventDefault();
    var id = $(this).parent().attr("id");  // pega o id do select clicado

    //var val= $(this).val();       // pega o valor do option selecionado
    var dados = {id: id};

    //console.log("ID: "+id);
    $.get("http://localhost:8080/mostraPacienteJSON", dados, mostraPaciente);

});

function mostraPaciente(dadosRetornadosDoServidor) {
    console.log("entrou");
    console.log(dadosRetornadosDoServidor);
}