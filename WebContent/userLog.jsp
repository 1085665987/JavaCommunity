<%@page import="com.firday.bean.UserInfo"%>
<%@page import="com.firday.util.CookieUtils"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link href="styles/register.css" rel="stylesheet" type="text/css">

<%@include file="commons/header.jsp"%>
<title>登录</title>
</head>
<body>
	<div class=" container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default ">
				<form action="login.do" method="post">
					<div class="container" style="padding-left: 30px">
						<div class="row">
							<p class="col-md-6 text-center mb20 mt10">登录您的账号</p>
						</div>
						<div class="row">
							<table class="col-md-6">

								<tbody>
									<tr>
										<td class="span"><span>手机号</span></td>
										<td class="reg-input"><input id="account" name="account"
											placeholder="用于登录的手机号" type="number"></td>
									</tr>
									<tr>
										<td class="span"><span>密 码</span></td>
										<td class="reg-input"><input placeholder="请输入6-20位密码"
											id="password" name="password" type="password"></td>
									</tr>
									<tr>
										<td colspan="2"><button
												class="btn btn-success btn-block mt10  btn-lg mb15"
												id="submit">登录</button></td>
									</tr>
								</tbody>
							</table>
							<div class="col-md-3"></div>
						</div>
					</div>
					</form>
				</div>
			</div>

			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">每日格言</h3>
					</div>
					<div class="panel-body">
						<p>内容</p>
					</div>
					<div class="panel-footer">
						<p>—王金杰</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		// 当登录页面，设置header.jsp的头部
		var headerList = document.getElementById("navbar");
		var items = headerList.getElementsByTagName('li');
		//设置'登录'这两个字，背景颜色
		/* items[1].style.backgroundColor='#e7e7e7'; */

		var form = document.getElementsByTagName("form")[0];

		var account = document.getElementById("account");
		var password = document.getElementById("password");

		var submit = document.getElementById("submit");
		submit.onclick = function() {
			if (account.value.length < 10) {
				alert("手机号出错");
				return;
			}
			if (password.value.length < 6) {
				alert("密码不得少于6位");
				return;
			}
			form.submit();
		}
	</script>
</body>
</html>
