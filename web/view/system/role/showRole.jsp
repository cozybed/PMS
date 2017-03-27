<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/view/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script type="text/javascript">
        $(document).ready(function () {
            window.menu = function (id) {
                $.zgx_dialog({
                    width: 500,
                    height: 500,
                    title: '权限编辑',
                    url: '<%=path%>/role/toEditRoleMenu?roleId=' + id
                });
            }
        });
        function expByCheckBox() {
            $.ajax({
                type: "POST",
                url: "<%=path%>/role/exportRole",
                data: $('#form1').serialize(),
                success: function (data) {
                    console.log($('#form1').serialize());
                    if (data == "notOk") {
                        alert("系统错误，请联系管理员!");
                    }
                    location = location;
                }
            });
        }
        function expNoData() {
            $.ajax({
                type: "POST",
                url: "<%=path%>/role/exportRole",
                success: function (data) {
                    if (data == "notOk") {
                        alert("系统错误，请联系管理员!");
                    }
                    location = location;
                }
            });
        }
        function impByCheckBox() {
            $.ajax({
                type: "POST",
                url: "<%=path%>/role/importRole",
                success: function (data) {
                    if (data == "notOk") {
                        alert("系统错误，请联系管理员!");
                    }
                    location = location;
                }
            });
        }
        function delByCheckBox() {
            $.ajax({
                type: "POST",
                url: "<%=path%>/role/delRole",
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
<form action="<%=path%>/role/roleList" method="post" id="form1" name="form1">
    <input type="hidden" id="currentPage" name="currentPage" value="1"/>
    <!-- begin breadcrumb -->

    <!-- end breadcrumb -->
    <!-- begin page-header -->

    <!-- end page-header -->

    <!-- begin row -->
    <div class="row" style="height:800px;">
        <!-- begin col-12 -->
        <div class="col-md-12">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">

                    <h4 class="panel-title">
                        角色管理
                    </h4>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">


                        <div class="dataTables_filter">
                            <label>
                                角色名称：<input name="s_role_name" id="s_role_name"
                                            value="${pageBean.paramMap.s_role_name }" type="search" class=""
                                            placeholder="" aria-controls="data-table"/>
                                <a class="btn btn-success btn-sm" href="javascript:$('#form1').submit();"><i
                                        class="fa fa-search"></i> </a>
                            </label>
                        </div>
                        <p>
                            <c:set var="view" value="0"/>
                            <c:set var="update" value="0"/>
                            <c:forEach items="${menulist }" var="q">
                                <c:if test="${fn:containsIgnoreCase(q.menuurl, 'rolelist')}">
                                    <c:forEach items="${mymenulist }" var="e">

                                        <%--<c:out value="${q.id}"></c:out>--%>
                                        <c:if test="${fn:split(e.menu_id, '.')[0] == q.id}">
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'add')}"><a
                                                    class="btn btn-sm btn-success"
                                                    href="<%=path%>/view/system/role/editRole.jsp"><i
                                                    class="fa fa-plus"></i> 添加</a> </c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'delete')}"><a
                                                    data-toggle="modal" class="btn btn-sm btn-success"
                                                    href="javascript:delByCheckBox();"><i
                                                    class="fa fa-times"></i>删除</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'import')}"><a
                                                    class="btn btn-sm btn-success" href="javascript:impByCheckBox();">
                                                <i class="fa fa-sign-out"></i>导入</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'export')}"><a
                                                    class="btn btn-sm btn-success" href="javascript:expByCheckBox();">
                                                <i class="fa fa-sign-out"></i>导出</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'import')}"><a
                                                    class="btn btn-sm btn-success" href="javascript:expNoData();"> <i
                                                    class="fa fa-sign-out"></i>导出模板</a></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'view')}"><c:set var="view"
                                                                                                            value="1"/></c:if>
                                            <c:if test="${fn:containsIgnoreCase(e.menu_id, 'update')}"><c:set
                                                    var="update" value="1"/></c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </p>

                        <!-- #modal-dialog-del -->


                        <c:if test="${view == '1' }">

                            <c:if test="${update == '1' }">
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
                                            角色名称
                                        </th>
                                        <th width="">
                                            备注
                                        </th>
                                        <th width="200">
                                            操作
                                        </th>
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
                                                    ${v.rolename }
                                            </td>
                                            <td>
                                                    ${v.remark }
                                            </td>
                                            <td>
                                                <a href="<%=path %>/role/toUpdateRole?id=${v.id }"><i
                                                        class="fa fa-edit"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <!-- <a href="<%=path %>/role/toViewRole?ROLE_ID=${v.ROLE_ID }"><i class="fa fa-arrow-circle-o-right"></i></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
                                                <a style="cursor:pointer;" onclick="menu('${v.id}');">编辑权限</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>


                            <c:if test="${update == '0' }">
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
                                            角色名称
                                        </th>
                                        <th width="">
                                            备注
                                        </th>
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
                                                    ${v.rolename }
                                            </td>
                                            <td>
                                                    ${v.remark }
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
