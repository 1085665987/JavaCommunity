<%@page import="com.friday.bean.UserInfo"%>
<%@page import="com.friday.util.CookieUtils"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link href="styles/register.css" rel="stylesheet" type="text/css">
<%@include file="commons/header.jsp"%>
<title>注册</title>
</head>
<body>
	<div class=" container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default ">

					<div class="container" style="padding-left: 30px">
						<div class="row">
							<p class="col-md-6 text-center mb20 mt10">创建一个新账号</p>
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
										<td class="span"><span>确认密码</span></td>
										<td class="reg-input"><input
											placeholder="请使用真实电子邮箱注册，我们不会将你的邮箱地址分享给任何人" id="rePassword"
											name="rePassword" type="password"></td>
									</tr>
									<tr>
										<td class="span"><span>验证码</span></td>
										<td class="reg-input"><input placeholder="请输入验证码"
											id="identifying_code" name="identifying_code" type="number"></td>
									</tr>
									<tr>
										<td colspan="2"><button
												class="btn btn-success btn-block btn-lg mt10 mb15"
												id="submit">创建一个新账号</button></td>
									</tr>
								</tbody>
							</table>
							<div class="col-md-3"></div>
						</div>
					</div>
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
		//当登录页面，设置header.jsp的头部
		var headerList = document.getElementById("navbar");
		var items = headerList.getElementsByTagName('li');
		//设置'注册'这两个字，背景颜色
		/* items[2].style.backgroundColor='#e7e7e7'; */

		var account = document.getElementById("account");
		var password = document.getElementById("password");
		var rePassword = document.getElementById("rePassword");
		var identifying_code = document.getElementById("identifying_code");

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
			if (rePassword.value != password.value) {
				alert("两次密码不一致");
				return;
			}
			var request = new XMLHttpRequest();
			var url = "http://localhost:8080/china-java/register.do?account="
					+ account.value + "&password=" + password.value;
			console.log(url);
			request.open("GET", url, true);
			request.onload = function() {
				var responseText = JSON.parse(this.responseText);
				console.log(responseText.msg);
				if (responseText.msg == 100) {
					window.location.replace("index.jsp");
				} else if (responseText.msg == 102) {
					alert("此账号已存在\n请直接登录");
					window.location.replace("userLogin.jsp");
				} else {
					alert("服务器出错\n请稍后再试");
				}
			}
			request.send();
		}
	</script>
</body>
</html>
