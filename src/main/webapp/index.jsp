<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Autopark!</title>
    <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/gear.png" />
</head>
<body>
<img src="resources/gear_animation.gif" alt="Hello world!">
    <div class="center flex full-vh">
        <a class="button" href="${pageContext.request.contextPath}/viewTypes"> Просмотр всех типов машин</a>
        <a class="button" href="${pageContext.request.contextPath}/viewCars"> Просмотр всех машин</a>
        <a class="button" href="${pageContext.request.contextPath}/viewReport"> Отчёт</a>
        <a class="button" href="${pageContext.request.contextPath}/viewDiagnostic"> Отправить машины на диагностику</a>
        <a class="button" href="${pageContext.request.contextPath}/viewPlannedDiagnostic"> Информация о плановой диагностике</a>
    </div>
</body>
</html>
