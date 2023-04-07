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
	/**
	* 用于操作的函数
	<!-- actionId: -->
	<!-- 1：激活操作 -->
	<!-- 2：设置管理员操作 -->
	<!-- 3：拉黑操作 -->
	*/
	function action(userId,actionId){
		$.post('operationUser.do', {
			"userId" : userId,
			'actionId':actionId
		}, function(data, status) {
			console.log(data);
			if(data=='success'){
				var displayStatus='#displayStatus_'+userId;
				if(actionId==1){
					alert('已成功激活此用户');
					var id='#activation_'+userId;
					var onclickId='action('+userId+',3)';
					$(id).attr('onclick',onclickId);
					$(id).text('拉黑');
					$(id).after(' <a href="javascript:void(0)" id="admin_'+userId+'" onclick="action('+userId+',2)" class="btn btn-danger btn-outline btn-sm setAdmin" >设置管理员</a>');
					actionId='black_'+userId;
					$(id).attr('id',actionId);
					$(displayStatus).text('正常');
				}else if(actionId==3){
					alert('已把此用户列入黑名单');
					var id='#black_'+userId;
					var onclickId='action('+userId+',1)';
					$(id).attr('onclick',onclickId);
					$(id).text('再次激活');
					actionId='activation_'+userId;
					$(id).attr('id',actionId);
					var adminId='#admin_'+userId;
					$(adminId).remove();
					$(displayStatus).text('已拉黑');
				}else if(actionId==2){
					alert('已把此用户设为管理员');
					var id='#admin_'+userId;
					$(id).remove();
					id='#black_'+userId;
					$(id).remove();
				}
			}else{
				if(actionId==1){
					alert('激活此用户失败');
				}else if(actionId==3){
					alert('列入黑名单失败');
				}else if(actionId==2){
					alert('设为管理员失败');
				}
			}
		});
	}
</script>
	<div class="app layout-fixed-header">
		<div class="sidebar-panel offscreen-left">
			<div class="brand">
				<div class="brand-logo">
					<img height="15" alt="" src="/java-china/assets/images/logo1.png">
				</div>
				<a class="toggle-sidebar hidden-xs hamburger-icon v3"
					href="javascript:;" data-toggle="layout-small-menu"> <span></span>
					<span></span> <span></span> <span></span>
				</a>
			</div>
			<nav role="navigation">
			<ul class="nav">
				<li><a href="javascript:void(0)"> <i class="fa fa-flask"></i>
						<span>仪表盘</span></a></li>
				<li><a
					href="manageNode.do?page=1&administratorId=${administrator.userId}">
						<i class="fa fa-leaf"></i> <span>节点管理</span>
				</a></li>
				<li class="open"><a href="javascript:void(0)"> <i
						class="fa fa-user"></i> <span>用户管理</span></a></li>
				<li><a href="javascript:void(0)"> <i class="fa fa-gears"></i>
						<span>系统设置</span></a></li>
				<li><a href="javascript:void(0)"> <i class="fa fa-paw"></i>
						<span>系统工具</span></a></li>
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
					<img height="15" alt="" src="/java-china/assets/images/logo.png">
				</div>
			</div>
			<ul class="nav navbar-nav hidden-xs">
				<p></p>
				<ol class="breadcrumb">
					<li><a href="javascript:void(false);">仪表盘</a></li>
					<li class="active ng-binding">用户管理</li>
				</ol>
				<p></p>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.do">回到主页</a></li>
				<li><a href="#">注销</a></li>
				<li>
					<a href="javascript:void(0);"> <img
						class="header-avatar img-circle " alt="${administrator.username }"
						src="/javacommunity/appimg${administrator.portrait }"> <span
						class="pull-left">管理员：${administrator.username }</span>
					</a>
				</li>
			</ul>
			</header>
			<div class="main-content">
				<div class="panel mb25">
					<div class="panel-heading">用户列表</div>
					<div class="panel-body">
						<div class="table-responsive">
							<div class="row">
								<div class="pull-left pl10">
									<label> <input class="form-control" placeholder="搜索...">
									</label> <a class="btn btn-icon-icon btn-sm btn-google-plus btn-round"><i
										class="fa fa-search"></i></a>
								</div>
								<div class="pull-right pr10">
									<!-- <a class="btn btn-success" href="#" 	type="button">新增节点</a> -->
								</div>
							</div>
							<table class="table table-bordered table-striped mb0">
								<thead>
									<tr>
										<th>用户名</th>
										<th>账号</th>
										<th>注册日期</th>
										<th>用户状态</th>
										<th width="25%" align="center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${userList }" var="user">
										<tr>
											<td>${user.username }</td>
											<td>${user.userAccount }</td>
											<td>${	DisplayDateUtil.longTimeToDateFormatYMDMd(user.registerDate) }</td>
											<c:if test="${user.userStatus==0 }">
												<td id="displayStatus_${ user.userId}">未激活</td>
												<td>
													<!-- actionId: --> <!-- 1：激活操作 --> <!-- 2：设置管理员操作 --> <!-- 3：拉黑操作 -->
													<a href="javascript:void(0)" id="activation_${user.userId}"
													onclick="action(${user.userId},1)"
													class="btn btn-danger btn-outline btn-sm activeAccount">激活账户</a>
												</td>
											</c:if>
											<c:if test="${user.userStatus==1}">
												<td id="displayStatus_${ user.userId}">正常</td>
												<td>
													<!-- actionId: --> <!-- 1：激活操作 --> <!-- 2：设置管理员操作 --> <!-- 3：拉黑操作 -->
													<a href="javascript:void(0)" id="black_${user.userId}"
													onclick="action(${user.userId},3)"
													class="btn btn-danger btn-outline btn-sm disable">拉黑</a> <a
													href="javascript:void(0)" id="admin_${user.userId}"
													onclick="action(${user.userId},2)"
													class="btn btn-danger btn-outline btn-sm setAdmin">设置管理员</a>
												</td>
											</c:if>
											<c:if test="${user.userStatus==2}">
												<td id="displytStatus_${ user.userId}">已拉黑</td>
												<td>
													<!-- actionId: --> <!-- 1：激活操作 --> <!-- 2：设置管理员操作 --> <!-- 3：拉黑操作 -->
													<a href="javascript:void(0)" id="activation_${user.userId}"
													onclick="action(${user.userId},1)"
													class="btn btn-danger btn-outline btn-sm activeAccount">再次激活</a>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="datatable-bottom pt10">
							<div class="pull-left">
								<div class="dataTables_info" id="DataTables_Table_0_info"
									role="status" aria-live="polite">第${currentPage}页
									共${sumPageCount}页</div>
							</div>
							<div class="pull-right">
								<div class="dataTables_paginate paging_bootstrap"
									id="DataTables_Table_0_paginate">
									<ul class="pagination no-margin">
										<c:if test="${currentPage!=1}">
											<li class="prev "><a title="Previous"
												href="managerUser.do?page=${currentPage-1 }&administratorId=${administrator.userId}">←</a></li>
										</c:if>
										<li class="active"><a href="#">${currentPage}</a></li>
										<c:if test="${currentPage!=sumPageCount}">
											<li class="next"><a title="Next"
												href="managerUser.do?page=${currentPage+1 }&administratorId=${administrator.userId}">→</a></li>
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
