<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Import das taglibs -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Listagem de Exames</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <div class="topo">
        <img src="/resources/imagens/hospital.png" class="mr-3 img-thumbnail">
        Sistema Médico
    </div>

    <h1>Lista de exames do paciente</h1>

    <a class="btn btn-primary" href="listarPacientes">Voltar para a listagem de pacientes</a>
    <br /> <br />

    <a class="btn btn-primary" href="cadastroExame?pacienteId=${pacienteId}">Cadastrar novo exame</a>
    <br /> <br />


    <!--parte de exames-->
    ${exameInexistenteMostrarExame}
    ${sucessoAlterarExame}
    ${sucessoAdicionarExame}
    ${exameInexistenteRemoverExame}
    ${pacienteSemExames}



    <table class="table table-hover table-dark">
        <tr>
            <td>Nome do Exame</td>
            <td>Descrição</td>
            <td>Data de Realização</td>

        </tr>
        <c:forEach items="${exames}" var="exame">
            <tr>
                <td>${exame.nomeExame}</td>
                <td>${exame.descricao}</td>
                <td>
                    <fmt:formatDate value="${exame.dataRealizacao.time}" pattern="dd/MM/yyyy"/>
                </td>
                <td>
                    <a href="mostrarExame?id=${exame.id}">Alterar</a>
                </td>
                <td>
                    <a href="removerExame?id=${exame.id}&pacienteId=${pacienteId}">Remover</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
