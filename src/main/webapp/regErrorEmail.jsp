<%--
  Created by IntelliJ IDEA.
  User: romanvohmin
  Date: 23.08.2020
  Time: 12:18
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
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
<div class="jumbotron">
    <h1 class="display-4">Привет пользователь!</h1>
    <p class="lead">Пользователь с таким e-mail уже зарегистрирован в приложении</p>
    <hr class="my-4">
    <p>Вы можете попробовать снова зарегистриоваться или авторизоваться </p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/login.jsp"
           role="button"> Авторизоваться </a>
    </p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/reg.jsp"
           role="button"> Зарегистрироваться </a>
    </p>
</div>
</body>
</html>
