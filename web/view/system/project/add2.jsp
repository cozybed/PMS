<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/view/common/common.jsp" %>
<%@page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript">
        //      默认的日期是0001-01-01
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
        <c:if test="${map!=null }">

        $(function () {
            $("#province").val("${map.province}");
            $("#city").val("${map.city}");
            $("#area").val("${map.area}");
        });


        </c:if>
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
                                   <c:if test="${map!=null }"></c:if> class="form-control" style="width: 40%;"/>
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
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 项目所属人：</label>
                        <div class="col-md-9">
                            <input type="text" name="username" id="username" value="${map.username }"
                                   class="form-control"
                                   style="width: 40%;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 联系人姓名：</label>
                        <div class="col-md-9">
                            <input type="text" name="contactName" id="contactName" value="${map.contactName }"
                                   class="form-control"
                                   style="width: 40%;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 联系人电话：</label>
                        <div class="col-md-9">
                            <input type="text" name="contactPhone" id="contactPhone" value="${map.contactPhone }"
                                   class="form-control"
                                   style="width: 40%;"/>
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
                            <input type="text" name="budget" id="budget"
                                   value="${map.budget}" class="form-control"
                                   style="width: 40%;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span> 所属类型：</label>
                        <div class="col-md-9">
                            一级类型：
                            <select class="form-control" style="width: 30%;" id="type1" name="type1">
                                <option value="0">请选择一级类型</option>
                                <c:forEach items="${protypes}" var="pp">
                                    <c:if test="${pp.isfather eq '1'}">
                                        <option value="${pp.id}"
                                                <c:if test="${pp.id eq map.type1}">selected</c:if>>${pp.typename}${pp.id}</option>
                                    </c:if>

                                </c:forEach>
                            </select>
                            二级类型：
                            <select class="form-control" style="width: 30%;" id="type2"
                                    name="type2">
                                <option value="0" id="default_type2">请选择二级类型</option>
                                <c:forEach items="${protypes}" var="pp">
                                    <c:if test="${pp.isfather eq '2'}">
                                        <option value="${pp.id}"
                                                <c:if test="${pp.id eq map.type2}">selected</c:if>
                                                class="${pp.pid} type2">${pp.typename}</option>
                                    </c:if>
                                </c:forEach>
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
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red"></span> 项目进展：</label>

                        <div class="col-md-9">
                            项目进展阶段
                            <select class="form-control" style="width: 50%;" id="processId" name="processId">
                                <option value="0">请选择项目进展阶段</option>
                                <c:forEach items="${process}" var="pp">
                                    <option value="${pp.id}"
                                            <c:if test="${pp.id eq map.processId}">selected</c:if>>
                                            ${pp.pname}
                                    </option>
                                </c:forEach>
                            </select>
                            项目进展情况
                            <textarea name="processDiscription" id="processDiscription" class="form-control"
                                      rows="5">${map.processDiscription }</textarea>
                        </div>
                        <%--项目进展情况--%>
                        <%--<div class="col-md-9">--%>
                        <%--<textarea name="processDiscription" id="processDiscription" class="form-control"--%>
                        <%--rows="5">${map.processDiscription }</textarea>--%>
                        <%--</div>--%>
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
<script type="text/javascript">

    $('#type1').change(function (index, value) {
//        alert($("#type1").select().val())
        var fatherId = "." + $("#type1").select().val();
        var options = $("option.type2");

        $("#type2").children().each(function () {
            $(this).hide();
        })
        $("#type2").children(fatherId).each(function () {
            $(this).show();
        })
        $("#default_type2").show();
        $("#type2").val("0")
    });

</script>
</body>

</html>