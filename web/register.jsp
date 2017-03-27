<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0, user-scalable=no" name="viewport"/>
<title></title>
<link href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet"/>
<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
<link href="assets/css/animate.min.css" rel="stylesheet"/>
<link href="assets/css/style.min.css" rel="stylesheet"/>
<link href="assets/css/style-responsive.min.css" rel="stylesheet"/>
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme"/>
<style>
    .login-em {
        position: absolute;
        margin-top: 12%;
        padding: 5px;
        width: 120px;
        height: 20px;
        font-size: 12px;
        line-height: 10px;
        color: #FC8523;
        background: #FFFCDF;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
        display: none;
    }
</style>
</head>
<body>
<div class="login-cover">
    <div class="login-cover-image">
        <img src="assets/img/login_bg.jpg" style="width: 100%"/>
    </div>
</div>
<div class="fade">
    <div class="login login-v2" data-pageload-addclass="animated flipInX">
        <div class="login-content">
            <div class="brand">
                <h2 class="text-center text-white" style="margin: 0 0 20px;">信息注册</h2>
            </div>
            <form name="form1" id="form1" method="post" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0"/>


                <!-- begin row -->
                <div class="row">

                    <div class="col-md-7" style="width: 100%;">
                        <!-- begin panel -->
                        <div data-sortable-id="form-stuff-1">

                            <div class="panel-body" style="margin-right:5%">

                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span>
                                        用户姓名：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="username" id="username" value="${map.username }"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 登录名：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="loginname" id="loginname" value="${map.loginname }"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 密码：</label>
                                    <div class="col-md-9">
                                        <input type="password" name="loginpass" id="loginpass" value="${map.loginpass }"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span>
                                        确认密码：</label>
                                    <div class="col-md-9">
                                        <input type="password" name="LOGIN_PASS_1" id="LOGIN_PASS_1"
                                               value="${map.loginpass }" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 角色：</label>
                                    <div class="col-md-9">
                                        <select class="form-control" id="role_id" name="role_id">
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 电话：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="phone" id="phone" value="${map.phone }"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red">*</span> 邮箱：</label>
                                    <div class="col-md-9">
                                        <input type="text" name="email" id="email" value="${map.email }"
                                               class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label"><span style="color: red"></span> 性别：</label>
                                    <div class="col-md-9">
                                        <select class="form-control" id="sex" name="sex">
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </div>
                                </div>
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
                                        <c:if test="${sta!='view' }">
                                            <a class="btn btn-sm btn-success" onclick="javascript:tj();"><i
                                                    class="fa "></i>提交</a>
                                        </c:if>
                                        <a class="btn btn-sm btn-success" href="<%=path %>/user/userList"><i
                                                class="fa "></i>返回</a>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- end panel -->
                    </div>


                </div>
                <!-- end row -->

            </form>
        </div>
    </div>
</div>
<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
<script type="text/javascript">
    function tj() {
        var errorstr = '';
        var err_po = '提交失败，原因如下：\n\n';
        var ei = 0;
        if ($("#username").val().trim() == '') {
            ei++;
            errorstr = errorstr + ei + '.用户姓名不能为空\n';
        }
        if ($("#loginname").val().trim() == '') {
            ei++;
            errorstr = errorstr + ei + '.登录名不能为空\n';
        }
        if ($("#loginpass").val().trim() == '') {
            ei++;
            errorstr = errorstr + ei + '.密码不能为空\n';
        }
        if ($("#LOGIN_PASS_1").val().trim() == '') {
            ei++;
            errorstr = errorstr + ei + '.确认密码不能为空\n';
        }
        if ($("#loginpass").val() != $("#LOGIN_PASS_1").val()) {
            ei++;
            errorstr = errorstr + ei + '.两次密码不一致\n';
        }
        if ($("#role_id").val().trim() == '') {
            ei++;
            errorstr = errorstr + ei + '.用户角色不能为空\n';
        }
        if ($("#email").val() == "") {
            ei++;
            errorstr = errorstr + ei + '.邮箱地址不能为空\n';
        } else if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            ei++;
            errorstr = errorstr + ei + '.邮箱格式不正确\n';
        }
        if ($("#phone").val() == "") {
            ei++;
            errorstr = errorstr + ei + '.手机号码不能为空\n';
        } else if (!$("#phone").val() == "") {
            var mobile = $.trim($("#phone").val());
            var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
            var isPhone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
            if (mobile.substring(0, 1) == 1) {
                if (!isMobile.exec(mobile) && mobile.length != 11) {
                    ei++;
                    errorstr = errorstr + ei + '.手机号码格式不正确\n';
                }
            } else if (mobile.substring(0, 1) == 0) {
                if (!isPhone.test(mobile)) {
                    ei++;
                    errorstr = errorstr + ei + '.固话号码格式不正确\n';
                }
            } else {
                ei++;
                errorstr = errorstr + ei + '.电话号码格式不正确。请正确填写电话号码，例如:13511111111或010-11111111\n';
            }
        }
        if ($("#loginname").val().trim() != '' && $("#id").val().trim() == '') {
            $.ajax({
                type: "POST",
                async: false,
                url: "<%=path%>/user/searchYhm",
                data: $('#form1').serialize(),
                success: function (data) {
                    if (data == "n") {
                        ei++;
                        errorstr = errorstr + ei + '.登录名不能重复\n';
                    }
                }
            });
        }

        if (errorstr.length > 0) {
            alert(err_po + errorstr);
        }
        if (errorstr.length == 0) {
            $.ajax({
                type: "POST",
                url: "<%=path%>/user/editUser",
                data: $('#form1').serialize(),
                success: function (data) {
                    if (data == "notOk") {
                        alert("系统错误，请联系管理员!");
                    } else {
                        window.location.href = "<%=path%>/user/userList";
                    }
                }
            });
        }
    }


    $(function () {

        jQuery("#role_id").empty();
        $.ajax({
            type: "POST",
            url: "<%=path %>/user/getRoleForSelect",
            async: false,
            dataType: 'json',
            data: $('#form1').serialize(),
            success: function (data) {
                jQuery("<option value=''>请选择</option>").appendTo("#role_id");
                for (var i = 1; i < data.length; i++) {
                    jQuery("<option value='" + data[i].id + "'>" + data[i].rolename + "</option>").appendTo("#role_id");
                }
            }
        });

        $("#role_id").val("${map.role_id}");
        $("#sex").val("${map.sex}");
    });

</script>

</body>
</html>
