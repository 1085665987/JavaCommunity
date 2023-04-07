<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="js/ajaxfileupload.js" type="text/javascript"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="styles/main.css" rel="stylesheet" type="text/css">
<link href="styles/setting.css" rel="stylesheet" type="text/css">

<%@include file="commons/header.jsp"%>
<title>设置</title>
</head>
<body>
	<div class=" container">
		<div class="row">
			<div class="col-md-9">
				<div class="panel panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title">用户编辑</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="userData" method="post" action="updateUser.do">
							<div class="form-group">
								<label for="phone" class="col-sm-2 control-label">账号</label>
								<div class="col-sm-10">
									<p class="form-control-static">${user.userAccount }</p>
								</div>
							</div>
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username"
										value="${user.username }">
								</div>
							</div>
							<div class="form-group">
								<label for="job" class="col-sm-2 control-label">职业</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="job" name="job"
										value="${user.job }">
								</div>
							</div>

							<div class="form-group">
								<label for="address" class="col-sm-2 control-label">所在地</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="address"
										name="address" value="${user.address}">
								</div>
							</div>
							<div class="form-group">
								<label for="home_page" class="col-sm-2 control-label">个人主页</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="home_page"
										name="home_page">
								</div>
							</div>
							<div class="form-group">
								<label for="signature" class="col-sm-2 control-label">个性签名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="signature"
										value="${user.signature }" name="signature">
								</div>
							</div>
							<div class="form-group">
								<label for="instructions" class="col-sm-2 control-label">个人简介</label>
								<div class="col-sm-10">
									<textarea class="form-control" rows="3" id="instructions"
										name="instructions">${user.instructions }</textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button"
										class="btn btn-success btn-sm submit ladda-button"
										data-style="zoom-out" id="userDataSubmit">
										<span class="ladda-label">保存信息</span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="panel panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title">更换头像</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-2 control-label">当前头像</label>
								<div class="col-sm-10">
									<img src="appimg${user.portrait}" class="img-rounded " id="img-change"
										style="width: 73px; height: 73px;"> <input type="file"
										name="fileUpload" class="fileUpload ladda-button" id="fileUpload"
										data-style="zoom-out" accept="image/png,image/jpg,image/jpeg"
										style="display: none;">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button"
										class="btn  btn-primary btn-sm submit ladda-button"
										data-style="zoom-out" id="uploadPortrait">
										<span class="ladda-label">点击头像，选择图片</span>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="panel panel-default ">
					<div class="panel-heading">
						<h3 class="panel-title">修改密码</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="old_password" class="col-sm-2 control-label">当前密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="old_password">
								</div>
							</div>
							<div class="form-group">
								<label for="new_password" class="col-sm-2 control-label">新密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="new_password">
								</div>
							</div>
							<div class="form-group">
								<label for="confirm_new_password" class="col-sm-2 control-label">确认新密码</label>
								<div class="col-sm-10">
									<input type="password" class="form-control"
										id="confirm_new_password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button"
										class="btn  btn-danger btn-sm submit ladda-button"
										data-style="zoom-out">
										<span class="ladda-label">修改密码</span>
									</button>
								</div>
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
			</div>
		</div>
	</div>
	<script type="text/javascript">
		if(${msg!=null}){
			alert('${msg}');
		}
	</script>
	
	<script type="text/javascript">
		$(function(){
			$('#userDataSubmit').click(function(){
				var username=$('#job').val();
				var userIdInput=$("<input type='text' name='userId' value='${user.userId}' style='display:none;'/>");
				$('#userData').append(userIdInput);
				$('#userData').submit();
			});
		});
	</script>
	
	
	<script type="text/javascript">
		//上传图片
		$(function(){
			$("#img-change").click(function () {
			    $("#fileUpload").click();
			});
			$("#fileUpload").change(function (event) {
				var files = event.target.files, file;
			    if (files && files.length > 0) {
			        // 获取目前上传的文件
			        file = files[0];// 文件大小校验的动作
			        if(file.size > 1024 * 1024 * 2) {
			            alert('图片大小不能超过 2MB!');
			            return false;
			        }
			        // 获取 window 的 URL 工具
			        var URL = window.URL || window.webkitURL;
			        // 通过 file 生成目标 url
			        var imgURL = URL.createObjectURL(file);
			        //用attr将img的src属性改成获得的url
			        $("#img-change").attr("src",imgURL);
			        // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
			        // URL.revokeObjectURL(imgURL);
			        $("#uploadPortrait").html('<span class="ladda-label">点击上传</span>');
			    }
			});
			$("#uploadPortrait").click(function () {
		        $.ajaxFileUpload({
		            url: 'updatePortrait.do',
		            type:"POST",
		            data:{
                        userId:"${user.userId}"
                    },
		            fileElementId:'fileUpload',
		            dataType:'txt',
		            secureuri : false,
		            success: function (data){
		            	$("#uploadPortrait").html('<span class="ladda-label">头像以上传</span>');
		            },
		            error:function(data,status,e){
		                alert(e);
		            }
		        });
		    });
		});
	</script>
</body>
</html>