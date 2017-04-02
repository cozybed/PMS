<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/view/common/common.jsp" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript">
        function tj() {
            var errorstr = '';
            var err_po = '提交失败，原因如下：\n\n';
            var ei = 0;
            if ($.trim($("#proname").val()) == '') {
                ei++;
                errorstr = errorstr + ei + '、项目名称不能为空\n';
            }

            if (errorstr.length > 0) {
                alert(err_po + errorstr);
            }

            if (errorstr.length == 0) {
                $.ajax({
                    type: "POST",
                    url: "<%=path%>/project/editProject",
                    data: $("#form1").serialize(),
                    success: function (data) {
                        if (data == "notOk") {
                            alert("系统错误，请联系管理员!");
                        } else {
                            window.location.href = "<%=path%>/protype/protypeList";
                        }
                    }
                });
            }
        }

    </script>
</head>
<body>

<form name="form1" id="form1" method="post" class="form-horizontal">
    <input type="hidden" id="id" name="id"
           <c:if test="${map==null }">value="0"</c:if>
           <c:if test="${map!=null }">value="${map.id }"</c:if> />
    <!-- end breadcrumb -->
    <!-- begin page-header -->
    <h1 class="page-header">项目申报</h1>
    <!-- end page-header -->

    <!-- begin row -->
    <div class="row">

        <div class="col-md-7" style="width: 100%;">
            <!-- begin panel -->
            <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
                <div class="panel-heading">
                    <h4 class="panel-title">基本信息</h4>
                </div>
                <div class="panel-body" style="margin-right:30%">

                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 项目名称：</label>
                        <div class="col-md-9">
                            <input type="text" name="proname" id="proname" value="${map.proname }" class="form-control"
                                   style="width: 40%;"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 报送部门：</label>
                        <div class="col-md-9">
                            <input type="text" name="sourceDepartment" id="sourceDepartment"
                                   value="${map.sourceDepartment }"
                                   <c:if test="${map!=null }">readonly</c:if> class="form-control" style="width: 40%;"/>
                        </div>
                    </div>

                    <div class="form-group">

                        <label class="col-md-3 control-label"><span style="color: red">*</span> 建设工期：</label>
                        <div class="col-md-9">
                            <input type="date" name="startTime" id="startTime" value="${map.startTime }"
                                   class="form-control" style="width: 40%;"/>至
                            <input type="date" name="endTime" id="endTime" value="${map.startTime }"
                                   class="form-control" style="width: 40%;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 建设规模：</label>
                        <div class="col-md-9">
                            <input type="text" name="scale" id="scale" value="${map.scale }" class="form-control"
                                   style="width: 40%;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 总投资（万元）：</label>
                        <div class="col-md-9">
                            <input type="text" name="budget" id="budget" value="${map.budget }" class="form-control"
                                   style="width: 40%;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 所属类型：</label>
                        <div class="col-md-9">
                            一级类型：
                            <select class="form-control" style="width: 30%;" id="type1_id" name="type1_id">
                                <option value="0">请选择一级类型</option>
                                <c:forEach items="${parentProtypes}" var="pp">
                                    <option value="${pp.id}"
                                            <c:if test="${pp.id eq map.pid}">selected</c:if>>${pp.typename}</option>
                                </c:forEach>
                            </select>
                            二级类型：
                            <select class="form-control" style="width: 30%;" id="type2_id"
                                    name="type2_id">
                                <option value="0">请选择二级类型</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 建设地址：</label>
                        <div class="col-md-9">
                            省及直辖市
                            <select id="province" class="province form-control" name="province"></select>
                            市
                            <select id="city" class="city form-control" name="city"></select>
                            区
                            <select id="area" class="area form-control" name="area"></select>
                            详细地址
                            <input type="text" name="address" id="address" value="${map.address }"
                                   class="form-control" style="width: 40%;"/>


                        </div>
                    </div>

                    <!-- <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 项目联系人：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="email" id="email" value="${map.personname }" class="form-control"  style="width: 40%;"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span>联系人手机号码：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="phone" id="phone" value="${map.phone }" class="form-control"  style="width: 40%;"/>
                                    </div> -->

                    <%--<div class="form-group">--%>
                    <%--<label class="col-md-3 control-label"><span style="color: red">*</span> 建设地址：</label>--%>
                    <%--<div class="col-md-9">--%>
                    <%--<select class="form-control" style="width: 20%;" id="cityId" name="cityId">--%>
                    <%--<option>---请选择---</option>--%>
                    <%--<option value="hns">河南省</option>--%>
                    <%--<option value="hbs">河北省</option>--%>
                    <%--<option value="sds">山东省</option>--%>
                    <%--</select>--%>
                    <%--市--%>
                    <%--<select class="form-control" style="width: 20%;" id="areaId" name="areaId">--%>
                    <%--<option>---请选择---</option>--%>
                    <%--</select>--%>
                    <%--<script type="text/javascript">--%>
                    <%--var hns = new Array("hns", "a", "b", "c");--%>
                    <%--var hbs = new Array("hbs", "d", "e", "f");--%>
                    <%--var sds = new Array("sds", "g", "h", "i");--%>
                    <%--var datas = new Array(hns, hbs, sds);--%>
                    <%--function x(v) {--%>
                    <%--var s2 = document.getElementById("s2");--%>
                    <%--var o = s2.getElementsByTagName("option");--%>
                    <%--for (var k = 0; k < o.length; ++k) {--%>
                    <%--s2.removeChild(o[k]);--%>
                    <%----k;--%>
                    <%--}--%>
                    <%--for (var i = 0; i < datas.length; ++i) {--%>
                    <%--if (v == datas[i][0]) {--%>
                    <%--for (var j = 1; j < datas[i].length; ++j) {--%>
                    <%--var ops = document.createElement("option");--%>
                    <%--ops.innerHTML = datas[i][j];--%>
                    <%--s2.appendChild(ops);--%>
                    <%--}--%>
                    <%--}--%>
                    <%--}--%>
                    <%--}--%>
                    <%--</script>--%>
                    <%--区/县--%>
                    <%--<input type="text" name="address" id="address" value="${map.address }"--%>
                    <%--class="form-control" style="width: 40%;"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>


                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red"></span> 备注：</label>
                        <div class="col-md-9">
                                <textarea name="remark" id="remark" class="form-control"
                                          rows="5">${map.remark }</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-9">
                            <a class="btn btn-sm btn-success" onclick="javascript:tj();"><i class="fa "></i>提交</a>
                            <a class="btn btn-sm btn-success" href="<%=path %>/menu/mainMenu"><i class="fa "></i>返回</a>
                        </div>
                    </div>

                </div>
            </div>
            <!-- end panel -->
        </div>


    </div>
    <!-- end row -->
</form>
<script type="text/javascript" src="<%=path%>/assets/js/area.js"></script>

</body>

</html>