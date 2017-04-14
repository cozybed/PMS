<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title>JScharts Test</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
</head>
<body>
<script type="text/javascript" src="/PMS/assets/js/jscharts.js"></script>
<%--<div id="graph1">JSCharts...</div>--%>
<%--<div id="graph2">JSCharts...</div>--%>
<%--<div id="graph3">JSCharts...</div>--%>
<div class="col-md-8 col-sm-offset-2 text-center">
        <div id="graph1" class="">各个类型分布</div>
        <div id="graph2" class="">各个时间的项目</div>
        <div id="graph3" class="">各个地区项目</div>
</div>
<script type="text/javascript">
    // 定义一个Array数组
    var myData = new Array(['重点地区污染治理工程',20], ['重点地区污染治理工程',10], ['资源节约循环利用重点工程',30], ['大气污染治理',10]);
    var myChart = new JSChart('graph1', 'pie');
    //将数组信息注入mychart
    myChart.setDataArray(myData);
    //绘图
    myChart.setTitle("各个类型分布");
    myChart.setSize(500, 500);
    myChart.setTitleFontSize(20);
    myChart.draw();
</script>
<script type="text/javascript">
    // 定义一个Array数组
    var myData = new Array([1,20], [2,10], [3,30], [4,10], [5,5],[6,20], [7,10], [8,30], [9,10], [10,5],[11,20], [12,10]);
    var myChart = new JSChart('graph2', 'line');
    //将数组信息注入mychart
    myChart.setDataArray(myData);
    //绘图
    myChart.setTitle("各个时间的项目");
    myChart.setSize(400, 400);
    myChart.setTitleFontSize(20);
    myChart.draw();
</script>
<script type="text/javascript">
    // 定义一个Array数组
    var myData = new Array(['南岗区 1',20], ['呼兰区',10], ['市辖区',30], ['香坊区',10],['Unit 5',5]);
    var myChart = new JSChart('graph3', 'pie');
    //将数组信息注入mychart
    myChart.setDataArray(myData);
    //绘图
    myChart.setTitle("各个地区项目");
    myChart.setSize(500, 500);
    myChart.setTitleFontSize(20);
    myChart.draw();
</script>
</body>
</html>