<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700,900&display=swap&subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file='base.css' %>
        <%@include file='main.css' %>
    </style>
    <title>TODO List</title>
    <script src="script.js" type="text/javascript"></script>
</head>

<body>
<div class="container">

    <div class="wrapper">
        <div class="header">
            <div class="jumbotron jumbotron-fluid">
                <c:if test="${user.name != null}">
                    <div class="alert alert-success" role="alert">

                        <a class="nav-link" href="<%=request.getContextPath()%>/exit.do">
                            Текущий пользователь: <c:out value="${user.name}"/> | Выйти</a>
                    </div>
                </c:if>
                <c:if test="${user.name == null}">
                    <div class="alert alert-primary" role="alert">
                        <ul class="nav">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">
                                    Авторизация </a>
                            </li>
                        </ul>
                    </div>
                </c:if>
<%--                <c:out value="${user.name}"/>--%>

                <div class="container, jumbotron__head">
                    <h1 class="display-6">Планировщик задач</h1>
                    <p class="lead">Это простое WEB-приложение для планирования дел</p>
                </div>
            </div>
        </div>
        <div class="wrapper__content">

            <form class="form">
                <div class="form-group">
                    <label for="textarea">Введите описание задачи</label>
                    <textarea class="form-control" id="textarea" name="describe" rows="2"
                              placeholder="Введите задачу"
                    ></textarea>
                </div>
                <div class="form-group">
                    <button type="submit" id="sendTask" class="btn btn-primary"
                            onclick="return doSend()">Добавить
                        задачу
                    </button>
                </div>
            </form>

            <div class="alert alert-info" role="alert">
                <div class="form-check">
                    <label class="form-check-label" for="showTasks">
                    <input class="form-check-input" type="checkbox" value="" id="showTasks">
                        Показать все задачи
                    </label>
                </div>
            </div>
        </div>

        <table class="table" id="table">
            <thead>
            <tr>
                <th>Задача</th>
                <th>Дата</th>
                <th>Пользователь</th>
                <th>Статус</th>
            </tr>
            </thead>
            <tbody>
            <tr id="tableRow">

            </tr>
            </tbody>
        </table>
        <!--        <div class="footer">-->
        <!--            <span class="footer">&#169 Вохмин Роман Александрович, 2020</span>-->
        <!--        </div>-->
    </div>
</div>
</body>

</html>
