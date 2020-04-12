<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Java中国</a>
                    </div>
                    <div style="float: right">
                        <form class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input type="text"  class="form-control" placeholder="搜索...">
                            </div>
                        </form>
                        <ul class="nav navbar-nav" id="navbar">
                            <li class="active"><a href="postList.do">首页</a></li> 
                            <c:if test="${sessionScope.user==null}">
            					<li><a href="userLog.jsp">登录</a></li>
            					<li><a href="userReg.jsp">注册</a></li>
            				</c:if>
            				 <c:if test="${sessionScope.user!=null}">
            					<li><a href="queryUserDetailData.do?userId=${sessionScope.user.userId }">${sessionScope.user.username}</a></li><!-- 进入设置界面 -->
            					<c:if test="${sessionScope.user.role==1}"><!-- 用户是管理员 -->
            						<li><a href="managerUser.do?administratorId=${sessionScope.user.userId }">进入后台</a></li>
            					</c:if>
            					<li><a href="outLog.jsp">注销</a></li>
            				</c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>

<script type="text/javascript">
	//当登录页面，设置header.jsp的头部
	var headerList=document.getElementById("navbar");
</script>
</body>
</html>