<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Import das taglibs -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Listagem de Pacientes</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <%@include file="/WEB-INF/views/cabecalho.jsp"%>

    <h1>Lista de Pacientes Cadastrados</h1>

    <a class="btn btn-primary" href="cadastroPaciente">Cadastrar novo paciente</a>
    <br /> <br />

    <!--parte de paciente-->
    ${sucessoAdicionarPaciente}
    ${pacienteInexistenteMostrarPaciente}
    ${sucessoAlterarPaciente}
    ${pacienteInexistenteRemoverPaciente}
    <!--parte de exames-->
    ${pacienteInexistenteRemoverExames}
    ${pacienteInexistenteListarExames}


    <br />

    <table class="table table-hover table-dark">
        <tr>
            <td>Nome</td>
            <td>Idade</td>
            <td>Telefone</td>

        </tr>
        <c:forEach items="${pacientes}" var="paciente">
            <tr>
                <td>${paciente.nome}</td>
                <td>${paciente.idade}</td>
                <td>${paciente.telefone}</td>
                <td>
                    <a href="${s:mvcUrl('PC#mostra').arg(0, paciente.id).build()}">Alterar</a><!--mostraPaciente?id=${paciente.id}-->
                </td>
                <td>
                    <a href="removerPaciente?id=${paciente.id}">Remover</a>
                </td>
                <td>
                    <a href="listarExames?pacienteId=${paciente.id}">Exames</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</body>
</html>
