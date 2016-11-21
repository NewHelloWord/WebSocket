<%@ page import="com.yl.model.Visit" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>userHistory</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="res/css/bootstrap.min.css">
    <link rel="stylesheet" href="res/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div style="width: 80%;margin: 0 auto;">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>#</th>
                <th>id</th>
                <th>nick</th>
                <th>address</th>
                <th>joinTime</th>
                <th>uid</th>
                <th>ip</th>
            </tr>
        </thead>
        <tbody>
            <%--<c:forEach items="${list}" var="item" varStatus="i">--%>
                <%--<tr>--%>
                    <%--<td>${i.count}</td>--%>
                    <%--<td>${item.id}</td>--%>
                    <%--<td>${item.nick}</td>--%>
                    <%--<td>${item.address}</td>--%>
                    <%--<td>${item.joinTime}</td>--%>
                    <%--<td>${item.uid}</td>--%>
                    <%--<td>${item.ip}</td>--%>
                <%--</tr>--%>
                <%--&lt;%&ndash;<tr><td>${item.nick}</td><td>${item.notes}</td><td><img src="${item.defPath}"/></td></tr>&ndash;%&gt;--%>
            <%--</c:forEach>--%>

            <%
                List<Visit> list = (List<Visit>) request.getAttribute("list");
                for(int i = 0;i<list.size();i++){%>

                <% if(i%2==0){%>
                <tr>
                <%}else{%>
                <tr class="success">
                <% }%>
                    <td><%= i+1%></td>
                    <td><%= list.get(i).getId()%></td>
                    <td><%= list.get(i).getNick()%></td>
                    <td><%= list.get(i).getAddress()%></td>
                    <td><%= list.get(i).getJoinTime()%></td>
                    <td><%= list.get(i).getUid()%></td>
                    <td><%= list.get(i).getIp()%></td>
                </tr>
            <%}
            %>
            <%--<tr class="active">--%>
                <%--<td>1</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>2</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr class="success">--%>
                <%--<td>3</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>4</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr class="info">--%>
                <%--<td>5</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>6</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr class="warning">--%>
                <%--<td>7</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>8</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
            <%--<tr class="danger">--%>
                <%--<td>9</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
                <%--<td>Column content</td>--%>
            <%--</tr>--%>
        </tbody>
    </table>
</div>


</body>
</html>
