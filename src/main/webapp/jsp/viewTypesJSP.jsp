<%@ page import="Project.Classes.Infrastructure.dto.entity.TypeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %><%--
  Created by IntelliJ IDEA.
  User: 23102
  Date: 27.06.2024
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>Просмотр типов машин</title>
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
                String sortKey = null;
                String order = null;
                if (request.getParameter("name") != null) sortKey = "name";
                if (request.getParameter("tax") != null) sortKey = "tax";
                if (request.getParameter("asc") != null) order = "asc";
                if (request.getParameter("desc") != null) order = "desc";
            %>
            <%if (sortKey != null) {%>
                <%
                    String clearPath = "http://localhost:8081/ProjectMavenAutopark_war_exploded/viewTypes";
                    String ascPath = "?" + sortKey + "&asc";
                    String descPath = "?" + sortKey +"&desc";
                %>
            <div>
                <a class="button" href="<%=descPath%>">Сортировать по убыванию</a>
                <a class="button" href="<%=ascPath%>">Сортировать по возрастанию</a>
                <a class="button" href="<%=clearPath%>">Очистить параметры поиска</a>
            </div>
            <br/>
            <hr/>
            <br/>
            <%}%>
            <table>
                <caption>Типы машин</caption>
                <tr>
                    <th>Название</th>
                    <th>Коэффициент Дорожного налога</th>
                </tr>
                <%
                    List<TypeDTO> dtoList = (List<TypeDTO>) request.getAttribute("types");

                    Comparator<TypeDTO> comparator = null;
                    if (sortKey != null && sortKey.equals("name")) {
                        comparator = Comparator.comparing(TypeDTO::getName);
                    }
                    if (sortKey != null && sortKey.equals("tax")) {
                        comparator = Comparator.comparingDouble(TypeDTO::getCoefTaxes);
                    }
                    if (order != null && comparator != null && order.equals("desc")){
                        comparator = comparator.reversed();
                    }
                    if (comparator != null) {
                        dtoList.sort(comparator);
                    }
                    for (TypeDTO dto : dtoList) {
                %>
                <tr>
                <td><%=dto.getName()%></td>
                <td><%=dto.getCoefTaxes()%></td>
                </tr>
                <%}%>
            </table>
            <%if(!dtoList.isEmpty()){%>
            <p>Минимальный коэффициент:
                <strong><%=dtoList.stream().map(TypeDTO::getCoefTaxes).min(Double::compare).get()%></strong>
            </p>
            <p>Максимальный коэффициент:
                <strong><%=dtoList.stream().map(TypeDTO::getCoefTaxes).max(Double::compare).get()%></strong>
            </p>
            <%}%>
            <br/>
            <hr/>
            <br/>
            <div>
                    <% if (request.getParameter("name") == null){%><a class="button" href="${pageContext.request.contextPath}/viewTypes?name">Сортировать по названию</a><%}%>
                    <% if (request.getParameter("tax") == null){%><a class="button" href="${pageContext.request.contextPath}/viewTypes?tax">Сортировать по коэффициенту</a><%}%>
            </div>
        </div>
    </div>
</body>
</html>