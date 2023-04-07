<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.friday.util.DisplayDateUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<link href="styles/admin.css" rel="stylesheet" type="text/css">

<title>后台管理</title>
</head>
<body>
	<script type="text/javascript">
	function go() {
		var url='manageNode.do?page=${currentPage}&administratorId=${administrator.userId}';
		window.location.replace(url);
		alert('删除成功');
		}

		function deleteNode(nodeId){
			$.post('deleteChildNode.json',{
				'childNodeId':nodeId
			},function(data,status){
				if(data=='successful'){
					setTimeout('go()',1000);
				}else{
					alert('删除失败了');
				}
			});
		}
	</script>
	<div class="app layout-fixed-header">
		<div class="sidebar-panel offscreen-left">
			<div class="brand">
				<div class="brand-logo">
					<img height="15" alt="" src="/javacommunity/appimg/logo.png">
				</div>
				<a class="toggle-sidebar hidden-xs hamburger-icon v3"
					href="javascript:;" data-toggle="layout-small-menu"> <span></span>
					<span></span> <span></span> <span></span>
				</a>
			</div>
			<nav role="navigation">
			<ul class="nav">
				<li><a href="javascript:void(0)"> <i class="fa fa-flask"></i> <span>仪表盘</span></a></li>
				<li  class="open"><a href="javascript:void(0)"> <i class="fa fa-leaf"></i> <span>节点管理</span></a></li>
				<li><a href="managerUser.do?page=1&administratorId=${administrator.userId}"> <i class="fa fa-user"></i> <span>用户管理</span></a></li>
				<li><a href="javascript:void(0)"> <i class="fa fa-gears"></i> <span>系统设置</span></a></li>
				<li><a href="javascript:void(0)"> <i class="fa fa-paw"></i> <span>系统工具</span></a></li>
			</ul>
			</nav>
		</div>
		<div class="main-panel">
			<header class="header navbar">
			<div class="brand visible-xs">
				<div class="toggle-offscreen">
					<a class="hamburger-icon visible-xs" href="#"
						data-toggle="offscreen" data-move="ltr"> <span></span> <span></span>
						<span></span>
					</a>
				</div>
				<div class="brand-logo">
					<img height="15" alt="" src="/javacommunity/appimg/logo.png">
				</div>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<p></p>
				<ol class="breadcrumb">
					<li><a href="javascript:void(false);">仪表盘</a></li>
					<li class="active ng-binding">节点管理</li>
				</ol>
				<p></p>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.do">回到主页</a></li>
				<li><a href="#">注销</a></li>
				<li>
					<a href="javascript:void(0);" >
						<img class="header-avatar img-circle " alt="${administrator.username }" src="/javacommunity/appimg${administrator.portrait }">
						<span class="pull-left">管理员：${administrator.username }</span>
					</a>
				</li>

			</ul>
			</header>
			<div class="main-content">
				<div class="panel mb25">
					<div class="panel-heading">节点列表</div>
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="pull-left pl10">
									<label> <input class="form-control" placeholder="搜索...">
									</label> <a class="btn btn-icon-icon btn-sm btn-google-plus btn-round"><i
										class="fa fa-search"></i></a>
								</div>
								<div class="pull-right pr10">
									<a class="btn btn-success" href="#" 	type="button">新增节点</a>
								</div>
							</div>
							<table class="table table-bordered table-striped mb0">
								<thead>
									<tr>
										<th>节点名称</th>
										<th>父节点名称</th>
										<th>节点标题</th>
										<th>帖子数</th>
										<th align="center">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- node：是指子节点 -->
									<c:forEach items="${nodeList }" var="node">
										<tr>
											<td>${node.nodeName}</td>
											<td>${node.parentNode.nodeName }</td>
											<td>${node.nodeTitle}</td>
											<td>${node.postCount}</td>
											<td>
												<a href="editNode.do?nodeId=${node.nodeId}&administratorId=${administrator.userId}" class="btn btn-info btn-outline btn-sm">编辑</a>
												<a href="javascript:void(0)" class="btn btn-danger btn-outline btn-sm" onclick="deleteNode(${node.nodeId})">删除</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="datatable-bottom pt10">
							<div class="pull-left">
								<div class="dataTables_info" id="DataTables_Table_0_info"
									role="status" aria-live="polite">第&nbsp;${currentPage}&nbsp;页&nbsp;&nbsp;
									共&nbsp;${sumPageCount}&nbsp;页</div>
							</div>
							<div class="pull-right">
								<div class="dataTables_paginate paging_bootstrap"
									id="DataTables_Table_0_paginate">
									<ul class="pagination no-margin">
										<c:if test="${currentPage!=1}">
											<li class="prev "><a title="Previous"
												href="manageNode.do?page=${currentPage-1}&administratorId=${administrator.userId}">←</a></li>
										</c:if>
										<li class="active"><a href="#">${currentPage}</a></li>
										<c:if test="${currentPage!=sumPageCount}">
											<li class="next"><a title="Next"
												href="manageNode.do?page=${currentPage+1}&administratorId=${administrator.userId}">→</a></li>
										</c:if>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer class="content-footer"> <nav class="footer-left">
		<ul class="nav">
			<li><a href="javascript:;"> Copyright <i
					class="fa fa-copyright"></i><span> JAVA community</span> 2020. All
					rights reserved
			</a></li>
		</ul>
		</nav> </footer>
	</div>

</body>
</html>
