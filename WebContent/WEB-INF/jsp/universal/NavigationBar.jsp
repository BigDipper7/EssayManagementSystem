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
                            <a href="${pageContext.request.contextPath}/all">所有语料及管理</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/deamon">增加语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/#">语料统计</a>
                        </li>
                        <li>
                            <a href="javascript:show_exp_btn_confirm()">语料导出</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/dataImEx">语料导入、导出Excel</a>
                        </li>
                        <!-- <li>
                            <a href="${pageContext.request.contextPath}/corpus/delete">删除语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/edit">更新语料</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/search">搜索语料</a>
                        </li> -->
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
                            <a href="${pageContext.request.contextPath}/admin/showusrs">所有用户及管理</a>
                        </li>
                        <!-- <li>
                            <a href="#">管理用户</a>
                        </li>-->
                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i> 词典管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/#">常用词查询</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/#">自定义词典管理</a>
                        </li>
                        
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                <li>
                    <a href="#"><i class="fa fa-sitemap fa-fw"></i> API展示<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <!-- li>
                            <a href="#">JSON Test</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/index/rebuild">重建所有索引</a>
                        </li> -->
                        <!-- <li>
                            <a href="${pageContext.request.contextPath}/admin/showusrs">所有用户</a>
                        </li> -->
                        <li>
                            <a href="${pageContext.request.contextPath}/word/segment">分词</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/corpus/search">帮助API展示</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/#">搜索API展示</a>
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
                    <a href="#"><i class="fa fa-align-justify fa-fw"></i> 其他设置<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/index/rebuild">重建索引</a>
                        </li>
                        <li>
                            <a href="#">属性值设置</a>
                        </li>
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


<!-- export btn confirm btn -->
<script type="text/javascript">
	var xmlHttpRequest;	
	
	//XmlHttpRequest对象 
	function createXmlHttpRequest(){    
	    if(window.ActiveXObject){ //如果是IE浏览器    
	        return new ActiveXObject("Microsoft.XMLHTTP");    
	    }else if(window.XMLHttpRequest){ //非IE浏览器    
	        return new XMLHttpRequest();    
	    }
	}
	
	function show_exp_btn_confirm(){  
	    var result = confirm('是否导出所有数据，导出时会清空以前文档，建议不要进行其他操作！');  
	    if(result){
	   	    var url = "${ pageContext.request.contextPath }/admin/persist";       
	   	            
	   	    //1.创建XMLHttpRequest组建    
	   	    xmlHttpRequest = createXmlHttpRequest();    
	   	        
	   	    //2.设置回调函数    
	   	    xmlHttpRequest.onreadystatechange = exp_callback;
	   	        
	   	    //3.初始化XMLHttpRequest组建    
	   	    xmlHttpRequest.open("GET",url,true);
	   	        
	   	    //4.发送请求 
	   	    xmlHttpRequest.send(null); 
	        
	        //window.location = "${ pageContext.request.contextPath }/all";
	    }else{  
	        //alert('不删除！');
	    }  
	} 
	
	function exp_callback() {
        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
	   	    alert('导出成功！');
        }
	}
</script>