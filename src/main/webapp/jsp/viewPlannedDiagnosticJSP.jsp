<%@ page import="java.util.List" %>
<%@ page import="Project.Classes.Infrastructure.dto.entity.OrderDTO" %>
<%@ page import="Project.Classes.Infrastructure.dto.service.VehiclesService" %>
<%@ page import="Project.Classes.Infrastructure.dto.entity.VehicleDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>
<head>
    <meta charset="UTF-8">
    <title>Диагностика</title>
    <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
</head>
<body>
<div class="center flex full-vh">
    <div class="vertical-center">
        <%
            List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
            long time  = (long) request.getAttribute("timeToShow");
            VehiclesService vehiclesService = (VehiclesService) request.getAttribute("vehicles");
        %>
        <a class="button" href="${pageContext.request.contextPath}/">На главную</a>
        <br/>
        <br/>
        <hr/>
        <br/>
        <table>
            <caption>Диагностика машин</caption>
            <caption>Время плаоновой диагностики: 5 минут</caption>
            <caption>Время после предыдущей диагностики: <%= time / 60%> минут(ы)</caption>
            <caption>Машины после послендней диагностики</caption>
            <tr>
                <th>Тип</th>
                <th>Модель</th>
                <th>Номер</th>
                <th>Вес</th>
                <th>Двигатель</th>
                <th>Цвет</th>
                <th>Пробег</th>
            </tr>
            <%
                for (OrderDTO order : orders) {
                    VehicleDTO vehicle = vehiclesService.get(order.getVehicleId());
            %>
            <tr>
                <td><%=vehicle.getType()%></td>
                <td><%=vehicle.getModel()%></td>
                <td><%=vehicle.getRegistrationNumber()%></td>
                <td><%=vehicle.getMass()%></td>
                <td><%=vehicle.getEngine()%></td>
                <td><%=vehicle.getColor()%></td>
                <td><%=vehicle.getMileage()%></td>
            </tr>
            <%}%>
        </table>
    </div>
</div>
</body>
</html>--%>
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
            long time = (long) request.getAttribute("timeToShow");
            List<VehicleDTO> vehicles =(List<VehicleDTO>) request.getAttribute("vehicles");

        %>
        <a class="button" href="${pageContext.request.contextPath}/">На главную</a>
        <br/>
        <br/>
        <hr/>
        <br/>
        <table>
            <caption>Время плаоновой диагностики: 5 минут</caption>
            <caption>Время после предыдущей диагностики: <%= time / 60%> минут(ы)</caption>
            <caption>Машины после послендней диагностики</caption>
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
                for (VehicleDTO vehicle : vehicles) {
            %>
            <tr>
                <td><%=vehicle.getType()%>
                </td>
                <td><%=vehicle.getModel()%>
                </td>
                <td><%=vehicle.getRegistrationNumber()%>
                </td>
                <td><%=vehicle.getMass()%>
                </td>
                <td><%=vehicle.getYearOfManufacture()%>
                </td>
                <td><%=vehicle.getColor()%>
                </td>
                <td><%=vehicle.getEngine()%>
                </td>
                <td><%=vehicle.getMileage()%>
                </td>
                <td><%=vehicle.getTankVolume()%>
                </td>
                <td><%=vehicle.getWasBroken() ? "Нет" : "Да"%>
                </td>
                <td><%=vehicle.getFixed() ? "Да" : "Нет"%>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
</div>
</body>
</html>
