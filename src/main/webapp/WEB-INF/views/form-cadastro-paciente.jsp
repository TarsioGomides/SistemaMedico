<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cadastro de Pacientes</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="topo">
        <img src="/resources/imagens/hospital.png" class="mr-3 img-thumbnail">
        Sistema Médico
    </div>

    <form action="adicionaPaciente" method="POST">
        <div class="form-group col-6">
            Nome:
            <input class="form-control" type="text" name="nome">
            ${falhaNome}
        </div>
        <div class="form-group col-6">
            Idade:
            <input class="form-control" type="text" name="idade">
            ${falhaIdade}
        </div>
        <div class="form-group col-6">
            Telefone:
            <input class="form-control" type="text" name="telefone">
            ${falhaTelefone}
        </div>
        <input class="btn btn-primary" type="submit" value="Adicionar">
    </form>
</body>
</html>

