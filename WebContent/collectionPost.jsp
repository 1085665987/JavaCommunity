<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="styles/main.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%@include file="commons/header.jsp"%>
<title>帖子收藏</title>
</head>
<body>
	<div class="container">
		<div class="row" style="padding: 10px;">
			<div class="col-md-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">发布新帖子</h3>
					</div>
					<div class="panel panel-default" style="border: none;">
						<div class="panel-body">
							<i class="fa fa-heart"></i> <a href="#">分享一个Windows下类似alfred功能的软件Launchy</a>
							<p class="pull-right">
								<span class="label label-default"> <a href="#">分享发现</a> </span>
							</p>
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

