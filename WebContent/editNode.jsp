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
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>后台管理</title>
</head>
<body>
	<script type="text/javascript">

	</script>
	<div class="app layout-fixed-header">
		<div class="sidebar-panel offscreen-left">
			<div class="brand">
				<div class="brand-logo">
					<img height="15" alt="javacommunity"
						src="/javacommunity/appimg/logo.png">
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
				<li class="open"><a href="javascript:void(0)"> <i
						class="fa fa-leaf"></i> <span>节点管理</span></a></li>
				<li><a
					href="managerUser.do?page=1&administratorId=${administrator.userId}">
						<i class="fa fa-user"></i> <span>用户管理</span>
				</a></li>
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
					<img height="15" alt="javacommunity" src="/javacommunity/appimg/logo.png">
				</div>
			</div>
			<ul class="nav navbar-nav">
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
				<li><a href="javascript:void(0);"> <img
						class="header-avatar img-circle " alt="${administrator.username }"
						src="/javacommunity/appimg${administrator.portrait }"> <span
						class="pull-left">管理员：${administrator.username }</span>
				</a></li>

			</ul>
			</header>
			<div class="main-content">
				<div class="panel mb25">
					<div class="panel-heading border">编辑节点</div>
					<div class="panel-body">
						<div class="row no-margin">
							<div class="col-lg-12">
								<form class="form-horizontal bordered-group" role="form"
									action="updateNode.do" method="post" id="form_upload">
									<input value="${node.nodeId }" name="childNodeId" type="hidden">
									<div class="form-group">
										<label for="nodeName" class="col-sm-2 control-label">节点名称</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="node_name" name="childNodeName"
												value="${node.nodeName }">
										</div>
									</div>

									<div class="form-group">
										<label for="nodeTitle" class="col-sm-2 control-label">节点标题</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="node_title" name="childNodeTitle"
												placeholder="请输入节点标题" value="${node.nodeTitle}">
										</div>
									</div>

									<div class="form-group">
										<label for="name" class="col-sm-2 control-label">父节点</label>
										<div class="col-sm-8">
											<select class="form-control" id="parent_select" name="parentNodeId" >
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label">修改节点图标</label>
										<div class="col-sm-8">
											<img src="appimg${user.portrait}" class="img-rounded "
												id="img-change" style="width: 73px; height: 73px;"> <input
												type="file" name="fileUpload"
												class="fileUpload ladda-button" id="fileUpload"
												data-style="zoom-out"
												accept="image/png,image/jpg,image/jpeg"
												style="display: none;">
										</div>
									</div>

									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="button"
												class="btn  btn-success btn-sm submit ladda-button"
												data-style="zoom-out" id="upload">
												<span class="ladda-label">点击上传</span>
											</button>
										</div>
									</div>
								</form>
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
	<script type="text/javascript">
		$(function() {
			$.post('queryAllParentNode.json', function(data, status) {
				var msg = jQuery.parseJSON(data).msg; //msg：true 或者是false
				if (msg) {
					var parentSelect = $('#parent_select');
					$.each(jQuery.parseJSON(data).rs, function(index, element) {
						var parentNode = '';
						if ("${node.parentNode.nodeName}" == element.nodeName) {
							parentNode = '<option selected="selected" value="'+element.nodeId+'">'
									+ element.nodeName + '</option>';
						} else {
							parentNode = '<option value="'+element.nodeId+'">' + element.nodeName
									+ '</option>';
						}
						console.log(parentNode);
						parentSelect.append(parentNode);
					});
				}
			});
		});
		function go() {
			window.history.go(-1);
		}
		$("#upload").click(function(){
			var nodeName=$('#node_name').val();
			var nodeTitle=$('#node_title').val();
			var selectedValue=$("#parent_select option:selected").val();
			console.log(selectedValue);
			if(nodeName==''){
				 alert('节点名称是必填项');
				 return;
			}
			if(nodeTitle==''){
				 alert('节点标题是必填项');
				 return ;
			}
			$('#form_upload').submit();
		})
	</script>
</body>
</html>
