<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Formuário de Alteração do Exame</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css" media="all" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <%@include file="/WEB-INF/views/cabecalho.jsp"%>

    <h1>Alterar exame</h1>

    <form action="alterarExame" method="POST">
        <input type="hidden" name="id" value="${exame.id}" />

        <div class="form-group col-6">
            Nome do Exame:<br/>
            <input class="form-control" type="text" name="nomeExame" value="${exame.nomeExame}"/>
        </div>
        <div class="form-group col-6">
            Descrição: <br/>
            <textarea class="form-control" name="descricao">${exame.descricao}</textarea> <br/>
        </div>
        <div class="form-group col-6">
            Data de Realização:<br/>
            <input class="form-control" type="text" name="dataRealizacao" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${exame.dataRealizacao.time}" />">
        </div>

        <input type="hidden" name="paciente.id" value="${exame.paciente.id}">
        <input class="btn btn-primary" type="submit" value="Alterar"/>
    </form>
</body>
</html>


