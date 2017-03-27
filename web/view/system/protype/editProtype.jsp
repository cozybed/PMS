<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/view/common/common.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript">
        function tj() {
            var errorstr = '';
            var err_po = '提交失败，原因如下：\n\n';
            var ei = 0;
            if ($.trim($("#typename").val()) == '') {
                ei++;
                errorstr = errorstr + ei + '、项目类型名称不能为空\n';
            }

//            if ($.trim($("#pid").val()) == '') {
//                ei++;
//                errorstr = errorstr + ei + '、pid不能为空\n';
//            }
            if (errorstr.length > 0) {
                alert(err_po + errorstr);
            }
            if (errorstr.length == 0) {
                $.ajax({
                    type: "POST",
                    url: "<%=path%>/protype/editProtype",
                    data: $('#form1').serialize(),
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
    <h1 class="page-header">信息编辑</h1>
    <div class="row">
        <div class="col-md-7" style="width: 100%;">
            <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
                <div class="panel-heading">
                    <h4 class="panel-title">项目类型管理</h4>
                </div>
                <div class="panel-body" style="margin-right:30%">
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red">*</span>项目类型名称：</label>
                        <div class="col-md-9">
                            <input type="text" name="typename" id="typename" value="${map.getTypename()}"
                                   class="form-control" style="width: 40%;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">类型等级：</label>
                        <div class="col-md-9">
                            <select name="isfather" id="isfather" class="form-control" style="width:40%;">
                                <option value="1">请选择...(默认一级类型)</option>
                                <option value="1" <c:if test="${map.isfather eq '1'}">selected</c:if>>一级类型</option>
                                <option value="2" <c:if test="${map.isfather eq '2'}">selected</c:if>>二级类型</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">父级类型：</label>
                        <div class="col-md-9">
                            <select name="pid" id="pid" class="form-control" style="width:40%;">
                                <option value="0">请选择...</option>
                                <c:forEach items="${parentProtypes}" var="pp">
                                    <option value="${pp.id}"
                                            <c:if test="${pp.id eq map.pid}">selected</c:if>>${pp.typename}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"><span style="color: red"></span> 备注：</label>
                        <div class="col-md-9">
                            <textarea name="remark" id="remark" class="form-control" rows="5">${map.remark }</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-9">

                            <a class="btn btn-sm btn-success" onclick="javascript:tj();"><i class="fa "></i>提交</a>
                            <a class="btn btn-sm btn-success" href="javascript:history.go(-1);"><i
                                    class="fa "></i>返回</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
