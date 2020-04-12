<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">个人信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="profile-sidebar">
					<div class="profile-sidebar-item profile-avatar">
						<a href="#"> <img class="avatar avatar-lg img-circle"
							alt="${sessionScope.user.username }"
							src="/javacommunity/appimg${sessionScope.user.portrait}">
						</a>
					</div>
					<div class="profile-sidebar-item profile-info">
						<span class="h5 bold">${sessionScope.user.username }</span>
						<p>Web Dev!</p>
						<div class="w150 center-block mt10">
							<a class="btn btn-success btn-outline btn-block"
								href="writePost.jsp"> <span>发布新帖子</span>
							</a>
						</div>
					</div>
					<hr>
					<ul class="profile-sidebar-item profile-numbers-count" >
						<li><a href="#"><span class="bold"  id="post_collection">${sessionScope.user.userId}</span>帖子收藏</a></li>
						<li><a href="#"><span class="bold"  id="node_collection">${sessionScope.user.userId}</span>节点收藏</a></li>
						<li><a href="#"><span class="bold"  id="user_interest">${sessionScope.user.userId}</span>特别关注</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<a>王金杰</a>
		</div>
	</div>
	
	<script type="text/javascript">
	$(function(){
		
		$.post('queryCollectionInterest.json',{
			'userId':${sessionScope.user.userId}
		},function(data,state){
			//console.log(data);
			//{"nodeCollectionCount":2,"postCollectionCount":0,"userInterestCount":2}
			var postCollectionCount=jQuery.parseJSON(data).postCollectionCount;
			var nodeCollectionCount=jQuery.parseJSON(data).nodeCollectionCount;
			var userInterestCount=jQuery.parseJSON(data).userInterestCount;
			
			$('#post_collection').text(postCollectionCount);
			$('#node_collection').text(nodeCollectionCount);
			$('#user_interest').text(userInterestCount);
		});
	});
		
	</script>
</body>
</html>