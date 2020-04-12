<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.first.util.DisplayDateUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link href="styles/postDetail.css" rel="stylesheet" type="text/css">
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@include file="commons/header.jsp" %>
<title>${post.postTitle}- Java中国</title>
</head>

<body>
	<div class="container">
		<div class="row" style="padding: 10px;">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading post-title">
						<p><a href="#">首页</a> /  <a href="#">${post.postType}</a></p>
						<p class="panel-title">${post.postTitle}</p>
						<h6>by&nbsp;
								<a href="#">${sender.username}</a>&nbsp; 
								<img src="/javacommunity/appimg/circle.jpg" class="img-circle">&nbsp;
								${DisplayDateUtil.timeDifference(post.sendTime)}
						</h6>
					</div>
					<div class="panel-body">
						<p class="post-content">${post.postContent.textContent}</p>
					</div>
					<div class="panel-footer post-footer">
						<a href="#"><span class="heart"></span></a>&nbsp;&nbsp; <span>${post.loveCount}</span>
						<a href="#"><span class="love-count">收藏</span>&nbsp;&nbsp;${post.collectionCount}</a>
						<a href="#"><span class="love-count">评论</span>&nbsp;&nbsp;${post.commentCount}</a>
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
				<%@include file="personalInformationPanel.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>