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


        function delByCheckBox() {
            $.ajax({
                type: "POST",
                url: "<%=path%>/user/delUser",
                data: $('#form1').serialize(),
                success: function (data) {
                    if (data == "notOk") {
                        alert("系统错误，请联系管理员!");
                    }
                    location = location;
                }
            });
        }
    </script>

</head>

<body>
<form action="<%=path%>/user/userList" method="post" id="form1" name="form1">
    <input type="hidden" id="currentPage" name="currentPage" value="1"/>
    <!-- begin breadcrumb -->

    <!-- end breadcrumb -->
    <!-- begin page-header -->

    <!-- end page-header -->

    <!-- begin row -->
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">

                    <h4 class="panel-title">
                        用户管理
                    </h4>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">


                        <div class="dataTables_filter">
                            <label>
                                用户姓名：<input name="s_user_name" id="s_user_name"
                                            value="${pageBean.paramMap.s_user_name }" type="search" class=""
                                            placeholder="" aria-controls="data-table"/>
                                <a class="btn btn-success btn-sm" href="javascript:$('#form1').submit();"><i
                                        class="fa fa-search"></i> </a>
                            </label>
                        </div>
                        <c:set var="view" value="0"/>
                        <c:set var="edit" value="0"/>
                        <p>
                            <c:forEach items="${menulist }" var="q">
                                <%--<c:out value="${q.menuurl}"></c:out>--%>
                                <%--<c:out value="${fn:containsIgnoreCase(q.menuurl, 'userlist')}\n"></c:out>--%>
                                <c:if test="${fn:containsIgnoreCase(q.menuurl, 'userlist')}">
                                    <%--<c:out value="true"></c:out>--%>
                                    <c:forEach items="${mymenulist }" var="e">
                                        <%--<c:out value="<${e.menu_id},${q.id}>"></c:out>--%>
                                        <c:if test="${fn:substring(e.menu_id, 0, 1) == q.id}">
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'add')}"><a
                                                    class="btn btn-sm btn-success"
                                                    href="<%=path%>/view/system/user/editUser.jsp"><i
                                                    class="fa fa-plus"></i> 添加</a> </c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'delete')}"><a
                                                    data-toggle="modal" class="btn btn-sm btn-success"
                                                    href="javascript:delByCheckBox();"><i
                                                    class="fa fa-times"></i>删除</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'import')}"><a
                                                    class="btn btn-sm btn-success" href=""> <i
                                                    class="fa fa-sign-out"></i>导入</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'export')}"><a
                                                    class="btn btn-sm btn-success" href=""> <i
                                                    class="fa fa-sign-out"></i>导出</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'import')}"><a
                                                    class="btn btn-sm btn-success" href=""> <i
                                                    class="fa fa-sign-out"></i>导出模板</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'view')}"><c:set var="view"
                                                                                                            value="1"/></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'edit')}"><c:set var="edit"
                                                                                                            value="1"/></c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </p>
                        <c:if test="${view == '1' }">

                            <c:if test="${edit == '1' }">
                                <table
                                        class="table table-striped table-bordered table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th width="3">
                                            <input name="" type="checkbox" value="" id="controlAll"
                                                   onclick="javascript:selectAll();"/>
                                        </th>
                                        <th width="60">
                                            序号
                                        </th>
                                        <th width="">
                                            用户姓名
                                        </th>
                                        <th width="">
                                            用户登录名
                                        </th>
                                        <th width="">
                                            电话
                                        </th>
                                        <th width="">
                                            角色
                                        </th>
                                        <th width="">
                                            备注
                                        </th>
                                        <th width="100">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach items="${pageBean.list }" var="v" varStatus="s">
                                        <tr>
                                            <td>
                                                <input name="delCheckBox" type="checkbox" value="${v.id }"/>
                                            </td>
                                            <td>
                                                    ${(pageBean.currentPage-1)*pageBean.pageSize+s.index+1 }
                                            </td>
                                            <td>
                                                    ${v.username }
                                            </td>
                                            <td>
                                                    ${v.loginname }
                                            </td>
                                            <td>
                                                    ${v.phone }
                                            </td>
                                            <td>
                                                    ${v.rolename }
                                            </td>
                                            <td>
                                                    ${v.remark }
                                            </td>
                                            <td>
                                                <a href="<%=path %>/user/toUpdateUser?id=${v.id }"><i
                                                        class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <!-- <a href="<%=path %>/user/toViewUser?USER_ID=${v.id }"><i class="fa fa-arrow-circle-o-right"></i></a> -->
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </c:if>


                            <c:if test="${edit == '0' }">
                                <table
                                        class="table table-striped table-bordered table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th width="3">
                                            <input name="" type="checkbox" value="" id="controlAll"
                                                   onclick="javascript:selectAll();"/>
                                        </th>
                                        <th width="60">
                                            序号
                                        </th>
                                        <th width="">
                                            用户姓名
                                        </th>
                                        <th width="">
                                            用户登录名
                                        </th>
                                        <th width="">
                                            电话
                                        </th>
                                        <th width="">
                                            角色
                                        </th>
                                        <th width="">
                                            备注
                                        </th>
                                        <th width="100">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach items="${pageBean.list }" var="v" varStatus="s">
                                        <tr>
                                            <td>
                                                <input name="delCheckBox" type="checkbox" value="${v.id }"/>
                                            </td>
                                            <td>
                                                    ${(pageBean.currentPage-1)*pageBean.pageSize+s.index+1 }
                                            </td>
                                            <td>
                                                    ${v.username }
                                            </td>
                                            <td>
                                                    ${v.loginname }
                                            </td>
                                            <td>
                                                    ${v.phone }
                                            </td>
                                            <td>
                                                    ${v.rolename }
                                            </td>
                                            <td>
                                                    ${v.remark }
                                            </td>
                                            <td>
                                                <a href="<%=path %>/user/toUpdateUser?id=${v.id }"><i
                                                        class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <!-- <a href="<%=path %>/user/toViewUserUSER_ID=${v.id }"><i class="fa fa-arrow-circle-o-right"></i></a> -->
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </c:if>

                        </c:if>


                        <jsp:include page="/view/common/page.jsp">
                            <jsp:param name="formId" value="form1"/>
                        </jsp:include>


                    </div>
                </div>
            </div>
            <!-- end panel -->
        </div>
        <!-- end col-12 -->
    </div>
    <!-- end row -->


</form>
</body>
</html>
