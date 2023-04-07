<%@page import="com.friday.bean.UserInfo"%>
<%@page import="com.friday.util.CookieUtils"%>
<%@page import="com.friday.util.DisplayDateUtil"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link href="styles/index.css" rel="stylesheet" type="text/css">

<%@include file="commons/header.jsp" %>
<title>首页</title>
</head>

<body>
	<div class="container">
		<div class="row" style="padding: 10px;">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading home-panel">
						<div class="box-tab">
							<ul class="nav nav-tabs" role="tablist">
								<li class="active"><a data-toggle="tab" href="#">热门</a></li>
								<li><a data-toggle="tab" href="#">分享发现</a></li>
								<li><a data-toggle="tab" href="#">爬虫</a></li>
								<li><a data-toggle="tab" href="#">问与答</a></li>
								<li><a data-toggle="tab" href="#">开源项目</a></li>
								<li><a data-toggle="tab" href="#">求职招聘</a></li>
								<li><a data-toggle="tab" href="#">申请节点</a></li>
							</ul>
							<div class="tab-content node-content">
								<div id="home" class="tab-pane active">
									<ul class="list-group">
										<c:forEach var="post" items="${postList}">
											<li class="list-group-item">
												<div class="media">
													<a class="media-left" href="javascript:void(false)"> <img
														src="/javacommunity/appimg/moren_man.png"
														class="media-object img-rounded portrait">
													</a>
													<div class="media-body">
														<a class="content" href="queryPostDetail.do?postId=${post.postId }"><span>${post.postTitle }</span></a>
														<span id="type" class="type">${post.postType }</span> <img
															src="/javacommunity/appimg/circle.jpg"
															class="img-circle"> <a
															href="javascript:void(false)" id="articler"
															class="articler">${post.senderName }</a> <img
															src="/javacommunity/appimg/circle.jpg"
															class="img-circle"> <span id="time"
															class="articler" style="color: grey">${DisplayDateUtil.timeDifference(post.sendTime)}</span>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
								<ul class="pager" style="padding: 10px;">
									<li class="previous"><a href="#">查看最新贴</a></li>
									<li class="next"><a href="#">下一页</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">社区节点导航</h3>
					</div>
					<div class="panel-body" id="node_navigation">
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
	<script type="text/javascript">
		$(function() {
			$.post("/javacommunity/queryAllNode.do", function(data, status) {
				console.log(data);
				var msg = jQuery.parseJSON(data).msg;
				if (msg == 'successful') {
					var nodeList = jQuery.parseJSON(data).nodeList;

					$.each(nodeList, function(index, element) {
						var childNodes='';
						$.each(element.childNodeList,function(childNodeIndex,childNodeElement){
							childNodes=childNodes+'<a href="queryNodeDetail.do?nodeId=' + childNodeElement.nodeId +'"  class="btn btn-node btn-xs nodes" role="button">'+childNodeElement.nodeName+'</a>';
						});
						var row='<div class="row" style="padding-bottom:10px;"><div class="col-md-2"><span>'+element.nodeName+'</span></div>'
						+'<div class="col-md-10"><div>'+childNodes+'</div></div></div>';
						$('#node_navigation').append(row);
					});
				}
			});
		});
	</script>
</body>
</html>
