<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/view/common/common.jsp" %>

<html>

<body>
<!-- begin #page-container -->
<div id="page-container" class="fade page-sidebar-minified page-sidebar-fixed page-header-fixed in">
    <!-- begin #header -->
    <div id="header" class="header navbar navbar-default navbar-fixed-top">
        <!-- begin container-fluid -->
        <div class="container-fluid">
            <!-- begin mobile sidebar expand / collapse button -->
            <div class="navbar-header">

            </div>
            <!-- end mobile sidebar expand / collapse button -->

            <!-- begin header navigation right -->
            <ul class="nav navbar-nav navbar-right">

                <li class="dropdown navbar-user">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <span
                            class="hidden-xs">${user.username }</span> <b class="caret"></b> </a>
                    <ul class="dropdown-menu animated fadeInLeft">
                        <li class="arrow"></li>
                        <li>
                            <a href="javascript:;">个人资料</a>
                        </li>
                        <li>
                            <a href="javascript:;">个人设置</a>
                        </li>
                        <li>
                            <a href="javascript:;">联系我们</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="javascript:;">安全退出</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="javascript:history.go(-1)"> <i class="fa fa-sign-in"></i> 后退</a>
                </li>
                <li>
                    <a href="<%=path %>/view/nav.jsp" target="iframepage"> <i class="fa fa-sign-out"></i> 首页 </a>
                </li>

                <li>
                    <a href="<%=path %>/logout"> <i class="fa fa-sign-out"></i> 退出 </a>
                </li>
            </ul>
            <!-- end header navigation right -->
        </div>
        <!-- end container-fluid -->
    </div>
    <!-- end #header -->

    <!-- begin #sidebar -->
    <div id="sidebar" class="sidebar">
        <!-- begin sidebar scrollbar -->
        <div data-scrollbar="true" data-height="100%">
            <!-- begin sidebar top -->
            <ul class="nav">
                <li>
                    <a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i
                            class="fa fa-angle-double-left"></i> </a>
                </li>

            </ul>
            <!-- end sidebar top -->

            <!-- begin sidebar nav -->
            <ul class="nav">
                <c:forEach items="${menulist }" var="q">
                    <c:if test="${q.isfather=='1' }">
                        <li class="has-sub">
                            <a href="javascript:;">
                                <b class="caret pull-right"></b>
                                <i class="fa fa-video-camera"></i>
                                <span>${q.menuname }</span>
                            </a>
                            <ul class="sub-menu">
                                <c:forEach items="${menulist }" var="w">
                                    <c:if test="${w.pid==q.id }">

                                        <c:forEach items="${mymenulist }" var="e">
                                            <c:if test="${fn:length(e.menu_id) <= '3' }">
                                                <c:if test="${w.id==e.menu_id }">
                                                    <li><a href="<%=path %>/${w.menuurl }"
                                                           target="iframepage">${w.menuname }</a></li>
                                                </c:if></c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
            <!-- end sidebar nav -->
        </div>
        <!-- end sidebar scrollbar -->
    </div>

    <!-- end #sidebar -->

    <!-- begin #content -->
    <div id="content" class="content">
        <iframe id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="iFrameHeight()"
                src="<%=path %>/view/nav.jsp"></iframe>
    </div>

    <script type="text/javascript" language="javascript">
        function iFrameHeight() {
            var ifm = document.getElementById("iframepage");
            var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;
            if (ifm != null && subWeb != null) {
                ifm.height = subWeb.body.scrollHeight;
            }
        }
    </script>


    <!-- end #content -->


</div>
<!-- end page container -->


</body>
</html>