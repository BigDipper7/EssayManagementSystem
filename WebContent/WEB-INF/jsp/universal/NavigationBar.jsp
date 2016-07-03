<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">语料库管理系统</a>
    </div>
    <!-- /.navbar-header -->

<!-- Header -->
    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> 用户资料</a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> 设置</a>
                </li>
                <li class="divider"></li>
                <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">

                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" style="height: 34px;">
                            <i class="fa fa-search"></i>
                        </button>
                    </span>
                    </div>
                    <!-- /input-group -->
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/main"><i class="fa fa-dashboard fa-fw"></i> 控制板</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> 语料管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/all">所有语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/deamon">增加语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/delete">删除语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/edit">更新语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/search">搜索语料</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                <!-- <li>
                    <a href="#"><i class="fa fa-table fa-fw"></i> Tables</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-edit fa-fw"></i> Forms</a>
                </li> -->
                <li>
                    <a href="#"><i class="fa fa-user-md fa-fw"></i> 用户<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/showusrs">所有用户列表</a>
                        </li>
                        <li>
                            <a href="#">管理用户</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i> 词<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/word/segment">分词</a>
                        </li>
                        <!-- <li>
                            <a href="#">Buttons</a>
                        </li>
                        <li>
                            <a href="#">Notifications</a>
                        </li> -->
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-fw"></i> 测试选项<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">JSON Test</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/index/rebuild">重建所有索引</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/showusrs">所有用户</a>
                        </li>
                        <!-- <li>
                            <a href="#">Third Level <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level Item</a>
                                </li>
                            </ul>
                            <!-- /.nav-third-level -->
                        <!--</li>-->
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="#"><i class="fa fa-users fa-fw"></i> 我<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="#">用户资料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/logout">登出</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>
