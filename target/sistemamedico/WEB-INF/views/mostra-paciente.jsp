<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Formuário de Alteração do Paciente</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <nav class="topo">
        <img src="/resources/imagens/hospital.png" class="mr-3 img-thumbnail">
        Sistema Médico
    </nav>

    <h1>Alterar cadastro do paciente</h1>

    <form action="alterarPaciente" method="POST">
        <input type="hidden" name="id" value="${paciente.id}" />

        <div class="form-group col-6">
            Nome:<br />
            <input class="form-control" type="text" name="nome" value="${paciente.nome}"/>
        </div>
        <div class="form-group col-6">
            Idade:<br />
            <input class="form-control" type="text" name="idade" value="${paciente.idade}"/>
        </div>
        <div class="form-group col-6">
            Telefone:<br />
            <input class="form-control" type="text" name="telefone" value="${paciente.telefone}"/>
        </div>

        <input class="btn btn-primary" type="submit" value="Alterar"/>
    </form>
</body>
</html>
