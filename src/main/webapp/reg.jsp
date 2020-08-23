<%--
  Created by IntelliJ IDEA.
  User: romanvohmin
  Date: 05.08.2020
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script>
        function validate() {
            let elements = document.getElementsByTagName("input");
            for (let i = 0, element; element = elements[i++];) {
                console.log(element);
                if (element.value === "") {
                    console.log("Please fill field ")
                    alert("Заполните поле " + element.name);
                    return false;
                }
            }
            let email = document.getElementById("email").value;
            console.log(email);
            $.ajax({
                type: 'POST',
                crossdomain: true,
                url: 'http://localhost:8080/todo/reg.do',
                data: {
                    email: email + ""
                }
            }).done(function () {
                console.log("updateStatus OK");
            }).fail(function (err) {
                alert("Пользователь с таким email уже зарегистрирован!" + err);
            })
        }
    </script>
    <title>Работа мечты!</title>
</head>
<body>
<div class="container pt-3">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
        </li>

    </ul>

    <div>
        <div>
            <h2>Регистрация пользователя</h2>
        </div>

        <form action="<%=request.getContextPath()%>/reg.do" method="post">
            <div class="col">
                <div class="col-sm-4">
                    <label>
                        <input type="text" name="login" class="form-control" placeholder="User name">
                    </label>
                </div>
                <br>
                <div class="col-sm-4">
                    <label>
                        <input type="email" name="email" class="form-control" id="email"
                               placeholder="Email">
                    </label>
                </div>
                <br>
                <div class="col-sm-4">
                    <label>
                        <input type="password" name="password" class="form-control" placeholder="password">
                    </label>
                </div>
                <br>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-primary btn-lg" onclick="return validate();" style="width: 134px">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
