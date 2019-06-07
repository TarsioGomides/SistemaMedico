listarPacientes();

/*********************************************************************************************************************
 * FUNÇÕES OBJETIVOS
 *********************************************************************************************************************/
function listarPacientes() {
    //console.log($(".link-remover").attr('id').val());
    var linkListar = document.querySelector("#tabela-lista-pacientes");

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/listarPacientesJSON");

//Captura o response
    xhr.addEventListener("load", function() {//quando a página estiver carregada, executa a função
        var erroAjax = document.querySelector("#erro-ajax");

        if(xhr.status == 200) {
            erroAjax.classList.add("invisivel");
            //transforama a resposta string(json) em objeto JS
            var pacientes = JSON.parse(xhr.responseText);

            console.log(pacientes);
            pacientes.forEach(function(paciente){
                adicionaPacienteNaTabela(paciente);
            });
        } else {
            console.log(xhr.status);
            console.log(xhr.response);
            erroAjax.classList.remove("invisivel");
        }
    });

    xhr.send();
}

/*********************************************************************************************************************
 * FUNÇÕES AUXILIARES
 *********************************************************************************************************************/
function adicionaPacienteNaTabela(paciente) {
    var pacienteTr = montarTr(paciente);
    var tabela = document.querySelector("#tabela-lista-pacientes");
    tabela.appendChild(pacienteTr);
}

function montarTr(paciente) {
    //console.log(document.querySelector("#nome").value);
    var pacienteTr = document.createElement("tr");
    pacienteTr.classList.add("paciente");
    pacienteTr.setAttribute("id", paciente.id);

    pacienteTr.appendChild(montarTd(paciente.nome, "info-nome"));
    pacienteTr.appendChild(montarTd(paciente.idade, "info-idade"));
    pacienteTr.appendChild(montarTd(paciente.telefone, "info-telefone"));

    pacienteTr.appendChild(montarTd("Alterar", "td-alterar", true, "link-alterar", "../../mostra-paciente.html"));
    pacienteTr.appendChild(montarTd("Remover", "td-remover", true, "link-remover", "../../listar-pacientes.html"));///////////
    pacienteTr.appendChild(montarTd("Exames", "td-exames", true, "link-exames", "../../listar-exames.html"));

    return pacienteTr;
}

function montarTd(dado, classe, hasChild, classeA, caminho) {
    var td = document.createElement("td");
    td.textContent = dado;
    td.classList.add(classe);

    if(hasChild){
        td.textContent = "";
        td.appendChild(montaA(dado, classeA, caminho));
    }

    return td;
}

function montaA(dado, classeA, caminho) {
    var a = document.createElement("a");

    a.textContent = dado;
    a.setAttribute("href", caminho);
    a.classList.add(classeA);

    return a;
}

//AJAX. Requisição com JS de modo assíncrono(Não está parando o fluxo do JS)
