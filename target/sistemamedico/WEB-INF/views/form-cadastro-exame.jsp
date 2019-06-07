<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cadastro de Exames</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <%@include file="/WEB-INF/views/cabecalho.jsp"%>

    <form action="adicionaExame" method="POST">
        <div class="form-group col-6">
            Nome do Exame:<br/>
            <input class="form-control" type="text" name="nomeExame" />
            ${falhaNomeExame}
        </div>
        <div class="form-group col-6">
            Descrição: <br/>
            <textarea class="form-control" name="descricao"></textarea>
            ${falhaDescricao}
        </div>
        <div class="form-group col-6">
            Data de Realização:<br/>
            <input class="form-control" type="text" name="dataRealizacao">
            ${falhaDataRealizacao}
        </div>

        <input type="hidden" name="paciente.id" value="${pacienteId}">
        <input class="btn btn-primary" type="submit" value="Adicionar"> <br/>
    </form>
</body>
</html>
