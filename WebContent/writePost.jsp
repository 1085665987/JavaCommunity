<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/18.0.0/classic/ckeditor.js"></script>
<%@include file="commons/header.jsp"%>
<title>撰写帖子</title>
</head>
<body>
	<div class="container">
		<div class="row" style="padding: 10px;">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">发布新帖子</div>
					<div class="panel-body">
						<form role="form" method="post" action="writePost.do" id="post">
							<input type="hidden" name="senderId" value="${sessionScope.user.userId}">
							<input type="hidden" name="senderName" value="${sessionScope.user.username }">
							<div class="form-group">
								<label for="postTitle">标题</label> <input type="text"
									class="form-control"  id="postTitle"  name="postTitle" placeholder="请输入帖子标题">
							</div>
							<div class="form-group">
								<label for="node_navigation" >节点</label> <select class="form-control"
									placeholder="请选择节点"  id="node_navigation"  name="childNodeName" >
								</select>
							</div>

							<div class="form-group">
								<label for="content">内容</label>
								<textarea id="content" name="content"  class="form-control" rows="3" placeholder="请输入帖子内容" >
									
								</textarea>
							</div>
							<div class="form-group">
								<button class="btn btn-success btn-block btn-lg mt10 mb15"
									id="submit_post">发&nbsp;&nbsp;布</button>
							</div>
						</form>
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

	<script>
        /*  查询所有节点*/
        $(function() {
			$.post("queryAllNode.do", function(data, status) {
				var msg = jQuery.parseJSON(data).msg;
				if (msg == 'successful') {
					var nodeList = jQuery.parseJSON(data).nodeList;
					$.each(nodeList, function(index, element) {
						var childNodes='';
						$.each(element.childNodeList,function(childNodeIndex,childNodeElement){
							childNodes=childNodes+'<option value=' + childNodeElement.nodeName +'>'+childNodeElement.nodeName+'</option>';
						});
						var row='<optgroup label='+element.nodeName+'>'+childNodes+'</optgroup>';
						$('#node_navigation').append(row);
					});
				}
			});
		});
        /*  初始化Editor*/
	$(function() {
		ClassicEditor.create( document.querySelector( '#content' ) , {
			cloudServices: {
				tokenUrl: 'https://70253.cke-cs.com/token/dev/UdMcqyW0poevfziLspkejZpOIfGJFebdzXeHIwP8psblPHKoDfmEdy0JjeLn',
				uploadUrl: 'https://70253.cke-cs.com/easyimage/upload/'
				}
			}).catch( error => {
				console.error( error );
			});
	});
    /* 提交*/
	$('#submit_post').click(function(){
    	var childNodeTitle=$('#postTitle').val();
    	var childNodeId=$('#node_navigation').val();
    	var childNodeContent=$('#content').val();
    	if(childNodeTitle==''){
    		alert("请填写帖子的标题");
    		return false;
    	}
    	if(childNodeContent==''){
    		alert("请填写帖子的正文");
    		return false;
    	}
    	$('#post').submit();
    });
    </script>
</body>
</html>