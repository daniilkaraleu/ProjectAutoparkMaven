<%@ page import="Project.Classes.Infrastructure.dto.entity.VehicleDTO" %>
<%@ page import="Project.Classes.Infrastructure.dto.entity.TypeDTO" %>
<%@ page import="Project.Classes.Infrastructure.dto.entity.RentDTO" %>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Информация о машине</title>
        <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="center flex full-vh">
            <div class="vertical-center">
                <a class="button" href="${pageContext.request.contextPath}/">На главную</a>
                <br/>
                <br/>
                <hr/>
                <br/>
                <%
                    VehicleDTO vehicles = (VehicleDTO) request.getAttribute("car");
                TypeDTO types = (TypeDTO) request.getAttribute("type");
                List <RentDTO> rents = (List <RentDTO>) request.getAttribute("rents");%>

                <table>
                    <caption>Машина</caption>
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
                        <th>Расход</th>
                        <th>Коэффициент Дорожного налога</th>
                        <th>km на полный бак</th>
                    </tr>
                    <tr>
                        <td><%=types.getName()%></td>
                        <td><%=vehicles.getModel()%></td>
                        <td><%=vehicles.getRegistrationNumber()%></td>
                        <td><%=vehicles.getMass()%></td>
                        <td><%=vehicles.getYearOfManufacture()%></td>
                        <td><%=vehicles.getColor()%></td>
                        <td><%=vehicles.getEngine()%></td>
                        <td><%=vehicles.getMileage()%></td>
                        <td><%=vehicles.getTankVolume()%></td>
                        <td><%=10%></td>
                        <td><%=types.getCoefTaxes()%></td>
                        <td><%=vehicles.getTankVolume()*10/100%></td>
                    </tr>
                </table>
                <br/>
                <hr/>
                <br/>
                <table>
                    <caption>Аренда</caption>
                    <tr>
                        <th>Дата</th>
                        <th>Стоимость</th>
                    </tr>
                    <%for (RentDTO rents1 : rents) {%>
                    <tr>
                        <th><%=rents1.getRentDate()%></th>
                        <th><%=rents1.getRentCost()%></th>
                    </tr>
                    <%}%>
                </table>


            </div>
        </div>
    </body>
</html>
