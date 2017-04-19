<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>JScharts Test</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
</head>
<body>
<script type="text/javascript" src="/PMS/assets/js/jscharts.js"></script>
<%--<div id="graph1">JSCharts...</div>--%>
<%--<div id="graph2">JSCharts...</div>--%>
<%--<div id="graph3">JSCharts...</div>--%>
<div class="col-md-8 col-sm-offset-2 text-center">
    <div id="graph1" class="">各个类型分布</div>
    <div id="graph3" class="">各个地区项目</div>
</div>
<script type="text/javascript">
    $(function () {
        var areasData =[];
        var typesData =[];
        $.getJSON('<%=path%>/project/getStats',function (data) {
            $.each(data[0],function (key, val) {

                if (key=='null'){key='无一级类型'}
                typesData.push([key,val]);

            });
            $.each(data[1],function (key, val) {
                areasData.push([key,val]);

            });
            var areasChart = new JSChart('graph1', 'pie');
            //将数组信息注入mychart
            areasChart.setDataArray(typesData);
            //绘图
            areasChart.setTitle("各个类型分布");
            areasChart.setSize(500, 500);
            areasChart.setTitleFontSize(20);
            areasChart.draw();

            var typesChart = new JSChart('graph3', 'pie');
            //将数组信息注入mychart
            typesChart.setDataArray(areasData);
            //绘图
            typesChart.setTitle("各个地区项目");
            typesChart.setSize(500, 500);
            typesChart.setTitleFontSize(20);
            typesChart.draw();
        });
    });
</script>

</body>
</html>