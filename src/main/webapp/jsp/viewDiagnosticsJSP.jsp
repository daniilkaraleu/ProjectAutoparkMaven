<%@ page import="Project.Classes.Infrastructure.dto.entity.VehicleDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 23102
  Date: 30.06.2024
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Диагностика</title>
    <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
</head>
<body>
<div class="center flex full-vh">
    <div class="vertical-center">
        <%
            List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("cars");
        %>
        <a class="button" href="${pageContext.request.contextPath}/">На главную</a>
        <br/>
        <br/>
        <hr/>
        <br/>
        <table>
            <caption>Диагностика машин</caption>
            <tr>
                <th>Тип</th>
                <th>Модель</th>
                <th>Номер</th>
                <th>Масса</th>
                <th>Дата выпуска</th>
                <th>Цвет</th>
                <th>Модель двигателя</th>
                <th>Пробег</th>
                <th>Бак</th>
                <th>Была исправна</th>
                <th>Починена</th>
            </tr>
            <%
                for (VehicleDTO vehicles1 : vehicles) {
            %>
            <tr>
                <td><%=vehicles1.getType()%></td>
                <td><%=vehicles1.getModel()%></td>
                <td><%=vehicles1.getRegistrationNumber()%></td>
                <td><%=vehicles1.getMass()%></td>
                <td><%=vehicles1.getYearOfManufacture()%></td>
                <td><%=vehicles1.getColor()%></td>
                <td><%=vehicles1.getEngine()%></td>
                <td><%=vehicles1.getMileage()%></td>
                <td><%=vehicles1.getTankVolume()%></td>
                <td><%=vehicles1.getWasBroken()?"Да" : "Нет"%></td>
                <td><%=vehicles1.getFixed()?"Да" : "Нет"%></td>
            </tr>
            <%}%>
        </table>
    </div>
</div>
</body>
</html>
