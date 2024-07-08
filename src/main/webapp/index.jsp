<%--
  Created by IntelliJ IDEA.
  User: 23102
  Date: 25.06.2024
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello world!</title>
    <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
</head>
<body>
    <div class="center flex full-vh">
        <a class="ml-20 vertical-center" href="${pageContext.request.contextPath}/viewTypes"> Просмотр всех типов машин</a>
        <a class="ml-20 vertical-center" href="${pageContext.request.contextPath}/viewCars"> Просмотр всех машин</a>
        <a class="ml-20 vertical-center" href="${pageContext.request.contextPath}/viewReport"> Отчёт</a>
        <a class="ml-20 vertical-center" href="${pageContext.request.contextPath}/viewDiagnostic"> Отправить машины на диагностику</a>
        <a class="ml-20 vertical-center" href="${pageContext.request.contextPath}/viewPlannedDiagnostic"> Информация о плановой диагностике</a>
    </div>
</body>
</html>
