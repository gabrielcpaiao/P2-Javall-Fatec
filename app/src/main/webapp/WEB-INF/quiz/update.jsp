<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar Quiz</title>
</head>
<body>
<div class="container">
    <h1>Editar Quiz</h1>
    <form action="/quiz/update" method="post">
        <input type="hidden" name="id" value="${quiz.id}" />
        <div class="form-group">
            <label for="nome">Nome</label>
            <input type="text" class="form-control" name="nome" value="${quiz.nome}" />
        </div>
        <a href="/quiz/list" class="btn btn-primary">Voltar</a>
        <button type="submit" class="btn btn-success">Salvar</button>
    </form>
</div>
</body>
</html>
