<%@ page import="Project.Classes.Infrastructure.dto.entity.TypeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="Project.Classes.Infrastructure.dto.entity.VehicleDTO" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.concurrent.atomic.AtomicReference" %>
<%@ page import="java.util.function.Predicate" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>Просмотр машин</title>
    <link href="${pageContext.request.contextPath}/jsp/style.css" rel="stylesheet">
</head>
<body>
    <div class="center flex full-vh">
        <div class="vertical-center">
            <%
                List<VehicleDTO> vehicles = (List<VehicleDTO>) request.getAttribute("cars");
                List<TypeDTO> types = (List<TypeDTO>) request.getAttribute("types");
                Set<String> uniqTypes = types.stream().map(TypeDTO::getName).collect(Collectors.toSet());
                Set<String> uniqModels = vehicles.stream().map(VehicleDTO::getModel).collect(Collectors.toSet());
                Set<String> uniqMColors = vehicles.stream().map(VehicleDTO::getColor).collect(Collectors.toSet());
                Set<String> uniqEngineNames = vehicles.stream().map(VehicleDTO::getEngine).collect(Collectors.toSet());

                AtomicReference<Predicate<VehicleDTO>> filter = new AtomicReference<>(vehicles1 -> true);

                Optional.ofNullable(request.getParameter("type"))
                        .filter(type -> !type.isEmpty())
                        .ifPresent(type -> {
                    filter.set(filter.get().and(vehicleDTO -> vehicleDTO.getModel().equals(type)));
                });
                Optional.ofNullable(request.getParameter("model"))
                        .filter(s -> !s.isEmpty())
                        .ifPresent(s -> {
                    filter.set(filter.get().and(vehicles1 -> vehicles1.getModel().equals(s)));
                });
                Optional.ofNullable(request.getParameter("color"))
                        .filter(s -> !s.isEmpty())
                        .ifPresent(s -> {
                    filter.set(filter.get().and(vehicles1 -> vehicles1.getColor().equals(s)));
                });
                Optional.ofNullable(request.getParameter("engine"))
                        .filter(s -> !s.isEmpty())
                        .ifPresent(s -> {
                    filter.set(filter.get().and(vehicles1 -> vehicles1.getEngine().equals(s)));
                });

                vehicles = vehicles.stream().filter(filter.get()).toList();
            %>
            <a class="button" href="${pageContext.request.contextPath}/">На главную</a>
            <a class="button" href="${pageContext.request.contextPath}/viewCars">Очистить фильтры</a>
            <br/>
            <br/>
            <hr/>
            <br/>
            <table>
                <caption>Машины</caption>
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
                    <th>&#x1F4CB;</th>
                </tr>
                <%
                    if (vehicles.isEmpty()) {%>
                <tr>
                <td colspan="10">Нет машин, соответствующих параметрам</td>
                </tr>
                <%}%>
                <%
                    for (VehicleDTO vehicles1 : vehicles) {
                %>
                <tr>
                    <td><%=types.get(Math.toIntExact(vehicles1.getType()) - 1).getName()%></td>
                    <td><%=vehicles1.getModel()%></td>
                    <td><%=vehicles1.getRegistrationNumber()%></td>
                    <td><%=vehicles1.getMass()%></td>
                    <td><%=vehicles1.getYearOfManufacture()%></td>
                    <td><%=vehicles1.getColor()%></td>
                    <td><%=vehicles1.getEngine()%></td>
                    <td><%=vehicles1.getMileage()%></td>
                    <td><%=vehicles1.getTankVolume()%></td>
                    <td>
                        <button class="button" type="submit">
                            <a href="${pageContext.request.contextPath}/info?id=<%=vehicles1.getId()%>">Выбрать</a>
                        </button>
                    </td>
                </tr>
                <%}%>
            </table>
            <br/>
            <div>
                <hr/>
                <br/>
                <form method="get" action="${pageContext.request.contextPath}/viewCars" class="flex">
                    <div class="button">
                        <p>Тип</p>
                        <select name = "type">
                            <option value=
                                    <%=request.getParameter("type") != null?"selected":""%>>Не выбрано</option>
                            <%for (String s : uniqTypes) {%>
                            <option value="<%=s%>"
                                <%=(request.getParameter("type") != null && s.equals(request.getParameter("type")) ? "selected" : "")%>><%=s%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="button">
                        <p>Модель</p>
                        <select name="model" >
                            <option value=
                                    <%=request.getParameter("model") != null?"selected":""%>>Не выбрано</option>
                            <%for (String s : uniqModels) {%>
                            <option value="<%=s%>"
                                    <%=(request.getParameter("model") != null && s.equals(request.getParameter("model")) ? "selected" : "")%>><%=s%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="button">
                        <p>Двигатель</p>
                        <select name="engine" >
                            <option value=
                                    <%=request.getParameter("engine") != null?"selected":""%>>Не выбрано</option>
                            <%for (String s : uniqEngineNames) {%>
                            <option value="<%=s%>"
                                    <%=(request.getParameter("engine") != null&& s.equals(request.getParameter("engine")) ? "selected" : "")%>><%=s%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="button">
                        <p>Цвет</p>
                        <select name="color" >
                            <option value=
                                    <%=request.getParameter("color") != null?"selected":""%>>Не выбрано</option>
                            <%for (String s : uniqMColors) {%>
                            <option value="<%=s%>"
                                    <%=(request.getParameter("color") != null&& s.equals(request.getParameter("color")) ? "selected" : "")%>><%=s%></option>
                            <%}%>
                        </select>
                    </div>
                    <button class="button" type="submit">Выбрать</button>
                </form>
                <br/>
                <hr/>
            </div>
        </div>
    </div>
</body>
</html>