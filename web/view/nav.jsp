<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    if (request.getSession().getAttribute("user") == null) {
        response.sendRedirect(path + "/login.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link href='../assets/css/bootstrap.min.css' rel='stylesheet' type='text/css'>

    <script src="../assets/js/jquery-1.9.1.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="<%=path%>/assets/js/highcharts.js"></script>
    <script type="text/javascript" src="<%=path %>/assets/js/themes/skies.js"></script>
    <script type="text/javascript" src="<%=path%>/assets/js/exporting.js"></script>
</head>
<body>
<div class="frame-cover">
    <div class="frame-cover-image">
        <img src="assets/img/welcome.gif" style="width: 100%"/>
    </div>
</div>
<div class="row" style="margin-top:30px">
    <button style="float:left;margin-left:100px" class="btn"><a href="">统计查询</a></button>
    <div class="btn-group dropup" style="float:right">
        <button style="float:right;margin-right:100px" class="btn btn-primary btn-squared dropdown-toggle"
                data-toggle="dropdown" onclick="clickInfo()">信息管理
        </button>
        <ul class="dropdown-menu">
            <li>
                <a href="<%=path %>/user/userList">
                    用户信息管理
                </a>
            </li>
            <li>
                <a href="<%=path %>/menu/menuList">
                    菜单信息管理
                </a>
            </li>
        </ul>
    </div>
</div>


</body>
</html>
