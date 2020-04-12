<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@page import="com.first.util.DisplayDateUtil"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="styles/index.css" rel="stylesheet" type="text/css">
<link href="styles/main.css" rel="stylesheet" type="text/css">

<%@include file="commons/header.jsp"%>
<title>${node.nodeName}</title>
</head>
<body>
	<div class="container">
		<div class="row" style="padding: 10px;">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="media">
							<div class="media-left">
								<img src="moren_man.png" class="media-object"
									style="width: 73px">
							</div>
							<div class="media-body">
								<div class="pull-left">
									<span>China Java › ${node.nodeName}</span> <span></span>
									<p class="mt10 mb10">${node.nodeTitle}</p>
								</div>
								<div class="pull-right text-right mr10">
									<span>帖子总数 ${node.postList.size()}</span>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${node.postList!=null&&node.postList.size()>0}">
						<div class="panel-body home-panel">
							<div class="box-tab">
								<div class="tab-content">
									<div class="tab-pane active">
										<div id="home" class="tab-pane active">

											<ul class="list-group">
												<c:forEach var="post" items="${node.postList}">
													<li class="list-group-item">
														<div class="media">
															<a class="media-left" href="javascript:void(false)">
																<img src="/javacommunity/appimg/moren_man.png"
																class="media-object img-rounded portrait">
															</a>
															<div class="media-body">
																<a class="content" href="javascript:void(false)"><span>${post.postTitle }</span></a>
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
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${node.postList==null||node.postList.size()==0}">
						<div class="panel-body">
							<div class="tab-pane active" id="home">
								<div class="alert alert-info alert-dismissable">
									该节点下暂时还木有帖子呢 <i class="twa twa-lg twa-sparkles"></i>
								</div>
							</div>
						</div>
					</c:if>
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