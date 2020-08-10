<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                ${courierSecurity.username}
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">

            <li class="header">菜单</li>

            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/pages/main.jsp"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <li>
                <a href="${pageContext.request.contextPath}/index/findAllPage"><i
                    class="fa fa-dashboard"></i> <span>客户列表</span></a></li>

            <li>
                <a href="${pageContext.request.contextPath}/courier/findAllPage"><i
                    class="fa fa-dashboard"></i> <span>配送人员</span></a></li>

            <li>
                <a href="${pageContext.request.contextPath}/pages/courier-modify.jsp"><i
                    class="fa fa-dashboard"></i> <span>修改信息</span></a></li>
        </ul>
    </section>
</aside>